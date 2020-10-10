package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        ImageView image_top,gotosingle;
        TextView product_top,mrp_top,quantity_top,discount;
        LinearLayout ll_layer;
        public MyViewHolder( View view) {
            super(view);
            image_top= view.findViewById(R.id.image_top);
            product_top= view.findViewById(R.id.product_top);
            mrp_top= view.findViewById(R.id.mrp_top);
            quantity_top= view.findViewById(R.id.quantity_top);
            discount= view.findViewById(R.id.discount);
            gotosingle= view.findViewById(R.id.gotosingle);
            ll_layer= view.findViewById(R.id.ll_layer);

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
        holder.mrp_top.setText("Rs."+modelList.get(position).getVariationProducts().get(0).getVariationProductData().getGstData().getGstPrice()+"/");
        holder.quantity_top.setText(modelList.get(position).getVariationProducts().get(0).getVariationProductData().getUnitData().getCompleteUnit());
        if(modelList.get(position).getVariationProducts().get(0).getVariationProductData().getDiscountData().getDiscountAmount().equals("0.00"))
        {
            holder.discount.setVisibility(View.GONE);
        }
        else
        {
            holder.discount.setVisibility(View.VISIBLE);
            holder.discount.setText("("+modelList.get(position).getVariationProducts().get(0).getVariationProductData().getDiscountData().getDiscountTypeText()+" off)");

        }
        holder.ll_layer.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(context, SingledetailsActivity.class);
            i.putExtra("productid",modelList.get(position).getProductId());
            i.putExtra("productname",modelList.get(position).getName());
            context.startActivity(i);
        }
    });
     holder.gotosingle.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(context, SingledetailsActivity.class);
            i.putExtra("productid",modelList.get(position).getProductId());
            i.putExtra("productname",modelList.get(position).getName());
            context.startActivity(i);
        }
    });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
