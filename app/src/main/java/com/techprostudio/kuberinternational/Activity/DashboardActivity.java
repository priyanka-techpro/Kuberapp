package com.techprostudio.kuberinternational.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.techprostudio.kuberinternational.Adapter.CategoryAdapter;
import com.techprostudio.kuberinternational.Adapter.NewArrivalAdapter;
import com.techprostudio.kuberinternational.Adapter.SliderAdapter;
import com.techprostudio.kuberinternational.Fragment.SearchFragment;
import com.techprostudio.kuberinternational.Model.DashboardModel.BannerList;
import com.techprostudio.kuberinternational.Model.DashboardModel.DashboardMainModel;
import com.techprostudio.kuberinternational.Model.DashboardModel.NewArrival;
import com.techprostudio.kuberinternational.Model.DashboardModel.ParentCategory;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce= false;
    private AppBarConfiguration mAppBarConfiguration;
    ViewPager2 myViewPager2;
   public static TextView titlebar,proname,tv_count;
   public static ImageView drawer_open,back,img_cart,img_notify;
   public static LinearLayout mainlayout;
    public static RelativeLayout cart_count,ll_dashboard,ll_profile,ll_product,ll_offer,ll_cart,ll_ordrhistory,ll_helpfaq,logout;
    DrawerLayout drawer;
    private Handler sliderHandler = new Handler();
    RecyclerView categorylist,newarrivallist;
    private List<ParentCategory> categoryModelList;
    private List<NewArrival> newArrivalModelList;
    private CategoryAdapter categoryAdapter;
    private NewArrivalAdapter newArrivalAdapter;
    public static LinearLayout main;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    public static EditText search;
    public static  String product;
    String device_token;
    List<BannerList> sliderItems;
    ProgressDialog progressDialog;

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
        View header = navigationView.getHeaderView(0);
        myViewPager2=findViewById(R.id.viewpager);
        categorylist=findViewById(R.id.categorylist);
        newarrivallist=findViewById(R.id.newarrivallist);
        titlebar=findViewById(R.id.titlebar);
        drawer_open=findViewById(R.id.drawer_open);
        search = findViewById(R.id.search);
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
        main=findViewById(R.id.main);
        cart_count=findViewById(R.id.cart_count);
        tv_count=findViewById(R.id.tv_count);
        proname=header.findViewById(R.id.proname);
        img_notify=findViewById(R.id.img_notify);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);

        String username=new AppPreference(DashboardActivity.this).getUserName();
        String customerid=new AppPreference(DashboardActivity.this).getUserId();
        proname.setText("Hi "+username);
        drawer_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.LEFT);
            }

        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("edit111",""+s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("edit222",""+s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("edit333",""+s);
                product = String.valueOf(s);
                if (product.length() !=0)
                {

                    mainlayout.setVisibility(View.GONE);
                    drawer_open.setVisibility(View.VISIBLE);
                    back.setVisibility(View.GONE);
                    titlebar.setVisibility(View.GONE);
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.addToBackStack("top");
                    transaction.add(R.id.nav_host_fragment, new SearchFragment()).commit();

                }
                else
                {
                    titlebar.setVisibility(View.VISIBLE);
                    mainlayout.setVisibility(View.VISIBLE);
                    ll_dashboard.setBackgroundColor(getResources().getColor(R.color.lightgrey));
                    ll_profile.setBackgroundColor(getResources().getColor(R.color.transparent));
                    ll_product.setBackgroundColor(getResources().getColor(R.color.transparent));
                    ll_offer.setBackgroundColor(getResources().getColor(R.color.transparent));
                    ll_cart.setBackgroundColor(getResources().getColor(R.color.transparent));
                    ll_ordrhistory.setBackgroundColor(getResources().getColor(R.color.transparent));
                    ll_helpfaq.setBackgroundColor(getResources().getColor(R.color.transparent));
                    logout.setBackgroundColor(getResources().getColor(R.color.transparent));
                }

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

                if (InternetAccess.isConnected(DashboardActivity.this)) {
                    funLogout(customerid);
                } else {
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
        });





        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, CartActivity.class));

            }
        });
        img_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, WishListActivity.class));

            }
        });

         sliderItems = new ArrayList<>();
//        sliderItems.add(new SliderItem(R.drawable.bannerone));
//        sliderItems.add(new SliderItem(R.drawable.bannerone));
//        sliderItems.add(new SliderItem(R.drawable.bannerone));
        getBanner(customerid);



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


        newArrivalModelList=new ArrayList<>();

    }

    private void getBanner(String customerid) {
        Call<DashboardMainModel> call=apiInterface.getDashboardProduct(Config.header,customerid);
        progressDialog = new ProgressDialog(DashboardActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<DashboardMainModel>() {
            @Override
            public void onResponse(Call<DashboardMainModel> call, Response<DashboardMainModel> response) {
                progressDialog.dismiss();
                if(response.body().isStatus() == true)
                {
                    String cartCount = String.valueOf(response.body().getCartCount());
                    Config.cart = cartCount;
                    if (cartCount.equals("0")) {
                        cart_count.setVisibility(View.GONE);
                        tv_count.setVisibility(View.GONE);
                    } else {
                        cart_count.setVisibility(View.VISIBLE);
                        tv_count.setVisibility(View.VISIBLE);
                        tv_count.setText(Config.cart);
                    }
                    sliderItems=response.body().getBannerList();
                    myViewPager2.setAdapter(new SliderAdapter(sliderItems,myViewPager2,DashboardActivity.this));
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
                    categoryModelList=response.body().getParentCategory();
                    categoryAdapter = new CategoryAdapter(DashboardActivity.this,categoryModelList);
                    LinearLayoutManager horizontaLayoutManagaer = new LinearLayoutManager(DashboardActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    categorylist.setLayoutManager(horizontaLayoutManagaer);
                    categorylist.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();

                    newArrivalModelList=response.body().getNewArrivals();
                    newArrivalAdapter = new NewArrivalAdapter(DashboardActivity.this,newArrivalModelList);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(DashboardActivity.this,2);
                    newarrivallist.setLayoutManager(mLayoutManager);
                    newarrivallist.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(0), true));
                    newarrivallist.setItemAnimator(new DefaultItemAnimator());
                    newarrivallist.setAdapter(newArrivalAdapter);
                    newArrivalAdapter.notifyDataSetChanged();

                }
                else
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(DashboardActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DashboardMainModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void funLogout(String customerid)
    {
        Call<JsonObject> call=apiInterface.logoutFun(Config.header,customerid);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {

                    JsonObject object = response.body();
                    Log.e("Logout",""+object);

                    JsonElement jsonObject = object;

                    JSONObject converintoJsonObject = new JSONObject(String.valueOf(jsonObject));

                    if(converintoJsonObject.getString("status").equals("true"))
                    {
                        Toast.makeText(DashboardActivity.this, converintoJsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        device_token = new AppPreference(DashboardActivity.this).getDeviceToken();
                        new AppPreference(DashboardActivity.this).clearUserId();
                        new AppPreference(DashboardActivity.this).clearPreference();
                        new AppPreference(DashboardActivity.this).saveDeviceToken(device_token);



                        Intent intent = new Intent(DashboardActivity.this, NumberVerifyActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(DashboardActivity.this,converintoJsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    }


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
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