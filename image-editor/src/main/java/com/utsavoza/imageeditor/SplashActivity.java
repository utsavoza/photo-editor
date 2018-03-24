package com.utsavoza.imageeditor;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.alienage.android.imageeditor.R;

/** A dummy splash screen activity. */
public class SplashActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    int delayDuration = 1500;
    new Handler().postDelayed(() -> {
      startActivity(HomeActivity.getIntent(SplashActivity.this));
      finish();
    }, delayDuration);
  }
}
