package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.log_out_IV:
                CurrentUserLoginStatusDao currentUserLoginStatusDao = db.currentUserLoginStatusDao();
                currentUserLoginStatusDao.updateUserLoginStatus("N");
                startActivity(new Intent(this,OperatorLoginActivity.class));
                break;


            case R.id.close_trip:

                String currentDateTime = commonMethods.getDateTime();

                CurrentTripsDao currentTripsDao = db.currentTripsDao();
                currentTripsDao.closeTripAndUpdateStatus(currentTripNo,currentDateTime);

                TripsDao tripsDao = db.tripsDao();

                if(strpId!=0){
                    tripsDao.updateTripStatus(strpId, "C");
                }
                Intent conductorDashActivity = new Intent(this, ConductorDashActivity.class);
                startActivity(conductorDashActivity);
                Toast.makeText(this, "Trip is closed successfully ", Toast.LENGTH_SHORT).show();

                break;


            case R.id.select_psg_BT:



                break;

        }
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
        fromStationList.get(position).setSelected(true);
        for (int i=0;i<fromStationList.size();i++){
            fromStationList.get(i).setSelected(i == position);
        }
        ticketBookingFromStationAdapter.notifyDataSetChanged();

    }
}