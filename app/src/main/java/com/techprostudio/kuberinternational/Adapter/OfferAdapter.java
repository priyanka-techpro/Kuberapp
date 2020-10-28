package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.techprostudio.kuberinternational.Activity.OfferActivity;
import com.techprostudio.kuberinternational.Activity.PaymentActivity;
import com.techprostudio.kuberinternational.Model.OfferModelPackage.OfferList;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OfferAdapter  extends RecyclerView.Adapter<OfferAdapter.MyViewHolder>{
    private Context context;
    private List<OfferList> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView offr,offr_valid,off;
        RelativeLayout gotoorderDetails;
        ImageView ofer_image;
        public MyViewHolder( View view) {
            super(view);
            gotoorderDetails=view.findViewById(R.id.gotoorderDetails);
            offr=view.findViewById(R.id.offr);
            offr_valid=view.findViewById(R.id.offr_valid);
            ofer_image=view.findViewById(R.id.ofer_image);
            off=view.findViewById(R.id.off);
        }
    }

    public OfferAdapter(Context context, List<OfferList> modelList){
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
        Picasso.with(context).load(modelList.get(position).getOfferImage()).into(holder.ofer_image);
//       String dates=modelList.get(position).getOfferValid();
//        Date date=new Date(dates);
//        SimpleDateFormat formatter5=new SimpleDateFormat("dd-MMM-yyyy");
//        String formats1 = formatter5.format(date);
//
        holder.offr_valid.setText("Offer valid: "+modelList.get(position).getOfferValid());
       holder.off.setText(modelList.get(position).getPriceTagText()+" off");

        holder.gotoorderDetails.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context, PaymentActivity.class));
            Config.offerid=modelList.get(position).getOfferId();
        }
    });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}


