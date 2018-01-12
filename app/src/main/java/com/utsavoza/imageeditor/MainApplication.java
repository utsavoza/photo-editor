package com.utsavoza.imageeditor;

import android.app.Application;

import com.adobe.creativesdk.foundation.AdobeCSDKFoundation;
import com.adobe.creativesdk.foundation.auth.IAdobeAuthClientCredentials;

public class MainApplication extends Application implements IAdobeAuthClientCredentials {

  private static final String CREATIVE_SDK_CLIENT_ID = Keys.CREATIVE_SDK_CLIENT_ID;
  private static final String CREATIVE_SDK_CLIENT_SECRET = Keys.CREATIVE_SDK_CLIENT_SECRET;
  private static final String CREATIVE_SDK_REDIRECT_URI = Keys.CREATIVE_SDK_REDIRECT_URI;
  private static final String[] CREATIVE_SDK_SCOPES = Keys.CREATIVE_SDK_SCOPES;

  @Override public void onCreate() {
    super.onCreate();
    AdobeCSDKFoundation.initializeCSDKFoundation(getApplicationContext());
  }

  @Override public String getClientID() {
    return CREATIVE_SDK_CLIENT_ID;
  }

  @Override public String getClientSecret() {
    return CREATIVE_SDK_CLIENT_SECRET;
  }

  @Override public String[] getAdditionalScopesList() {
    return CREATIVE_SDK_SCOPES;
  }

  @Override public String getRedirectURI() {
    return CREATIVE_SDK_REDIRECT_URI;
  }
}
