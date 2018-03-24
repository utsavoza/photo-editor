package com.utsavoza.imageeditor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.alienage.android.imageeditor.R;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class HomeActivity extends AppCompatActivity {

  private static final String TAG = "HomeActivity";
  private static final int GALLERY_RESULT = 1;
  private static final int CAMERA_RESULT = 2;
  private static final String FILE_PROVIDER_AUTHORITY = "com.utsavoza.imageeditor";
  private static final int CAMERA_PERMISSION_REQ_CODE = 1001;
  private static final int STORAGE_PERMISSION_REQ_CODE = 1002;

  private String mCapturedImagePath;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
  }

  public void openCamera(View view) {
    // check for camera permission if not granted before
    if (ContextCompat.checkSelfPermission(this, CAMERA) != PERMISSION_GRANTED) {
      String[] cameraPermission = {CAMERA};
      ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_PERMISSION_REQ_CODE);
    } else {
      dispatchImageCaptureIntent();
    }
  }

  public void openGallery(View view) {
    // check for storage permission if not granted before
    if (ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
      String[] storagePermissions = {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE};
      ActivityCompat.requestPermissions(this, storagePermissions, STORAGE_PERMISSION_REQ_CODE);
    } else {
      dispatchGalleryIntent();
    }
  }

  private void dispatchGalleryIntent() {
    Intent galleryIntent = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);
    startActivityForResult(galleryIntent, GALLERY_RESULT);
  }

  private void dispatchImageCaptureIntent() {
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

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case CAMERA_PERMISSION_REQ_CODE:
        if (grantResults[0] == PERMISSION_GRANTED) {
          dispatchImageCaptureIntent();
        } else {
          Toast.makeText(this, "Required camera permission not granted", Toast.LENGTH_SHORT).show();
        }
        break;

      case STORAGE_PERMISSION_REQ_CODE:
        if (grantResults[0] == PERMISSION_GRANTED) {
          dispatchGalleryIntent();
        } else {
          Toast.makeText(this, "Required storage permission not granted", Toast.LENGTH_SHORT)
              .show();
        }
        break;

      default:
        throw new IllegalArgumentException("Unexpected request code");
    }
  }

  private File createImageFile() throws IOException {
    String timeStamp = DateFormat.getDateTimeInstance().format(new Date());
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

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      if (requestCode == GALLERY_RESULT) {
        Uri imageUri = data.getData();
        startActivity(MainActivity.getIntent(this, uriToBundle(Objects.requireNonNull(imageUri))));
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

  public static Intent getIntent(Context context) {
    return new Intent(context, HomeActivity.class);
  }
}
