package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.techprostudio.kuberinternational.Model.DashboardModel.BannerList;
import com.techprostudio.kuberinternational.Model.SliderItem;
import com.techprostudio.kuberinternational.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    Context context;
    private ViewPager2 viewPager2;
    private List<BannerList> sliderItems;
    //  private Integer [] images = {R.drawable.bannerone,R.drawable.bannertwo,R.drawable.bannerthree,R.drawable.bannerfour};


    public SliderAdapter(List<BannerList> sliderItems, ViewPager2 viewPager2,Context context) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
        this.context = context;
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{

        RoundedImageView imageView;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
        }

        void setImage(BannerList sliderItem) {

          // imageView.setImageResource(sliderItem.getBannerImage());

        }


    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.custom_layout,
                        parent,
                        false
                )
        );


    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        Picasso.with(context).load(sliderItems.get(position).getBannerImage()).into(holder.imageView);
        Log.e("jhgjg",""+sliderItems.get(position).getBannerImage());
        holder.setImage(sliderItems.get(position));
        if(position == sliderItems.size() - 2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }





}

