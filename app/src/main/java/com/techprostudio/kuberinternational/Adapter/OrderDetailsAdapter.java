package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.Activity.OrderDetailsActivity;
import com.techprostudio.kuberinternational.Model.OrderDetailsModel;
import com.techprostudio.kuberinternational.Model.OrderHistoryModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder>{
private Context context;
private List<OrderDetailsModel> modelList;

public static class MyViewHolder extends RecyclerView.ViewHolder{
    public MyViewHolder( View view) {
        super(view);


    }
}

    public OrderDetailsAdapter(Context context, List<OrderDetailsModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public OrderDetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.orderdetails,parent,false);
        return new OrderDetailsAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderDetailsAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

