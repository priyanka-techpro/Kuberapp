package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techprostudio.kuberinternational.Activity.SingledetailsActivity;
import com.techprostudio.kuberinternational.Model.SubProductMain.ProductList_product;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.MyViewHolder>{
    private Context context;
    private List<ProductList_product> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image_top;
        TextView product_top,mrp_top,quantity_top,discount;
        public MyViewHolder( View view) {
            super(view);
            image_top= view.findViewById(R.id.image_top);
            product_top= view.findViewById(R.id.product_top);
            mrp_top= view.findViewById(R.id.mrp_top);
            quantity_top= view.findViewById(R.id.quantity_top);
            discount= view.findViewById(R.id.discount);

        }
    }

    public SubcategoryAdapter(Context context,List<ProductList_product> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public SubcategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.subcategorylay,parent,false);
        return new SubcategoryAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SubcategoryAdapter.MyViewHolder holder, int position) {
        Picasso.with(context).load(modelList.get(position).getImage()).into(holder.image_top);
        holder.product_top.setText(modelList.get(position).getName());
        holder.mrp_top.setText(modelList.get(position).getVariationProducts().get(0).getVariationProductData().getGstData().getGstPrice());
        holder.quantity_top.setText(modelList.get(position).getVariationProducts().get(0).getVariationProductData().getUnitData().getCompleteUnit());
        if(modelList.get(position).getVariationProducts().get(0).getVariationProductData().getDiscountData().getDiscountAmount().equals("0"))
        {
            holder.discount.setVisibility(View.GONE);
        }
        else
        {
            holder.discount.setVisibility(View.VISIBLE);
            holder.discount.setText(modelList.get(position).getVariationProducts().get(0).getVariationProductData().getDiscountData().getDiscountTypeText());

        }
        holder.image_top.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context, SingledetailsActivity.class));

        }
    });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
