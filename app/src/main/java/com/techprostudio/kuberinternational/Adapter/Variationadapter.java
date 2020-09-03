package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.techprostudio.kuberinternational.Activity.SingledetailsActivity;
import com.techprostudio.kuberinternational.Model.SubcategoryModel;
import com.techprostudio.kuberinternational.Model.VariationModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Variationadapter extends RecyclerView.Adapter<Variationadapter.MyViewHolder>{
    private Context context;
    private List<VariationModel> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image_top;
        public MyViewHolder( View view) {
            super(view);
            image_top= view.findViewById(R.id.image_top);

        }
    }

    public Variationadapter(Context context,List<VariationModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public Variationadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.variationqty,parent,false);
        return new Variationadapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Variationadapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

