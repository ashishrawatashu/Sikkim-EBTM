package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adapters.FromStationListAdapter;
import in.nic.snt.starbus.ebtm.adapters.ToStationListAdapter;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.StationsOnClicks;
import in.nic.snt.starbus.ebtm.databinding.ActivityCreateOwnTripBinding;
import in.nic.snt.starbus.ebtm.databinding.SelectStationDialogBinding;
import in.nic.snt.starbus.ebtm.models.StationsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentTripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesStationModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentTripsDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.MachineCurrentStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.RoutesStationDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.TripsDao;
import in.nic.snt.starbus.ebtm.utils.CommonMethods;

public class CreateOwnTripActivity extends AppCompatActivity implements View.OnClickListener , StationsOnClicks {

    ActivityCreateOwnTripBinding activityCreateOwnTripBinding;
    private AppDatabase db;
    int routeId;
    String routeName;

    CommonMethods commonMethods;


    List<RoutesStationModel> routesStationModelList;
    Dialog stationsDialog;
    SelectStationDialogBinding selectStationDialogBinding;


    List<StationsModel> fromStationList = new ArrayList<>();
    List<StationsModel> toStationList = new ArrayList<>();

    String fromStationName="" , toStationName="", direction="";
    int fromStationId , toStationId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateOwnTripBinding = ActivityCreateOwnTripBinding.inflate(getLayoutInflater());
        View view = activityCreateOwnTripBinding.getRoot();
        setContentView(view);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();
        commonMethods = new CommonMethods(this);
        initMethod();

        getDataFromIntent();

        getAllStationByRouteId();
        
    }

    private void initMethod() {
        activityCreateOwnTripBinding.backArrowIV.setOnClickListener(this);
        activityCreateOwnTripBinding.selectFromStationTV.setOnClickListener(this);
        activityCreateOwnTripBinding.selectToStationTV.setOnClickListener(this);
        activityCreateOwnTripBinding.createTripBT.setOnClickListener(this);
        activityCreateOwnTripBinding.forwardRB.setOnClickListener(this);
        activityCreateOwnTripBinding.inwardRB.setOnClickListener(this);
    }

    private void getDataFromIntent() {

        Intent intent = getIntent();
        routeId = intent.getIntExtra("routeId",0);
        routeName = intent.getStringExtra("routeName");
        activityCreateOwnTripBinding.routeNameTV.setText(routeName);

    }

    private void getAllStationByRouteId() {
        
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

        Log.e("SIZE", String.valueOf(toStationList.size()));
        Log.e("SIZE", String.valueOf(fromStationList.size()));


        
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.back_arrow_IV:
                onBackPressed();
                break;
                
            case R.id.select_from_station_TV:
                showStationListDialog("From");
                break;
                
            case R.id.select_to_station_TV:
                showStationListDialog("To");
                break;

            case R.id.cross_IV:
                stationsDialog.dismiss();
                break;

            case R.id.create_trip_BT:
                createTrip();
                break;

            case R.id.forward_RB:
                direction = "Forward";
                break;

            case R.id.inward_RB:
                direction = "Inward";
                break;

        }
    }

    private void createTrip() {

        String currentDateTime = commonMethods.getDateTime();
        Log.e("DATE_TIME",currentDateTime);
        if(fromStationName.isEmpty()){
            Toast.makeText(this, "Please select from station .", Toast.LENGTH_SHORT).show();
            return;
        }
        if(toStationName.isEmpty()){
            Toast.makeText(this, "Please select to station .", Toast.LENGTH_SHORT).show();
            return;
        }
        if(direction.isEmpty()){
            Toast.makeText(this, "Please select direction .", Toast.LENGTH_SHORT).show();
            return;
        }

        String directionId;

        if(direction.equals("Forward")){
            directionId = "F";
        }else {
            directionId ="I";
        }

        CurrentTripsDao currentTripsDao = db.currentTripsDao();
        CurrentTripsModel currentTripsModel = new CurrentTripsModel();
        currentTripsModel.setStrpId(0);
        currentTripsModel.setTripDirection(directionId);
        currentTripsModel.setRouteId(routeId);
        currentTripsModel.setFromStationId(fromStationId);
        currentTripsModel.setToStationId(toStationId);
        currentTripsModel.setWithWaybillYn("N");
        currentTripsModel.setCompleteYN("N");
        currentTripsModel.setStartDateTime(currentDateTime);
        currentTripsModel.setCompleteDateTime("");

        currentTripsDao.insertCurrentTrips(currentTripsModel);
//        CurrentTripsModel tripNo = currentTripsDao.insertCurrentTrips1(currentTripsModel);
//        Log.e("TRIP_NO", String.valueOf(tripNo.getTripNo()));
        // update all pending trip status
        TripsDao tripsDao = db.tripsDao();
        tripsDao.updateAllPendingTrips();

        //  update machine status
        MachineCurrentStatusDao machineCurrentStatusDao = db.machineCurrentStatusDao();
        machineCurrentStatusDao.updateMachineCurrentStatus("2",currentDateTime);

        Toast.makeText(this, "Trips is created ", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(CreateOwnTripActivity.this,TicketBookingDashActivity.class));




    }

    private void showStationListDialog(String fromOrTo) {
        stationsDialog = new Dialog(this);
        stationsDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        stationsDialog.setCancelable(true);
        stationsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        selectStationDialogBinding = SelectStationDialogBinding.inflate(LayoutInflater.from(this));
        stationsDialog.setContentView(selectStationDialogBinding.getRoot());

        selectStationDialogBinding.crossIV.setOnClickListener(this);

        if(fromOrTo.equals("From")){
            selectStationDialogBinding.fromToStationTV.setText("Select From Station");
            selectStationDialogBinding.fromStationListRV.setVisibility(View.VISIBLE);
            selectStationDialogBinding.toStationListRV.setVisibility(View.GONE);


        }else {
            selectStationDialogBinding.fromToStationTV.setText("Select To Station");
            selectStationDialogBinding.fromStationListRV.setVisibility(View.GONE);
            selectStationDialogBinding.toStationListRV.setVisibility(View.VISIBLE);
        }


        LinearLayoutManager toLinearLayoutManager =new LinearLayoutManager(this);

        selectStationDialogBinding.toStationListRV.setLayoutManager(toLinearLayoutManager);
        ToStationListAdapter toStationListAdapter = new ToStationListAdapter(this,toStationList,this);
        selectStationDialogBinding.toStationListRV.setAdapter(toStationListAdapter);

        LinearLayoutManager fromLinearLayoutManager =new LinearLayoutManager(this);
        selectStationDialogBinding.fromStationListRV.setLayoutManager(fromLinearLayoutManager);
        FromStationListAdapter fromStationListAdapter = new FromStationListAdapter(this,fromStationList,this);
        selectStationDialogBinding.fromStationListRV.setAdapter(fromStationListAdapter);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = stationsDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        stationsDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        stationsDialog.show();


    }

    @Override
    public void selectToStation(int position, StationsModel stationsModel) {
        stationsDialog.dismiss();
        toStationName = stationsModel.getStationName();
        toStationId = stationsModel.getStationId();
        activityCreateOwnTripBinding.selectToStationTV.setText(stationsModel.getStationName());

    }

    @Override
    public void selectFromStation(int position, StationsModel stationsModel) {
        stationsDialog.dismiss();
        fromStationName = stationsModel.getStationName();
        fromStationId = stationsModel.getStationId();
        activityCreateOwnTripBinding.selectFromStationTV.setText(stationsModel.getStationName());

    }
}