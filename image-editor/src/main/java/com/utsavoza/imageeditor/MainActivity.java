package com.utsavoza.imageeditor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import com.adobe.creativesdk.aviary.AdobeImageIntent;
import com.alienage.android.imageeditor.R;

public class MainActivity extends AppCompatActivity {

  public static final String IMAGE_URI = "IMAGE_URI_KEY";

  private static final String TAG = "MainActivity";
  private static final int IMAGE_EDITOR_RESULT = 1;

  private ImageView mEditedImageView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mEditedImageView = (ImageView) findViewById(R.id.edited_image_view);

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
      Uri imageUri = Uri.parse(getIntent().getExtras().getString(IMAGE_URI));
      Intent imageEditorIntent = new AdobeImageIntent.Builder(this).setData(imageUri).build();
      startActivityForResult(imageEditorIntent, IMAGE_EDITOR_RESULT);
      finish(); // Comment this out to receive edited image
    }
  }

  // Do something with the edited image
  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      switch (requestCode) {
        case IMAGE_EDITOR_RESULT:
          Uri editedImageUri = data.getParcelableExtra(AdobeImageIntent.EXTRA_OUTPUT_URI);
          Log.d(TAG, "editedImageUri: " + editedImageUri.toString());
          Bundle extra = data.getExtras();
          if (extra != null) {
            boolean changed = extra.getBoolean(AdobeImageIntent.EXTRA_OUT_BITMAP_CHANGED);
            Log.d(TAG, "Image edited: " + changed);
            if (changed) {
              mEditedImageView.setImageURI(editedImageUri);
            }
          }
          break;

        default:
          throw new IllegalArgumentException("Unexpected request code");
      }
    }
  }

  public static Intent getIntent(Context context, Bundle bundle) {
    Intent intent = new Intent(context, MainActivity.class);
    if (bundle != null) {
      intent.putExtras(bundle);
    }
    return intent;
  }
}
