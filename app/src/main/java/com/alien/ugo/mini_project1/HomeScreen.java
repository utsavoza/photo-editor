package com.alien.ugo.mini_project1;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.aviary.android.feather.sdk.AviaryIntent;

public class HomeScreen extends AppCompatActivity {

    private static int RESULT_LOAD = 1;
    private static int CAMERA_LOAD = 2;
    protected static Uri image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
    }

    protected void openGallery(View view) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, RESULT_LOAD);
    }

    protected void openCamera(View view) {
        Intent camera = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(camera,CAMERA_LOAD);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if(requestCode==RESULT_LOAD && resultCode==RESULT_OK && data!=null) {
                image = data.getData();
                startActivity(new Intent(HomeScreen.this, MainActivity.class));
            }
            else {
                image = data.getData();
                startActivity(new Intent(HomeScreen.this, MainActivity.class));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
