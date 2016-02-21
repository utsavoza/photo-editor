package com.alien.ugo.mini_project1;

import android.app.Application;

import com.adobe.creativesdk.foundation.AdobeCSDKFoundation;
import com.adobe.creativesdk.foundation.internal.auth.AdobeAuthIMSEnvironment;
import com.aviary.android.feather.sdk.IAviaryClientCredentials;

/**
 * Created by vastu5996 on 21-02-2016.
 */
public class MainApplication extends Application implements IAviaryClientCredentials {

    private static final String CREATIVE_SDK_CLIENT_ID = "94e7b29a433a45d89a90cf1e8e990c90";
    private static final String CREATIVE_SDK_CLIENT_SECRET = "a0cbba99-f5e0-4c66-981d-a15970e7c888";

    @Override
    public void onCreate() {
        super.onCreate();
        AdobeCSDKFoundation.initializeCSDKFoundation(
                getApplicationContext(),
                AdobeAuthIMSEnvironment.AdobeAuthIMSEnvironmentProductionUS
        );
    }

    @Override
    public String getBillingKey() {
        return "";
    }

    @Override
    public String getClientID() {
        return CREATIVE_SDK_CLIENT_ID;
    }

    @Override
    public String getClientSecret() {
        return CREATIVE_SDK_CLIENT_SECRET;
    }
}
