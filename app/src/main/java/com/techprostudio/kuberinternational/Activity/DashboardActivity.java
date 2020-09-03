package com.techprostudio.kuberinternational.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.techprostudio.kuberinternational.Adapter.CategoryAdapter;
import com.techprostudio.kuberinternational.Adapter.NewArrivalAdapter;
import com.techprostudio.kuberinternational.Adapter.SliderAdapter;
import com.techprostudio.kuberinternational.Model.CategoryModel;
import com.techprostudio.kuberinternational.Model.NewArrivalModel;
import com.techprostudio.kuberinternational.Model.SliderItem;
import com.techprostudio.kuberinternational.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

public class DashboardActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce= false;
    private AppBarConfiguration mAppBarConfiguration;
    ViewPager2 myViewPager2;
   public static TextView titlebar;
   public static ImageView drawer_open,back,img_cart;
   public static LinearLayout mainlayout;
    public static RelativeLayout ll_dashboard,ll_profile,ll_product,ll_offer,ll_cart,ll_ordrhistory,ll_helpfaq,logout;
    DrawerLayout drawer;
    private Handler sliderHandler = new Handler();
    RecyclerView categorylist,newarrivallist;
    private List<CategoryModel> categoryModelList;
    private List<NewArrivalModel> newArrivalModelList;
    private CategoryAdapter categoryAdapter;
    private NewArrivalAdapter newArrivalAdapter;
    CategoryModel categoryModel;
    NewArrivalModel newArrivalModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        myViewPager2=findViewById(R.id.viewpager);
        categorylist=findViewById(R.id.categorylist);
        newarrivallist=findViewById(R.id.newarrivallist);
        titlebar=findViewById(R.id.titlebar);
        drawer_open=findViewById(R.id.drawer_open);
        back=findViewById(R.id.back);
        mainlayout=findViewById(R.id.mainlayout);
        img_cart=findViewById(R.id.img_cart);
        ll_dashboard=findViewById(R.id.ll_dashboard);
        ll_profile=findViewById(R.id.ll_profile);
        ll_product=findViewById(R.id.ll_product);
        ll_offer=findViewById(R.id.ll_offer);
        ll_cart=findViewById(R.id.ll_cart);
        ll_ordrhistory=findViewById(R.id.ll_ordrhistory);
        ll_helpfaq=findViewById(R.id.ll_helpfaq);
        logout=findViewById(R.id.logout);

        drawer_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }

        });

        ll_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer();

                ll_dashboard.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                ll_profile.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_product.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_offer.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_cart.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_ordrhistory.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_helpfaq.setBackgroundColor(getResources().getColor(R.color.transparent));
                logout.setBackgroundColor(getResources().getColor(R.color.transparent));
                Intent i = new Intent(DashboardActivity.this,DashboardActivity.class);
                startActivity(i);
            }
        });

        ll_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer();

                ll_dashboard.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_profile.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                ll_product.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_offer.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_cart.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_ordrhistory.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_helpfaq.setBackgroundColor(getResources().getColor(R.color.transparent));
                logout.setBackgroundColor(getResources().getColor(R.color.transparent));
                Intent i = new Intent(DashboardActivity.this,ProfileActivity.class);
                startActivity(i);
            }
        });

        ll_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer();

                ll_dashboard.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_profile.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_product.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                ll_offer.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_cart.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_ordrhistory.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_helpfaq.setBackgroundColor(getResources().getColor(R.color.transparent));
                logout.setBackgroundColor(getResources().getColor(R.color.transparent));
                Intent i = new Intent(DashboardActivity.this,CategoryMasterActivity.class);
                startActivity(i);
            }
        });


        ll_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer();

                ll_dashboard.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_profile.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_product.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_offer.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                ll_cart.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_ordrhistory.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_helpfaq.setBackgroundColor(getResources().getColor(R.color.transparent));
                logout.setBackgroundColor(getResources().getColor(R.color.transparent));
                Intent i = new Intent(DashboardActivity.this,OfferActivity.class);
                startActivity(i);
            }
        });


        ll_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer();

                ll_dashboard.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_profile.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_product.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_offer.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_cart.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                ll_ordrhistory.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_helpfaq.setBackgroundColor(getResources().getColor(R.color.transparent));
                logout.setBackgroundColor(getResources().getColor(R.color.transparent));
                Intent i = new Intent(DashboardActivity.this,CartActivity.class);
                startActivity(i);
            }
        });
        ll_ordrhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer();

                ll_dashboard.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_profile.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_product.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_offer.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_cart.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_ordrhistory.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                ll_helpfaq.setBackgroundColor(getResources().getColor(R.color.transparent));
                logout.setBackgroundColor(getResources().getColor(R.color.transparent));
                Intent i = new Intent(DashboardActivity.this,OrderHistoryActivity.class);
                startActivity(i);
            }
        });
        ll_helpfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer();

                ll_dashboard.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_profile.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_product.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_offer.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_cart.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_ordrhistory.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_helpfaq.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                logout.setBackgroundColor(getResources().getColor(R.color.transparent));
                Intent i = new Intent(DashboardActivity.this,HelpandfaqActivity.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeDrawer();
                ll_dashboard.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_profile.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_product.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_offer.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_cart.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_ordrhistory.setBackgroundColor(getResources().getColor(R.color.transparent));
                ll_helpfaq.setBackgroundColor(getResources().getColor(R.color.transparent));
                logout.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                Intent i = new Intent(DashboardActivity.this,NumberVerifyActivity.class);
                startActivity(i);
            }
        });





        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, CartActivity.class));

            }
        });
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.bannerone));
        sliderItems.add(new SliderItem(R.drawable.bannerone));
        sliderItems.add(new SliderItem(R.drawable.bannerone));


        myViewPager2.setAdapter(new SliderAdapter(sliderItems,myViewPager2));
        myViewPager2.setClipToPadding(false);
        myViewPager2.setClipChildren(false);
        myViewPager2.setOffscreenPageLimit(3);
        myViewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        myViewPager2.setPageTransformer(compositePageTransformer);

        myViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,3000);
            }
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        categoryModelList=new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this,categoryModelList);
        categorydata();
        LinearLayoutManager horizontaLayoutManagaer = new LinearLayoutManager(DashboardActivity.this, LinearLayoutManager.HORIZONTAL, false);
        categorylist.setLayoutManager(horizontaLayoutManagaer);
        categorylist.setAdapter(categoryAdapter);

        newArrivalModelList=new ArrayList<>();
        newArrivalAdapter = new NewArrivalAdapter(DashboardActivity.this,newArrivalModelList);
        newArrivaldata();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(DashboardActivity.this,2);
        newarrivallist.setLayoutManager(mLayoutManager);
        newarrivallist.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(0), true));
        newarrivallist.setItemAnimator(new DefaultItemAnimator());
        newarrivallist.setAdapter(newArrivalAdapter);
    }

    private void newArrivaldata() {
        for(int i=0;i<12;i++){

            newArrivalModel=new NewArrivalModel();
            newArrivalModel.setProductname("");
            newArrivalModel.setPrice("");
            newArrivalModel.setDiscount("");
            newArrivalModelList.add(newArrivalModel);
        }

        newArrivalAdapter.notifyDataSetChanged();
    }

    private void categorydata() {
        for(int i=0;i<11;i++){

            categoryModel=new CategoryModel();
            categoryModel.setProductname("");
            categoryModel.setPrice("");
            categoryModel.setDiscount("");
            categoryModelList.add(categoryModel);
        }

        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {

            myViewPager2.setCurrentItem(myViewPager2.getCurrentItem() + 1);
        }
    };
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
    private void closeDrawer() {

        drawer.closeDrawer(Gravity.LEFT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ll_dashboard.setBackgroundColor(getResources().getColor(R.color.lightgrey));
        ll_profile.setBackgroundColor(getResources().getColor(R.color.transparent));
        ll_product.setBackgroundColor(getResources().getColor(R.color.transparent));
        ll_offer.setBackgroundColor(getResources().getColor(R.color.transparent));
        ll_cart.setBackgroundColor(getResources().getColor(R.color.transparent));
        ll_ordrhistory.setBackgroundColor(getResources().getColor(R.color.transparent));
        ll_helpfaq.setBackgroundColor(getResources().getColor(R.color.transparent));
        logout.setBackgroundColor(getResources().getColor(R.color.transparent));
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        FragmentManager fm = getSupportFragmentManager();
        Log.e("back stack entry", fm.getBackStackEntryCount() + "");

        if (fm.getBackStackEntryCount() >1) {
            fm.popBackStack();
            //super.onBackPressed();
            //  return;
        }
        else if (fm.getBackStackEntryCount() == 1) {
            fm.popBackStack();
            closeDrawer();


        }


        else {
            if (doubleBackToExitPressedOnce) {
                fm.popBackStack();
                //  super.onBackPressed();
                return;
            }

        }
    }
}