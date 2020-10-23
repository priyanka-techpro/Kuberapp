package com.techprostudio.kuberinternational.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.techprostudio.kuberinternational.Activity.CartActivity;
import com.techprostudio.kuberinternational.Activity.SingledetailsActivity;
import com.techprostudio.kuberinternational.Model.CartPackage.CartList;
import com.techprostudio.kuberinternational.Model.CartPackage.CartListMainModel;
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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{
    private Context context;
    private List<CartList> modelList;
    ApiInterface apiInterface;
    ProgressDialog progressDialog,progressDialog1;
    public int count = 0;
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView product_image,delete_cart,cart_minus,cart_add;
        TextView product_name,product_size,price_cart,quantity_cart;
        public MyViewHolder( View view) {
            super(view);
            product_image=view.findViewById(R.id.product_image);
            product_name=view.findViewById(R.id.product_name);
            product_size=view.findViewById(R.id.product_size);
            price_cart=view.findViewById(R.id.price_cart);
            quantity_cart=view.findViewById(R.id.quantity_cart);
            delete_cart=view.findViewById(R.id.delete_cart);
            cart_minus=view.findViewById(R.id.cart_minus);
            cart_add=view.findViewById(R.id.cart_add);
        }
    }

    public CartAdapter(Context context,List<CartList> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cartlayout,parent,false);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        return new CartAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.MyViewHolder holder, int position) {
        Picasso.with(context).load(modelList.get(position).getProductImage()).into(holder.product_image);
        holder.product_name.setText(modelList.get(position).getProductName());
        holder.quantity_cart.setText(modelList.get(position).getQuantity());
        String mrp= String.valueOf(modelList.get(position).getTotalAfterDiscountPrice());
        holder.price_cart.setText("Rs"+mrp);
        holder.product_size.setText(modelList.get(position).getUnitData());
        String customerid=new AppPreference(context).getUserId();
        holder.cart_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String quantity = holder.quantity_cart.getText().toString();
                int qty = Integer.parseInt(quantity);
                count=qty + 1;
                String quantityupdate = String.valueOf(count);
                String cartid=modelList.get(position).getCartId();
                updateItems(cartid,customerid,quantityupdate);
            }
        });
        holder.cart_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qty= holder.quantity_cart.getText().toString();
                if (qty.equals("1")) {
                    String cartid=modelList.get(position).getCartId();
                    deleteCartitems(cartid,customerid);
                }
                else{
                    String quantity = holder.quantity_cart.getText().toString();
                    int qty1 = Integer.parseInt(quantity);
                    count= qty1 - 1;
                    String quantityupdate = String.valueOf(count);
                    String cartid=modelList.get(position).getCartId();
                    updateItems(cartid,customerid,quantityupdate);
                }


            }
        });

        holder.delete_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cartid=modelList.get(position).getCartId();
                deleteCartitems(cartid,customerid);
            }
        });

    }

    private void updateItems(String cartid, String customerid, String quantityupdate) {
        Call<CartListMainModel> call=apiInterface.UpdateCartItem(Config.header,cartid,customerid,quantityupdate);
        progressDialog1 = new ProgressDialog(context);
        progressDialog1.setMessage("Please wait...");
        progressDialog1.show();
        call.enqueue(new Callback<CartListMainModel>() {
            @Override
            public void onResponse(Call<CartListMainModel> call, Response<CartListMainModel> response) {
                progressDialog1.dismiss();
                if(response.body().getStatus()==true)
                {
                    modelList=response.body().getCartList();
                    notifyDataSetChanged();


                        CartActivity.subtotal_ll.setVisibility(View.VISIBLE);
                        CartActivity.proceedtocheckout.setVisibility(View.VISIBLE);
                        String cartCount = String.valueOf(response.body().getCartCount());
                        Config.cart = cartCount;
                        if (cartCount.equals("0")) {
                            CartActivity.cart_count.setVisibility(View.GONE);
                            CartActivity.tv_count.setVisibility(View.GONE);
                        } else {
                            CartActivity.cart_count.setVisibility(View.VISIBLE);
                            CartActivity.tv_count.setVisibility(View.VISIBLE);
                            CartActivity.tv_count.setText(Config.cart);
                        }
                        CartActivity.sub_total_amt.setText("Rs "+response.body().getCartPriceData().getTotalPayablePrice());
                        CartActivity.discount_amt.setText("Rs "+response.body().getCartPriceData().getTotalSavePrice());
                        CartActivity.shippingchrge.setText("Rs "+response.body().getCartPriceData().getDeliveryCharge());
                        CartActivity.rndoff.setText("Rs "+response.body().getCartPriceData().getTotalGstPrice());
                        CartActivity.ttl_amnt.setText("Rs "+response.body().getCartPriceData().getFinalPayablePrice());




                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    CartActivity.subtotal_ll.setVisibility(View.GONE);
                    CartActivity.proceedtocheckout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CartListMainModel> call, Throwable t) {
            progressDialog1.dismiss();
            }
        });

    }

    private void deleteCartitems(String cartid, String customerid) {
        Call<CartListMainModel> call=apiInterface.deleteCartItem(Config.header,cartid,customerid);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<CartListMainModel>() {
            @Override
            public void onResponse(Call<CartListMainModel> call, Response<CartListMainModel> response) {
                progressDialog.dismiss();
                if(response.body().getStatus()==true)
                {
                    modelList=response.body().getCartList();
                    notifyDataSetChanged();

                    if(response.body().getCartCount().equals(0)){
                        String msg=response.body().getMessage();
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        CartActivity.subtotal_ll.setVisibility(View.GONE);
                        CartActivity.proceedtocheckout.setVisibility(View.GONE);
                        CartActivity.cart_count.setVisibility(View.GONE);
                        CartActivity.tv_count.setVisibility(View.GONE);
                    }
                    else{
                        CartActivity.subtotal_ll.setVisibility(View.VISIBLE);
                        CartActivity.proceedtocheckout.setVisibility(View.VISIBLE);
                        String cartCount = String.valueOf(response.body().getCartCount());
                        Config.cart = cartCount;
                        if (cartCount.equals("0")) {
                            CartActivity.cart_count.setVisibility(View.GONE);
                            CartActivity.tv_count.setVisibility(View.GONE);
                        } else {
                            CartActivity.cart_count.setVisibility(View.VISIBLE);
                            CartActivity.tv_count.setVisibility(View.VISIBLE);
                            CartActivity.tv_count.setText(Config.cart);
                        }
                        CartActivity.sub_total_amt.setText("Rs "+response.body().getCartPriceData().getTotalPayablePrice());
                        CartActivity.discount_amt.setText("Rs "+response.body().getCartPriceData().getTotalSavePrice());
                        CartActivity.shippingchrge.setText("Rs "+response.body().getCartPriceData().getDeliveryCharge());
                        CartActivity.rndoff.setText("Rs "+response.body().getCartPriceData().getTotalGstPrice());
                        CartActivity.ttl_amnt.setText("Rs "+response.body().getCartPriceData().getFinalPayablePrice());

                    }


                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    CartActivity.subtotal_ll.setVisibility(View.GONE);
                    CartActivity.proceedtocheckout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CartListMainModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

