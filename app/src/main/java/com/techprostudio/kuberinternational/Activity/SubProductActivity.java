package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.techprostudio.kuberinternational.Adapter.FilterAdapter;
import com.techprostudio.kuberinternational.Adapter.SubcategoryAdapter;
import com.techprostudio.kuberinternational.Fragment.CategoryFragment;
import com.techprostudio.kuberinternational.Model.FilterModel;
import com.techprostudio.kuberinternational.Model.SubcategoryModel;
import com.techprostudio.kuberinternational.R;

import java.util.ArrayList;
import java.util.List;

import static com.techprostudio.kuberinternational.Activity.DashboardActivity.back;
import static com.techprostudio.kuberinternational.Activity.DashboardActivity.drawer_open;
import static com.techprostudio.kuberinternational.Activity.DashboardActivity.titlebar;

public class SubProductActivity extends AppCompatActivity {
    RecyclerView filterlist,subcategorylist;
    private List<FilterModel> filterModelList;
    private List<SubcategoryModel> subcategoryModelList;
    private FilterAdapter filterAdapter;
    private SubcategoryAdapter subcategoryAdapter;
    FilterModel filterModel;
    SubcategoryModel subcategoryModel;
    ImageView back,img_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_product);
        filterlist=findViewById(R.id.filterlist);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubProductActivity.this, CartActivity.class));
            }
        });
        subcategorylist=findViewById(R.id.subcategorylist);
        filterModelList=new ArrayList<>();
        filterAdapter = new FilterAdapter(this,filterModelList);
        filterdata();
        LinearLayoutManager horizontaLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        filterlist.setLayoutManager(horizontaLayoutManagaer);
        filterlist.setAdapter(filterAdapter);

        subcategoryModelList=new ArrayList<>();
        subcategoryAdapter = new SubcategoryAdapter(this,subcategoryModelList);
        subcategorydata();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,2);
        subcategorylist.setLayoutManager(mLayoutManager);
        subcategorylist.addItemDecoration(new CategoryFragment.GridSpacingItemDecoration(2, dpToPx(0), true));
        subcategorylist.setItemAnimator(new DefaultItemAnimator());
        subcategorylist.setAdapter(subcategoryAdapter);
    }
    private void subcategorydata() {
        for(int i=0;i<12;i++){

            subcategoryModel=new SubcategoryModel();
            subcategoryModel.setProductname("");
            subcategoryModel.setPrice("");
            subcategoryModel.setDiscount("");
            subcategoryModelList.add(subcategoryModel);
        }

        subcategoryAdapter.notifyDataSetChanged();
    }

    private void filterdata() {
        for(int i=0;i<11;i++){

            filterModel=new FilterModel();
            filterModel.setProductname("");
            filterModel.setPrice("");
            filterModel.setDiscount("");
            filterModelList.add(filterModel);
        }

        filterAdapter.notifyDataSetChanged();
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
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}