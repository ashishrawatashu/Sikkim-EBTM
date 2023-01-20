package in.nic.snt.starbus.ebtm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.nic.snt.starbus.ebtm.databinding.TripsListLayoutBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.TripsModel;

public class TripsAdapter extends  RecyclerView.Adapter<TripsAdapter.TripsAdapterViewHolder>{

    Context context;
    List<TripsModel> tripsModelList;



    public TripsAdapter(Context context, List<TripsModel> tripsModelList) {
        this.context = context;
        this.tripsModelList = tripsModelList;
    }

    @NonNull
    @Override
    public TripsAdapter.TripsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TripsAdapter.TripsAdapterViewHolder(TripsListLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TripsAdapter.TripsAdapterViewHolder holder, int position) {

        String serialNo = String.valueOf(position+1)+". ";
        holder.tripsListLayoutBinding.tripNoTV.setText(serialNo);
        holder.tripsListLayoutBinding.tripNameTV.setText(tripsModelList.get(position).getFrStonName()+" T) "+tripsModelList.get(position).getToStonName());

    }

    @Override
    public int getItemCount() {
        return tripsModelList.size();
    }

    public class TripsAdapterViewHolder extends RecyclerView.ViewHolder {
        TripsListLayoutBinding tripsListLayoutBinding;
        public TripsAdapterViewHolder(TripsListLayoutBinding tripsListLayoutBinding) {
            super(tripsListLayoutBinding.getRoot());
            this.tripsListLayoutBinding = tripsListLayoutBinding;

        }
    }
}
