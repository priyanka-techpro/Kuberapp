package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techprostudio.kuberinternational.Activity.CategoryMasterActivity;
import com.techprostudio.kuberinternational.Activity.ConfirmpasswordActivity;
import com.techprostudio.kuberinternational.Activity.DashboardActivity;
import com.techprostudio.kuberinternational.Activity.SigninActivity;
import com.techprostudio.kuberinternational.Activity.SubProductActivity;
import com.techprostudio.kuberinternational.Fragment.CategoryFragment;
import com.techprostudio.kuberinternational.Model.CategoryModel;
import com.techprostudio.kuberinternational.Model.NewArrivalModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.techprostudio.kuberinternational.Activity.DashboardActivity.back;
import static com.techprostudio.kuberinternational.Activity.DashboardActivity.drawer_open;
import static com.techprostudio.kuberinternational.Activity.DashboardActivity.mainlayout;
import static com.techprostudio.kuberinternational.Activity.DashboardActivity.titlebar;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{
    private Context context;
    private List<CategoryModel> modelList;
    int selected_position = 10;

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout seemore,main_ll;
        ImageView image_top;
        TextView loadmore;
        public MyViewHolder( View view) {
            super(view);
            image_top= view.findViewById(R.id.image_top);
            seemore= view.findViewById(R.id.seemore);
            main_ll= view.findViewById(R.id.main_ll);

        }
    }

    public CategoryAdapter(Context context,List<CategoryModel> modelList){
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
        final CategoryModel mList=modelList.get(position);
        if(selected_position == position){
            holder.seemore.setVisibility(View.VISIBLE);
             holder.main_ll.setVisibility(View.GONE);

        }
        else{
            holder.seemore.setVisibility(View.GONE);
            holder.main_ll.setVisibility(View.VISIBLE);

        }
        holder.image_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mainlayout.setVisibility(View.GONE);
//                drawer_open.setVisibility(View.GONE);
//                back.setVisibility(View.VISIBLE);
//                titlebar.setText("Furnishing");
//                CategoryFragment optionsFrag = new CategoryFragment ();
//
//                ((DashboardActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, optionsFrag,"OptionsFragment").addToBackStack(null).commit();

                context.startActivity(new Intent(context, SubProductActivity.class));

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
