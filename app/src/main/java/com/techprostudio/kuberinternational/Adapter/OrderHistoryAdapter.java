package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.Activity.OrderConfirmationActivity;
import com.techprostudio.kuberinternational.Activity.OrderDetailsActivity;
import com.techprostudio.kuberinternational.Activity.OrderHistoryActivity;
import com.techprostudio.kuberinternational.Model.OrderHistoryModel;
import com.techprostudio.kuberinternational.Model.PaymentModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder>{
private Context context;
private List<OrderHistoryModel> modelList;

public static class MyViewHolder extends RecyclerView.ViewHolder{
    RelativeLayout gotoorderDetails;
    public MyViewHolder( View view) {
        super(view);
        gotoorderDetails=view.findViewById(R.id.gotoorderDetails);


    }
}

    public OrderHistoryAdapter(Context context, List<OrderHistoryModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public OrderHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.orderhistory,parent,false);
        return new OrderHistoryAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderHistoryAdapter.MyViewHolder holder, int position) {
    holder.gotoorderDetails.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context, OrderDetailsActivity.class));

        }
    });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
