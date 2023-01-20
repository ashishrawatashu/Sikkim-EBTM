package in.nic.snt.starbus.ebtm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import java.util.List;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adapters.TripsListAdapter;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.TripsListOnClick;
import in.nic.snt.starbus.ebtm.databinding.ActivityConductorDashBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentTripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.TripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentTripsDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.MachineCurrentStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.TripsDao;
import in.nic.snt.starbus.ebtm.utils.CommonMethods;

public class ConductorDashActivity extends AppCompatActivity implements View.OnClickListener , TripsListOnClick {

    private ActivityConductorDashBinding activityConductorDashBinding;
    private AppDatabase db;

    CommonMethods commonMethods;
    boolean  exit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConductorDashBinding = ActivityConductorDashBinding.inflate(getLayoutInflater());
        View view = activityConductorDashBinding.getRoot();
        setContentView(view);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();
        commonMethods = new CommonMethods(this);

        initMethods();

        setTripsList();




    }

    private void initMethods() {
        activityConductorDashBinding.createNewTripBT.setOnClickListener(this);
    }

    private void setTripsList() {

        TripsDao tripsDao = db.tripsDao();
        List<TripsModel> tripsModelList = tripsDao.getTrips();

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        activityConductorDashBinding.listViewTrips.setLayoutManager(linearLayoutManager);
        TripsListAdapter tripsAdapter = new TripsListAdapter(this,tripsModelList,this);
        activityConductorDashBinding.listViewTrips.setAdapter(tripsAdapter);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.create_new_trip_BT:
                startActivity(new Intent(ConductorDashActivity.this,SearchAnotherRouteActivity.class));
                break;

        }


    }

    @Override
    public void selectTrip(int position, TripsModel tripsModel) {


        if(tripsModel.getTripStatus().equals("D")){

            Toast.makeText(this, "This trips is disable ", Toast.LENGTH_SHORT).show();
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


            startActivity(new Intent(ConductorDashActivity.this, TicketBookingDashActivity.class));

            Toast.makeText(this, "Trip Started Successfully", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            super.onBackPressed();
            finishAffinity();
        } else {
            Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2000);
        }
    }
}