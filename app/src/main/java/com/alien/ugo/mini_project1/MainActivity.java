package com.alien.ugo.mini_project1;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.adobe.creativesdk.foundation.AdobeCSDKFoundation;
import com.adobe.creativesdk.foundation.auth.IAdobeAuthClientCredentials;
import com.adobe.creativesdk.foundation.internal.auth.AdobeAuthIMSEnvironment;
import com.aviary.android.feather.sdk.AviaryIntent;
import com.aviary.android.feather.sdk.IAviaryClientCredentials;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static Uri imageUri;
    private static ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageUri = HomeScreen.image;
        imageView = (ImageView)findViewById(R.id.main_image);

        Intent imageUI = new AviaryIntent.Builder(this)
                .setData(imageUri)
                .build();
        startActivityForResult(imageUI, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(resultCode == RESULT_OK) {
                imageView.setImageURI(imageUri);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
