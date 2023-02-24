package in.nic.snt.starbus.ebtm.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import in.nic.snt.starbus.ebtm.adaptersOnClicks.RefundListOnClick;
import in.nic.snt.starbus.ebtm.databinding.CustomTripListBinding;
import in.nic.snt.starbus.ebtm.databinding.RefundListItemBinding;

public class RefundAdapter extends RecyclerView.Adapter<RefundAdapter.RefundAdapterViewHolder>{

    Context context;
    RefundListOnClick refundListOnClick;

    public RefundAdapter(Context context, RefundListOnClick refundListOnClick) {
        this.context = context;
        this.refundListOnClick = refundListOnClick;
    }

    @NonNull
    @Override
    public RefundAdapter.RefundAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RefundAdapter.RefundAdapterViewHolder(RefundListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RefundAdapter.RefundAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {





        holder.refundListItemBinding.refundTicketBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (refundListOnClick != null) {
                    refundListOnClick.giveRefund(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class RefundAdapterViewHolder extends RecyclerView.ViewHolder {
        RefundListItemBinding refundListItemBinding;
        public RefundAdapterViewHolder(@NonNull RefundListItemBinding refundListItemBinding) {
            super(refundListItemBinding.getRoot());
            this.refundListItemBinding = refundListItemBinding;

        }
    }
}
