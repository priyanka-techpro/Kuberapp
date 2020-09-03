package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.Model.FaqModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FaqAdapter  extends RecyclerView.Adapter<FaqAdapter.MyViewHolder>{
    private Context context;
    private List<FaqModel> modelList;
    boolean count = false;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout faqans,re_minus,re_add,mains;
        //ImageView minus,add;

        public MyViewHolder( View view) {
            super(view);
            faqans = view.findViewById(R.id.faqans);
            re_minus = view.findViewById(R.id.re_minus);
            re_add = view.findViewById(R.id.re_add);
            mains = view.findViewById(R.id.main);


        }
    }
    public FaqAdapter(Context context, List<FaqModel> modelList){
        this.context = context;
        this.modelList = modelList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.faqlayout,parent,false);
        return new FaqAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.mains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count == false){
                    holder.re_minus.setVisibility(View.VISIBLE);
                    holder.faqans.setVisibility(View.VISIBLE);
                    holder.re_add.setVisibility(View.GONE);
                    count = true;
                }
                else{
                    holder.re_add.setVisibility(View.VISIBLE);
                    holder.faqans.setVisibility(View.GONE);
                    holder.re_minus.setVisibility(View.GONE);
                    count = false;
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

}

