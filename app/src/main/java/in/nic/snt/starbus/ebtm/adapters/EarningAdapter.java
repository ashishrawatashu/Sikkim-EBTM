package in.nic.snt.starbus.ebtm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.nic.snt.starbus.ebtm.activity.EarningActivity;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.EarningListOnClick;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.ExpenseListOnClick;
import in.nic.snt.starbus.ebtm.databinding.CustomEarningListBinding;
import in.nic.snt.starbus.ebtm.databinding.CustomExpanseListBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpensesEarningModel;

public class EarningAdapter extends RecyclerView.Adapter<EarningAdapter.EarningListAdapterViewHolder>  {

    Context context;
    List<ExpensesEarningModel> earningModelList;
    EarningListOnClick earningListOnClick;


    public EarningAdapter(Context context, List<ExpensesEarningModel> expenseModels, EarningListOnClick earningListOnClick) {
        this.context = context;
        this.earningModelList = expenseModels;
        this.earningListOnClick = earningListOnClick;
    }

    @NonNull
    @Override
    public EarningAdapter.EarningListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EarningAdapter.EarningListAdapterViewHolder(CustomEarningListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EarningListAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if( earningModelList.get(position).getType().equalsIgnoreCase("ER")) {
            holder.customEarningListBinding.tvEarningName.setText(earningModelList.get(position).getName().toString());
        }


        holder.customEarningListBinding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (earningListOnClick != null) {

                  /*  int v = (holder.customEarningListBinding.llExpandDetails.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                    TransitionManager.beginDelayedTransition(holder.customEarningListBinding.card, new AutoTransition());
                    holder.customEarningListBinding.llExpandDetails.setVisibility(v);*/
                    earningListOnClick.selectCard(position, earningModelList.get(position));
                    //holder.customEarningListBinding.imgDownArrow.setRotation(holder.customEarningListBinding.imgDownArrow.getRotation() + 180);

                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return earningModelList.size();
    }


    public class EarningListAdapterViewHolder extends  RecyclerView.ViewHolder {
        CustomEarningListBinding customEarningListBinding;
        public EarningListAdapterViewHolder(CustomEarningListBinding earningListBinding) {
            super(earningListBinding.getRoot());
            this.customEarningListBinding = earningListBinding;
        }
    }

}
