package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adapters.FromStationListAdapter;
import in.nic.snt.starbus.ebtm.adapters.TicketBookingFromStationAdapter;
import in.nic.snt.starbus.ebtm.adapters.TicketBookingToStationAdapter;
import in.nic.snt.starbus.ebtm.adapters.ToStationListAdapter;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.StationOnClickInDashBoard;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.StationsOnClicks;
import in.nic.snt.starbus.ebtm.databinding.ActivityTicketBookingDashBinding;
import in.nic.snt.starbus.ebtm.databinding.ChnageStationStateConfirmationDialogBinding;
import in.nic.snt.starbus.ebtm.databinding.CloseTripConfirmationDialogBinding;
import in.nic.snt.starbus.ebtm.databinding.PsgDialogLayoutBinding;
import in.nic.snt.starbus.ebtm.databinding.TicketBookingStationListItemBinding;
import in.nic.snt.starbus.ebtm.models.StationsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentTripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesStationModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentTripsDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentUserLoginStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.RoutesStationDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.TripsDao;
import in.nic.snt.starbus.ebtm.utils.CommonMethods;

public class TicketBookingDashActivity extends AppCompatActivity implements View.OnClickListener , StationOnClickInDashBoard {


    ActivityTicketBookingDashBinding                activityTicketBookingDashBinding;
    boolean                                         exit = false;
    AppDatabase                                     db;
    CommonMethods                                   commonMethods;
    int                                             currentTripNo;
    int                                             strpId;
    int                                             routeId;
    List<RoutesStationModel>                        routesStationModelList;
    List<StationsModel>                             fromStationList = new ArrayList<>();
    List<StationsModel>                             toStationList = new ArrayList<>();
    TicketBookingToStationAdapter                   ticketBookingToStationAdapter;
    TicketBookingFromStationAdapter                 ticketBookingFromStationAdapter;

    
    CloseTripConfirmationDialogBinding              closeTripConfirmationDialogBinding;
    BottomSheetDialog                               closeTripConfirmationBottomSheet;


    ChnageStationStateConfirmationDialogBinding     chnageStationStateConfirmationDialogBinding;
    BottomSheetDialog                               changeStateBottomSheet;



    Dialog                                          psgDilaog;
    PsgDialogLayoutBinding                          psgDialogLayoutBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTicketBookingDashBinding = ActivityTicketBookingDashBinding.inflate(getLayoutInflater());
        View view = activityTicketBookingDashBinding.getRoot();
        setContentView(view);

        commonMethods = new CommonMethods(this);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();

        getTripDetails();

        initMethod();

    }


    private void getAllStationByRouteId(int routeId) {

        RoutesStationDao routesStationDao = db.routesStationDao();
        routesStationModelList = routesStationDao.getRoutesStationByRouteId(routeId);

        for (int i =0; i< routesStationModelList.size();i++){

            StationsModel toStationsModel = new StationsModel();
            toStationsModel.setStationId(routesStationModelList.get(i).getToStationId());
            toStationsModel.setStationName(routesStationModelList.get(i).getToStationName());
            
            StationsModel fromStationsModel = new StationsModel();
            fromStationsModel.setStationId(routesStationModelList.get(i).getFromStationId());
            fromStationsModel.setStationName(routesStationModelList.get(i).getFromStationName());

            toStationList.add(toStationsModel);
            fromStationList.add(fromStationsModel);
            
        }


        setToStationListAdapter();

       setFromStationListAdapter();

    }

    private void setFromStationListAdapter() {
        fromStationList.get(0).setSelected(true);
        LinearLayoutManager fromLinearLayoutManager =new LinearLayoutManager(this);
        activityTicketBookingDashBinding.fromStationRV.setLayoutManager(fromLinearLayoutManager);
        ticketBookingFromStationAdapter = new TicketBookingFromStationAdapter(this,fromStationList,this);
        activityTicketBookingDashBinding.fromStationRV.setAdapter(ticketBookingFromStationAdapter);

    }

    private void setToStationListAdapter() {

        LinearLayoutManager toLinearLayoutManager =new LinearLayoutManager(this);

        activityTicketBookingDashBinding.toStationRV.setLayoutManager(toLinearLayoutManager);
        ticketBookingToStationAdapter = new TicketBookingToStationAdapter(this,toStationList,this);
        activityTicketBookingDashBinding.toStationRV.setAdapter(ticketBookingToStationAdapter);
    }

    private void getTripDetails() {

        CurrentTripsDao currentTripsDao = db.currentTripsDao();
        CurrentTripsModel currentTripsModel = currentTripsDao.getIncompleteTripData();
        currentTripNo = currentTripsModel.getTripNo();
        strpId = currentTripsModel.getStrpId();
        routeId = currentTripsModel.getRouteId();

        //all stations by routeId
        getAllStationByRouteId(routeId);




    }

    private void initMethod() {
        activityTicketBookingDashBinding.closeTrip.setOnClickListener(this);
        activityTicketBookingDashBinding.logOutIV.setOnClickListener(this);
        activityTicketBookingDashBinding.selectPsgBT.setOnClickListener(this);
        activityTicketBookingDashBinding.selectConcessionBT.setOnClickListener(this);
        activityTicketBookingDashBinding.selectLugBT.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        if (exit) {
            super.onBackPressed();
            finishAffinity();
        } else {
            Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_LONG).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2000);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.log_out_IV:
                CurrentUserLoginStatusDao currentUserLoginStatusDao = db.currentUserLoginStatusDao();
                currentUserLoginStatusDao.updateUserLoginStatus("N");
                startActivity(new Intent(this,OperatorLoginActivity.class));
                break;


            case R.id.close_trip:

                
                showCloseTripConfirmationDialog();

                break;


            case R.id.select_psg_BT:

               showPsgDilaog();

                break;



            case R.id.change_state_no_RL:
                changeStateBottomSheet.dismiss();
                break;

            case R.id.change_state_yes_RL:

                break;

            case R.id.close_trip_no_RL:
                closeTripConfirmationBottomSheet.dismiss();
                break;

            case R.id.close_trip_yes_RL:
                String currentDateTime = commonMethods.getDateTime();

                CurrentTripsDao currentTripsDao = db.currentTripsDao();
                currentTripsDao.closeTripAndUpdateStatus(currentTripNo,currentDateTime);

                TripsDao tripsDao = db.tripsDao();

                if(strpId!=0){
                    tripsDao.updateTripStatus(strpId, "C");
                }
                Intent conductorDashActivity = new Intent(this, ConductorDashActivity.class);
                startActivity(conductorDashActivity);
                closeTripConfirmationBottomSheet.dismiss();
                Toast.makeText(this, "Trip is closed successfully ", Toast.LENGTH_LONG).show();
                break;

        }
    }

    private void showPsgDilaog() {
        psgDilaog = new Dialog(this);
        psgDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        psgDilaog.setCancelable(true);
        psgDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        psgDialogLayoutBinding = PsgDialogLayoutBinding.inflate(LayoutInflater.from(this));
        psgDilaog.setContentView(psgDialogLayoutBinding.getRoot());


        psgDilaog.show();
        Window window = psgDilaog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


    }

    private void showCloseTripConfirmationDialog() {

        closeTripConfirmationBottomSheet = new BottomSheetDialog(this);
        closeTripConfirmationBottomSheet.requestWindowFeature(Window.FEATURE_NO_TITLE);
        closeTripConfirmationBottomSheet.setCancelable(true);
        closeTripConfirmationBottomSheet.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        closeTripConfirmationDialogBinding = CloseTripConfirmationDialogBinding.inflate(LayoutInflater.from(this));
        closeTripConfirmationBottomSheet.setContentView(closeTripConfirmationDialogBinding.getRoot());

        closeTripConfirmationDialogBinding.closeTripNoRL.setOnClickListener(this);
        closeTripConfirmationDialogBinding.closeTripYesRL.setOnClickListener(this);

        closeTripConfirmationBottomSheet.show();
        Window window = closeTripConfirmationBottomSheet.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void selectToStation(int position, List<StationsModel> toStationList) {


        toStationList.get(position).setSelected(true);
        for (int i=0;i<toStationList.size();i++){
            toStationList.get(i).setSelected(i == position);
        }
        ticketBookingToStationAdapter.notifyDataSetChanged();
    }

    @Override
    public void selectFromStation(int position, List<StationsModel> fromStationList) {



        if (position>0){
            showChangeStateConfirmationDialog();
        }else {
            fromStationList.get(position).setSelected(true);
            for (int i=0;i<fromStationList.size();i++){
                fromStationList.get(i).setSelected(i == position);
            }
            ticketBookingFromStationAdapter.notifyDataSetChanged();

        }



    }

    private void showChangeStateConfirmationDialog() {
        changeStateBottomSheet = new BottomSheetDialog(this);
        changeStateBottomSheet.requestWindowFeature(Window.FEATURE_NO_TITLE);
        changeStateBottomSheet.setCancelable(true);
        changeStateBottomSheet.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        chnageStationStateConfirmationDialogBinding = ChnageStationStateConfirmationDialogBinding.inflate(LayoutInflater.from(this));
        changeStateBottomSheet.setContentView(chnageStationStateConfirmationDialogBinding.getRoot());

        chnageStationStateConfirmationDialogBinding.changeStateNoRL.setOnClickListener(this);
        chnageStationStateConfirmationDialogBinding.changeStateYesRL.setOnClickListener(this);

        changeStateBottomSheet.show();
        Window window = changeStateBottomSheet.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}