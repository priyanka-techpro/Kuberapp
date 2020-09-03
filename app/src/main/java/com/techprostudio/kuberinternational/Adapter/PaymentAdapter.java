package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techprostudio.kuberinternational.Model.NewArrivalModel;
import com.techprostudio.kuberinternational.Model.PaymentModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder>{
private Context context;
private List<PaymentModel> modelList;

public static class MyViewHolder extends RecyclerView.ViewHolder{


    public MyViewHolder( View view) {
        super(view);


    }
}

    public PaymentAdapter(Context context, List<PaymentModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public PaymentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.payment,parent,false);
        return new PaymentAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PaymentAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

