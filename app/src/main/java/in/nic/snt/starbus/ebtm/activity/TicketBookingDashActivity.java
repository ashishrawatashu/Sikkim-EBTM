package in.nic.snt.starbus.ebtm.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adapters.TicketBookingFromStationAdapter;
import in.nic.snt.starbus.ebtm.adapters.TicketBookingToStationAdapter;
import in.nic.snt.starbus.ebtm.databinding.ActivityTicketBookingDashBinding;
import in.nic.snt.starbus.ebtm.databinding.ChnageStationStateConfirmationDialogBinding;
import in.nic.snt.starbus.ebtm.databinding.CloseTripConfirmationDialogBinding;
import in.nic.snt.starbus.ebtm.databinding.PsgDialogLayoutBinding;
import in.nic.snt.starbus.ebtm.models.StationsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RouteFareModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesStationModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentUserLoginStatusDao;
import in.nic.snt.starbus.ebtm.utils.CommonMethods;

public class TicketBookingDashActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /*implements View.OnClickListener, StationOnClickInDashBoard*/


    ActivityTicketBookingDashBinding                        activityTicketBookingDashBinding;
    boolean                                                 exit = false;
    AppDatabase                                             db;
    CommonMethods                                           commonMethods;
    int                                                     currentTripNo;
    int strpId;
    int routeId;
    List<RoutesStationModel>                                routesStationModelList;
    List<StationsModel>                                     fromStationList = new ArrayList<>();
    List<StationsModel>                                     toStationList = new ArrayList<>();
    TicketBookingToStationAdapter                           ticketBookingToStationAdapter;
    TicketBookingFromStationAdapter                         ticketBookingFromStationAdapter;


    CloseTripConfirmationDialogBinding                      closeTripConfirmationDialogBinding;
    BottomSheetDialog                                       closeTripConfirmationBottomSheet;


    ChnageStationStateConfirmationDialogBinding             chnageStationStateConfirmationDialogBinding;
    BottomSheetDialog                                       changeStateBottomSheet;


    Dialog                                                  psgDilaog;
    PsgDialogLayoutBinding                                  psgDialogLayoutBinding;


    RouteFareModel                                          routeFareModel;
    int                                                     statePositionFromStation = 0;
    int                                                     statePositionToStation = 0;
    int                                                     selectedFromStationId, selectedToStationId=0;
    int                                                     no_of_gen_ticket = 0;
    int                                                     no_of_cld_ticket = 0;

    double  gen_ticket_fare, cld_ticketFare, total_fare_psg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTicketBookingDashBinding = ActivityTicketBookingDashBinding.inflate(getLayoutInflater());
        View view = activityTicketBookingDashBinding.getRoot();
        setContentView(view);

        commonMethods = new CommonMethods(this);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.setTitle(getResources().getString(R.string.conductor_dashboard));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View mHeaderView =  navigationView.getHeaderView(0);


        navigationView.setNavigationItemSelectedListener(this);


        /*getTripDetails();

        initMethod();*/

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_expense) {
            //class_ConnectionRelated.setDefaultSharedPreferences(getApplicationContext());
            Intent ii = new Intent(TicketBookingDashActivity.this, ExpenseActivity.class);
            startActivity(ii);
        }  else if (id == R.id.nav_earning) {
            Intent ii = new Intent(TicketBookingDashActivity.this, EarningActivity.class);
            startActivity(ii);
        }  else if (id == R.id.nav_logout) {
            CurrentUserLoginStatusDao currentUserLoginStatusDao = db.currentUserLoginStatusDao();
            currentUserLoginStatusDao.updateUserLoginStatus("N");
            startActivity(new Intent(this, OperatorLoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




/*    private void getAllStationByRouteId(int routeId) {

        RoutesStationDao routesStationDao = db.routesStationDao();
        routesStationModelList = routesStationDao.getRoutesStationByRouteId(routeId);

        for (int i = 0; i < routesStationModelList.size(); i++) {

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
        fromStationList.get(statePositionFromStation).setSelected(true);
        LinearLayoutManager fromLinearLayoutManager = new LinearLayoutManager(this);
        activityTicketBookingDashBinding.fromStationRV.setLayoutManager(fromLinearLayoutManager);
        ticketBookingFromStationAdapter = new TicketBookingFromStationAdapter(this, fromStationList, this);
        activityTicketBookingDashBinding.fromStationRV.setAdapter(ticketBookingFromStationAdapter);

    }

    private void setToStationListAdapter() {

        LinearLayoutManager toLinearLayoutManager = new LinearLayoutManager(this);
        activityTicketBookingDashBinding.toStationRV.setLayoutManager(toLinearLayoutManager);
        ticketBookingToStationAdapter = new TicketBookingToStationAdapter(this, toStationList, this);
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
        switch (view.getId()) {

            case R.id.close_psg_no_RL:
                psgDilaog.dismiss();
                break;

            case R.id.print_psg_no_RL:
                psgDilaog.dismiss();
                break;

            case R.id.log_out_IV:
                CurrentUserLoginStatusDao currentUserLoginStatusDao = db.currentUserLoginStatusDao();
                currentUserLoginStatusDao.updateUserLoginStatus("N");
                startActivity(new Intent(this, OperatorLoginActivity.class));
                break;

            case R.id.close_trip:


                showCloseTripConfirmationDialog();

                break;


            case R.id.select_psg_BT:

                if(selectedToStationId==0){
                    Toast.makeText(this, "Please select to station", Toast.LENGTH_SHORT).show();
                    break;
                }

                RouteFareDao routeFareDao = db.routeFareDao();
                routeFareModel = routeFareDao.getFare(routeId,selectedFromStationId,selectedToStationId,strpId);

                showPsgDilaog();

                break;


            case R.id.change_state_no_RL:
                changeStateBottomSheet.dismiss();
                break;

            case R.id.change_state_yes_RL:

                for (int i = 0; i < fromStationList.size(); i++) {
                    if (i < statePositionFromStation) {
                        Log.e("STATE_POSITION", String.valueOf(statePositionFromStation));
                        Log.e("POSITION", String.valueOf(i));
                        fromStationList.get(i).setEnable(false);
                    }
                }

                for (int i = 0; i < toStationList.size(); i++) {
                    if (i < statePositionToStation) {
                        Log.e("STATE_POSITION", String.valueOf(statePositionToStation));
                        Log.e("POSITION", String.valueOf(i));
                        toStationList.get(i).setEnable(false);
                    }
                }
                setToStationListAdapter();
                setFromStationListAdapter();
                ticketBookingFromStationAdapter.notifyDataSetChanged();
                ticketBookingToStationAdapter.notifyDataSetChanged();
                changeStateBottomSheet.dismiss();

                break;

            case R.id.close_trip_no_RL:
                closeTripConfirmationBottomSheet.dismiss();
                break;

            case R.id.close_trip_yes_RL:
                String currentDateTime = commonMethods.getDateTime();

                CurrentTripsDao currentTripsDao = db.currentTripsDao();
                currentTripsDao.closeTripAndUpdateStatus(currentTripNo, currentDateTime);

                TripsDao tripsDao = db.tripsDao();

                if (strpId != 0) {
                    tripsDao.updateTripStatus(strpId, "C");
                }
                Intent conductorDashActivity = new Intent(this, ConductorDashActivity.class);
                startActivity(conductorDashActivity);
                closeTripConfirmationBottomSheet.dismiss();
                Toast.makeText(this, "Trip is closed successfully ", Toast.LENGTH_LONG).show();
                break;

            case R.id.gen_add_RL:

                no_of_gen_ticket = no_of_gen_ticket+1;
                psgDialogLayoutBinding.noOfGenTV.setText(String.valueOf(no_of_gen_ticket));
                gen_ticket_fare = routeFareModel.totalFare*no_of_gen_ticket;
                total_fare_psg = gen_ticket_fare + cld_ticketFare;
                psgDialogLayoutBinding.genTotalFareTV.setText(String.valueOf(gen_ticket_fare));
                psgDialogLayoutBinding.psgTotalFareTV.setText(String.valueOf("₹ "+total_fare_psg));
                break;

            case R.id.gen_subtract_RL:

                if (no_of_gen_ticket>0){
                    no_of_gen_ticket = no_of_gen_ticket-1;
                }

                gen_ticket_fare = routeFareModel.totalFare*no_of_gen_ticket;
                total_fare_psg = gen_ticket_fare + cld_ticketFare;
                psgDialogLayoutBinding.noOfGenTV.setText(String.valueOf(no_of_gen_ticket));
                psgDialogLayoutBinding.genTotalFareTV.setText(String.valueOf(gen_ticket_fare));
                psgDialogLayoutBinding.psgTotalFareTV.setText(String.valueOf(total_fare_psg));
                psgDialogLayoutBinding.psgTotalFareTV.setText(String.valueOf("₹ "+total_fare_psg));

                break;


            case R.id.cld_add_RL:

                break;

            case R.id.cld_subtract_RL:


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
        psgDialogLayoutBinding.closePsgNoRL.setOnClickListener(this);
        psgDialogLayoutBinding.printPsgNoRL.setOnClickListener(this);
        psgDialogLayoutBinding.genSubtractRL.setOnClickListener(this);
        psgDialogLayoutBinding.genAddRL.setOnClickListener(this);
        psgDialogLayoutBinding.cldSubtractRL.setOnClickListener(this);
        psgDialogLayoutBinding.cldAddRL.setOnClickListener(this);

        psgDialogLayoutBinding.fromStationTV.setText(routeFareModel.fromStationName);
        psgDialogLayoutBinding.toStationTV.setText(routeFareModel.toStationName);
        psgDialogLayoutBinding.genFareTV.setText(String.valueOf(routeFareModel.totalFare));
        psgDialogLayoutBinding.psgTotalKmTV.setText(String.valueOf(routeFareModel.getDistance_km()));


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
        for (int i = 0; i < toStationList.size(); i++) {
            toStationList.get(i).setSelected(i == position);
        }
        ticketBookingToStationAdapter.notifyDataSetChanged();
        selectedToStationId = toStationList.get(position).getStationId();
    }

    @Override
    public void selectFromStation(int position, List<StationsModel> fromStationList) {

        if (position > 0) {
            showChangeStateConfirmationDialog();
            statePositionFromStation = position;
            statePositionToStation = position;
        } else {
            fromStationList.get(position).setSelected(true);
            for (int i = 0; i < fromStationList.size(); i++) {
                fromStationList.get(i).setSelected(i == position);
            }
            ticketBookingFromStationAdapter.notifyDataSetChanged();

        }

        selectedFromStationId = fromStationList.get(position).getStationId();


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
    }*/
}