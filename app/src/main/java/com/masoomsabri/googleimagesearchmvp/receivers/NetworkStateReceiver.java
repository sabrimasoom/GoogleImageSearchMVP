package com.masoomsabri.googleimagesearchmvp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.masoomsabri.googleimagesearchmvp.data.ImageRepositories;

/**
 * Created by masoomsabri on 5/31/17.
 */

public class NetworkStateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getExtras() != null) {
            final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
            ImageRepositories.changeOffLineRepository(ni != null && ni.isConnectedOrConnecting());
        }
    }
}
