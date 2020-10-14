package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techprostudio.kuberinternational.Activity.SingledetailsActivity;
import com.techprostudio.kuberinternational.Model.SingleProductPackage.VariationProductSingle;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.techprostudio.kuberinternational.Activity.SingledetailsActivity.discount_single;
import static com.techprostudio.kuberinternational.Activity.SingledetailsActivity.mrp_single;
import static com.techprostudio.kuberinternational.Activity.SingledetailsActivity.producname;
import static com.techprostudio.kuberinternational.Activity.SingledetailsActivity.product_name;
import static com.techprostudio.kuberinternational.Activity.SingledetailsActivity.qty;
import static com.techprostudio.kuberinternational.Activity.SingledetailsActivity.quantity_single;

public class Variationadapter extends RecyclerView.Adapter<Variationadapter.MyViewHolder>{
    private Context context;
    private List<VariationProductSingle> modelList;
    int row_index=0;
    public static String price,variationid;
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView del_place;
        RadioButton choose;
        LinearLayout variationlay;
        public MyViewHolder( View view) {
            super(view);
            del_place= view.findViewById(R.id.del_place);
            choose= view.findViewById(R.id.choose);
            variationlay= view.findViewById(R.id.variationlay);

        }
    }

    public Variationadapter(Context context,List<VariationProductSingle> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public Variationadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.variationqty,parent,false);
        return new Variationadapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Variationadapter.MyViewHolder holder, int position) {
        holder.del_place.setText(modelList.get(position).getVariationProductData().getUnitData().getCompleteUnit());
        if (row_index == position)
        {
            variationid =modelList.get(position).getVariationProductId();
            holder.choose.setChecked(true);
            holder.del_place.setText(modelList.get(position).getVariationProductData().getUnitData().getCompleteUnit());
            Picasso.with(context).load(modelList.get(position).getVariationProductImage()).into(SingledetailsActivity.image_top);
            product_name.setText(producname+","+modelList.get(position).getVariationProductData().getUnitData().getCompleteUnit());
            price= String.valueOf(modelList.get(position).getVariationProductData().getGstData().getFinalPricePlusGst());
            mrp_single.setText("Rs."+price+"/");
            quantity_single.setText(modelList.get(position).getVariationProductData().getUnitData().getCompleteUnit());
            if(modelList.get(position).getVariationProductData().getDiscountData().getDiscountAmount().equals("0.00"))
            {
                discount_single.setVisibility(View.GONE);
            }
            else
            {
                discount_single.setVisibility(View.VISIBLE);
                discount_single.setText(modelList.get(position).getVariationProductData().getDiscountData().getDiscountTypeText()+" off");

            }
        }
        else
        {
            holder.choose.setChecked(false);
            holder.del_place.setText(modelList.get(position).getVariationProductData().getUnitData().getCompleteUnit());
        }
        holder.variationlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
                qty.setText("1");
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}

