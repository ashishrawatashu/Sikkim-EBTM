package in.nic.snt.starbus.ebtm.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import in.nic.snt.starbus.ebtm.databinding.ActivityTiDashBinding;

public class TiDashActivity extends AppCompatActivity {
    private ActivityTiDashBinding activityTiDashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTiDashBinding = ActivityTiDashBinding.inflate(getLayoutInflater());
        View view = activityTiDashBinding.getRoot();
        setContentView(view);

    }
}