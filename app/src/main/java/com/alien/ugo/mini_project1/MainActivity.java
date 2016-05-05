package com.alien.ugo.mini_project1;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.adobe.creativesdk.aviary.AdobeImageIntent;


public class MainActivity extends AppCompatActivity {

    private static Uri imageUri;
    private static ImageView imageView;
    private static final String fileName = "Aliens_Images";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageUri = HomeScreen.image;
        imageView = (ImageView)findViewById(R.id.main_image);

        Intent imageUI = new AdobeImageIntent.Builder(this)
                .setData(imageUri)
                .build();
        startActivityForResult(imageUI, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}
