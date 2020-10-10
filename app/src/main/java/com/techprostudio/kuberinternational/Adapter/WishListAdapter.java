package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techprostudio.kuberinternational.Activity.SingledetailsActivity;
import com.techprostudio.kuberinternational.Model.SingleProductPackage.VariationProductSingle;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.MyViewHolder>{
    private Context context;
    private List<VariationProductSingle> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder( View view) {
            super(view);

        }
    }

    public WishListAdapter(Context context,List<VariationProductSingle> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public WishListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.wishlayout,parent,false);
        return new WishListAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final WishListAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

