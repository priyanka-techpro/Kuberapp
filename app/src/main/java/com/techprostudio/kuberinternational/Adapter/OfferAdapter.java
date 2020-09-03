package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techprostudio.kuberinternational.Model.OfferModel;
import com.techprostudio.kuberinternational.Model.OrderDetailsModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OfferAdapter  extends RecyclerView.Adapter<OfferAdapter.MyViewHolder>{
    private Context context;
    private List<OfferModel> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder( View view) {
            super(view);


        }
    }

    public OfferAdapter(Context context, List<OfferModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public OfferAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.offerlayout,parent,false);
        return new OfferAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final OfferAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}


