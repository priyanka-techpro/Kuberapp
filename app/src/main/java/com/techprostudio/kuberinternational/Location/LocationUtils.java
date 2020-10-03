package com.techprostudio.kuberinternational.Location;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.LocationManager;
import android.provider.Settings;
import android.util.Log;


import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.util.List;

public class LocationUtils {
    private static double latitude = 0.0;
    private static double longitude = 0.0;
    private LocationSetting locationSetting;
    private static List<Address> addressDetailsList;

    public static void checkGpsStatus(Activity activity, LocationSetting locationSetting) {
        final LocationManager manager = (LocationManager) activity.getSystemService(activity.LOCATION_SERVICE);

        try{
            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                showSettingsAlert(activity, locationSetting);
            } else {
                locationSetting.onGpsAlreadyEnabled();
                GPSTracker gpsTracker = new GPSTracker(activity);
                if(Double.valueOf(gpsTracker.getLatitude()) != null) {
                    AppPreference appSharedPrefs = new AppPreference(activity);
                    appSharedPrefs.setLat(gpsTracker.getLatitude());
//                latitude = Double.valueOf(gpsTracker.getLatitude());
                }
                if(Double.valueOf(gpsTracker.getLongitude()) != null) {
                    AppPreference appSharedPrefs = new AppPreference(activity);
                    appSharedPrefs.setLong(gpsTracker.getLongitude());
//                longitude = Double.valueOf(gpsTracker.getLongitude());
                }

                if (gpsTracker.getGeocoderAddress(activity).size() > 0) {
//                addressDetailsList.clear();
                    addressDetailsList = gpsTracker.getGeocoderAddress(activity);

                    Log.e("fdjgbkljfdlkb",""+addressDetailsList.get(0).getPostalCode());

                    new AppPreference(activity).saveAddress(addressDetailsList.get(0).getAddressLine(0)); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    Log.e("TAG", "Address: " + new AppPreference(activity).getAddress());
                    new AppPreference(activity).saveCity(addressDetailsList.get(0).getLocality());
                    Log.e("TAG", "City: " + new AppPreference(activity).getCity());
                    new AppPreference(activity).saveState(addressDetailsList.get(0).getAdminArea());
                    Log.e("TAG", "State: " + new AppPreference(activity).getState());
                    new AppPreference(activity).saveCountry(addressDetailsList.get(0).getCountryName());
                    Log.e("TAG", "Country: " + new AppPreference(activity).getCountry());
                    Log.e("TAG", "Country: " + new AppPreference(activity).getCountry());
                    new AppPreference(activity).saveCountryCode(addressDetailsList.get(0).getCountryCode());
                    Log.e("TAG", "Country code: " + new AppPreference(activity).getCountryCode());
                    new AppPreference(activity).savePostalCode(addressDetailsList.get(0).getPostalCode());
                    Log.e("TAG","Postal code: "+ new AppPreference(activity).getPostalCode());


                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }

    public static double getLatitude() {
        return latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    /**
     * Function to show settings alert dialog
     * On pressing Settings button will launch Settings Options
     */
    public static void showSettingsAlert(final Activity activity, final LocationSetting locationSetting) {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(activity);

        // Setting Dialog Title
        alertDialog.setTitle("GPS Alert");
        alertDialog.setCancelable(false);

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                activity.startActivity(intent);
                locationSetting.onLocationSettingActivityOpened();
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                locationSetting.onLocationSettingAlertCancelled();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
