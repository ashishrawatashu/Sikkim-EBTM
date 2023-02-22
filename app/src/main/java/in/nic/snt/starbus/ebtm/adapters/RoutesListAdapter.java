package in.nic.snt.starbus.ebtm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.nic.snt.starbus.ebtm.adaptersOnClicks.RoutesListOnClick;
import in.nic.snt.starbus.ebtm.databinding.CustomRouteListBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.RoutesModel;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.TripsModel;

public class RoutesListAdapter extends  RecyclerView.Adapter<RoutesListAdapter.RoutesListAdapterViewHolder> implements Filterable {

    Context context;
    List<RoutesModel> routesModelList;
    List<RoutesModel> routesModelListFilter;
    private RoutesListOnClick routesListOnClick;


    public RoutesListAdapter(Context context, List<RoutesModel> routesModelList, RoutesListOnClick routesListOnClick) {
        this.context            = context;
        this.routesModelList    = routesModelList;
        this.routesListOnClick  = routesListOnClick;
        routesModelListFilter   = new ArrayList<>(routesModelList);
    }

    @NonNull
    @Override
    public RoutesListAdapter.RoutesListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoutesListAdapter.RoutesListAdapterViewHolder(CustomRouteListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RoutesListAdapter.RoutesListAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {




        holder.customRouteListBinding.tvRouteName.setText(routesModelList.get(position).getRouteName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (routesListOnClick != null) {
                    routesListOnClick.selectRoute(position,routesModelList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return routesModelList.size();
    }

    @Override
    public Filter getFilter() {
        return routesList;
    }

    private Filter routesList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RoutesModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(routesModelListFilter);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (RoutesModel item : routesModelListFilter) {
                    if (item.getRouteName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            routesModelList.clear();
            routesModelList.addAll((List<RoutesModel>) results.values);
            notifyDataSetChanged();

        }
    };

    public class RoutesListAdapterViewHolder extends RecyclerView.ViewHolder {
        CustomRouteListBinding customRouteListBinding;
        public RoutesListAdapterViewHolder(CustomRouteListBinding customRouteListBinding) {
            super(customRouteListBinding.getRoot());
            this.customRouteListBinding = customRouteListBinding;

        }
    }
}