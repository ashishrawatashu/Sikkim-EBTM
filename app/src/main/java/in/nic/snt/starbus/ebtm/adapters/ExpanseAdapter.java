package in.nic.snt.starbus.ebtm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.nic.snt.starbus.ebtm.adaptersOnClicks.ExpenseListOnClick;
import in.nic.snt.starbus.ebtm.databinding.CustomExpanseListBinding;
import in.nic.snt.starbus.ebtm.roomDataBase.entities.ExpensesEarningModel;

public class ExpanseAdapter extends RecyclerView.Adapter<ExpanseAdapter.ExpenseListAdapterViewHolder>{

    Context context;
    List<ExpensesEarningModel> expenseModelsList;
    ExpenseListOnClick expenseListOnClick;


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
        holder.customExpanseListBinding.tvExpenseName.setText(expenseModelsList.get(position).getName().toString());

        holder.customExpanseListBinding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expenseListOnClick != null) {

                    int v = (holder.customExpanseListBinding.llExpandDetails.getVisibility() == View.GONE) ? View.VISIBLE : View.GONE;
                    TransitionManager.beginDelayedTransition(holder.customExpanseListBinding.card, new AutoTransition());
                    holder.customExpanseListBinding.llExpandDetails.setVisibility(v);
                    holder.customExpanseListBinding.imgDownArrow.setRotation(holder.customExpanseListBinding.imgDownArrow.getRotation() + 180);

                }
            }
        });

        holder.customExpanseListBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expenseListOnClick != null) {
                    if(holder.customExpanseListBinding.etAmount.getText().toString().isEmpty())
                    {
                        Toast.makeText(context,"Enter Amount",Toast.LENGTH_LONG).show();
                        return;

                    }
                    if(holder.customExpanseListBinding.etAmount.getText().toString().trim().equalsIgnoreCase("0"))
                    {
                        Toast.makeText(context,"Amount Should be greater then Zero",Toast.LENGTH_LONG).show();
                        return;
                    }

                    if(holder.customExpanseListBinding.etRemark.getText().toString().isEmpty())
                    {
                        Toast.makeText(context,"Enter Remark",Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(holder.customExpanseListBinding.etAmount.getText().toString().trim().length() > 2 )
                    {
                        Toast.makeText(context,"Remark length should be more then two alphabet",Toast.LENGTH_LONG).show();
                        return;

                    }


                    expenseListOnClick.selectSaveExpense(position, expenseModelsList.get(position), holder.customExpanseListBinding.etAmount.getText().toString(), holder.customExpanseListBinding.etRemark.getText().toString().trim());
                    Toast.makeText(context,"Click",Toast.LENGTH_LONG).show();
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
