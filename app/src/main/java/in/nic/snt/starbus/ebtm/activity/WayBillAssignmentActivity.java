package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.HashMap;
import java.util.Map;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adapters.TripsAdapter;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.TripsListOnClick;
import in.nic.snt.starbus.ebtm.api.ApiClient;
import in.nic.snt.starbus.ebtm.api.ApiResponse;
import in.nic.snt.starbus.ebtm.api.ApiService;
import in.nic.snt.starbus.ebtm.databinding.ActivityWayBillAssignmentBinding;
import in.nic.snt.starbus.ebtm.databinding.AssignWayBillConfirmationDialogBinding;
import in.nic.snt.starbus.ebtm.databinding.CancelWayBillConfirmationDialogBinding;
import in.nic.snt.starbus.ebtm.response.getWayBillDetailsResponse.GetWaybillDetailsResponse;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.MachineCurrentStatusModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.OperatorLoginResultModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.TripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.WaybillModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.ConductorDriverTIDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentUserLoginStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.MachineCurrentStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.OperatorLoginDetailsDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.TripsDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.WayBillDao;
import in.nic.snt.starbus.ebtm.utils.CommonMethods;
import retrofit2.Response;

public class WayBillAssignmentActivity extends AppCompatActivity implements ApiResponse , View.OnClickListener {

    private ActivityWayBillAssignmentBinding activityWayBillAssignmentBinding;
    private CommonMethods commonMethods;
    private AppDatabase db;
    private GetWaybillDetailsResponse getWaybillDetailsResponse;


    private CancelWayBillConfirmationDialogBinding cancelWayBillConfirmationDialogBinding;
    private BottomSheetDialog cancelConfirmationBottomSheet;

    private AssignWayBillConfirmationDialogBinding assignWayBillConfirmationDialogBinding;
    private BottomSheetDialog assignConfirmationBottomSheet;


    boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWayBillAssignmentBinding = ActivityWayBillAssignmentBinding.inflate(getLayoutInflater());
        View view = activityWayBillAssignmentBinding.getRoot();
        setContentView(view);
        
        commonMethods = new CommonMethods(this);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();

        initMethod();
        checkWay();
        setOperatorData();

    }

    private void initMethod() {

        activityWayBillAssignmentBinding.wayBillNoET.requestFocus();
        activityWayBillAssignmentBinding.proceedWaybillBT.setOnClickListener(this);
        activityWayBillAssignmentBinding.cardCancelWayBillBT.setOnClickListener(this);
        activityWayBillAssignmentBinding.operatorLogOutIV.setOnClickListener(this);
    }

    private void checkWay() {
        WayBillDao wayBillDao = db.wayBillDao();
        WaybillModel waybillModel = wayBillDao.getWayBillData();
        if (waybillModel==null){
            activityWayBillAssignmentBinding.enterWayBillMCV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.wayBillDetailsMCV.setVisibility(View.GONE);
            activityWayBillAssignmentBinding.wayBillDetailsLL.setVisibility(View.GONE);
        }else {
            setDataInAssigWayBillCard();
            activityWayBillAssignmentBinding.wayBillDetailsLL.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.enterWayBillMCV.setVisibility(View.GONE);
            activityWayBillAssignmentBinding.wayBillDetailsMCV.setVisibility(View.GONE);

        }
    }

    private void setOperatorData() {
        OperatorLoginDetailsDao operatorLoginDetailsDao = db.operatorLoginDetailsDao();
        OperatorLoginResultModel operatorLoginResultModel = operatorLoginDetailsDao.getOperatorLoginResult();
        activityWayBillAssignmentBinding.operatorNameTV.setText(operatorLoginResultModel.getUsername());
        activityWayBillAssignmentBinding.roleNameNameTV.setText(operatorLoginResultModel.getUserdesignation());
        activityWayBillAssignmentBinding.operatorDepotNameTV.setText(operatorLoginResultModel.getDepotname());
    }


    private void getWayBillDetails(String wayBillNo) {
        commonMethods.showCustomProgressBarDialog(this);
        Map<String , String> wayBillDetailsRequest = new HashMap<>();
        wayBillDetailsRequest.put("waybillNo",wayBillNo);
        wayBillDetailsRequest.put("token",getString(R.string.TOKEN));

        ApiService<GetWaybillDetailsResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getWaybillDetails(wayBillDetailsRequest), "getWayBillDetails");
        Log.e("getWayBillDetails", String.valueOf(wayBillDetailsRequest));

    }


    @Override
    public void onResponse(Response response, String key) {

        switch (key) {

            case "getWayBillDetails":
                try {
//                    Log.e("RESPONSE",response.body());
                    commonMethods.customProgressDismiss();
                    getWaybillDetailsResponse = (GetWaybillDetailsResponse) response.body();
                    Log.e("data_response_getRoute", commonMethods.getJsonFormat(response.body()));

                    if (getWaybillDetailsResponse.getCode().equals("100")){

                        Log.e("DEPART_TIME",getWaybillDetailsResponse.getWaybill().get(0).getDeprttime());
                        activityWayBillAssignmentBinding.enterWayBillMCV.setVisibility(View.GONE);
                        activityWayBillAssignmentBinding.wayBillDetailsMCV.setVisibility(View.VISIBLE);
                        setWayBillData();

                    }else {
                        Toast.makeText(this, "Something went wrong !", Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;

        }
    }

    @SuppressLint("SetTextI18n")
    private void setWayBillData() {
        activityWayBillAssignmentBinding.assignWayBillBT.setOnClickListener(this);

        activityWayBillAssignmentBinding.wayBillNoTV.setText(getWaybillDetailsResponse.getWaybill().get(0).getWaybillid());
        activityWayBillAssignmentBinding.busNoTV.setText(getWaybillDetailsResponse.getWaybill().get(0).getBusno());
        activityWayBillAssignmentBinding.departDateTV.setText(getWaybillDetailsResponse.getWaybill().get(0).getDeprtdate());
        activityWayBillAssignmentBinding.departTimeTV.setText(getWaybillDetailsResponse.getWaybill().get(0).getDeprttime());
        activityWayBillAssignmentBinding.routeNameTV.setText(getWaybillDetailsResponse.getWaybill().get(0).getRoute());

        if(!getWaybillDetailsResponse.getConductor().isEmpty()){
            activityWayBillAssignmentBinding.conductorNameHeadlineTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.conductorNameTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.conductorNameTV.setText(getWaybillDetailsResponse.getConductor().get(0).getUsername()+" ("+getWaybillDetailsResponse.getConductor().get(0).getUsercode()+")");
        }else  {
            activityWayBillAssignmentBinding.conductorNameHeadlineTV.setVisibility(View.GONE);
            activityWayBillAssignmentBinding.conductorNameTV.setVisibility(View.GONE);
        }
        if(!getWaybillDetailsResponse.getConductor2().isEmpty()){
            activityWayBillAssignmentBinding.conductorTwoNameHeadlineTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.conductorTwoNameTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.conductorTwoNameTV.setText(getWaybillDetailsResponse.getConductor2().get(0).getUsername()+" ("+getWaybillDetailsResponse.getConductor2().get(0).getUsercode()+")");
        }else  {
            activityWayBillAssignmentBinding.conductorTwoNameHeadlineTV.setVisibility(View.GONE);
            activityWayBillAssignmentBinding.conductorTwoNameTV.setVisibility(View.GONE);
        }

        if(!getWaybillDetailsResponse.getDriver().isEmpty()){
            activityWayBillAssignmentBinding.driverNameHeadlineTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.driverNameTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.driverNameTV.setText(getWaybillDetailsResponse.getDriver().get(0).getUsername()+" ("+getWaybillDetailsResponse.getDriver().get(0).getUsercode()+")");
        }else {
            activityWayBillAssignmentBinding.driverNameHeadlineTV.setVisibility(View.GONE);
            activityWayBillAssignmentBinding.driverNameTV.setVisibility(View.GONE);
        }
        if(!getWaybillDetailsResponse.getDriver2().isEmpty()){
            activityWayBillAssignmentBinding.driverTwoNameHeadlineTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.driverTwoNameTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.driverTwoNameTV.setText(getWaybillDetailsResponse.getDriver2().get(0).getUsername()+" ("+getWaybillDetailsResponse.getDriver2().get(0).getUsercode()+")");
        }else  {
            activityWayBillAssignmentBinding.driverTwoNameHeadlineTV.setVisibility(View.GONE);
            activityWayBillAssignmentBinding.driverTwoNameTV.setVisibility(View.GONE);
        }


        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        activityWayBillAssignmentBinding.tripsRV.setLayoutManager(linearLayoutManager);
        TripsAdapter tripsAdapter = new TripsAdapter(this,getWaybillDetailsResponse.getTrips());
        activityWayBillAssignmentBinding.tripsRV.setAdapter(tripsAdapter);


    }

    private void insertTripsData(GetWaybillDetailsResponse getWaybillDetailsResponse) {
        //insert Trips
        TripsDao tripsDao = db.tripsDao();
        tripsDao.deleteTripsTable();

        //insert Trips
        if (!getWaybillDetailsResponse.getTrips().isEmpty()){
            for (int i = 0; i < getWaybillDetailsResponse.getTrips().size();i++){
                tripsDao.insertRecord(getWaybillDetailsResponse.getTrips().get(i));
            }
        }

    }

    private void insertConductorDriverAndTIsData(GetWaybillDetailsResponse getWaybillDetailsResponse) {

        //conductor Driver TIs Data insert
        ConductorDriverTIDao conductorDriverTIDao = db.conductorDriverTIDao();
        conductorDriverTIDao.deleteConductorDriverTable();

        //insert Data  in conductor Driver TIs
        //insert conductor
        if (!getWaybillDetailsResponse.getConductor().isEmpty()){
            conductorDriverTIDao.insertRecord(getWaybillDetailsResponse.getConductor().get(0));
        }
        if (!getWaybillDetailsResponse.getConductor2().isEmpty()){
            conductorDriverTIDao.insertRecord(getWaybillDetailsResponse.getConductor2().get(0));
        }

        //insert Driver
        if (!getWaybillDetailsResponse.getDriver().isEmpty()){
            conductorDriverTIDao.insertRecord(getWaybillDetailsResponse.getDriver().get(0));
        }
        if (!getWaybillDetailsResponse.getDriver2().isEmpty()){
            conductorDriverTIDao.insertRecord(getWaybillDetailsResponse.getDriver2().get(0));
        }

        //insert TIs
        if (!getWaybillDetailsResponse.gettIs().isEmpty()){
            for (int i = 0; i< getWaybillDetailsResponse.gettIs().size();i++){
                conductorDriverTIDao.insertRecord(getWaybillDetailsResponse.gettIs().get(i));
            }
        }
    }

    private void insertWayBillData(GetWaybillDetailsResponse getWaybillDetailsResponse) {

        //wayBill
        WayBillDao wayBillDao = db.wayBillDao();
        wayBillDao.deleteWayBillWaybill();

        //insert way bill data
        wayBillDao.insertRecord(getWaybillDetailsResponse.getWaybill().get(0));
    }

    @Override
    public void onError(Throwable t, String key) {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.proceed_waybill_BT:
                String wayBillNO = activityWayBillAssignmentBinding.wayBillNoET.getText().toString().trim();
                if (wayBillNO.isEmpty()){
                    activityWayBillAssignmentBinding.wayBillNoET.setError("Enter way bill no...");
                    Toast.makeText(this, "Please enter way bill no !", Toast.LENGTH_LONG).show();
                } else {
                    getWayBillDetails(wayBillNO);
                }

                break;

            case R.id.assign_way_bill_BT:

                showAssignWayBillConfirmationDialog();

                break;


            case R.id.card_cancel_way_bill_BT:

                showCancelConfirmationDialog();


                break;


            case R.id.assign_yes_RL:

                commonMethods.showCustomProgressBarDialog(this);
                //insertWayBillData
                insertWayBillData(getWaybillDetailsResponse);
                // insertConductorDriverAndTIsData
                insertConductorDriverAndTIsData(getWaybillDetailsResponse);
                //insertTripsData
                insertTripsData(getWaybillDetailsResponse);

                commonMethods.customProgressDismiss();
                setDataInAssigWayBillCard();
                setMachineCurrentStatus();
                activityWayBillAssignmentBinding.wayBillDetailsLL.setVisibility(View.VISIBLE);
                activityWayBillAssignmentBinding.wayBillDetailsMCV.setVisibility(View.GONE);
                assignConfirmationBottomSheet.dismiss();
                Toast.makeText(this, "Waybill assign successfully ", Toast.LENGTH_LONG).show();

                break;

            case R.id.cancel_yes_RL:

                deleteWayBillData();
                deleteTripsData();
                deleteAllUserData();
                activityWayBillAssignmentBinding.wayBillNoET.setText("");
                activityWayBillAssignmentBinding.wayBillNoET.requestFocus();
                activityWayBillAssignmentBinding.enterWayBillMCV.setVisibility(View.VISIBLE);
                activityWayBillAssignmentBinding.wayBillDetailsMCV.setVisibility(View.GONE);
                activityWayBillAssignmentBinding.wayBillDetailsLL.setVisibility(View.GONE);
                cancelConfirmationBottomSheet.dismiss();
                Toast.makeText(this, "Machine is fresh now ", Toast.LENGTH_LONG).show();

                break;

            case R.id.cancel_no_RL:

                cancelConfirmationBottomSheet.dismiss();


                break;

            case R.id.operator_log_out_IV:
                CurrentUserLoginStatusDao currentUserLoginStatusDao = db.currentUserLoginStatusDao();
                currentUserLoginStatusDao.updateUserLoginStatus("N");
                startActivity(new Intent(this,OperatorLoginActivity.class));
                break;

        }


    }

    private void showAssignWayBillConfirmationDialog() {

        assignConfirmationBottomSheet = new BottomSheetDialog(this);
        assignConfirmationBottomSheet.requestWindowFeature(Window.FEATURE_NO_TITLE);
        assignConfirmationBottomSheet.setCancelable(true);
        assignConfirmationBottomSheet.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        assignWayBillConfirmationDialogBinding = AssignWayBillConfirmationDialogBinding.inflate(LayoutInflater.from(this));
        assignConfirmationBottomSheet.setContentView(assignWayBillConfirmationDialogBinding.getRoot());

        assignWayBillConfirmationDialogBinding.assignNoRL.setOnClickListener(this);
        assignWayBillConfirmationDialogBinding.assignYesRL.setOnClickListener(this);

        assignConfirmationBottomSheet.show();
        Window window = assignConfirmationBottomSheet.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


    }

    private void setMachineCurrentStatus() {
        String currentDateTime = commonMethods.getDateTime();
        MachineCurrentStatusDao  machineCurrentStatusDao = db.machineCurrentStatusDao();



        boolean checkData;
        checkData = machineCurrentStatusDao.is_exist("1");

        if (!checkData){
            MachineCurrentStatusModel machineCurrentStatusModel = new MachineCurrentStatusModel();
            machineCurrentStatusModel.setMachineStatusId("1");
            machineCurrentStatusModel.setWaybillNo(getWaybillDetailsResponse.getWaybill().get(0).getWaybillid());
            machineCurrentStatusModel.setWaybillDateTime(currentDateTime);
            machineCurrentStatusModel.setCurrentTripNo("");
            machineCurrentStatusModel.setCurrentStage("");
            machineCurrentStatusModel.setTripStartDateTime("");
            machineCurrentStatusDao.insertRecord(machineCurrentStatusModel);
        }else {
            machineCurrentStatusDao.updateMachineCurrentStatusAndWaybillNo("1",getWaybillDetailsResponse.getWaybill().get(0).getWaybillid(),currentDateTime);
        }




    }

    private void showCancelConfirmationDialog() {
        cancelConfirmationBottomSheet = new BottomSheetDialog(this);
        cancelConfirmationBottomSheet.requestWindowFeature(Window.FEATURE_NO_TITLE);
        cancelConfirmationBottomSheet.setCancelable(true);
        cancelConfirmationBottomSheet.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        cancelWayBillConfirmationDialogBinding = CancelWayBillConfirmationDialogBinding.inflate(LayoutInflater.from(this));
        cancelConfirmationBottomSheet.setContentView(cancelWayBillConfirmationDialogBinding.getRoot());

        cancelWayBillConfirmationDialogBinding.cancelNoRL.setOnClickListener(this);
        cancelWayBillConfirmationDialogBinding.cancelYesRL.setOnClickListener(this);


        cancelConfirmationBottomSheet.show();
        Window window = cancelConfirmationBottomSheet.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private void deleteAllUserData() {
        ConductorDriverTIDao conductorDriverTIDao = db.conductorDriverTIDao();
        conductorDriverTIDao.deleteConductorDriverTable();
    }

    private void deleteTripsData() {
        TripsDao tripsDao = db.tripsDao();
        tripsDao.deleteTripsTable();
    }

    private void deleteWayBillData() {
        WayBillDao wayBillDao = db.wayBillDao();
        wayBillDao.deleteWayBillWaybill();
    }

    private void setDataInAssigWayBillCard() {

        WayBillDao wayBillDao = db.wayBillDao();
        WaybillModel waybillModel = wayBillDao.getWayBillData();

        activityWayBillAssignmentBinding.cardWayBillNoTV.setText(waybillModel.getWaybillid());
        activityWayBillAssignmentBinding.cardBusNoTV.setText(waybillModel.getBusno());
        activityWayBillAssignmentBinding.cardDepartDateTV.setText(waybillModel.getDeprtdate());
        activityWayBillAssignmentBinding.cardDepartTimeTV.setText(waybillModel.getDeprttime());
        activityWayBillAssignmentBinding.cardRouteNameTV.setText(waybillModel.getRoute());

        if(!waybillModel.getConductor1name().equals("N/A")){
            activityWayBillAssignmentBinding.cardConductorNameHeadlineTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.cardConductorNameTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.cardConductorNameTV.setText(waybillModel.getConductor1name());
        }else  {
            activityWayBillAssignmentBinding.cardConductorNameHeadlineTV.setVisibility(View.GONE);
            activityWayBillAssignmentBinding.cardConductorNameTV.setVisibility(View.GONE);
        }
        if(!waybillModel.getConductor2name().equals("N/A")){
            activityWayBillAssignmentBinding.cardConductorTwoNameHeadlineTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.cardConductorTwoNameTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.cardConductorTwoNameTV.setText(waybillModel.getConductor2name());
        }else  {
            activityWayBillAssignmentBinding.cardConductorTwoNameHeadlineTV.setVisibility(View.GONE);
            activityWayBillAssignmentBinding.cardConductorTwoNameTV.setVisibility(View.GONE);
        }

        if(!waybillModel.getDriver1name().equals("N/A")){
            activityWayBillAssignmentBinding.cardDriverNameHeadlineTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.cardDriverNameTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.cardDriverNameTV.setText(waybillModel.getDriver1name());
        }else {
            activityWayBillAssignmentBinding.cardDriverNameHeadlineTV.setVisibility(View.GONE);
            activityWayBillAssignmentBinding.cardDriverNameTV.setVisibility(View.GONE);
        }
        if(!waybillModel.getDriver2name().equals("N/A")){
            activityWayBillAssignmentBinding.cardDriverTwoNameHeadlineTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.cardDriverTwoNameTV.setVisibility(View.VISIBLE);
            activityWayBillAssignmentBinding.cardDriverTwoNameTV.setText(waybillModel.getDriver2name());
        }else  {
            activityWayBillAssignmentBinding.cardDriverTwoNameHeadlineTV.setVisibility(View.GONE);
            activityWayBillAssignmentBinding.cardDriverTwoNameTV.setVisibility(View.GONE);
        }

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

}