package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.Activity.CategoryMasterActivity;
import com.techprostudio.kuberinternational.Activity.DashboardActivity;
import com.techprostudio.kuberinternational.Activity.SubProductActivity;
import com.techprostudio.kuberinternational.Fragment.CategoryFragment;
import com.techprostudio.kuberinternational.Model.CategoryMainModel;
import com.techprostudio.kuberinternational.Model.FilterModel;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import static com.techprostudio.kuberinternational.Activity.DashboardActivity.back;
import static com.techprostudio.kuberinternational.Activity.DashboardActivity.drawer_open;
import static com.techprostudio.kuberinternational.Activity.DashboardActivity.mainlayout;
import static com.techprostudio.kuberinternational.Activity.DashboardActivity.titlebar;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.MyViewHolder>{
    private Context context;
    private List<CategoryMainModel> modelList;

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout main_product_ll;

        public MyViewHolder( View view) {
            super(view);

            main_product_ll= view.findViewById(R.id.main_product_ll);
        }
    }

    public MainCategoryAdapter(Context context,List<CategoryMainModel> modelList){
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
        holder.main_product_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mainlayout.setVisibility(View.GONE);
//                drawer_open.setVisibility(View.GONE);
//                back.setVisibility(View.VISIBLE);
//                titlebar.setText("Furnishing");
//                CategoryFragment optionsFrag = new CategoryFragment();
//                ((CategoryMasterActivity)context).getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment, optionsFrag,"OptionsFragment").addToBackStack(null).commit();
//
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                Fragment myFragment = new CategoryFragment();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, myFragment).addToBackStack(null).commit();
                context.startActivity(new Intent(context, SubProductActivity.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
