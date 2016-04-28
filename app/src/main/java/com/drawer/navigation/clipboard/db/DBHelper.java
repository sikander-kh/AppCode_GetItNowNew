package com.drawer.navigation.clipboard.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.drawer.navigation.clipboard.util.Logger;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Database Helper functions
 * 
 * @author Thana
 * 
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private final static String TAG = "DBHelper";
    private static DBHelper helperInstance;

    public DBHelper(Context context) {
        // super(context, Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/" + DBConstants.DATABASE_NAME, null,
        // DBConstants.DATABASE_VERSION);
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);

    }

    public static DBHelper getInstance(Context context) {
        if (helperInstance == null)
            helperInstance = new DBHelper(context);

        return helperInstance;
    }

    /* (non-Javadoc)
     * @see com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase, com.j256.ormlite.support.ConnectionSource)
     */
    @Override
    public void onCreate(SQLiteDatabase arg0, ConnectionSource connectionSource) {

        try {
            // Create all required tables
            TableUtils.createTable(connectionSource, AssetTableRecord.class);
        }
        catch (Exception e) {
            Logger.showFilteredLog(TAG, "could not create table Contact");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2, int arg3)
    {
          //TODO
    }

    @Override
    public void close() {
        super.close();
        helperInstance = null;
    }

}
