package com.masoomsabri.googleimagesearchmvp;

import android.app.Application;
import android.content.Context;

/**
 * Created by masoomsabri on 5/31/17.
 */

public class GoogleImageSearchMVPApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}


