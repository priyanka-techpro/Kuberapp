package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.techprostudio.kuberinternational.Model.SearchPAckage.ProductList;
import com.techprostudio.kuberinternational.Model.SearchPAckage.VariationProduct_search;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private Context context;
    private List<ProductList> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout ll_layer;
        ImageView image_product;
        TextView product_top,mrp_top,quantity_top,discount;
        public MyViewHolder( View view) {
            super(view);
            ll_layer=view.findViewById(R.id.ll_layer);
            image_product=view.findViewById(R.id.image_top);
            product_top=view.findViewById(R.id.product_top);
            mrp_top=view.findViewById(R.id.mrp_top);
            quantity_top=view.findViewById(R.id.quantity_top);
            discount=view.findViewById(R.id.discount);
        }
    }
    public SearchAdapter(Context context,List<ProductList> modelList){
        this.context = context;
        this.modelList = modelList;
    }
    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.searchlayer,parent,false);
        return new SearchAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
        Picasso.with(context).load(modelList.get(position).getImage()).into(holder.image_product);
        holder.product_top.setText(modelList.get(position).getName());
       // holder.mrp_top.setText("Rs."+modelList.get(position).getVariationProductData().getGstData().getFinalPricePlusGst());
       // holder.discount.setText("("+modelList.get(position).getVariationProducts().get(0).getVariationProductData().getDiscountData().getDiscountTypeText()+" off)");
//    Log.e("test",""+modelList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

