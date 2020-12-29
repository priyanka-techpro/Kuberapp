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
import com.techprostudio.kuberinternational.Activity.CategoryMasterActivity;
import com.techprostudio.kuberinternational.Activity.SubProductActivity;
import com.techprostudio.kuberinternational.Model.DashboardModel.ParentCategory;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{
    private Context context;
    private List<ParentCategory> modelList;
    int selected_position = 10;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout seemore,main_ll;
        ImageView image_top;
        TextView loadmore,product_top;
        public MyViewHolder( View view) {
            super(view);
            image_top= view.findViewById(R.id.image_top);
            seemore= view.findViewById(R.id.seemore);
            main_ll= view.findViewById(R.id.main_ll);
            product_top= view.findViewById(R.id.product_top);

        }
    }

    public CategoryAdapter(Context context,List<ParentCategory> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.categorylist,parent,false);
        return new CategoryAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryAdapter.MyViewHolder holder, int position) {

        final ParentCategory mList=modelList.get(position);
        holder.product_top.setText(mList.getParentCategoryName());
        Picasso.with(context).load(mList.getParentCategoryIcon()).into(holder.image_top);
        holder.main_ll.setVisibility(View.VISIBLE);
//        if(selected_position == position){
//            holder.seemore.setVisibility(View.VISIBLE);
//             holder.main_ll.setVisibility(View.GONE);
//
//        }
//        else{
//            holder.seemore.setVisibility(View.GONE);
//            holder.main_ll.setVisibility(View.VISIBLE);
//
//        }
        holder.image_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  context.startActivity(new Intent(context, SubProductActivity.class));

                Intent i=new Intent(context, SubProductActivity.class);
                i.putExtra("categoryname",mList.getParentCategoryName());
                i.putExtra("categoryid",mList.getParentCategoryId());
                context.startActivity(i);
            }
        });
        holder.seemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,CategoryMasterActivity.class));

            }
        });
    }

    @Override
    public int getItemCount() {

        return modelList.size();
    }
}
