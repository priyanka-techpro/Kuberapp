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
import com.techprostudio.kuberinternational.Activity.SubProductActivity;
import com.techprostudio.kuberinternational.Model.ParentCategory.CategoryList;
import com.techprostudio.kuberinternational.Model.ParentCategory.CategoryMainModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.MyViewHolder>{
    private Context context;
    private List<CategoryList> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout main_product_ll;
        ImageView image_top;
        TextView product_name_main;
        public MyViewHolder( View view) {
            super(view);
            main_product_ll= view.findViewById(R.id.main_product_ll);
            image_top= view.findViewById(R.id.image_top);
            product_name_main= view.findViewById(R.id.product_name_main);
        }
    }

    public MainCategoryAdapter(Context context,List<CategoryList> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public MainCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.maincategory,parent,false);
        return new MainCategoryAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainCategoryAdapter.MyViewHolder holder, int position) {
        Picasso.with(context).load(modelList.get(position).getCategoryIcon()).into(holder.image_top);
        holder.product_name_main.setText(modelList.get(position).getCategoryName());
        holder.main_product_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, SubProductActivity.class);
                i.putExtra("categoryname",modelList.get(position).getCategoryName());
                i.putExtra("categoryid",modelList.get(position).getCategoryId());
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
