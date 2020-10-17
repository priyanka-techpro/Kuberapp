package com.techprostudio.kuberinternational;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.techprostudio.kuberinternational.Activity.DashboardActivity;
import com.techprostudio.kuberinternational.Activity.NumberVerifyActivity;
import com.techprostudio.kuberinternational.Activity.OtpVerifyActivity;
import com.techprostudio.kuberinternational.Activity.SigninActivity;
import com.techprostudio.kuberinternational.Location.LocationSetting;
import com.techprostudio.kuberinternational.Location.LocationUtils;
import com.techprostudio.kuberinternational.Location.MarshMallowPermissions;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {
RelativeLayout gotologin;
    private int seekBarProgress = 0;
    private int progressDuration = 50;
    private Double latitude;
    private Double longitude;
    private boolean waitDone = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        gotologin=findViewById(R.id.gotologin);
        gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }
    Handler handler = new Handler();
    Runnable r = new Runnable() {

        @Override
        public void run() {

            seekBarProgress = seekBarProgress + 1;
            if (seekBarProgress <= 100)
                handler.postDelayed(r, progressDuration);
            else
                checkNecessaryPermissions();
//                startApp();
        }
    };


    private void startApp() {
//        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);

        if (new AppPreference(SplashActivity.this).getUserEmail().equals(""))
        {
            Intent i = new Intent(SplashActivity.this, NumberVerifyActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
        else
        {
            Intent i = new Intent(SplashActivity.this, DashboardActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }

    }
    private void checkNecessaryPermissions() {

        MarshMallowPermissions mmPermission = new MarshMallowPermissions(SplashActivity.this);
        //boolean checkPermissionResponse = mmPermission.checkPermissionForWriteExternalStorage();
        if (mmPermission.isAllSplashPermissionAllowed()) {
            onSuccessGpsPermission();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case MarshMallowPermissions.SPLASH_AT_ONCE_REQUEST_CODE: {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
//                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
//                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_CALENDAR, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_COARSE_LOCATION, PackageManager.PERMISSION_GRANTED);
//                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                // Check for
                if (perms.get(Manifest.permission.WRITE_CALENDAR) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                {
                    checkNecessaryPermissions();
                } else {
                    // Permission Denied
                    new MarshMallowPermissions(SplashActivity.this).showMessageOKCancel(getString(R.string.cannot_proceed_permission_msg),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    onBackPressed();
                                    return;
                                }
                            });
                }
            }
            break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    } //End of OnPermissionResult
    private void onSuccessGpsPermission() {
        //flagReadyForNextPage = true;
        LocationUtils.checkGpsStatus(SplashActivity.this, new LocationSetting() {
            @Override
            public void onLocationSettingAlertCancelled() {
                finish();
            }

            @Override
            public void onLocationSettingActivityOpened() {
                //
            }

            @Override
            public void onGpsAlreadyEnabled() {
                if (new AppPreference(SplashActivity.this).getLat() != null && new AppPreference(SplashActivity.this).getLat() != 0 &&
                        new AppPreference(SplashActivity.this).getLong() != null && new AppPreference(SplashActivity.this).getLong() != 0) {
                    latitude = new AppPreference(SplashActivity.this).getLat();
                    longitude = new AppPreference(SplashActivity.this).getLong();
                } else {
                }
                Log.e("TAG", "Latitude: " + latitude + " \nLongitude: " + longitude);
                startApp();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (waitDone) {
            handler.postDelayed(r, progressDuration);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    waitDone = true;
                    handler.postDelayed(r, progressDuration);

                }
            }, 2 * 500);
        }
    }
}