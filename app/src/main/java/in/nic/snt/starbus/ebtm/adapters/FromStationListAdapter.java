package in.nic.snt.starbus.ebtm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.nic.snt.starbus.ebtm.adaptersOnClicks.StationsOnClicks;
import in.nic.snt.starbus.ebtm.databinding.StationListBinding;
import in.nic.snt.starbus.ebtm.models.StationsModel;

public class FromStationListAdapter extends  RecyclerView.Adapter<FromStationListAdapter.StationListAdapterViewHolder>{

    Context context;
    List<StationsModel> stationsModelList;
    StationsOnClicks stationsOnClicks;

    public FromStationListAdapter(Context context, List<StationsModel> stationsModelList, StationsOnClicks stationsOnClicks) {
        this.context = context;
        this.stationsModelList = stationsModelList;
        this.stationsOnClicks = stationsOnClicks;
    }

    @NonNull
    @Override
    public FromStationListAdapter.StationListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FromStationListAdapter.StationListAdapterViewHolder(StationListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FromStationListAdapter.StationListAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.stationListBinding.stationNameTV.setText(stationsModelList.get(position).getStationName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (stationsOnClicks != null) {
                    stationsOnClicks.selectFromStation(position,stationsModelList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stationsModelList.size();
    }

    public class StationListAdapterViewHolder extends RecyclerView.ViewHolder {
        StationListBinding stationListBinding;
        public StationListAdapterViewHolder(StationListBinding stationListBinding) {
            super(stationListBinding.getRoot());
            this.stationListBinding = stationListBinding;

        }
    }
}