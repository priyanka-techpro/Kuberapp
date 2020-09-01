package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techprostudio.kuberinternational.Model.FilterModel;
import com.techprostudio.kuberinternational.Model.NewArrivalModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.MyViewHolder> {
    private Context context;
    private List<FilterModel> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder( View view) {
            super(view);


        }
    }

    public FilterAdapter(Context context,List<FilterModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public FilterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.filterlay,parent,false);
        return new FilterAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilterAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
