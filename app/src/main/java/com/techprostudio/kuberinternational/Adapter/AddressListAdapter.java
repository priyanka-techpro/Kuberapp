package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techprostudio.kuberinternational.Model.AddressListPakage.AddressList;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddressListAdapter  extends RecyclerView.Adapter<AddressListAdapter.MyViewHolder>{
    private Context context;
    private List<AddressList> modelList;
    int row_index=0;
    String tag ="Address";
    public static String AddressId="0";

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView del_place,text_address;
        RadioButton location;
        RelativeLayout edit_ll,delete_address,edit_address;
        public MyViewHolder( View view) {
            super(view);
            del_place=view.findViewById(R.id.del_place);
            text_address=view.findViewById(R.id.text_address);
            location=view.findViewById(R.id.location);
            edit_ll=view.findViewById(R.id.edit_ll);
            edit_address=view.findViewById(R.id.edit_address);
            delete_address=view.findViewById(R.id.delete_address);
        }
    }

    public AddressListAdapter(Context context,List<AddressList> modelList){
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
    holder.del_place.setText(modelList.get(position).getFullName());
    holder.text_address.setText(modelList.get(position).getAddressLineOne()+","+modelList.get(position).getAddressLineTwo()+","+modelList.get(position).getLandMark()+","+modelList.get(position).getCity()+","+modelList.get(position).getState()+","+modelList.get(position).getPin());
        if (row_index == position)
        {
            holder.location.setChecked(true);
            AddressId =modelList.get(position).getAddressId();
            Log.e("AddressId",""+AddressId);
        }
        else
        {
            holder.location.setChecked(false);
            holder.edit_ll.setVisibility(View.GONE);

        }
        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
                holder.edit_ll.setVisibility(View.VISIBLE);
            }
        });


    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
