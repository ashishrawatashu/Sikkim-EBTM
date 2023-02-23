package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
import in.nic.snt.starbus.ebtm.databinding.ActivityDataUpdationBinding;
import in.nic.snt.starbus.ebtm.response.GetExpensesEarnings;
import in.nic.snt.starbus.ebtm.response.getConcessionResponse.GetConcessionResponse;
import in.nic.snt.starbus.ebtm.response.getFareStationsResponse.GetFareStationsResponse;
import in.nic.snt.starbus.ebtm.response.getRouteResponse.GetRouteResponse;
import in.nic.snt.starbus.ebtm.response.getRouteStationsResponse.GetRouteStationsResponse;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ConcessionsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RouteFareModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesStationModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.ConcessionDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.ExpensesEarningsDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.RouteFareDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.RoutesDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.RoutesStationDao;
import in.nic.snt.starbus.ebtm.utils.CommonMethods;
import in.nic.snt.starbus.ebtm.utils.MySingleton;
import retrofit2.Response;

public class DataUpdationActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener {

    private ActivityDataUpdationBinding activityDataUpdationBinding;
    CommonMethods commonMethods;
    private Map<String, String> getDataRequest = new HashMap<>();
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDataUpdationBinding = ActivityDataUpdationBinding.inflate(getLayoutInflater());
        View view = activityDataUpdationBinding.getRoot();
        setContentView(view);

        initMethod();

        commonMethods = new CommonMethods(this);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();
        if (commonMethods.isInternetOn()) {
            getDataRequest.put("officeId", MySingleton.getInstance().OFFICE_ID);
            getDataRequest.put("token", getString(R.string.TOKEN));

            getData();
        } else {
            commonMethods.DialogInternet();
        }

    }

    private void initMethod() {
        activityDataUpdationBinding.nextBT.setOnClickListener(this);
    }

    private void getData() {
        activityDataUpdationBinding.nextBT.setBackgroundColor(Color.GRAY);
        if (MySingleton.getInstance().checkRouteLastUpdate) {
            activityDataUpdationBinding.routesPercentTV.setText("0 %");
            activityDataUpdationBinding.routesTotalProgressTV.setText("Please wait ...");
            activityDataUpdationBinding.routesPB.setVisibility(View.VISIBLE);
            getRoute();
        } else {
            activityDataUpdationBinding.routesDoneIV.setVisibility(View.VISIBLE);
            activityDataUpdationBinding.routesTotalProgressTV.setText("Downloaded");
            activityDataUpdationBinding.routesPercentTV.setText("100 %");
            activityDataUpdationBinding.routesHorizontalProgressBar.setProgress(100);
            activityDataUpdationBinding.routesPB.setVisibility(View.GONE);
        }
        if (MySingleton.getInstance().checkConcessionLastUpdate) {
            activityDataUpdationBinding.concessionPercentTV.setText("0 %");
            activityDataUpdationBinding.concessionTotalProgressTV.setText("Please wait ...");
            activityDataUpdationBinding.concessionPB.setVisibility(View.VISIBLE);
            getConcession();
        } else {
            activityDataUpdationBinding.concessionDoneIV.setVisibility(View.VISIBLE);
            activityDataUpdationBinding.concessionTotalProgressTV.setText("Downloaded");
            activityDataUpdationBinding.concessionPercentTV.setText("100 %");
            activityDataUpdationBinding.concessionPB.setVisibility(View.GONE);
            activityDataUpdationBinding.concessionHorizontalProgressBar.setProgress(100);
        }
        if (MySingleton.getInstance().checkFareStationLastUpdate) {
            activityDataUpdationBinding.fareStationsPercentTV.setText("0 %");
            activityDataUpdationBinding.fareStationsTotalProgressTV.setText("Please wait ...");
            activityDataUpdationBinding.fareStationsPB.setVisibility(View.VISIBLE);
            getFareStations();
        } else {

            activityDataUpdationBinding.expensesEarningsDoneIV.setVisibility(View.VISIBLE);
            activityDataUpdationBinding.expensesEarningsTotalProgressTV.setText("Downloaded");
            activityDataUpdationBinding.expensesEarningsPercentTV.setText("100 %");
            activityDataUpdationBinding.expensesEarningsPB.setVisibility(View.GONE);
            activityDataUpdationBinding.expensesEarningsHorizontalProgressBar.setProgress(100);
        } if (MySingleton.getInstance().checkExpensesEarnings) {
            activityDataUpdationBinding.expensesEarningsPercentTV.setText("0 %");
            activityDataUpdationBinding.expensesEarningsTotalProgressTV.setText("Please wait ...");
            activityDataUpdationBinding.expensesEarningsPB.setVisibility(View.VISIBLE);
            getExpensesEarnings();
        } else {

            activityDataUpdationBinding.fareStationsDoneIV.setVisibility(View.VISIBLE);
            activityDataUpdationBinding.fareStationsTotalProgressTV.setText("Downloaded");
            activityDataUpdationBinding.fareStationsPercentTV.setText("100 %");
            activityDataUpdationBinding.fareStationsPB.setVisibility(View.GONE);
            activityDataUpdationBinding.fareStationsHorizontalProgressBar.setProgress(100);
        }
        if (MySingleton.getInstance().checkRouteStationLastUpdate) {
            activityDataUpdationBinding.routesStationsPercentTV.setText("0 %");
            activityDataUpdationBinding.routesStationsTotalProgressTV.setText("Please wait ...");
            activityDataUpdationBinding.routesStationsPB.setVisibility(View.VISIBLE);

            getRouteStations();
        } else {
            activityDataUpdationBinding.routesStationsDoneIV.setVisibility(View.VISIBLE);
            activityDataUpdationBinding.routesStationsTotalProgressTV.setText("Downloaded");
            activityDataUpdationBinding.routesStationsPercentTV.setText("100 %");
            activityDataUpdationBinding.routesStationsPB.setVisibility(View.GONE);

            activityDataUpdationBinding.routesStationsHorizontalProgressBar.setProgress(100);
        }


    }

    private void getExpensesEarnings() {
        ApiService<GetExpensesEarnings> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getExpensesEarnings(getDataRequest), "getExpensesEarnings");
        Log.e("getExpensesEarningsRequest", String.valueOf(getDataRequest));
    }

    private void getConcession() {
        ApiService<GetConcessionResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getConcession(getDataRequest), "getConcession");
        Log.e("getConcessionRequest", String.valueOf(getDataRequest));
    }


    private void getRoute() {
        ApiService<GetRouteResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getRoute(getDataRequest), "getRoute");
        Log.e("getRouteRequest", String.valueOf(getDataRequest));

    }

    private void getRouteStations() {
        ApiService<GetRouteStationsResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getRouteStations(getDataRequest), "getRouteStations");
        Log.e("getRouteStationsRequest", String.valueOf(getDataRequest));

    }

    private void getFareStations() {
        ApiService<GetFareStationsResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getFareStations(getDataRequest), "getFareStations");
        Log.e("getFareStationsRequest", String.valueOf(getDataRequest));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onResponse(Response response, String key) {
        switch (key) {

            case "getRoute":
                try {
                    GetRouteResponse getRouteResponse = (GetRouteResponse) response.body();
                    Log.e("data_response_getRoute", commonMethods.getJsonFormat(getRouteResponse));
                    if (getRouteResponse.getCode().equals("100")) {
                        RoutesDao routesDao = db.routesDao();
                        routesDao.deleteRoutesTable();
                        for (int i = 0; i < getRouteResponse.getRoute().size(); i++) {
                            RoutesModel routesModel = new RoutesModel(
                                    getRouteResponse.getRoute().get(i).getRoutId(),
                                    getRouteResponse.getRoute().get(i).getRouteNameEn()
                            );
                            routesDao.insertRecord(routesModel);
                        }
                        db.dataLastDateUpdationDateModel().updateRouteLastUpdate(1, getRouteResponse.getRoute().get(0).getRoutelastdate());
                        if (routesDao.getRoutes().size()==getRouteResponse.getRoute().get(0).getCountt()){
                            activityDataUpdationBinding.routesHorizontalProgressBar.setProgress(20);
                            activityDataUpdationBinding.routesPercentTV.setText("20 %");
                            activityDataUpdationBinding.routesHorizontalProgressBar.setProgress(50);
                            activityDataUpdationBinding.routesPercentTV.setText("50 %");
                            activityDataUpdationBinding.routesHorizontalProgressBar.setProgress(100);
                            activityDataUpdationBinding.routesPercentTV.setText("100 %");
                            activityDataUpdationBinding.routesTotalProgressTV.setText("Downloaded");
                            MySingleton.getInstance().checkRouteLastUpdate = false;
                            activityDataUpdationBinding.routesDoneIV.setVisibility(View.VISIBLE);
                            activityDataUpdationBinding.routesRetryIV.setVisibility(View.GONE);
                            activityDataUpdationBinding.routesPB.setVisibility(View.GONE);
                            checkCondition();
                        }else {
                            activityDataUpdationBinding.routesDoneIV.setVisibility(View.INVISIBLE);
                            activityDataUpdationBinding.routesRetryIV.setVisibility(View.VISIBLE);
                            activityDataUpdationBinding.routesPB.setVisibility(View.GONE);
                            MySingleton.getInstance().checkRouteLastUpdate = true;
                            activityDataUpdationBinding.routesPercentTV.setText("0 %");
                            activityDataUpdationBinding.routesHorizontalProgressBar.setProgress(0);
                            activityDataUpdationBinding.routesTotalProgressTV.setText("Downloading failed");
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


            case "getConcession":
                try {
                    GetConcessionResponse getConcessionResponse = (GetConcessionResponse) response.body();
                    Log.e("data_response_getConcession", commonMethods.getJsonFormat(getConcessionResponse));
                    if (getConcessionResponse.getCode().equals("100")) {
                        ConcessionDao concessionDao = db.concessionDao();
                        concessionDao.deleteConcessionTable();
                        for (int i = 0; i < getConcessionResponse.getConcessions().size(); i++) {
                            ConcessionsModel concessionsModel = new ConcessionsModel(
                                    getConcessionResponse.getConcessions().get(i).getConcessionId(),
                                    getConcessionResponse.getConcessions().get(i).getConcessionName(),
                                    getConcessionResponse.getConcessions().get(i).getConcessionAbbr(),
                                    getConcessionResponse.getConcessions().get(i).getConcessionPerFare(),
                                    getConcessionResponse.getConcessions().get(i).getConcessionPerTax(),
                                    getConcessionResponse.getConcessions().get(i).getGenderYn(),
                                    getConcessionResponse.getConcessions().get(i).getGender(),
                                    getConcessionResponse.getConcessions().get(i).getNoOfKmsYn(),
                                    getConcessionResponse.getConcessions().get(i).getNoOfKms(),
                                    getConcessionResponse.getConcessions().get(i).getAgegroupYn(),
                                    getConcessionResponse.getConcessions().get(i).getMinAge(),
                                    getConcessionResponse.getConcessions().get(i).getMaxAge(),
                                    getConcessionResponse.getConcessions().get(i).getServicetypeYn(),
                                    getConcessionResponse.getConcessions().get(i).getServiceType(),
                                    getConcessionResponse.getConcessions().get(i).getStateYn(),
                                    getConcessionResponse.getConcessions().get(i).getWithinstateYn(),
                                    getConcessionResponse.getConcessions().get(i).getOtherstateYn(),
                                    getConcessionResponse.getConcessions().get(i).getAdditionalattendentYn(),
                                    getConcessionResponse.getConcessions().get(i).getOnlineverificationYn(),
                                    getConcessionResponse.getConcessions().get(i).getIdverificationYn(),
                                    getConcessionResponse.getConcessions().get(i).getIdverification(),
                                    getConcessionResponse.getConcessions().get(i).getDocumentverificationYn(),
                                    getConcessionResponse.getConcessions().get(i).getDocumentverification(),
                                    getConcessionResponse.getConcessions().get(i).getOtherconcessionyn()
                            );
                            concessionDao.insertRecord(concessionsModel);
                        }
                        db.dataLastDateUpdationDateModel().updateConcessionLastUpdate(1, getConcessionResponse.getConcessions().get(0).getConcessionlastdate());

                        if (concessionDao.getConcessionsData().size()==getConcessionResponse.getConcessions().get(0).getCountt()){
                            MySingleton.getInstance().checkConcessionLastUpdate = false;
                            activityDataUpdationBinding.concessionHorizontalProgressBar.setProgress(20);
                            activityDataUpdationBinding.concessionPercentTV.setText("20 %");
                            activityDataUpdationBinding.concessionHorizontalProgressBar.setProgress(50);
                            activityDataUpdationBinding.concessionPercentTV.setText("50 %");
                            activityDataUpdationBinding.concessionHorizontalProgressBar.setProgress(100);
                            activityDataUpdationBinding.concessionPercentTV.setText("100 %");

                            activityDataUpdationBinding.concessionTotalProgressTV.setText("Downloaded");
                            activityDataUpdationBinding.concessionDoneIV.setVisibility(View.VISIBLE);
                            activityDataUpdationBinding.concessionRetryIV.setVisibility(View.GONE);
                            activityDataUpdationBinding.concessionPB.setVisibility(View.GONE);

                            checkCondition();
                        }else {
                            activityDataUpdationBinding.concessionDoneIV.setVisibility(View.INVISIBLE);
                            activityDataUpdationBinding.concessionRetryIV.setVisibility(View.VISIBLE);
                            MySingleton.getInstance().checkConcessionLastUpdate = true;
                            activityDataUpdationBinding.concessionPercentTV.setText("0 %");
                            activityDataUpdationBinding.concessionPB.setVisibility(View.GONE);
                            activityDataUpdationBinding.concessionHorizontalProgressBar.setProgress(0);
                            activityDataUpdationBinding.concessionTotalProgressTV.setText("Downloading failed");
                        }




                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "getFareStations":
                try {
                    GetFareStationsResponse getFareStationsResponse = (GetFareStationsResponse) response.body();
                    Log.e("data_response_getFareStations", commonMethods.getJsonFormat(getFareStationsResponse));
                    if (getFareStationsResponse.getCode().equals("100")) {
                        RouteFareDao routeFareDao = db.routeFareDao();
                        routeFareDao.deleteRouteFareTable();
                        for (int i = 0; i < getFareStationsResponse.getFareStations().size(); i++) {
                            RouteFareModel routeFareModel = new RouteFareModel();
                            routeFareModel.setId(i + 1);
                            routeFareModel.setRouteId(getFareStationsResponse.getFareStations().get(i).getRoutId());
                            routeFareModel.setSrtpId(getFareStationsResponse.getFareStations().get(i).getSrtpId());
                            routeFareModel.setFromStationId(getFareStationsResponse.getFareStations().get(i).getFrStonId());
                            routeFareModel.setFromStationName(getFareStationsResponse.getFareStations().get(i).getFrStonName());
                            routeFareModel.setToStationId(getFareStationsResponse.getFareStations().get(i).getToStonId());
                            routeFareModel.setToStationName(getFareStationsResponse.getFareStations().get(i).getToStonName());
                            routeFareModel.setDistance_km(getFareStationsResponse.getFareStations().get(i).getTotalDistKm());
                            routeFareModel.setTotalFare(getFareStationsResponse.getFareStations().get(i).getFinalFare());
                            routeFareModel.setConcessionFare(getFareStationsResponse.getFareStations().get(i).getConcessionFare());
                            routeFareModel.setConcessionTax(getFareStationsResponse.getFareStations().get(i).getConcessionTax());
                            routeFareModel.setTollCharge(getFareStationsResponse.getFareStations().get(i).getTollCharges());
                            routeFareModel.setParkingCharge(getFareStationsResponse.getFareStations().get(i).getParkingCharges());

                            routeFareDao.insertRecord(routeFareModel);
                        }

                        if (routeFareDao.getRouteFare().size()==getFareStationsResponse.getFareStations().get(0).getCountt()){
                            activityDataUpdationBinding.fareStationsHorizontalProgressBar.setProgress(20);
                            activityDataUpdationBinding.fareStationsPercentTV.setText("20 %");
                            activityDataUpdationBinding.fareStationsHorizontalProgressBar.setProgress(50);
                            activityDataUpdationBinding.fareStationsPercentTV.setText("50 %");
                            activityDataUpdationBinding.fareStationsHorizontalProgressBar.setProgress(100);
                            activityDataUpdationBinding.fareStationsPercentTV.setText("100 %");


                            activityDataUpdationBinding.fareStationsTotalProgressTV.setText("Downloaded");
                            MySingleton.getInstance().checkFareStationLastUpdate = false;
                            db.dataLastDateUpdationDateModel().updateFareStationLastUpdate(1, getFareStationsResponse.getFareStations().get(0).getFarestationlastdate());
                            activityDataUpdationBinding.fareStationsDoneIV.setVisibility(View.VISIBLE);
                            activityDataUpdationBinding.fareStationsRetryIV.setVisibility(View.GONE);
                            activityDataUpdationBinding.fareStationsPB.setVisibility(View.GONE);

                            checkCondition();
                        }else {
                            activityDataUpdationBinding.fareStationsDoneIV.setVisibility(View.INVISIBLE);
                            activityDataUpdationBinding.fareStationsRetryIV.setVisibility(View.VISIBLE);
                            activityDataUpdationBinding.fareStationsHorizontalProgressBar.setProgress(0);
                            activityDataUpdationBinding.fareStationsPercentTV.setText("0 %");
                            activityDataUpdationBinding.fareStationsPB.setVisibility(View.GONE);
                            activityDataUpdationBinding.fareStationsTotalProgressTV.setText("Downloading failed");
                            MySingleton.getInstance().checkFareStationLastUpdate = true;
                        }



                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case "getRouteStations":
                try {
                    GetRouteStationsResponse getRouteStationsResponse = (GetRouteStationsResponse) response.body();
                    Log.e("data_response_getRouteStations", commonMethods.getJsonFormat(getRouteStationsResponse));
                    if (getRouteStationsResponse.getCode().equals("100")) {
                        RoutesStationDao routesStationDao = db.routesStationDao();
                        routesStationDao.deleteRoutesStation();
                        for (int i = 0; i < getRouteStationsResponse.getRouteStations().size(); i++) {
                            RoutesStationModel routesStationModel = new RoutesStationModel();
                            routesStationModel.setId(i + 1);
                            routesStationModel.setRouteId(getRouteStationsResponse.getRouteStations().get(i).getRoutId());
                            routesStationModel.setFromStationId(getRouteStationsResponse.getRouteStations().get(i).getFrStonId());
                            routesStationModel.setFromStationName(getRouteStationsResponse.getRouteStations().get(i).getFrStonName());
                            routesStationModel.setToStationId(getRouteStationsResponse.getRouteStations().get(i).getToStonId());
                            routesStationModel.setToStationName(getRouteStationsResponse.getRouteStations().get(i).getToStonName());
                            routesStationModel.setStation_seq(getRouteStationsResponse.getRouteStations().get(i).getRoutId());
                            routesStationModel.setDistance_km(0);
                            routesStationDao.insertRecord(routesStationModel);
                        }

                        if (routesStationDao.getRoutesStation().size()==getRouteStationsResponse.getRouteStations().get(0).getCountt()){
                            MySingleton.getInstance().checkRouteStationLastUpdate = false;
                            activityDataUpdationBinding.routesStationsPercentTV.setText("20 %");
                            activityDataUpdationBinding.routesStationsHorizontalProgressBar.setProgress(20);
                            activityDataUpdationBinding.routesStationsPercentTV.setText("50 %");
                            activityDataUpdationBinding.routesStationsHorizontalProgressBar.setProgress(50);
                            activityDataUpdationBinding.routesStationsPercentTV.setText("100 %");
                            activityDataUpdationBinding.routesStationsHorizontalProgressBar.setProgress(100);
                            activityDataUpdationBinding.routesStationsTotalProgressTV.setText("Downloaded");
                            db.dataLastDateUpdationDateModel().updateRouteStationLastUpdate(1, getRouteStationsResponse.getRouteStations().get(0).getRoutestationlastdate());
                            activityDataUpdationBinding.routesStationsDoneIV.setVisibility(View.VISIBLE);
                            activityDataUpdationBinding.routesStationsRetryIV.setVisibility(View.GONE);
                            activityDataUpdationBinding.routesStationsPB.setVisibility(View.GONE);
                            checkCondition();
                        }else {
                            activityDataUpdationBinding.routesStationsDoneIV.setVisibility(View.INVISIBLE);
                            activityDataUpdationBinding.routesStationsRetryIV.setVisibility(View.VISIBLE);
                            MySingleton.getInstance().checkRouteStationLastUpdate = true;
                            activityDataUpdationBinding.routesStationsPB.setVisibility(View.GONE);

                            activityDataUpdationBinding.routesStationsPercentTV.setText("0 %");
                            activityDataUpdationBinding.routesStationsHorizontalProgressBar.setProgress(0);
                            activityDataUpdationBinding.routesStationsTotalProgressTV.setText("Downloading failed");
                        }

                        activityDataUpdationBinding.fareStationsPercentTV.setText("30 %");
                        activityDataUpdationBinding.fareStationsHorizontalProgressBar.setProgress(30);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


            case "getExpensesEarnings":
                try {
                    GetExpensesEarnings getExpensesEarnings = (GetExpensesEarnings) response.body();
                    Log.e("data_response_getConcession", commonMethods.getJsonFormat(getExpensesEarnings));
                    ExpensesEarningsDao expensesEarningsDao = db.expensesEarningsDao();
                    expensesEarningsDao.deleteExpensesEarning();

                    if(getExpensesEarnings.getCode().equals("100")){
                        for (int i = 0; i < getExpensesEarnings.getExpensesEarnings().size(); i++) {
                            expensesEarningsDao.insertRecord(getExpensesEarnings.getExpensesEarnings().get(i));
                        }
                        if (expensesEarningsDao.getExpensesEarning().size()==getExpensesEarnings.getExpensesEarnings().size()){
                            activityDataUpdationBinding.expensesEarningsHorizontalProgressBar.setProgress(20);
                            activityDataUpdationBinding.expensesEarningsPercentTV.setText("20 %");
                            activityDataUpdationBinding.expensesEarningsHorizontalProgressBar.setProgress(50);
                            activityDataUpdationBinding.expensesEarningsPercentTV.setText("50 %");
                            activityDataUpdationBinding.expensesEarningsHorizontalProgressBar.setProgress(100);
                            activityDataUpdationBinding.expensesEarningsPercentTV.setText("100 %");
                            activityDataUpdationBinding.expensesEarningsTotalProgressTV.setText("Downloaded");
                            MySingleton.getInstance().checkExpensesEarnings = false;
                            activityDataUpdationBinding.expensesEarningsDoneIV.setVisibility(View.VISIBLE);
                            activityDataUpdationBinding.expensesEarningsRetryIV.setVisibility(View.GONE);
                            activityDataUpdationBinding.expensesEarningsPB.setVisibility(View.GONE);
                            checkCondition();
                        }else {
                            activityDataUpdationBinding.expensesEarningsDoneIV.setVisibility(View.INVISIBLE);
                            activityDataUpdationBinding.expensesEarningsRetryIV.setVisibility(View.VISIBLE);
                            activityDataUpdationBinding.expensesEarningsPB.setVisibility(View.GONE);
                            MySingleton.getInstance().checkExpensesEarnings = true;
                            activityDataUpdationBinding.expensesEarningsPercentTV.setText("0 %");
                            activityDataUpdationBinding.expensesEarningsHorizontalProgressBar.setProgress(0);
                            activityDataUpdationBinding.expensesEarningsTotalProgressTV.setText("Downloading failed");
                        }

                    }else {
                        Toast.makeText(this, "Data not found", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void checkCondition() {
        if (!MySingleton.getInstance().checkRouteLastUpdate && !MySingleton.getInstance().checkConcessionLastUpdate && !MySingleton.getInstance().checkFareStationLastUpdate && !MySingleton.getInstance().checkRouteStationLastUpdate) {
            Toast.makeText(this, "Data downloaded successfully ", Toast.LENGTH_SHORT).show();
            activityDataUpdationBinding.nextBT.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.color_primary, null));
        } else {
            activityDataUpdationBinding.nextBT.setBackgroundColor(Color.GRAY);
        }
    }

    @Override
    public void onError(Throwable t, String key) {
        switch (key) {
            case "getRoute":
                Log.e("getRouteConcession", t.getLocalizedMessage());
                break;

            case "getConcession":
                Log.e("getConcession", t.getLocalizedMessage());
                break;

            case "getFareStations":
                Log.e("getFareStations", t.getLocalizedMessage());
                break;

            case "getRouteStations":
                Log.e("getRouteStations", t.getLocalizedMessage());
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_BT:

                Intent operatorLoginIntent = new Intent(this, OperatorLoginActivity.class);
                startActivity(operatorLoginIntent);
                break;
        }
    }
}