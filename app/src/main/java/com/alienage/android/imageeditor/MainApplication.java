package com.alienage.android.imageeditor;

import android.app.Application;

import com.adobe.creativesdk.foundation.AdobeCSDKFoundation;
import com.adobe.creativesdk.foundation.auth.IAdobeAuthClientCredentials;

/**
 * Created by utsavoza on 17/4/17.
 */

public class MainApplication extends Application implements IAdobeAuthClientCredentials {

    private static final String CREATIVE_SDK_CLIENT_ID      = "56846827a2794ed6a4f1ca6928b3e764";
    private static final String CREATIVE_SDK_CLIENT_SECRET  = "172b13b0-1971-4d48-b028-2ea8b2f1b827";
    private static final String CREATIVE_SDK_REDIRECT_URI   = "ams+9b781ca8f76be3275af04c9af651fa84bfc9f4c1://adobeid/56846827a2794ed6a4f1ca6928b3e764";
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
