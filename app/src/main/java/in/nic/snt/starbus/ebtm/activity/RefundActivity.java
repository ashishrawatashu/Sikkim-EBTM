package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adapters.RefundAdapter;
import in.nic.snt.starbus.ebtm.adapters.TripsListAdapter;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.RefundListOnClick;
import in.nic.snt.starbus.ebtm.databinding.ActivityConductorDashBinding;
import in.nic.snt.starbus.ebtm.databinding.ActivityRefundBinding;
import in.nic.snt.starbus.ebtm.databinding.RefundConfirmationBottomSheetBinding;

public class RefundActivity extends AppCompatActivity implements RefundListOnClick {

    ActivityRefundBinding activityRefundBinding;
    
    RefundConfirmationBottomSheetBinding refundConfirmationBottomSheetBinding;
    BottomSheetDialog refundConfirmationBottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRefundBinding = ActivityRefundBinding.inflate(getLayoutInflater());
        View view = activityRefundBinding.getRoot();
        setContentView(view);



        getRefundTickets();

    }

    private void getRefundTickets() {

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        activityRefundBinding.refundListRV.setLayoutManager(linearLayoutManager);
        RefundAdapter refundAdapter = new RefundAdapter(this,this);
        activityRefundBinding.refundListRV.setAdapter(refundAdapter);

    }

    @Override
    public void giveRefund(int position) {
        showRefundConfirmationDialog();
    }

    private void showRefundConfirmationDialog() {
        refundConfirmationBottomSheetDialog = new BottomSheetDialog(this);
        refundConfirmationBottomSheetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        refundConfirmationBottomSheetDialog.setCancelable(true);
        refundConfirmationBottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        refundConfirmationBottomSheetBinding = refundConfirmationBottomSheetBinding.inflate(LayoutInflater.from(this));
        refundConfirmationBottomSheetDialog.setContentView(refundConfirmationBottomSheetBinding.getRoot());


        refundConfirmationBottomSheetDialog.show();
        Window window = refundConfirmationBottomSheetDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
}