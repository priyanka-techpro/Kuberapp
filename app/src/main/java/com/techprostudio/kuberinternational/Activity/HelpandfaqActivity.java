package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.techprostudio.kuberinternational.Adapter.FaqAdapter;
import com.techprostudio.kuberinternational.Model.FaqMainModel;
import com.techprostudio.kuberinternational.Model.FaqModelPackage.FaqContent;
import com.techprostudio.kuberinternational.Model.FaqModelPackage.FaqMainSubModel;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;

import java.util.ArrayList;
import java.util.List;

public class HelpandfaqActivity extends AppCompatActivity {
    RecyclerView faqlist;

    private List<FaqContent> faqarrlist;
    private FaqAdapter faqAdapter;
    ImageView back,img_cart;
    TextView help_det;
    RelativeLayout main;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    ProgressDialog progressDialog,progressDialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpandfaq);

        faqlist=findViewById(R.id.faqlist);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        help_det=findViewById(R.id.help_det);
        main=findViewById(R.id.main);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        faqarrlist = new ArrayList<>();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HelpandfaqActivity.this, CartActivity.class));
            }
        });
        if (InternetAccess.isConnected(HelpandfaqActivity.this)) {
            getFaqData();
            faqdata();
        }
        else {
            mSnackbar = Snackbar
                    .make(main, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).
                            setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    mSnackbar.dismiss();

                                }
                            });
            mSnackbar.show();
        }


    }

    private void getFaqData() {
        Call<FaqMainModel> call=apiInterface.GetFaqContents(Config.header);
        progressDialog = new ProgressDialog(HelpandfaqActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<FaqMainModel>() {
            @Override
            public void onResponse(Call<FaqMainModel> call, Response<FaqMainModel> response) {
                progressDialog.dismiss();
                if(response.body().getStatus()==true)
                {
                String faqdata=response.body().getHelpContent();
                help_det.setText(faqdata);
                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(HelpandfaqActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FaqMainModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void faqdata() {
       Call<FaqMainSubModel> call=apiInterface.GetFaqContentsSub(Config.header);
        progressDialog1 = new ProgressDialog(HelpandfaqActivity.this);
        progressDialog1.setMessage("Please wait...");
        progressDialog1.show();
       call.enqueue(new Callback<FaqMainSubModel>() {
           @Override
           public void onResponse(Call<FaqMainSubModel> call, Response<FaqMainSubModel> response) {
               progressDialog1.dismiss();
               if(response.body().getStatus()==true) {
                   faqarrlist=response.body().getFaqContent();
                   faqAdapter = new FaqAdapter(HelpandfaqActivity.this,faqarrlist);
                   RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(HelpandfaqActivity.this,1);
                   faqlist.setLayoutManager(mLayoutManager1);
                   faqlist.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(0), true));
                   faqlist.setItemAnimator(new DefaultItemAnimator());
                   faqlist.setAdapter(faqAdapter);
                   faqAdapter.notifyDataSetChanged();
               }
               else{
                   String msg=response.body().getMessage();
                   Toast.makeText(HelpandfaqActivity.this, msg, Toast.LENGTH_SHORT).show();
               }

           }

           @Override
           public void onFailure(Call<FaqMainSubModel> call, Throwable t) {
               progressDialog1.dismiss();
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