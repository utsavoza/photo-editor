package com.alien.ugo.mini_project1;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.main_image);
        if(getOrientation(HomeScreen.image).equals("landscape")) {
            imageView.setRotation(270);
        }
        imageView.setImageURI(HomeScreen.image);
    }

    private static String getOrientation(Uri uri){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        String orientation = "landscape";
        try{
            String image = uri.getPath();
            BitmapFactory.decodeFile(image, options);
            int imageHeight = options.outHeight;
            int imageWidth = options.outWidth;
            if (imageHeight > imageWidth){
                orientation = "portrait";
            }
        }catch (Exception e){
            //Do nothing
        }
        return orientation;
    }
}
