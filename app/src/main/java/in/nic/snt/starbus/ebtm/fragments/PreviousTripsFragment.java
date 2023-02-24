package in.nic.snt.starbus.ebtm.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.databinding.FragmentPreviousTripsBinding;
import in.nic.snt.starbus.ebtm.databinding.FragmentStartTripBinding;

public class PreviousTripsFragment extends Fragment {

    FragmentPreviousTripsBinding fragmentPreviousTripsBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentPreviousTripsBinding = FragmentPreviousTripsBinding.inflate(inflater, container, false);
        View view = fragmentPreviousTripsBinding.getRoot();


        return view;

    }
}