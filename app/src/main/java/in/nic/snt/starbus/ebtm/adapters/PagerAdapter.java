package in.nic.snt.starbus.ebtm.adapters;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import in.nic.snt.starbus.ebtm.fragments.PreviousTripsFragment;
import in.nic.snt.starbus.ebtm.fragments.StartTripFragment;

public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new StartTripFragment();
            case 1:
                return new PreviousTripsFragment();
            default:
                return new StartTripFragment();

        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
