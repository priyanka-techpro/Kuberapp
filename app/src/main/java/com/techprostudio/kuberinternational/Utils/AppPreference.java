package com.techprostudio.kuberinternational.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class AppPreference {
    private static final String APP_SHARED_PREFS = "com.techprostudio.arewah";
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    public AppPreference(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public void clearPreference() {
        this.prefsEditor.clear();
        this.prefsEditor.commit();
    }

    public void clearUserId(){
        this.prefsEditor.putString("userId", "");
        this.prefsEditor.clear();
        this.prefsEditor.apply();
    }


    public void saveDeviceToken(String token) {
        prefsEditor.putString("device_token", token);
        prefsEditor.commit();
    }
    public String getDeviceToken() {
        return appSharedPrefs.getString("device_token", "");
    }

    public void saveUserID(String userId) {
        prefsEditor.putString("userId", userId);
        prefsEditor.commit();
    }

    public String getUserId() {
        return appSharedPrefs.getString("userId", "");
    }

    public void saveUserType(String user_type) {
        prefsEditor.putString("user_type", user_type);
        prefsEditor.commit();
    }

    public String getUserType() {
        return appSharedPrefs.getString("user_type", "");
    }

    public void saveUserName(String user_name) {
        prefsEditor.putString("user_name", user_name);
        prefsEditor.commit();
    }

    public String getUserName() {
        return appSharedPrefs.getString("user_name", "");
    }


    public void saveUserEmail(String user_email) {
        prefsEditor.putString("user_email", user_email);
        prefsEditor.commit();
    }

    public String getUserEmail() {
        return appSharedPrefs.getString("user_email", "");
    }

    public void saveUserPhone(String user_phone) {
        prefsEditor.putString("user_phone", user_phone);
        prefsEditor.commit();
    }

    public String getUserPhone() {
        return appSharedPrefs.getString("user_phone", "");
    }

    public void saveUserPhoneTwo(String user_phone_two) {
        prefsEditor.putString("user_phone_two", user_phone_two);
        prefsEditor.commit();
    }

    public String getUserPhoneTwo() {
        return appSharedPrefs.getString("user_phone_two", "");
    }

    public void setUserImageUrl(String user_image) {
        prefsEditor.putString("user_image", user_image);
        prefsEditor.commit();
    }

    public String getUserImageUrl() {
        return appSharedPrefs.getString("user_image", "");
    }

    public void setDefaultAddressId(String address_id) {
        prefsEditor.putString("address_id", address_id);
        prefsEditor.commit();
    }

    public String getDefaultAddressId(){
        return appSharedPrefs.getString("address_id", "");
    }

    //location

    public void setLat(Double id) {
        Log.e("TAG", "Latitude: " + id +
                " \nLongitude: " + id);
        prefsEditor.putLong("latitude", Double.doubleToLongBits(id));
//        prefsEditor.putString("latitude", id);
        prefsEditor.commit();
    }

    public Double getLat() {
        Double latitude = Double.longBitsToDouble(appSharedPrefs.getLong("latitude", 0));
        Log.e("TAG", "getLat: " + latitude);
        return !(latitude + "").equals("null") ? latitude : 0.0;
    }

    public void setLong(Double id) {
        prefsEditor.putLong("longitude", Double.doubleToLongBits(id));
        prefsEditor.commit();
    }

    public Double getLong() {
        Double longitude = Double.longBitsToDouble(appSharedPrefs.getLong("longitude", 0));
        Log.e("TAG", "getLong: " + longitude);
        return !(longitude + "").equals("null") ? longitude : 0.0;
    }

    public void saveAddress(String category) {
        prefsEditor.putString("address", category);
        prefsEditor.commit();
    }

    public String getAddress() {
        return appSharedPrefs.getString("address", "");
    }

    public void saveCity(String category) {
        prefsEditor.putString("city", category);
        prefsEditor.commit();
    }

    public String getCity() {
        return appSharedPrefs.getString("city", "");
    }

    public void saveState(String category) {
        prefsEditor.putString("state", category);
        prefsEditor.commit();
    }

    public String getState() {
        return appSharedPrefs.getString("state", "");
    }

    public void saveCountry(String category) {
        prefsEditor.putString("country", category);
        prefsEditor.commit();
    }

    public String getCountry() {
        return appSharedPrefs.getString("country", "");
    }

    public void saveCountryCode(String category) {
        prefsEditor.putString("country_code", category);
        prefsEditor.commit();
    }

    public String getCountryCode() {
        return appSharedPrefs.getString("country_code", "");
    }
    public void savePostalCode(String category) {
        prefsEditor.putString("postal_code", category);
        prefsEditor.commit();
    }

    public String getPostalCode() {
        return appSharedPrefs.getString("postal_code", "");
    }









}
