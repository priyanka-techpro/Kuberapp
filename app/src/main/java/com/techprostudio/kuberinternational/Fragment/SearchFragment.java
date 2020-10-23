package com.techprostudio.kuberinternational.Fragment;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.techprostudio.kuberinternational.Activity.DashboardActivity;

import com.techprostudio.kuberinternational.Adapter.SearchAdapter;


import com.techprostudio.kuberinternational.Model.SearchPAckage.ProductList;
import com.techprostudio.kuberinternational.Model.SearchPAckage.SearchMainModel;
import com.techprostudio.kuberinternational.Model.SearchPAckage.VariationProduct_search;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.ArrayList;
import java.util.List;

import static com.techprostudio.kuberinternational.Activity.DashboardActivity.product;
import static com.techprostudio.kuberinternational.Activity.DashboardActivity.titlebar;

public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    RecyclerView searchView;
    List<ProductList> searchlist;
    SearchAdapter searchAdapter;
    Snackbar mSnackbar;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_search, container, false);
        searchView=v.findViewById(R.id.searchList);
        titlebar.setVisibility(View.GONE);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);

        String customerid=new AppPreference(getActivity()).getUserId();
        if (InternetAccess.isConnected(getActivity())) {
       //     String searchText=product;
            getSearchedProduct(product,customerid);
        }
        else {
            mSnackbar = Snackbar
                    .make(DashboardActivity.main, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).
                            setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    mSnackbar.dismiss();

                                }
                            });
            mSnackbar.show();
        }
        searchlist=new ArrayList<>();
        return v;
    }

    private void getSearchedProduct(String searchText, String customerid) {
        Call<SearchMainModel> call=apiInterface.searchProduct(Config.header,searchText,customerid);
        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        call.enqueue(new Callback<SearchMainModel>() {
            @Override
            public void onResponse(Call<SearchMainModel> call, Response<SearchMainModel> response) {
                progressDialog.dismiss();
                try {
                    if (response.body().isStatus() == true)
                    {
                        searchlist = response.body().getProductList();
                        searchAdapter = new SearchAdapter(getActivity(),searchlist);
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
                        searchView.setLayoutManager(mLayoutManager);
                        searchView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(0), true));
                        searchView.setItemAnimator(new DefaultItemAnimator());
                        searchView.setAdapter(searchAdapter);
                        searchAdapter.notifyDataSetChanged();

                        String msg = response.body().getMessage();
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        String msg = response.body().getMessage();
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<SearchMainModel> call, Throwable t) {
                progressDialog.dismiss();
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
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}