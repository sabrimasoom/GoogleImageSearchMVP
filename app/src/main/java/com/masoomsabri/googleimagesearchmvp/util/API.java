package com.masoomsabri.googleimagesearchmvp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.masoomsabri.googleimagesearchmvp.GoogleImageSearchMVPApp;
import com.masoomsabri.googleimagesearchmvp.networking.interfaces.APIService;

/**
 * Created by masoomsabri on 5/31/17.
 */

public class API {

    private static API instance;

    private APIService service;

    private API() {
        /* IGNORED */
    }

    public static API get() {
        if (instance == null) {
            instance = new API();
        }
        return instance;
    }
//For Restapi call using retrofit
/*    public APIService getRetrofitService() {
        if (service == null) {
            OkHttpClient client = new OkHttpClient();
            client.interceptors().add(new LoggingInterceptor());
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(APIService.class);
        }
        return service;
    }*/

    public static boolean isConnected() {
        ConnectivityManager conMgr = (ConnectivityManager) GoogleImageSearchMVPApp.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo i = conMgr.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        if (!i.isAvailable())
            return false;
        return true;
    }
}
