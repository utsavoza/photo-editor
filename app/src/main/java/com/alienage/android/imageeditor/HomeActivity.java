package com.alienage.android.imageeditor;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = HomeActivity.class.getName();
    private static final int GALLERY_RESULT = 1;
    private static final int CAMERA_RESULT  = 2;
    private static final String FILE_PROVIDER_AUTHORITY = "com.alienage.android.imageeditor";

    private static final int CAMERA_PERMISSION_REQ_CODE = 1001;
    private static final int WRITE_EXT_STORAGE_REQ_CODE = 1002;
    private static final int READ_EXT_STORAGE_REQ_CODE  = 1003;

    private String mCapturedImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeViews();
    }

    private void initializeViews() {
        findViewById(R.id.camera_button_text).setOnClickListener(this);
        findViewById(R.id.camera_button_image).setOnClickListener(this);
        findViewById(R.id.gallery_button_text).setOnClickListener(this);
        findViewById(R.id.gallery_button_image).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.camera_button_image || id == R.id.camera_button_text) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                checkPermissionAndDispatchIntent();
            } else {
                dispatchImageCaptureIntent();
            }
        } else if (id ==R.id.gallery_button_image || id == R.id.gallery_button_text) {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, GALLERY_RESULT);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissionAndDispatchIntent() {
        Log.d(TAG, "checkPermissionAndDispatch");

        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, CAMERA_PERMISSION_REQ_CODE);
        } else {
            dispatchImageCaptureIntent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSION_REQ_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchImageCaptureIntent();
                } else {
                    Toast.makeText(this, "Permission for Camera not granted", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    private void dispatchImageCaptureIntent() {
        Log.d(TAG, "dispatchImageCaptureIntent");
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (photoFile != null) {
                Uri photoFileUri = FileProvider.getUriForFile(this, FILE_PROVIDER_AUTHORITY, photoFile);
                Log.d(TAG, "dispatchImageCaptureIntent:photoFileUri: " + photoFile.toString());
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFileUri);
                startActivityForResult(cameraIntent, CAMERA_RESULT);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCapturedImagePath = image.getAbsolutePath();
        Log.d(TAG, "createImageFile: " + mCapturedImagePath);
        return image;
    }

    private Bundle uriToBundle(Uri imageUri) {
        Bundle bundle = new Bundle();
        bundle.putString(MainActivity.IMAGE_URI, imageUri.toString());
        return bundle;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_RESULT) {
                Uri imageUri = data.getData();
                startActivity(MainActivity.getIntent(this, uriToBundle(imageUri)));
            } else if (requestCode == CAMERA_RESULT) {
                if (data != null) {
                    File imageFile = new File(mCapturedImagePath);
                    Uri imageUri = Uri.fromFile(imageFile);
                    startActivity(MainActivity.getIntent(this, uriToBundle(imageUri)));
                }
            }
        } else {
            Toast.makeText(this, "Image not loaded.", Toast.LENGTH_SHORT).show();
        }
    }

    public static Intent getIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, HomeActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        return intent;
    }
}
