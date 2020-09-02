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
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.Adapter.AddressListAdapter;
import com.techprostudio.kuberinternational.Adapter.CartAdapter;
import com.techprostudio.kuberinternational.Model.AddressListModel;
import com.techprostudio.kuberinternational.Model.CartListModel;
import com.techprostudio.kuberinternational.R;

import java.util.ArrayList;

public class ChangeAddressActivity extends AppCompatActivity {
    RelativeLayout gotoaddaddress;
    RecyclerView addresslist;
    ArrayList<AddressListModel> addressListModelArrayList;
    AddressListModel addressListModel;
    AddressListAdapter addressListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);
        gotoaddaddress=findViewById(R.id.gotoaddaddress);
        addresslist=findViewById(R.id.addresslist);
        addressListModelArrayList=new ArrayList<>();
        addressListAdapter=new AddressListAdapter(this,addressListModelArrayList);
        addressitems();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        addresslist.setLayoutManager(mLayoutManager);
        addresslist.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(4), true));
        addresslist.setItemAnimator(new DefaultItemAnimator());
        addresslist.setAdapter(addressListAdapter);
        gotoaddaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeAddressActivity.this, AddAddressActivity.class));

            }
        });
    }

    private void addressitems() {
        for(int i=0;i<2;i++){

            addressListModel=new AddressListModel();
            addressListModel.setProductname("");
            addressListModel.setPrice("");
            addressListModel.setDiscount("");
            addressListModelArrayList.add(addressListModel);
        }
        addressListAdapter.notifyDataSetChanged();
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