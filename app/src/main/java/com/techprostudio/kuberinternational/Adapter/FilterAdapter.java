package com.techprostudio.kuberinternational.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techprostudio.kuberinternational.Activity.SubProductActivity;
import com.techprostudio.kuberinternational.Fragment.CategoryFragment;
import com.techprostudio.kuberinternational.Model.FilterSection.CategoryListFilter;
import com.techprostudio.kuberinternational.Model.FilterSection.FilterMainModel;
import com.techprostudio.kuberinternational.Model.SubProductMain.ProductList_product;
import com.techprostudio.kuberinternational.Model.SubProductMain.SubProductMainModel;
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

import static com.techprostudio.kuberinternational.Activity.SubProductActivity.subcategorylist;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.MyViewHolder> {
    private Context context;
    private List<CategoryListFilter> modelList;
    int row_index=0;
    public static String subproductcategoryid;
    private SubcategoryAdapter subcategoryAdapter;
    private List<ProductList_product> subcategoryModelList;
    ApiInterface apiInterface;
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        LinearLayout options_ll;
        TextView includes_one;
        public MyViewHolder( View view) {
            super(view);
            options_ll=view.findViewById(R.id.options_ll);
            includes_one=view.findViewById(R.id.includes_one);
        }
    }

    public FilterAdapter(Context context,List<CategoryListFilter> modelList){
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public FilterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.filterlay,parent,false);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        return new FilterAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilterAdapter.MyViewHolder holder, int position) {
        holder.includes_one.setText(modelList.get(position).getCategoryName());
        String customerid=new AppPreference(context).getUserId();

        if (row_index == position)
        {
            subproductcategoryid =modelList.get(position).getCategoryId();
            subcategorydata(subproductcategoryid,customerid);

            Log.e("subcategoryadapterId",""+subproductcategoryid);
        }
        else
        {
            subproductcategoryid="0";
        }
        holder.options_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
            }
        });
//        if (row_index==position) {
//            holder.includes_one.setText(modelList.get(position).getCategoryName());
//        }
//        else{
//            holder.includes_one.setText(modelList.get(position).getCategoryName());
//        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    private void subcategorydata(String subproductcategoryid, String customerid) {
        Log.e("catid",""+subproductcategoryid);
        Call<SubProductMainModel> call=apiInterface.getFilterProducts(Config.header,subproductcategoryid,customerid);
        call.enqueue(new Callback<SubProductMainModel>() {
            @Override
            public void onResponse(Call<SubProductMainModel> call, Response<SubProductMainModel> response) {
                if(response.body().getStatus()==true)
                {

                    subcategoryModelList=response.body().getProductList().get(0).getProductList();
                    subcategoryAdapter = new SubcategoryAdapter(context,subcategoryModelList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context,2);
                    subcategorylist.setLayoutManager(mLayoutManager);
                    subcategorylist.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(0), true));
                    subcategorylist.setItemAnimator(new DefaultItemAnimator());
                    subcategorylist.setAdapter(subcategoryAdapter);
                    subcategoryAdapter.notifyDataSetChanged();

                }
                else{
                    Toast.makeText(context, "no data found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubProductMainModel> call, Throwable t) {

            }
        });


    }
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
