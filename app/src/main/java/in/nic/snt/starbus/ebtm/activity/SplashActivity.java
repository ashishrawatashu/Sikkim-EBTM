package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.api.ApiClient;
import in.nic.snt.starbus.ebtm.api.ApiResponse;
import in.nic.snt.starbus.ebtm.api.ApiService;
import in.nic.snt.starbus.ebtm.databinding.ActivitySplashBinding;
import in.nic.snt.starbus.ebtm.response.checkETMResponse.CheckETMResponse;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentUserLoginModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.DataLastDateUpdationDateModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.MachineCurrentStatusModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentUserLoginStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.DataUpdationLastDateDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.MachineCurrentStatusDao;
import in.nic.snt.starbus.ebtm.utils.CommonMethods;
import in.nic.snt.starbus.ebtm.utils.JavaToCSharpAES;
import in.nic.snt.starbus.ebtm.utils.MySingleton;
import retrofit2.Response;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity implements ApiResponse {

    private ActivitySplashBinding activitySplashBinding;
    private CommonMethods commonMethods;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = activitySplashBinding.getRoot();
        setContentView(view);

        startActivity(new Intent(SplashActivity.this, StartAndPreviousTripListActivity.class));
        finish();


        /*JavaToCSharpAES javaToCSharpAES = new JavaToCSharpAES();
        try {
            Log.e("WORD",javaToCSharpAES.Encryptt());
        } catch (Exception e) {
            e.printStackTrace();
        }

        commonMethods = new CommonMethods(this);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();
        getDataUpdationLastDate();
        checkMachineStatus();*/
    }

    private void getDataUpdationLastDate() {
        DataUpdationLastDateDao dataUpdationLastDateDao = db.dataLastDateUpdationDateModel();
        List<DataLastDateUpdationDateModel> dataLastDateUpdationDateModelList = dataUpdationLastDateDao.getDataLastDateUpdationDateModel();
        Log.e("LAT_LOGIN", String.valueOf(dataLastDateUpdationDateModelList.size()));
        if (!dataLastDateUpdationDateModelList.isEmpty()) {
            MySingleton.getInstance().routeLastUpdate = dataLastDateUpdationDateModelList.get(0).routeLastUpdate;
            MySingleton.getInstance().routeStationLastUpdate = dataLastDateUpdationDateModelList.get(0).routeStationLastUpdate;
            MySingleton.getInstance().routeLastUpdate = dataLastDateUpdationDateModelList.get(0).routeLastUpdate;
            MySingleton.getInstance().fareStationLastUpdate = dataLastDateUpdationDateModelList.get(0).fareStationLastUpdate;
            MySingleton.getInstance().concessionLastUpdate = dataLastDateUpdationDateModelList.get(0).concessionLastUpdate;
        } else {
            DataLastDateUpdationDateModel dataLastDateUpdationDateModel = new DataLastDateUpdationDateModel();
            dataLastDateUpdationDateModel.setLastDateUpdationDateId(1);
            dataLastDateUpdationDateModel.setRouteLastUpdate("");
            dataLastDateUpdationDateModel.setRouteStationLastUpdate("");
            dataLastDateUpdationDateModel.setFareStationLastUpdate("");
            dataLastDateUpdationDateModel.setConcessionLastUpdate("");
            db.dataLastDateUpdationDateModel().insertRecord(dataLastDateUpdationDateModel);
        }

    }

    private void checkMachineStatus() {
        MachineCurrentStatusDao machineCurrentStatusDao = db.machineCurrentStatusDao();
        MachineCurrentStatusModel machineCurrentStatusModel = machineCurrentStatusDao.getMachineCurrentStatus();

        if (machineCurrentStatusModel == null) {
            checkETM();
        } else {
            if (machineCurrentStatusModel.machineStatusId.equals("0")) {
                operatorLoginIntent();
            } else if (machineCurrentStatusModel.machineStatusId.equals("2")) {

                CurrentUserLoginStatusDao currentUserLoginStatusDao = db.currentUserLoginStatusDao();
                CurrentUserLoginModel currentUserLoginModel = currentUserLoginStatusDao.getCurrentUserLoginStatus();

                Intent moveToConductorDashIntent = new Intent(this, TicketBookingDashActivity.class);
                startActivity(moveToConductorDashIntent);

//                if(currentUserLoginModel.getLoginStatus().equals("N")){
//                    operatorLoginIntent();
//                }else {
//                    Intent moveToConductorDashIntent = new Intent(this, TicketBookingDashActivity.class);
//                    startActivity(moveToConductorDashIntent);
//                }


            } else {
                checkETM();
            }
        }

    }

    private void operatorLoginIntent() {
        Intent operatorLoginActivityIntent = new Intent(this, OperatorLoginActivity.class);
        startActivity(operatorLoginActivityIntent);
    }

    private void checkETM() {
        if (commonMethods.isInternetOn()) {
            commonMethods.showCustomProgressBarDialog(this);
            Map<String, String> checkETMRequest = new HashMap<>();
            checkETMRequest.put("etmVersion", "1");
            checkETMRequest.put("IMEINo", MySingleton.getInstance().IMEI);
            checkETMRequest.put("etmOfficeId", MySingleton.getInstance().OFFICE_ID);
            checkETMRequest.put("routeLastUpdate", MySingleton.getInstance().routeLastUpdate);
            checkETMRequest.put("routeStationLastUpdate", MySingleton.getInstance().routeStationLastUpdate);
            checkETMRequest.put("fareStationLastUpdate", MySingleton.getInstance().fareStationLastUpdate);
            checkETMRequest.put("concessionLastUpdate", MySingleton.getInstance().concessionLastUpdate);
            checkETMRequest.put("token", getString(R.string.TOKEN));
            Log.d("CHECK_ETM", checkETMRequest.toString());

            ApiService<CheckETMResponse> service = new ApiService<>();
            service.get(this, ApiClient.getApiInterface().checkETM(checkETMRequest), "CheckETM");

            Log.e("CHECK_ETM",commonMethods.getJsonFormat(checkETMRequest));
        } else {
            commonMethods.DialogInternet();
        }

    }

    @Override
    public void onResponse(Response response, String key) {
        switch (key) {
            case "CheckETM":
                try {
                    Log.e("CHECK_ETM_RESPONSE", response.toString());
                    commonMethods.customProgressDismiss();
                    CheckETMResponse checkETMResponse = (CheckETMResponse) response.body();
                    Log.d("CHECK_ETM", commonMethods.getJsonFormat(checkETMResponse));
                    if (checkETMResponse.getCode().equals("100")) {
                        if (checkETMResponse.getResult().get(0).getETMRegistrationYN().equals("Y")) {
                            if (checkETMResponse.getResult().get(0).getRouteUpdatedYN().equals("N")) {
                                MySingleton.getInstance().checkRouteLastUpdate = true;
                                MySingleton.getInstance().checkExpensesEarnings = true;
                            }
                            if (checkETMResponse.getResult().get(0).getRouteStationUpdatedYN().equals("N")) {
                                MySingleton.getInstance().checkRouteStationLastUpdate = true;
                                MySingleton.getInstance().checkExpensesEarnings = true;
                            }
                            if (checkETMResponse.getResult().get(0).getFareStationUpdatedYN().equals("N")) {
                                MySingleton.getInstance().checkFareStationLastUpdate = true;
                                MySingleton.getInstance().checkExpensesEarnings = true;
                            }
                            if (checkETMResponse.getResult().get(0).getConcessionUpdatedYN().equals("N")) {
                                MySingleton.getInstance().checkConcessionLastUpdate = true;
                                MySingleton.getInstance().checkExpensesEarnings = true;
                            }

                            checkDataUpdateCondition();
//                      MySingleton.getInstance().OFFICE_ID = checkETMResponse.getResult().get(0).getOfficeId();
                        } else {
                            Toast.makeText(this, "Machine is not register", Toast.LENGTH_SHORT).show();
                        }
                    } else if (checkETMResponse.getCode().equals("900")) {
                        Toast.makeText(this, checkETMResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }


    }

    private void checkDataUpdateCondition() {

        if (MySingleton.getInstance().checkRouteLastUpdate || MySingleton.getInstance().checkConcessionLastUpdate || MySingleton.getInstance().checkFareStationLastUpdate || MySingleton.getInstance().checkRouteStationLastUpdate) {
            Intent dataUpdationActivity = new Intent(this, DataUpdationActivity.class);
            startActivity(dataUpdationActivity);
        } else {
            operatorLoginIntent();
        }


    }

    @Override
    public void onError(Throwable t, String key) {
        switch (key) {
            case "CheckETM":
                commonMethods.customProgressDismiss();
                Log.e("CHeckETM_ERROR", t.getLocalizedMessage());
                break;
        }

    }
}