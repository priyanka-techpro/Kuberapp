package com.techprostudio.kuberinternational.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.techprostudio.kuberinternational.Activity.SingledetailsActivity;
import com.techprostudio.kuberinternational.Activity.SubProductActivity;
import com.techprostudio.kuberinternational.Activity.WishListActivity;
import com.techprostudio.kuberinternational.Model.SingleProductPackage.VariationProductSingle;
import com.techprostudio.kuberinternational.Model.WishListModel.WishListMain;
import com.techprostudio.kuberinternational.Model.WishListModel.WishListMainModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.MyViewHolder>{
    private Context context;
    private List<WishListMain> modelList;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image_top,delete_wishlist;
        TextView product_nm,qty,price;
        public MyViewHolder( View view) {
            super(view);
            image_top=view.findViewById(R.id.image_top);
            delete_wishlist=view.findViewById(R.id.delete_wishlist);
            product_nm=view.findViewById(R.id.product_nm);
            qty=view.findViewById(R.id.qty);
            price=view.findViewById(R.id.price);
        }
    }

    public WishListAdapter(Context context,List<WishListMain> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public WishListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.wishlayout,parent,false);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        return new WishListAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final WishListAdapter.MyViewHolder holder, int position) {
        holder.product_nm.setText(modelList.get(position).getProductName());
        holder.qty.setText("("+modelList.get(position).getProductData().getUnitData().getCompleteUnit()+")");
        holder.price.setText("Rs."+modelList.get(position).getProductData().getGstData().getFinalPricePlusGst());
        Picasso.with(context).load(modelList.get(position).getVeriationImage()).into(holder.image_top);
        String customerid=new AppPreference(context).getUserId();
        holder.delete_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String wishid=modelList.get(position).getWishId();
            deleteitms(wishid,customerid);
            }
        });
        holder.image_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, SingledetailsActivity.class);
                i.putExtra("productid",modelList.get(position).getProductId());
                i.putExtra("productname",modelList.get(position).getProductName());
                context.startActivity(i);
            }
        });

    }

    private void deleteitms(String wishid, String customerid) {
        Call<WishListMainModel> call=apiInterface.deletefromWishlist(Config.header,wishid,customerid);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<WishListMainModel>() {
            @Override
            public void onResponse(Call<WishListMainModel> call, Response<WishListMainModel> response) {
                progressDialog.dismiss(); 
                if(response.body().isStatus() == true)
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(context,msg , Toast.LENGTH_SHORT).show();
                    modelList=response.body().getWishList();
                    notifyDataSetChanged();
                    String cartCount = String.valueOf(response.body().getCartCount());
                    Config.cart = cartCount;
                    if (cartCount.equals("0")) {
                        WishListActivity.cart_count.setVisibility(View.GONE);
                        WishListActivity.tv_count.setVisibility(View.GONE);
                    } else {
                        WishListActivity.cart_count.setVisibility(View.VISIBLE);
                        WishListActivity.tv_count.setVisibility(View.VISIBLE);
                        WishListActivity.tv_count.setText(Config.cart);
                    }

                }
                else{
                    String msg=response.body().getMessage();
                    Toast.makeText(context,msg , Toast.LENGTH_SHORT).show();
                    String cartCount = String.valueOf(response.body().getCartCount());
                    Config.cart = cartCount;
                    if (cartCount.equals("0")) {
                        WishListActivity.cart_count.setVisibility(View.GONE);
                        WishListActivity.tv_count.setVisibility(View.GONE);
                    } else {
                        WishListActivity.cart_count.setVisibility(View.VISIBLE);
                        WishListActivity.tv_count.setVisibility(View.VISIBLE);
                        WishListActivity.tv_count.setText(Config.cart);
                    }

                }
            }

            @Override
            public void onFailure(Call<WishListMainModel> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

