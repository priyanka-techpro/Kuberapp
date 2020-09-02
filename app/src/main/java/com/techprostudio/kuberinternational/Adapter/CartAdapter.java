package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techprostudio.kuberinternational.Model.CartListModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{
    private Context context;
    private List<CartListModel> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder( View view) {
            super(view);


        }
    }

    public CartAdapter(Context context,List<CartListModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cartlayout,parent,false);
        return new CartAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

