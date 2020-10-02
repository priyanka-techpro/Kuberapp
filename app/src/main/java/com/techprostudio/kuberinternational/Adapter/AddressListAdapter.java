package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techprostudio.kuberinternational.Model.AddressListModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddressListAdapter  extends RecyclerView.Adapter<AddressListAdapter.MyViewHolder>{
    private Context context;
    private List<AddressListModel> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder( View view) {
            super(view);


        }
    }

    public AddressListAdapter(Context context,List<AddressListModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public AddressListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.selectaddress,parent,false);
        return new AddressListAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddressListAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
