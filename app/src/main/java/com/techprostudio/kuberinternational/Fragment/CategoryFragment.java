package com.techprostudio.kuberinternational.Fragment;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.techprostudio.kuberinternational.Activity.DashboardActivity;
import com.techprostudio.kuberinternational.Adapter.CategoryAdapter;
import com.techprostudio.kuberinternational.Adapter.FilterAdapter;
import com.techprostudio.kuberinternational.Adapter.NewArrivalAdapter;
import com.techprostudio.kuberinternational.Adapter.SubcategoryAdapter;
import com.techprostudio.kuberinternational.Model.CategoryModel;
import com.techprostudio.kuberinternational.Model.FilterModel;
import com.techprostudio.kuberinternational.Model.NewArrivalModel;
import com.techprostudio.kuberinternational.Model.SubcategoryModel;
import com.techprostudio.kuberinternational.R;

import java.util.ArrayList;
import java.util.List;

import static com.techprostudio.kuberinternational.Activity.DashboardActivity.back;
import static com.techprostudio.kuberinternational.Activity.DashboardActivity.drawer_open;
import static com.techprostudio.kuberinternational.Activity.DashboardActivity.titlebar;

public class CategoryFragment extends Fragment {


    public CategoryFragment() {
        // Required empty public constructor
    }

  RecyclerView filterlist,subcategorylist;
    private List<FilterModel> filterModelList;
    private List<SubcategoryModel> subcategoryModelList;
    private FilterAdapter filterAdapter;
    private SubcategoryAdapter subcategoryAdapter;
    FilterModel filterModel;
    SubcategoryModel subcategoryModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_category, container, false);
        drawer_open.setVisibility(View.GONE);
        back.setVisibility(View.VISIBLE);
        titlebar.setText("Furnishing");
        filterlist=v.findViewById(R.id.filterlist);
        subcategorylist=v.findViewById(R.id.subcategorylist);
        filterModelList=new ArrayList<>();
        filterAdapter = new FilterAdapter(getActivity(),filterModelList);
        filterdata();
        LinearLayoutManager horizontaLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        filterlist.setLayoutManager(horizontaLayoutManagaer);
        filterlist.setAdapter(filterAdapter);

        subcategoryModelList=new ArrayList<>();
        subcategoryAdapter = new SubcategoryAdapter(getActivity(),subcategoryModelList);
        subcategorydata();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
        subcategorylist.setLayoutManager(mLayoutManager);
        subcategorylist.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(0), true));
        subcategorylist.setItemAnimator(new DefaultItemAnimator());
        subcategorylist.setAdapter(subcategoryAdapter);
        return v;
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