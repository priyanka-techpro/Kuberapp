package com.techprostudio.kuberinternational.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.techprostudio.kuberinternational.Fragment.ExampleFragment;
import com.techprostudio.kuberinternational.ImageCompression.ImageCompression;
import com.techprostudio.kuberinternational.Model.ProfileDetails.ProfileDetailsMain;
import com.techprostudio.kuberinternational.Model.ProfileUpdatePackage.ProfileUpdateMain;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.Network.Config;
import com.techprostudio.kuberinternational.Network.InternetAccess;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;
import com.techprostudio.kuberinternational.Utils.FileUtils;

import java.io.File;

public class ProfileActivity extends AppCompatActivity {
    ImageView back,img_cart,upload,img_share;
    RelativeLayout layout_main,ll_Update;
    ImageView edit_numberTwo,edit_email,edit_number;
    EditText name,email,number;
    Snackbar mSnackbar;
    ApiInterface apiInterface;
    ProgressDialog progressDialog,progressDialog1;
    public static ImageView profile_image;
    Fragment fragment ;
    TextView userName;
    String phone_profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        img_cart=findViewById(R.id.img_cart);
        back=findViewById(R.id.back);
        layout_main=findViewById(R.id.layout_main);
        edit_numberTwo=findViewById(R.id.edit_numberTwo);
        edit_email=findViewById(R.id.edit_email);
        edit_number=findViewById(R.id.edit_number);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        number=findViewById(R.id.number);
        ll_Update=findViewById(R.id.ll_Update);
        profile_image=findViewById(R.id.profile_image);
        upload=findViewById(R.id.upload);
        userName=findViewById(R.id.userName);
        img_share=findViewById(R.id.img_share);
        String customerid=new AppPreference(ProfileActivity.this).getUserId();
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        name.setEnabled(false);
        email.setEnabled(false);
        number.setEnabled(false);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                fragment = fm.findFragmentByTag("myFragmentTag");

                FragmentTransaction ft = fm.beginTransaction();
                fragment =new ExampleFragment();
                ft.replace(android.R.id.content,fragment,"myFragmentTag");
                ft.commit();
            }
        });
        edit_numberTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.isEnabled())
                {
                    name.setEnabled(false);
                }
                else
                {

                    name.setEnabled(true);
                    email.setEnabled(false);
                    number.setEnabled(false);
                    name.requestFocus();
                    name.setCursorVisible(true);
                    name.setSelection(name.getText().length());
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.showSoftInput(name, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });
        edit_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (email.isEnabled())
                {
                    email.setEnabled(false);
                }
                else
                {

                    email.setEnabled(true);
                    name.setEnabled(false);
                    number.setEnabled(false);
                    email.requestFocus();
                    email.setCursorVisible(true);
                    email.setSelection(email.getText().length());
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.showSoftInput(email, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });
        edit_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number.isEnabled())
                {
                    number.setEnabled(false);
                }
                else
                {

                    number.setEnabled(true);
                    email.setEnabled(false);
                    name.setEnabled(false);
                    number.requestFocus();
                    number.setCursorVisible(true);
                    number.setSelection(number.getText().length());
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.showSoftInput(number, InputMethodManager.SHOW_IMPLICIT);

                }
            }
        });

        img_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, CartActivity.class));
            }
        });
        img_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, WishListActivity.class));
            }
        });

        if (InternetAccess.isConnected(ProfileActivity.this)) {
            getProfileData(customerid);
        }
        else {
            mSnackbar = Snackbar
                    .make(layout_main, "No Internet Connection", Snackbar.LENGTH_INDEFINITE).
                            setAction("Ok", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    mSnackbar.dismiss();

                                }
                            });
            mSnackbar.show();
        }
        ll_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = name.getText().toString();
                String phonenumber = number.getText().toString();
                String emails = email.getText().toString();
                String secondphone = "";
                boolean cancel = false;
                View focusView = null;
                if (TextUtils.isEmpty(username))
                {
                    Toast.makeText(ProfileActivity.this, "Enter your name", Toast.LENGTH_SHORT).show();

                    focusView = name;
                    cancel = true;
                }

                else if (TextUtils.isEmpty(phonenumber))
                {
                    Toast.makeText(ProfileActivity.this, "Enter your phone number", Toast.LENGTH_SHORT).show();

                    focusView = number;
                    cancel = true;
                }
                else if (!isPhoneValid(phonenumber))
                {
                    Toast.makeText(ProfileActivity.this, "Enter valid phone number", Toast.LENGTH_SHORT).show();

                    focusView = number;
                    cancel = true;
                }
                else if (TextUtils.isEmpty(emails))
                {
                    Toast.makeText(ProfileActivity.this, "Enter your email address", Toast.LENGTH_SHORT).show();

                    focusView = email;
                    cancel = true;
                }
                else if (!isEmailValid(emails))
                {
                    Toast.makeText(ProfileActivity.this, "Enter valid email address", Toast.LENGTH_SHORT).show();

                    focusView = email;
                    cancel = true;
                }
                if (cancel) {

                    if (focusView != null)
                        focusView.requestFocus();
                }
                else
                    {
                    updateProfile(customerid,emails,username,ImageCompression.fileUri,secondphone,phonenumber);
                }
            }
        });
    }

    private void updateProfile(String customerid, String emails, String username, Uri fileUri, String secondphone, String phonenumber)
    {
        RequestBody useridpro=null;
        RequestBody usernamepro=null;
        RequestBody phonepro=null;
        RequestBody phonesecondpro=null;
        RequestBody emailpro=null;
        useridpro=RequestBody.create(MediaType.parse("text/plain"),customerid);
        usernamepro=RequestBody.create(MediaType.parse("text/plain"),username);
        phonepro=RequestBody.create(MediaType.parse("text/plain"),phonenumber);
        phonesecondpro=RequestBody.create(MediaType.parse("text/plain"),secondphone);
        emailpro=RequestBody.create(MediaType.parse("text/plain"),emails);
        MultipartBody.Part filetoupload=null;
        if (!ImageCompression.mImageName.equals(""))
        {
            try {
                File file = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    file = FileUtils.getFile(ProfileActivity.this, fileUri);
                }
                Log.e("FILENAME",""+file);

                if (file.exists())
                {
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                    filetoupload = MultipartBody.Part.createFormData("pic_url", file.getName(), requestBody);
                }


            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            RequestBody attachmentEmpty = RequestBody.create(MediaType.parse("text/plain"), "");
            filetoupload = MultipartBody.Part.createFormData("pic_url", "", attachmentEmpty);
        }
        Call<ProfileUpdateMain> call=apiInterface.UpdateProfile(Config.header,useridpro,emailpro,usernamepro,filetoupload,phonesecondpro,phonepro);
        progressDialog1 = new ProgressDialog(ProfileActivity.this);
        progressDialog1.setMessage("Please wait...");
        progressDialog1.show();
        call.enqueue(new Callback<ProfileUpdateMain>() {
            @Override
            public void onResponse(Call<ProfileUpdateMain> call, Response<ProfileUpdateMain> response) {
                progressDialog1.dismiss();
                if (response.body().isStatus() == true)
                {
                    String msg=response.body().getMessage();
                    Toast.makeText(ProfileActivity.this, msg, Toast.LENGTH_SHORT).show();
                    String name_profile=response.body().getUserData().getUserName();
                    name.setText(name_profile);
                    userName.setText(name_profile);
                    String email_profile=response.body().getUserData().getUserEmail();
                    email.setText(email_profile);
                    String update_profile=response.body().getUserData().getUserPhone();
                    if(response.body().isPhUpdate() == true)
                    {
                        Toast.makeText(ProfileActivity.this, "Your otp is " + response.body().getOtp(), Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(ProfileActivity.this,OtpVerifyProfileActivity.class);
                        i.putExtra("otptype",response.body().getOtpType());
                        startActivity(i);
                    }
                    else
                        {
                            number.setText(update_profile);
                    }
                    Picasso.with(ProfileActivity.this).load(response.body().getUserData().getUserImage()).into(profile_image);
                    new AppPreference(ProfileActivity.this).saveUserName(name_profile);
                    new AppPreference(ProfileActivity.this).saveUserEmail(email_profile);
                    new AppPreference(ProfileActivity.this).saveUserPhone(phone_profile);
                    new AppPreference(ProfileActivity.this).setUserImageUrl(response.body().getUserData().getUserImage());
                }
                else{
                    String msg=response.body().getMessage();
                    Toast.makeText(ProfileActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileUpdateMain> call, Throwable t) {
                progressDialog1.dismiss();
            }
        });

    }

    private void getProfileData(String customerid) {
        Call<ProfileDetailsMain> call=apiInterface.getProfileDetails(Config.header,customerid);
        progressDialog = new ProgressDialog(ProfileActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        call.enqueue(new Callback<ProfileDetailsMain>() {
            @Override
            public void onResponse(Call<ProfileDetailsMain> call, Response<ProfileDetailsMain> response) {
                progressDialog.dismiss();
                if (response.body().getStatus() == true)
                {
                    String name_profile=response.body().getProfileData().getUserName();
                    name.setText(name_profile);
                    userName.setText(name_profile);
                    String email_profile=response.body().getProfileData().getUserEmail();
                    email.setText(email_profile);
                    phone_profile=response.body().getProfileData().getUserPhone();
                    number.setText(phone_profile);
                    Picasso.with(ProfileActivity.this).load(response.body().getProfileData().getUserImage()).into(profile_image);

                }
                else{
                    String msg=response.body().getMessage();
                    Toast.makeText(ProfileActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileDetailsMain> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private boolean isPhoneValid(String phone) {

        return phone.length() > 9;
    }

    private boolean isEmailValid(String email) {

        return  android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}