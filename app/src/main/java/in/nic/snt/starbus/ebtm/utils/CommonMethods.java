package in.nic.snt.starbus.ebtm.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.room.Room;

import com.google.gson.Gson;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentTripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentTripsDao;

public class CommonMethods {

    Context context;
    private Dialog progressBarDialog;
    static String OFFICE_ID = "1011140000";
    public AppDatabase appDatabase;


    public CommonMethods(Context context) {
        this.context = context;
    }

    @SuppressLint("MissingPermission")
    public final boolean isInternetOn() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {


            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

            }
            return true;
        } else {
            return  false;
            // not connected to the internet
        }
    }


    public void DialogInternet() {
        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setMessage(R.string.CheckInternet);
        ad.setCancelable(true);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ad.show();
    }

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyy HH:mm a");
        Date date = new Date();
        return dateFormat.format(date);
    }


    public  void pleaseTryAgainToast(Context context){
        Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show();
    }


   public void getDataBaseAccess(Context context){
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, context.getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();
   }


    public boolean getActiveTrip(Context context){
        AppDatabase db;
        db = Room.databaseBuilder(context, AppDatabase.class, context.getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();
        CurrentTripsDao currentTripsDao = db.currentTripsDao();
        CurrentTripsModel currentTripsModel = currentTripsDao.getIncompleteTripData();

        if(currentTripsModel==null){
            return false;
        }else if(currentTripsModel.getCompleteYN().equals("N")){
            return true;
        }

        return  false;



    }

    public int getActiveCode(Context context){
        AppDatabase db;
        db = Room.databaseBuilder(context, AppDatabase.class, context.getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();
        CurrentTripsDao currentTripsDao = db.currentTripsDao();
        CurrentTripsModel currentTripsModel = currentTripsDao.getIncompleteTripData();


        return currentTripsModel.getTripNo();





    }


    public void showCustomProgressBarDialog(Context context) {
        try {
            progressBarDialog = new Dialog(context);
            progressBarDialog.setContentView(R.layout.custom_spin_progress_dialog);
            progressBarDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressBarDialog.setCancelable(false);
            progressBarDialog.setCanceledOnTouchOutside(false);
            progressBarDialog.show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            Window window = progressBarDialog.getWindow();
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void customProgressDismiss() {
        progressBarDialog.dismiss();
    }


    public String getJsonFormat(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }


    public  String getShaOne(String password){
        MessageDigest digest=null;
        String hash;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            digest.update(password.getBytes());
            hash = bytesToHexString(digest.digest());
            return hash;
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
            return null;
        }

    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public String getCurrentTimeAndDate()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        String formattedDateTime = sdf.format(calendar.getTime());
        System.out.println("Formatted date and time: " + formattedDateTime);


       /* int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Note: January is 0
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        String currentDateTime = String.format("%04d-%02d-%02d %02d:%02d:%02d", year, month, dayOfMonth, hour, minute, second);

        System.out.println("Current date and time: " + currentDateTime);*/
        return formattedDateTime;
    }


}
