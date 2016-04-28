package com.drawer.navigation.clipboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.drawer.navigation.clipboard.util.Logger;

/**
 * Created by thana on 9/4/16.
 */
public class ClipboardReceiver extends BroadcastReceiver {
    private static String TAG="ClipboardReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        /***** For start Service  ****/
        Intent myIntent = new Intent(context, ClipboardService.class);
        context.startService(myIntent);

        Logger.showFilteredLog(TAG,"#onReceive");
    }
}
