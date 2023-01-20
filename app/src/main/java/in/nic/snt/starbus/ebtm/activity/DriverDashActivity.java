package in.nic.snt.starbus.ebtm.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import in.nic.snt.starbus.ebtm.databinding.ActivityDriverDashBinding;

public class DriverDashActivity extends AppCompatActivity {

    private ActivityDriverDashBinding activityDriverDashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDriverDashBinding = ActivityDriverDashBinding.inflate(getLayoutInflater());
        View view = activityDriverDashBinding.getRoot();
        setContentView(view);



    }
}