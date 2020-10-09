package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techprostudio.kuberinternational.Activity.OrderDetailsActivity;
import com.techprostudio.kuberinternational.Model.OrderHistoryPackage.OrderHistoryMain;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder>{
private Context context;
private List<OrderHistoryMain> modelList;

public static class MyViewHolder extends RecyclerView.ViewHolder{
    RelativeLayout gotoorderDetails;
    TextView order_no,placedon,deliveryon,ph_no;
    ImageView mbl;
    public MyViewHolder( View view) {
        super(view);
        gotoorderDetails=view.findViewById(R.id.gotoorderDetails);
        order_no=view.findViewById(R.id.order_no);
        placedon=view.findViewById(R.id.placedon);
        deliveryon=view.findViewById(R.id.deliveryon);
        ph_no=view.findViewById(R.id.ph_no);
        mbl=view.findViewById(R.id.mbl);

    }
}

    public OrderHistoryAdapter(Context context, List<OrderHistoryMain> modelList){
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
    if(modelList.get(position).getOrderStatus().equals("Processing"))
    {
        holder.mbl.setBackground(context.getResources().getDrawable(R.drawable.processing));
        holder.ph_no.setText(modelList.get(position).getOrderStatus());
        holder.ph_no.setTextColor(context.getResources().getColor(R.color.yellow));
    }
    else if(modelList.get(position).getOrderStatus().equals("Cancelled"))
    {
        holder.mbl.setBackground(context.getResources().getDrawable(R.drawable.redbox));
        holder.ph_no.setText(modelList.get(position).getOrderStatus());
        holder.ph_no.setTextColor(context.getResources().getColor(R.color.red));
    }
    else
    {
        holder.mbl.setBackground(context.getResources().getDrawable(R.drawable.delivered));
        holder.ph_no.setText(modelList.get(position).getOrderStatus());
    }
        holder.order_no.setText("Order "+modelList.get(position).getOrderNumber());
        holder.placedon.setText("Placed on:"+modelList.get(position).getOrderedOn());
        holder.deliveryon.setText("Delivery on:"+modelList.get(position).getDeliveryOn());

        holder.gotoorderDetails.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(context, OrderDetailsActivity.class);
            i.putExtra("orderid",modelList.get(position).getOrderId());
            context.startActivity(i);
        }
    });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
