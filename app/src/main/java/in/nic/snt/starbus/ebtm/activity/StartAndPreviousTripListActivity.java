package in.nic.snt.starbus.ebtm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import in.nic.snt.starbus.ebtm.adapters.PagerAdapter;
import in.nic.snt.starbus.ebtm.databinding.ActivityStartAndPreviousTripListBinding;

public class StartAndPreviousTripListActivity extends AppCompatActivity {

    ActivityStartAndPreviousTripListBinding startAndPreviousTripListBinding;
    PagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startAndPreviousTripListBinding = ActivityStartAndPreviousTripListBinding.inflate(getLayoutInflater());
        View view = startAndPreviousTripListBinding.getRoot();
        setContentView(view);

        FragmentManager fm = getSupportFragmentManager();
        pagerAdapter = new PagerAdapter(fm,getLifecycle());
        startAndPreviousTripListBinding.viewPager.setAdapter(pagerAdapter);

        startAndPreviousTripListBinding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                startAndPreviousTripListBinding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        startAndPreviousTripListBinding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                startAndPreviousTripListBinding.tabLayout.selectTab(startAndPreviousTripListBinding.tabLayout.getTabAt(position));
            }

        });

    }
}