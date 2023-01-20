package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.api.ApiClient;
import in.nic.snt.starbus.ebtm.api.ApiResponse;
import in.nic.snt.starbus.ebtm.api.ApiService;
import in.nic.snt.starbus.ebtm.databinding.ActivityOperatorLoginBinding;
import in.nic.snt.starbus.ebtm.response.checkETMResponse.CheckETMResponse;
import in.nic.snt.starbus.ebtm.response.operatorLoginResponse.OperatorLoginResponse;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConductorDriverTiModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentTripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentUserLoginModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.MachineStatusModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.ConductorDriverTIDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentTripsDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentUserLoginStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.MachineStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.OperatorLoginDetailsDao;
import in.nic.snt.starbus.ebtm.utils.CommonMethods;
import in.nic.snt.starbus.ebtm.utils.MySingleton;
import retrofit2.Response;

public class OperatorLoginActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener {

    private ActivityOperatorLoginBinding activityOperatorLoginBinding;
    private CommonMethods commonMethods;
    private AppDatabase db;

    boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOperatorLoginBinding = ActivityOperatorLoginBinding.inflate(getLayoutInflater());
        View view = activityOperatorLoginBinding.getRoot();
        setContentView(view);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();
        commonMethods = new CommonMethods(this);
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        activityOperatorLoginBinding.operatorLoginBT.setOnClickListener(this);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.operator_login_BT:
                String user_id = activityOperatorLoginBinding.operatorOperatorUserIdET.getText().toString().trim();
                String password = activityOperatorLoginBinding.operatorPasswordET.getText().toString().trim();

                if (user_id.isEmpty()) {
                    activityOperatorLoginBinding.operatorUserIdTIL.setError(getString(R.string.invalid_user_id));
                    break;
                }
                if (password.isEmpty()) {
                    activityOperatorLoginBinding.operatorPasswordTIL.setError(getString(R.string.invalid_user_id));
                    break;
                }
                String password_SHA = commonMethods.getShaOne(password);

                login(user_id, password_SHA);

                break;
        }
    }

    private void operatorLogin(String user_id, String password_sha) {
        if (commonMethods.isInternetOn()) {
            commonMethods.showCustomProgressBarDialog(this);
            Map<String, String> operatorLoginRequest = new HashMap<>();
            operatorLoginRequest.put("userId", user_id);
            operatorLoginRequest.put("userPwd", password_sha);
            operatorLoginRequest.put("imei", MySingleton.getInstance().IMEI);
            operatorLoginRequest.put("officeId", MySingleton.getInstance().OFFICE_ID);
            operatorLoginRequest.put("token", getString(R.string.TOKEN));
            Log.e("checkETMResponse", operatorLoginRequest.toString());

            ApiService<OperatorLoginResponse> service = new ApiService<>();
            service.get(this, ApiClient.getApiInterface().OperatorLogin(operatorLoginRequest), "operatorLogin");
        } else {
            commonMethods.DialogInternet();
        }
    }

    @Override
    public void onResponse(Response response, String key) {
        switch (key) {
            case "operatorLogin":
                try {
                    commonMethods.customProgressDismiss();
                    OperatorLoginResponse operatorLoginResponse = (OperatorLoginResponse) response.body();
                    Log.e("operatorLoginResponse", commonMethods.getJsonFormat(operatorLoginResponse));
                    if (operatorLoginResponse.getCode().equals("100")) {
                        OperatorLoginDetailsDao operatorLoginDetailsDao = db.operatorLoginDetailsDao();
                        operatorLoginDetailsDao.deleteWayBillWaybill();
                        operatorLoginDetailsDao.insertRecord(operatorLoginResponse.getResult().get(0));

                        insertUserLoginStatusData(operatorLoginResponse.getResult().get(0).getUsername(),"O");
                        Intent wayBillAssignmentIntent = new Intent(this, WayBillAssignmentActivity.class);
                        startActivity(wayBillAssignmentIntent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void insertUserLoginStatusData(String name, String role) {

        CurrentUserLoginStatusDao currentUserLoginStatusDao = db.currentUserLoginStatusDao();

        //delete user table
        currentUserLoginStatusDao.deleteUserLoginTable();

        CurrentUserLoginModel currentUserLoginModel = new CurrentUserLoginModel();
        currentUserLoginModel.setName(name);
        currentUserLoginModel.setUserName(activityOperatorLoginBinding.operatorOperatorUserIdET.getText().toString().trim());
        currentUserLoginModel.setUserRole(role);
        currentUserLoginModel.setLoginStatus("Y");

        //insert user table
        currentUserLoginStatusDao.insertLoginRecord(currentUserLoginModel);

    }

    @Override
    public void onError(Throwable t, String key) {

    }

// local DB login

    private void login(String user_code, String user_pwd) {
        ConductorDriverTIDao conductorDriverTIDao = db.conductorDriverTIDao();
        ConductorDriverTiModel conductorDriverTiModel = conductorDriverTIDao.getConductorDriverInfoByUserId(user_code);

        if (conductorDriverTiModel == null) {
            operatorLogin(user_code, user_pwd);
        } else {

            String userCode = conductorDriverTiModel.getUsercode().toString();
            String userName = conductorDriverTiModel.getUsername().toString();
            String userPwd = conductorDriverTiModel.getUsrpwd().toString();
            String role = conductorDriverTiModel.getRolecode().toString();
            String roleName = conductorDriverTiModel.getUserrolename().toString();

            if (userPwd.equalsIgnoreCase(user_pwd)) {
                switch (role) {
                    case "22":

                        insertUserLoginStatusData(userName,"C");
                        CurrentTripsDao currentTripsDao = db.currentTripsDao();
                        CurrentTripsModel currentTripsModel = currentTripsDao.getIncompleteTripData();
                        if(currentTripsModel==null){
                            Intent conductorDashActivity = new Intent(this, ConductorDashActivity.class);
                            startActivity(conductorDashActivity);
                        }else if(currentTripsModel.getCompleteYN().equals("N")){
                            Intent ticketBookingDashActivity = new Intent(this, TicketBookingDashActivity.class);
                            startActivity(ticketBookingDashActivity);
                        }

                        break;
                    case "21":
                        insertUserLoginStatusData(userName,"D");
                        Intent ii = new Intent(this, DriverDashActivity.class);
                        startActivity(ii);
                        break;
                    case "14":
                        insertUserLoginStatusData(userName,"T");
                        Intent iii = new Intent(this, TiDashActivity.class);
                        startActivity(iii);
                        break;
                }
            } else {
                Toast.makeText(OperatorLoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
            }
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