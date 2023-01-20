package in.nic.snt.starbus.ebtm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.StationOnClickInDashBoard;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.StationsOnClicks;
import in.nic.snt.starbus.ebtm.databinding.StationListBinding;
import in.nic.snt.starbus.ebtm.databinding.TicketBookingStationListItemBinding;
import in.nic.snt.starbus.ebtm.models.StationsModel;

public class TicketBookingFromStationAdapter extends  RecyclerView.Adapter<TicketBookingFromStationAdapter.StationListAdapterViewHolder>{

    Context context;
    List<StationsModel> stationsModelList;
    StationOnClickInDashBoard stationOnClickInDashBoard;

    public TicketBookingFromStationAdapter(Context context, List<StationsModel> stationsModelList, StationOnClickInDashBoard stationOnClickInDashBoard) {
        this.context = context;
        this.stationsModelList = stationsModelList;
        this.stationOnClickInDashBoard = stationOnClickInDashBoard;
    }

    @NonNull
    @Override
    public TicketBookingFromStationAdapter.StationListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketBookingFromStationAdapter.StationListAdapterViewHolder(TicketBookingStationListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TicketBookingFromStationAdapter.StationListAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.ticketBookingStationListItemBinding.stationNameTV.setText(stationsModelList.get(position).getStationName());

        if(stationsModelList.get(position).isSelected()){
            holder.ticketBookingStationListItemBinding.stationNameTV.setTextColor(Color.WHITE);
            holder.ticketBookingStationListItemBinding.stationNameCL.setBackground(ContextCompat.getDrawable(context, R.drawable.select_station_bg));
        }else {
            holder.ticketBookingStationListItemBinding.stationNameTV.setTextColor(Color.BLACK);
            holder.ticketBookingStationListItemBinding.stationNameCL.setBackground(ContextCompat.getDrawable(context, R.drawable.button_border));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stationOnClickInDashBoard != null) {
                    stationOnClickInDashBoard.selectFromStation(position,stationsModelList);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationsModelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    public class StationListAdapterViewHolder extends RecyclerView.ViewHolder {
        TicketBookingStationListItemBinding ticketBookingStationListItemBinding;
        public StationListAdapterViewHolder(TicketBookingStationListItemBinding ticketBookingStationListItemBinding) {
            super(ticketBookingStationListItemBinding.getRoot());
            this.ticketBookingStationListItemBinding = ticketBookingStationListItemBinding;

        }
    }
}