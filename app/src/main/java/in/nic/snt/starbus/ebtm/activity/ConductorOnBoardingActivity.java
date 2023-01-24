package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adapters.SliderAdapter;
import in.nic.snt.starbus.ebtm.databinding.ActivityConductorOnBoardingBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.AppDatabase;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentTripsModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.CurrentUserLoginModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.OnBoardingStatusModel;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentTripsDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.CurrentUserLoginStatusDao;
import in.nic.snt.starbus.ebtm.roomDataBase.tablesQueries.OnBoardingStatusDao;
import in.nic.snt.starbus.ebtm.utils.CommonMethods;

public class ConductorOnBoardingActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityConductorOnBoardingBinding activityConductorOnBoardingBinding;
    CommonMethods commonMethods;
    AppDatabase db;

    SliderAdapter sliderAdapter;

    TextView[] dots;
    int mCurrentPage;

    String userName = "", userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConductorOnBoardingBinding = ActivityConductorOnBoardingBinding.inflate(getLayoutInflater());
        View view = activityConductorOnBoardingBinding.getRoot();
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
        activityConductorOnBoardingBinding.slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);


        activityConductorOnBoardingBinding.slideViewPager.addOnPageChangeListener(viewListner);

        activityConductorOnBoardingBinding.progressBarOnboarding.setVisibility(View.GONE);

        activityConductorOnBoardingBinding.startButton.setVisibility(View.GONE);
        activityConductorOnBoardingBinding.nextBtn.setOnClickListener(this);
        activityConductorOnBoardingBinding.nextText.setOnClickListener(this);
        activityConductorOnBoardingBinding.prevBtn.setOnClickListener(this);
        activityConductorOnBoardingBinding.startButton.setOnClickListener(this);
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
                activityConductorOnBoardingBinding.nextBtn.setVisibility(View.VISIBLE);
                activityConductorOnBoardingBinding.nextText.setVisibility(View.VISIBLE);
//                activityConductorOnBoardingBinding.prevBtn.setVisibility(View.GONE);
            } else if (position == dots.length - 1) {
//                 activityConductorOnBoardingBinding.prevBtn.setVisibility(View.VISIBLE);
                activityConductorOnBoardingBinding.nextText.setText("Done");
                activityConductorOnBoardingBinding.startButton.setVisibility(View.VISIBLE);
                activityConductorOnBoardingBinding.nextText.setVisibility(View.GONE);
                activityConductorOnBoardingBinding.nextBtn.setVisibility(View.GONE);
            } else {
//                activityConductorOnBoardingBinding.nextBtn.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.mutton_icon));
                activityConductorOnBoardingBinding.nextBtn.setVisibility(View.VISIBLE);
                activityConductorOnBoardingBinding.nextText.setText("Next");
                activityConductorOnBoardingBinding.startButton.setVisibility(View.GONE);
                activityConductorOnBoardingBinding.nextText.setVisibility(View.VISIBLE);
                activityConductorOnBoardingBinding.nextText.setVisibility(View.VISIBLE);
//                activityConductorOnBoardingBinding.prevBtn.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void addDotsIndicator(int pos) {
        dots = new TextView[3];
        activityConductorOnBoardingBinding.dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            activityConductorOnBoardingBinding.dotsLayout.addView(dots[i]);
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
                    activityConductorOnBoardingBinding.nextBtn.setEnabled(false);
                    activityConductorOnBoardingBinding.prevBtn.setEnabled(false);
                }
                activityConductorOnBoardingBinding.slideViewPager.setCurrentItem(mCurrentPage + 1);
                break;

            case R.id.next_text:
                if (mCurrentPage == 2) {
                    activityConductorOnBoardingBinding.nextBtn.setEnabled(false);
                    activityConductorOnBoardingBinding.prevBtn.setEnabled(false);
                }
                activityConductorOnBoardingBinding.slideViewPager.setCurrentItem(mCurrentPage + 1);
                break;
            case R.id.prevBtn:


                break;

            case R.id.start_button:
                insertUserLoginStatusData();

                //insertUserOnBoardingStatus
                OnBoardingStatusDao onBoardingStatusDao = db.onBoardingStatusDao();

                OnBoardingStatusModel onBoardingStatus = new OnBoardingStatusModel();
                onBoardingStatus.setLoginStatus("Y");
                onBoardingStatus.setUserType("C");
                onBoardingStatus.setId(2);
                onBoardingStatusDao.insertRecord(onBoardingStatus);

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