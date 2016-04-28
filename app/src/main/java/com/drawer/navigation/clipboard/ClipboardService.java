package com.drawer.navigation.clipboard;

import android.app.IntentService;
import android.app.Service;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.drawer.navigation.clipboard.db.AssetRepository;
import com.drawer.navigation.clipboard.db.AssetTableRecord;
import com.drawer.navigation.clipboard.util.Logger;

import java.util.ArrayList;


/**
 * Created by thana on 9/4/16.
 */
public class ClipboardService extends Service {

    private static String TAG = "APPClipboardService";

    /**
     * This is ClipboardManager for listening and changing primary clipboard data
     **/
    private ClipboardManager myClipboard;
    private ClipData myClip;
    String mPreviousText = "";

    private final IBinder clipboardServiceBinder = new MyLocalBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return clipboardServiceBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.showFilteredLog(TAG, "#onCreate Serive launcehed ...");
//        Toast.makeText(getApplicationContext(), "Clipboard app Service stated... ", Toast.LENGTH_SHORT).show();


    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *//*



    protected void onHandleIntent(Intent intent) {

        String dataString = intent.getDataString();
//        Toast.makeText(this,"Service stated..",Toast.LENGTH_SHORT).show();

        Handler mHandler = new Handler(getMainLooper());
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Clipboard app Service stated... ", Toast.LENGTH_SHORT).show();
            }
        });

        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);


        Log.d(TAG,"Serive launcehed ...");

        //Add listener to listen clipboard changes
        myClipboard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                // TODO Auto-generated method stub

                Log.d(TAG, "Text copied");


            }
        });



    }
*/
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();

        Logger.showFilteredLog(TAG, "#onStartCommand");


        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        //Add listener to listen clipboard changes
        myClipboard.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() {
            @Override
            public void onPrimaryClipChanged() {
                // TODO Auto-generated method stub

                Logger.showFilteredLog(TAG, "#addPrimaryClipChangedListener Text copied");

                ClipData clipData = myClipboard.getPrimaryClip();

                ClipData.Item item = clipData.getItemAt(0);
                if (mPreviousText.equals(item.getText())) return;
                else {
                    /// do something
                    if(null!=item && null!=item.getText() ) {
                        mPreviousText = item.getText().toString();

                        Logger.showFilteredLog(TAG, "Copied data: " + mPreviousText);

                        // write to db
                        AssetRepository assetRepository = new AssetRepository(getBaseContext());
                        ArrayList<AssetTableRecord> listAsset = new ArrayList<AssetTableRecord>();
                        listAsset.add(new  AssetTableRecord(mPreviousText));
                        if (assetRepository.saveAssets(listAsset)) {
                            Logger.showFilteredLog(TAG, "DB Write Status : Success");
                        }
                    }
                }


            }
        });


        return START_STICKY;
    }


    public class MyLocalBinder extends Binder {
        public ClipboardService getService() {
            return ClipboardService.this;
        }
    }
}
