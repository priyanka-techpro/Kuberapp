package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techprostudio.kuberinternational.Model.NewArrivalModel;
import com.techprostudio.kuberinternational.Model.SubcategoryModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.MyViewHolder>{
    private Context context;
    private List<SubcategoryModel> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder( View view) {
            super(view);


        }
    }

    public SubcategoryAdapter(Context context,List<SubcategoryModel> modelList){
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

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
