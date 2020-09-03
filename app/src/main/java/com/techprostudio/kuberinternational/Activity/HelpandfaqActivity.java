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

import com.techprostudio.kuberinternational.Adapter.FaqAdapter;
import com.techprostudio.kuberinternational.Model.FaqModel;
import com.techprostudio.kuberinternational.R;

import java.util.ArrayList;
import java.util.List;

public class HelpandfaqActivity extends AppCompatActivity {
    RecyclerView faqlist;
    FaqModel faqmodel;
    private List<FaqModel> faqarrlist;
    private FaqAdapter faqAdapter;
    ImageView back,img_cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpandfaq);
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });


        faqlist=findViewById(R.id.faqlist);
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
                startActivity(new Intent(HelpandfaqActivity.this, CartActivity.class));
            }
        });

        faqarrlist = new ArrayList<>();
        faqAdapter = new FaqAdapter(this,faqarrlist);
        faqdata();
        RecyclerView.LayoutManager mLayoutManager1 = new GridLayoutManager(this,1);
        faqlist.setLayoutManager(mLayoutManager1);
        faqlist.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(0), true));
        faqlist.setItemAnimator(new DefaultItemAnimator());
        faqlist.setAdapter(faqAdapter);
    }
    private void faqdata() {
        for(int i=0;i<6;i++){

            faqmodel=new FaqModel();
            faqmodel.setProductname("");
            faqmodel.setPrice("");
            faqmodel.setDiscount("");
            faqarrlist.add(faqmodel);
        }
        faqAdapter.notifyDataSetChanged();
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