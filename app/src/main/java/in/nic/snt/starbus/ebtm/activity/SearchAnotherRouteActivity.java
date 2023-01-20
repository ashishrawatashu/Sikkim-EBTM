package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adapters.RoutesListAdapter;
import in.nic.snt.starbus.ebtm.adapters.TripsListAdapter;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.RoutesListOnClick;
import in.nic.snt.starbus.ebtm.databinding.ActivityConductorDashBinding;
import in.nic.snt.starbus.ebtm.databinding.ActivitySearchAnotherRouteBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.RoutesDao;

public class SearchAnotherRouteActivity extends AppCompatActivity implements RoutesListOnClick , View.OnClickListener {


    private ActivitySearchAnotherRouteBinding activitySearchAnotherRouteBinding;
    private AppDatabase db;
    RoutesListAdapter routesListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchAnotherRouteBinding = ActivitySearchAnotherRouteBinding.inflate(getLayoutInflater());
        View view = activitySearchAnotherRouteBinding.getRoot();
        setContentView(view);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();

        activitySearchAnotherRouteBinding.backArrowIV.setOnClickListener(this);

        setRoutesList();

    }

    private void setRoutesList() {

        RoutesDao routesDao = db.routesDao();
        List<RoutesModel> routesModelList = routesDao.getRoutes();
        Log.e("ROutes", String.valueOf(routesModelList.size()));

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        activitySearchAnotherRouteBinding.routesListRV.setLayoutManager(linearLayoutManager);
        routesListAdapter = new RoutesListAdapter(this,routesModelList,this);
        activitySearchAnotherRouteBinding.routesListRV.setAdapter(routesListAdapter);
        routesListAdapter.notifyDataSetChanged();

        searchRoutes();


    }

    private void searchRoutes() {
        activitySearchAnotherRouteBinding.searchRoutesET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                routesListAdapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void selectRoute(int position, RoutesModel routesModel) {
        Intent createOwnTripIntent = new Intent(SearchAnotherRouteActivity.this,CreateOwnTripActivity.class);
        createOwnTripIntent.putExtra("routeId",routesModel.getRouteId());
        createOwnTripIntent.putExtra("routeName",routesModel.getRouteName());
        startActivity(createOwnTripIntent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.back_arrow_IV:
                onBackPressed();
                break;

        }
    }
}