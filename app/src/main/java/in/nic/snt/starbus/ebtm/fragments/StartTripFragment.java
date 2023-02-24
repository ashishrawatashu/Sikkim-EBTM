package in.nic.snt.starbus.ebtm.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.activity.SearchAnotherRouteActivity;
import in.nic.snt.starbus.ebtm.activity.TicketBookingDashActivity;
import in.nic.snt.starbus.ebtm.adapters.TripsListAdapter;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.TripsListOnClick;
import in.nic.snt.starbus.ebtm.databinding.FragmentStartTripBinding;
import in.nic.snt.starbus.ebtm.databinding.StartTripConfirmationDialogBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentTripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.TripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentTripsDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.MachineCurrentStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.TripsDao;
import in.nic.snt.starbus.ebtm.utils.CommonMethods;

public class StartTripFragment extends Fragment implements View.OnClickListener , TripsListOnClick {

    FragmentStartTripBinding fragmentStartTripBinding;
    private AppDatabase db;

    StartTripConfirmationDialogBinding startTripConfirmationDialogBinding;
    BottomSheetDialog startTripBottomSheet;

    CommonMethods commonMethods;
    boolean  exit = false;
    TripsModel tripsModel;
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentStartTripBinding = FragmentStartTripBinding.inflate(inflater, container, false);
        View view = fragmentStartTripBinding.getRoot();

        initMethods();
        setTripsList();
        
        return view;
        
       
        
    }

    private void initMethods() {
        fragmentStartTripBinding.createNewTripBT.setOnClickListener(this);
    }

    private void setTripsList() {

        TripsDao tripsDao = db.tripsDao();
        List<TripsModel> tripsModelList = tripsDao.getTrips();

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext());
        fragmentStartTripBinding.listViewTrips.setLayoutManager(linearLayoutManager);
        TripsListAdapter tripsAdapter = new TripsListAdapter(getContext(),tripsModelList,this);
        fragmentStartTripBinding.listViewTrips.setAdapter(tripsAdapter);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.create_new_trip_BT:
                startActivity(new Intent(getContext(), SearchAnotherRouteActivity.class));
                break;

            case R.id.start_trip_no_RL:
                startTripBottomSheet.dismiss();
                break;

            case R.id.start_trip_yes_RL:
                if(tripsModel.getTripStatus().equals("D")){

                    Toast.makeText(getContext(), "getContext() trips is disable ", Toast.LENGTH_LONG).show();
                }else {

                    String currentDateTime = commonMethods.getDateTime();

                    //insertCurrentTrip
                    CurrentTripsDao currentTripsDao = db.currentTripsDao();
                    CurrentTripsModel currentTripsModel = new CurrentTripsModel();
                    currentTripsModel.setStrpId(tripsModel.getStrpId());
                    currentTripsModel.setTripDirection(tripsModel.getTripDirection());
                    currentTripsModel.setRouteId(tripsModel.getRoutId());
                    currentTripsModel.setFromStationId(tripsModel.getFrStonId());
                    currentTripsModel.setToStationId(tripsModel.getToStonId());
                    currentTripsModel.setWithWaybillYn("Y");
                    currentTripsModel.setCompleteYN("N");
                    currentTripsModel.setStartDateTime(currentDateTime);
                    currentTripsModel.setCompleteDateTime("");

                    currentTripsDao.insertCurrentTrips(currentTripsModel);

                    //disable previous trips
                    TripsDao tripsDao = db.tripsDao();
                    tripsDao.updateTripStatus(tripsModel.getStrpId(), "R");
                    tripsDao.disablePeviousTrips(tripsModel.getSerialNo());

                    //update machine status
                    MachineCurrentStatusDao machineCurrentStatusDao = db.machineCurrentStatusDao();
                    machineCurrentStatusDao.updateMachineCurrentStatus("2",currentDateTime);


                    startActivity(new Intent(getContext(), TicketBookingDashActivity.class));
                    startTripBottomSheet.dismiss();
                    Toast.makeText(getContext(), "Trip Started Successfully", Toast.LENGTH_LONG).show();

                }
                break;


        }


    }

    @Override
    public void selectTrip(int position, TripsModel tripsModel) {
        this.tripsModel = tripsModel;
        showStartTripBottomSheet();

    }

    private void showStartTripBottomSheet() {
        startTripBottomSheet = new BottomSheetDialog(getContext());
        startTripBottomSheet.requestWindowFeature(Window.FEATURE_NO_TITLE);
        startTripBottomSheet.setCancelable(true);
        startTripBottomSheet.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        startTripConfirmationDialogBinding = StartTripConfirmationDialogBinding.inflate(LayoutInflater.from(getContext()));
        startTripBottomSheet.setContentView(startTripConfirmationDialogBinding.getRoot());

        startTripConfirmationDialogBinding.startTripNoRL.setOnClickListener(this);
        startTripConfirmationDialogBinding.startTripYesRL.setOnClickListener(this);

        startTripBottomSheet.show();
        Window window = startTripBottomSheet.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
    
}