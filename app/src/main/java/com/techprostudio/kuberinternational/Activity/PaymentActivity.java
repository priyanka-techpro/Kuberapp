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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techprostudio.kuberinternational.Adapter.CartAdapter;
import com.techprostudio.kuberinternational.Adapter.PaymentAdapter;
import com.techprostudio.kuberinternational.Model.CartListModel;
import com.techprostudio.kuberinternational.Model.PaymentModel;
import com.techprostudio.kuberinternational.R;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    TextView gotoofferpage;
    RecyclerView paymentlist;
    ArrayList<PaymentModel> paymentModelArrayList;
    PaymentModel paymentModel;
    PaymentAdapter paymentAdapter;
    RelativeLayout proceedtopay;
    ImageView back,img_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        paymentlist=findViewById(R.id.paymentlist);
        proceedtopay=findViewById(R.id.proceedtopay);
        gotoofferpage=findViewById(R.id.gotoofferpage);
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
                startActivity(new Intent(PaymentActivity.this, CartActivity.class));
            }
        });

        gotoofferpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, OfferActivity.class));

            }
        });
        proceedtopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PaymentActivity.this, OrderConfirmationActivity.class));

            }
        });
        paymentModelArrayList=new ArrayList<>();
        paymentAdapter=new PaymentAdapter(this,paymentModelArrayList);
        paymentitems();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        paymentlist.setLayoutManager(mLayoutManager);
        paymentlist.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
        paymentlist.setItemAnimator(new DefaultItemAnimator());
        paymentlist.setAdapter(paymentAdapter);
    }

    private void paymentitems() {
        for(int i=0;i<2;i++){

            paymentModel=new PaymentModel();
            paymentModel.setProductname("");
            paymentModel.setPrice("");
            paymentModel.setDiscount("");
            paymentModelArrayList.add(paymentModel);
        }
        paymentAdapter.notifyDataSetChanged();
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