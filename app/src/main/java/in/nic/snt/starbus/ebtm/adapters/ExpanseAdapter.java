package in.nic.snt.starbus.ebtm.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.nic.snt.starbus.ebtm.R;
import in.nic.snt.starbus.ebtm.adaptersOnClicks.ExpenseListOnClick;
import in.nic.snt.starbus.ebtm.databinding.CustomExpanseListBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpensesEarningModel;

public class ExpanseAdapter extends RecyclerView.Adapter<ExpanseAdapter.ExpenseListAdapterViewHolder>{

    Context context;
    List<ExpensesEarningModel> expenseModelsList;
    ExpenseListOnClick expenseListOnClick;
    AlertDialog.Builder builder;


    public ExpanseAdapter(Context context, List<ExpensesEarningModel> expenseModels, ExpenseListOnClick expenseListOnClick) {
        this.context = context;
        this.expenseModelsList = expenseModels;
        this.expenseListOnClick = expenseListOnClick;
    }

    @NonNull
    @Override
    public ExpanseAdapter.ExpenseListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExpanseAdapter.ExpenseListAdapterViewHolder(CustomExpanseListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExpanseAdapter.ExpenseListAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {



        if( expenseModelsList.get(position).getType().equalsIgnoreCase("EX")) {
            holder.customExpanseListBinding.tvExpenseName.setText(expenseModelsList.get(position).getName().toString());
        }
        holder.customExpanseListBinding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expenseListOnClick != null) {

                    /*int v = (holder.customExpanseListBinding.llExpandDetails.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                    TransitionManager.beginDelayedTransition(holder.customExpanseListBinding.card, new AutoTransition());
                    holder.customExpanseListBinding.llExpandDetails.setVisibility(v);*/

                    //holder.customExpanseListBinding.imgDownArrow.setRotation(holder.customExpanseListBinding.imgDownArrow.getRotation() + 180);
                    expenseListOnClick.selectCard(position, expenseModelsList.get(position));

                }
            }




        });


    }




    @Override
    public int getItemCount() {

        return expenseModelsList.size();
    }


    public class ExpenseListAdapterViewHolder extends  RecyclerView.ViewHolder {
        CustomExpanseListBinding customExpanseListBinding;
        public ExpenseListAdapterViewHolder(CustomExpanseListBinding expenseListBinding) {
            super(expenseListBinding.getRoot());
            this.customExpanseListBinding = expenseListBinding;
        }


    }



}
