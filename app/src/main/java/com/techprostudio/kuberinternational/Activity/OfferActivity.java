package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.techprostudio.kuberinternational.Adapter.OfferAdapter;
import com.techprostudio.kuberinternational.Adapter.OrderDetailsAdapter;
import com.techprostudio.kuberinternational.Model.OfferModel;
import com.techprostudio.kuberinternational.Model.OrderDetailsModel;
import com.techprostudio.kuberinternational.R;

import java.util.ArrayList;

public class OfferActivity extends AppCompatActivity {
    RecyclerView offerslist;
    ArrayList<OfferModel> offerModelArrayList;
    OfferModel offerModel;
    OfferAdapter offerAdapter;
    ImageView back,img_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        offerslist=findViewById(R.id.offerslist);
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
                startActivity(new Intent(OfferActivity.this, CartActivity.class));
            }
        });
        offerModelArrayList=new ArrayList<>();
        offerAdapter=new OfferAdapter(this,offerModelArrayList);
        offeritems();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        offerslist.setLayoutManager(mLayoutManager);
        offerslist.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
        offerslist.setItemAnimator(new DefaultItemAnimator());
        offerslist.setAdapter(offerAdapter);
    }
    private void offeritems() {
        for(int i=0;i<5;i++){

            offerModel=new OfferModel();
            offerModel.setProductname("");
            offerModel.setPrice("");
            offerModel.setDiscount("");
            offerModelArrayList.add(offerModel);
        }
        offerAdapter.notifyDataSetChanged();

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