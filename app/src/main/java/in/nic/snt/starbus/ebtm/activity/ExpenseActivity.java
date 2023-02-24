package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;


import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adapters.ExpanseAdapter;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.ExpenseListOnClick;
import in.nic.snt.starbus.ebtm.databinding.ActivityExpenseBinding;
import in.nic.snt.starbus.ebtm.databinding.CustomDilogBoxBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpenseDataModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpensesEarningModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.ExpenseDataDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.ExpensesEarningsDao;
import in.nic.snt.starbus.ebtm.utils.CommonMethods;

public class ExpenseActivity extends AppCompatActivity implements ExpenseListOnClick {

    ActivityExpenseBinding expenseBinding;
    AppDatabase db;
    CustomDilogBoxBinding customDilogBoxBinding;
    Dialog dialog;

    private String latitude, longitude;
    private LocationManager locationManager;
    private CommonMethods commonMethods;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        expenseBinding = ActivityExpenseBinding.inflate(getLayoutInflater());
        View view = expenseBinding.getRoot();
        setContentView(view);
        commonMethods = new CommonMethods(this);


        builder = new AlertDialog.Builder(this);

        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getCurrentLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();
        ExpensesEarningsDao expensesEarningsDao = db.expensesEarningsDao();

        List<ExpensesEarningModel> expenseDataList = new ArrayList<>();

        expenseDataList = expensesEarningsDao.getExpenses();
        if(!expenseDataList.isEmpty()) {
            expenseBinding.listViewExpense.setAdapter(null);
            LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
            expenseBinding.listViewExpense.setLayoutManager(linearLayoutManager);
            ExpanseAdapter expanseAdapter = new ExpanseAdapter(ExpenseActivity.this, expenseDataList, this);
            expenseBinding.listViewExpense.setAdapter(expanseAdapter);
        }
        else
        {
            Toast.makeText(ExpenseActivity.this,"No Data",Toast.LENGTH_SHORT).show();
        }

    }


    public void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                 latitude = String.valueOf(location.getLatitude());
                 longitude = String.valueOf(location.getLongitude());
                Log.i("Location", "Latitude: " + latitude + " Longitude: " + longitude);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        // Check if the app has permission to access the user's location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            // Register the location listener to receive location updates
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }








    @Override
    public void selectCard(int position, ExpensesEarningModel expenseModel) {


        customDilogBoxBinding = CustomDilogBoxBinding.inflate(getLayoutInflater());
        dialog=new Dialog(ExpenseActivity.this);

        // set background transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(
                Color.TRANSPARENT
        ));
        // set view
        dialog.setContentView(customDilogBoxBinding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        customDilogBoxBinding.tvExpenseName.setText(expenseModel.getName());
        customDilogBoxBinding.btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        customDilogBoxBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(customDilogBoxBinding.etAmount.getText().toString().isEmpty())
                {
                    Toast.makeText(ExpenseActivity.this,"Enter Amount",Toast.LENGTH_LONG).show();
                   customDilogBoxBinding.etAmount.requestFocus();
                    return;

                }

                if(customDilogBoxBinding.etAmount.getText().toString().trim().equalsIgnoreCase("0"))
                {
                    Toast.makeText(ExpenseActivity.this,"Amount Should be greater then Zero",Toast.LENGTH_LONG).show();
                    customDilogBoxBinding.etAmount.requestFocus();
                    return;

                }
                if(customDilogBoxBinding.etRemark.getText().toString().isEmpty())
                {
                    Toast.makeText(ExpenseActivity.this,"Enter Remark",Toast.LENGTH_LONG).show();
                    customDilogBoxBinding.etRemark.requestFocus();
                    return;

                }

                if(customDilogBoxBinding.etRemark.getText().toString().trim().length() < 2)
                {
                    Toast.makeText(ExpenseActivity.this,"Remark length should be more then two alphabet",Toast.LENGTH_LONG).show();
                    customDilogBoxBinding.etRemark.requestFocus();
                    return;

                }

                Log.d("SaveExpenseData"," > "+expenseModel.getId()+" "+customDilogBoxBinding.etAmount.getText().toString().trim()+" "+customDilogBoxBinding.etRemark.getText().toString().trim()+" "+"Stage"+" "+latitude+" "+longitude+" "+commonMethods.getCurrentTimeAndDate());
                ExpenseDataDao expenseDataDao = db.expensesDataSave();
                expenseDataDao.insertRecord(new ExpenseDataModel("TripeCode" ,String.valueOf(expenseModel.getId()), customDilogBoxBinding.etAmount.getText().toString().trim(), customDilogBoxBinding.etRemark.getText().toString().trim(), "Pending", latitude, longitude, commonMethods.getCurrentTimeAndDate()));
                dialog.dismiss();
                showConfirmation("Expense details of "+expenseModel.getName()+" has saved");
            }
        });




        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }



    public void showConfirmation(String msg) {
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(ExpenseActivity.this);
        //alertDialogBuilder.setTitle(heading);
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        androidx.appcompat.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}