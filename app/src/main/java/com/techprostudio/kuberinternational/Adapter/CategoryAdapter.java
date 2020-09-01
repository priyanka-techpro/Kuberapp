package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.techprostudio.kuberinternational.Activity.DashboardActivity;
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

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image_top;
        public MyViewHolder( View view) {
            super(view);
            image_top= view.findViewById(R.id.image_top);

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
        holder.image_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainlayout.setVisibility(View.GONE);
                drawer_open.setVisibility(View.GONE);
                back.setVisibility(View.VISIBLE);
                titlebar.setText("Furnishing");
                CategoryFragment optionsFrag = new CategoryFragment ();

                ((DashboardActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, optionsFrag,"OptionsFragment").addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
