package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techprostudio.kuberinternational.Model.OrderDetailsModel;
import com.techprostudio.kuberinternational.Model.OrderDetailsPackage.OrderDetailsDatum;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder>{
private Context context;
private List<OrderDetailsDatum> modelList;

public static class MyViewHolder extends RecyclerView.ViewHolder{
    TextView product_name,product_size,price;
    ImageView product_image;
    public MyViewHolder( View view) {
        super(view);
        product_name=view.findViewById(R.id.product_name);
        product_size=view.findViewById(R.id.product_size);
        price=view.findViewById(R.id.price);
        product_image=view.findViewById(R.id.product_image);
    }
}

    public OrderDetailsAdapter(Context context, List<OrderDetailsDatum> modelList){
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
        Picasso.with(context).load(modelList.get(position).getProductImage()).into(holder.product_image);
        holder.product_name.setText(modelList.get(position).getProductName());
        holder.product_size.setText(modelList.get(position).getUnitData());
        holder.price.setText("Rs."+modelList.get(position).getTotalAfterDiscountPrice()+" x "+modelList.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

