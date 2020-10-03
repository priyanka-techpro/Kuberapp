package com.techprostudio.kuberinternational.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techprostudio.kuberinternational.ImageCompression.ImageCompression;
import com.techprostudio.kuberinternational.ImageCompression.ImageCompressionListener;
import com.techprostudio.kuberinternational.ImagePicker.ImagePicker;
import com.techprostudio.kuberinternational.Network.ApiClient;
import com.techprostudio.kuberinternational.Network.ApiInterface;
import com.techprostudio.kuberinternational.R;
import com.techprostudio.kuberinternational.Utils.AppPreference;

import java.io.ByteArrayOutputStream;

import static com.techprostudio.kuberinternational.Activity.ProfileActivity.profile_image;

public class ExampleFragment extends Fragment {
    private ImagePicker imagePicker;
    // private ImageView imageView;
    ApiInterface apiInterface;
    String userid,auth;
    Bitmap selectedImage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_example, container, false);

        //  imageView = rootView.findViewById(R.id.imageView);
        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);

        imagePicker = new ImagePicker();
        imagePicker.withFragment(this).withCompression(true).start();

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImagePicker.SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
            imagePicker.addOnCompressListener(new ImageCompressionListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void onCompressed(String filePath) {
                    selectedImage = BitmapFactory.decodeFile(filePath);
                    Log.e("FILee11111",""+selectedImage);
                    profile_image.setImageBitmap(selectedImage);

                    // BitMapToString(selectedImage);
                    Log.e("ImageCompressionfileUri",""+ ImageCompression.fileUri);
                    //
                }
            });
            String filePath = imagePicker.getImageFilePath(data);
            Log.e("FILee",""+filePath);

            if (filePath != null) {
                selectedImage = BitmapFactory.decodeFile(filePath);
                profile_image.setImageBitmap(selectedImage);
            }
        }
    }


    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        Log.e("jdkgvf",""+temp);

        if (temp.equals(""))
        {

        }
        else
        {
//            Log.e("ImageCompressionfileUri",""+ImageCompression.fileUri);
//            String updatetype="photo";
//            changeImage(auth,userid,updatetype, ImageCompression.fileUri);
        }
        return temp;
    }
}
