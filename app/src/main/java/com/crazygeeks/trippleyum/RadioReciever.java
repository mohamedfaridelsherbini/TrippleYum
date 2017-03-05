package com.crazygeeks.trippleyum;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * Created by mymac on 3/1/17.
 */

public class RadioReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        if (cm == null)
            return;
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {

            intent = new Intent(context,RadioService.class);
            try {
                context.startService(intent);
            }catch (Exception e){}


        } else {
            // Do nothing or notify user somehow
        }
    }
}
