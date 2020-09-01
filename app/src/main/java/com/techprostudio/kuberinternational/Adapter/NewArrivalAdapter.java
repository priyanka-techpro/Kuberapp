package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techprostudio.kuberinternational.Model.NewArrivalModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewArrivalAdapter extends RecyclerView.Adapter<NewArrivalAdapter.MyViewHolder>{
    private Context context;
    private List<NewArrivalModel> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder( View view) {
            super(view);


        }
    }

    public NewArrivalAdapter(Context context,List<NewArrivalModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public NewArrivalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.newarrivallay,parent,false);
        return new NewArrivalAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewArrivalAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
