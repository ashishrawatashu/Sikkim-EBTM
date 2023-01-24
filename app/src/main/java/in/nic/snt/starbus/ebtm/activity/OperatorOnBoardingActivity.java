package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adapters.SliderAdapter;
import in.nic.snt.starbus.ebtm.databinding.ActivityCreateOwnTripBinding;
import in.nic.snt.starbus.ebtm.databinding.ActivityOperatorOnBoardingBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentUserLoginModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.OnBoardingStatusModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentUserLoginStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.OnBoardingStatusDao;
import in.nic.snt.starbus.ebtm.utils.CommonMethods;

public class OperatorOnBoardingActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityOperatorOnBoardingBinding activityOperatorOnBoardingBinding;
    private AppDatabase db;
    CommonMethods commonMethods;
    SliderAdapter sliderAdapter;
    
    TextView[] dots;
    int mCurrentPage;

    String userName="" , userId="";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOperatorOnBoardingBinding = ActivityOperatorOnBoardingBinding.inflate(getLayoutInflater());
        View view = activityOperatorOnBoardingBinding.getRoot();
        setContentView(view);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, getString(R.string.Sikkim_local_database_name)).allowMainThreadQueries().build();
        commonMethods = new CommonMethods(this);

        Intent intent = getIntent();



        userName = intent.getStringExtra("userName");
        userId = intent.getStringExtra("userId");

        initMethod();

    }

    private void initMethod() {


        sliderAdapter = new SliderAdapter(this);
        activityOperatorOnBoardingBinding.slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);


        activityOperatorOnBoardingBinding.slideViewPager.addOnPageChangeListener(viewListner);

        activityOperatorOnBoardingBinding.progressBarOnboarding.setVisibility(View.GONE);

        activityOperatorOnBoardingBinding.startButton.setVisibility(View.GONE);
        activityOperatorOnBoardingBinding.nextBtn.setOnClickListener(this);
        activityOperatorOnBoardingBinding.nextText.setOnClickListener(this);
        activityOperatorOnBoardingBinding.prevBtn.setOnClickListener(this);
        activityOperatorOnBoardingBinding.startButton.setOnClickListener(this);
        
    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;
            if (position == 0) {
                activityOperatorOnBoardingBinding.nextBtn.setVisibility(View.VISIBLE);
                activityOperatorOnBoardingBinding.nextText.setVisibility(View.VISIBLE);
//                activityOperatorOnBoardingBinding.prevBtn.setVisibility(View.GONE);
            } else if (position == dots.length - 1) {
//                 activityOperatorOnBoardingBinding.prevBtn.setVisibility(View.VISIBLE);
                activityOperatorOnBoardingBinding.nextText.setText("Done");
                activityOperatorOnBoardingBinding.startButton.setVisibility(View.VISIBLE);
                activityOperatorOnBoardingBinding.nextText.setVisibility(View.GONE);
                activityOperatorOnBoardingBinding.nextBtn.setVisibility(View.GONE);
            } else {
//                activityOperatorOnBoardingBinding.nextBtn.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.mutton_icon));
                activityOperatorOnBoardingBinding.nextBtn.setVisibility(View.VISIBLE);
                activityOperatorOnBoardingBinding.nextText.setText("Next");
                activityOperatorOnBoardingBinding.startButton.setVisibility(View.GONE);
                activityOperatorOnBoardingBinding.nextText.setVisibility(View.VISIBLE);
                activityOperatorOnBoardingBinding.nextText.setVisibility(View.VISIBLE);
//                activityOperatorOnBoardingBinding.prevBtn.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    
    private void addDotsIndicator(int pos) {
        dots = new TextView[3];
        activityOperatorOnBoardingBinding.dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            activityOperatorOnBoardingBinding.dotsLayout.addView(dots[i]);
            if (i == pos) {
                dots[pos].setTextColor(ContextCompat.getColor(this, R.color.color_primary));
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextBtn:
                if (mCurrentPage == 2) {
                    activityOperatorOnBoardingBinding.nextBtn.setEnabled(false);
                     activityOperatorOnBoardingBinding.prevBtn.setEnabled(false);
                }
                activityOperatorOnBoardingBinding.slideViewPager.setCurrentItem(mCurrentPage + 1);
                break;

            case R.id.next_text:
                if (mCurrentPage == 2) {
                    activityOperatorOnBoardingBinding.nextBtn.setEnabled(false);
                     activityOperatorOnBoardingBinding.prevBtn.setEnabled(false);
                }
                 activityOperatorOnBoardingBinding.slideViewPager.setCurrentItem(mCurrentPage + 1);
                break;
            case R.id.prevBtn:


                break;

            case R.id.start_button:
                insertUserLoginStatusData();

                //insertUserOnBoardingStatus

                OnBoardingStatusDao onBoardingStatusDao = db.onBoardingStatusDao();

                OnBoardingStatusModel onBoardingStatus = new OnBoardingStatusModel();
                onBoardingStatus.setLoginStatus("Y");
                onBoardingStatus.setUserType("O");
                onBoardingStatus.setId(1);

                onBoardingStatusDao.insertRecord(onBoardingStatus);

                Intent wayBillAssignmentIntent = new Intent(this, WayBillAssignmentActivity.class);
                startActivity(wayBillAssignmentIntent);

                break;
        }
    }

    private void insertUserLoginStatusData() {
        CurrentUserLoginStatusDao currentUserLoginStatusDao = db.currentUserLoginStatusDao();

        //delete user table
        currentUserLoginStatusDao.deleteUserLoginTable();

        CurrentUserLoginModel currentUserLoginModel = new CurrentUserLoginModel();
        currentUserLoginModel.setName(userName);
        currentUserLoginModel.setUserName(userId);
        currentUserLoginModel.setUserRole("O");
        currentUserLoginModel.setLoginStatus("Y");

        //insert user table
        currentUserLoginStatusDao.insertLoginRecord(currentUserLoginModel);

    }
}