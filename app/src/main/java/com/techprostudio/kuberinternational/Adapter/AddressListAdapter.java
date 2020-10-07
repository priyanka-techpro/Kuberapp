package com.techprostudio.kuberinternational.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techprostudio.kuberinternational.Activity.AddAddressActivity;
import com.techprostudio.kuberinternational.Activity.AddressActivity;
import com.techprostudio.kuberinternational.Activity.ChangeAddressActivity;
import com.techprostudio.kuberinternational.Model.AddressListPakage.AddressList;
import com.techprostudio.kuberinternational.Model.AddressListPakage.AddressMainModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressListAdapter  extends RecyclerView.Adapter<AddressListAdapter.MyViewHolder>{
    private Context context;
    private List<AddressList> modelList;
    int row_index=0;
    String tag ="Address";
    public static String AddressId="0";
    ApiInterface apiInterface;
    ProgressDialog progressDialog,progressDialog1;
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
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        return new AddressListAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AddressListAdapter.MyViewHolder holder, int position) {
    holder.del_place.setText(modelList.get(position).getFullName());
    holder.text_address.setText(modelList.get(position).getAddressLineOne()+","+modelList.get(position).getAddressLineTwo()+","+modelList.get(position).getLandMark()+","+modelList.get(position).getCity()+","+modelList.get(position).getState()+","+modelList.get(position).getPin());
        String customerid=new AppPreference(context).getUserId();
        if (row_index == position)
        {
            holder.location.setChecked(true);
            AddressId =modelList.get(position).getAddressId();
            Log.e("AddressId",""+AddressId);
            holder.edit_ll.setVisibility(View.VISIBLE);
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
        holder.delete_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String addressid=modelList.get(position).getAddressId();
                deletecurrentaddress(customerid,addressid);
            }
        });
        holder.edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, ChangeAddressActivity.class);
                i.putExtra("addressid",AddressId);
                context.startActivity(i);
            }
        });

    }

    private void deletecurrentaddress(String customerid, String addressid) {
        Call<AddressMainModel> call=apiInterface.deleteAddress(Config.header,customerid,addressid);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<AddressMainModel>() {
            @Override
            public void onResponse(Call<AddressMainModel> call, Response<AddressMainModel> response) {
                progressDialog.dismiss();
                if(response.body().getStatus()==true)
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    Call<AddressMainModel> call1=apiInterface.getAddressList(Config.header,customerid);
                    progressDialog1 = new ProgressDialog(context);
                    progressDialog1.setMessage("Please wait...");
                    progressDialog1.show();
                    call1.enqueue(new Callback<AddressMainModel>() {
                        @Override
                        public void onResponse(Call<AddressMainModel> call, Response<AddressMainModel> response) {
                            progressDialog1.dismiss();
                            modelList = response.body().getAddressList();
                            notifyDataSetChanged();
                            if (response.body().getStatus() == true) {
                                if(response.body().getAddressList().size() == 0)
                                {
                                    Toast.makeText(context, "No addresslist found.", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    modelList = response.body().getAddressList();
                                    notifyDataSetChanged();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<AddressMainModel> call, Throwable t) {
                            progressDialog1.dismiss();
                        }
                    });

                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddressMainModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
