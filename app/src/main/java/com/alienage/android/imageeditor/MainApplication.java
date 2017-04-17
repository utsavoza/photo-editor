package com.alienage.android.imageeditor;

import android.app.Application;

import com.adobe.creativesdk.foundation.AdobeCSDKFoundation;
import com.adobe.creativesdk.foundation.auth.IAdobeAuthClientCredentials;

/**
 * Created by utsavoza on 17/4/17.
 */

public class MainApplication extends Application implements IAdobeAuthClientCredentials {

    private static final String CREATIVE_SDK_CLIENT_ID      = "<CLIENT ID>";
    private static final String CREATIVE_SDK_CLIENT_SECRET  = "<CLIENT SECRET>";
    private static final String CREATIVE_SDK_REDIRECT_URI   = "<REDIRECT URI>";
    private static final String[] CREATIVE_SDK_SCOPES = {"email", "profile", "address"};

    @Override
    public void onCreate() {
        super.onCreate();
        AdobeCSDKFoundation.initializeCSDKFoundation(getApplicationContext());
    }

    @Override
    public String getClientID() {
        return CREATIVE_SDK_CLIENT_ID;
    }

    @Override
    public String getClientSecret() {
        return CREATIVE_SDK_CLIENT_SECRET;
    }

    @Override
    public String[] getAdditionalScopesList() {
        return CREATIVE_SDK_SCOPES;
    }

    @Override
    public String getRedirectURI() {
        return CREATIVE_SDK_REDIRECT_URI;
    }
}
