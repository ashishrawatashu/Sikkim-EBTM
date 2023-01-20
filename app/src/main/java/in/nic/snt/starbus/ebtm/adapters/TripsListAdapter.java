package in.nic.snt.starbus.ebtm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.nic.snt.starbus.ebtm.adaptersOnClicks.TripsListOnClick;
import in.nic.snt.starbus.ebtm.databinding.CustomTripListBinding;
import in.nic.snt.starbus.ebtm.databinding.TripsListLayoutBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.TripsModel;

public class TripsListAdapter extends RecyclerView.Adapter<TripsListAdapter.TripsListAdapterViewHolder>{

    Context context;
    List<TripsModel> tripsModelList;
    TripsListOnClick tripsListOnClick;

    public TripsListAdapter(Context context, List<TripsModel> tripsModelList, TripsListOnClick tripsListOnClick) {
        this.context = context;
        this.tripsModelList = tripsModelList;
        this.tripsListOnClick = tripsListOnClick;
    }

    @NonNull
    @Override
    public TripsListAdapter.TripsListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TripsListAdapter.TripsListAdapterViewHolder(CustomTripListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TripsListAdapter.TripsListAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.customTripListBinding.tvSTRPID.setText(tripsModelList.get(position).getStrpId().toString());
        holder.customTripListBinding.tvFromStationId.setText(tripsModelList.get(position).getFrStonId().toString());
        holder.customTripListBinding.tvToStationId.setText(tripsModelList.get(position).getToStonId().toString());
        holder.customTripListBinding.tvStations.setText(tripsModelList.get(position).getFrStonName()+" - "+tripsModelList.get(position).getToStonName());
        holder.customTripListBinding.tvStartTime.setText(tripsModelList.get(position).getStartTime());


        String direction =tripsModelList.get(position).getTripDirection();
        if (direction.equalsIgnoreCase("I")) {
            holder.customTripListBinding.tvDirection.setText("Inward, ");
        }
        else {
            holder.customTripListBinding.tvDirection.setText("Forward, ");
        }

        String onlineYN = tripsModelList.get(position).getIsonlineyn();
        if (onlineYN.equalsIgnoreCase("Y")) {
            holder.customTripListBinding.tvOnlineYN.setText("Yes");
        }
        else {
            holder.customTripListBinding.tvOnlineYN.setText("No");
        }


        String status = tripsModelList.get(position).getTripStatus();
        if (status.equalsIgnoreCase("C")) {
            holder.customTripListBinding.tvStatus.setText("Complete");
            holder.customTripListBinding.tvStatus.setTextColor(Color.GREEN);
            holder.customTripListBinding.startTripBT.setVisibility(View.GONE);
        }
        else if (status.equalsIgnoreCase("R")) {
            holder.customTripListBinding.tvStatus.setText("On Route");
            holder.customTripListBinding.tvStatus.setTextColor(Color.BLACK);
            holder.customTripListBinding.startTripBT.setVisibility(View.GONE);
        } else if (status.equalsIgnoreCase("D")) {
            holder.customTripListBinding.tvStatus.setText("Skipped");
            holder.customTripListBinding.tvStatus.setTextColor(Color.BLACK);
            holder.customTripListBinding.startTripBT.setVisibility(View.GONE);
        }else {
            holder.customTripListBinding.tvStatus.setText("Pending");
            holder.customTripListBinding.tvStatus.setTextColor(Color.RED);
            holder.customTripListBinding.startTripBT.setVisibility(View.VISIBLE);
        }




        holder.customTripListBinding.startTripBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tripsListOnClick != null) {
                    tripsListOnClick.selectTrip(position, tripsModelList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tripsModelList.size();
    }

    public class TripsListAdapterViewHolder extends RecyclerView.ViewHolder {
        CustomTripListBinding customTripListBinding;
        public TripsListAdapterViewHolder(CustomTripListBinding customTripListBinding) {
            super(customTripListBinding.getRoot());
            this.customTripListBinding = customTripListBinding;

        }
    }
}
