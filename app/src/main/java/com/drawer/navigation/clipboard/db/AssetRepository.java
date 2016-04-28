package com.drawer.navigation.clipboard.db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import android.content.Context;

import com.drawer.navigation.clipboard.util.Logger;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

/**
 * Asset Repository related database actions
 * 
 * @author Thana
 * @since 0.1
 */
public class AssetRepository {

    private final static String TAG = "AssetRepository";

    private Context context;
    DBHelper dbHelper;

    /**
     * @param context
     *            the context
     */
    public AssetRepository(Context context) {

        this.context = context;
    }

    /**
     * get eligible records for sync with server
     * 
     * @see AssetTableRecord
     * @return list Asset Table records
     */
    public List<AssetTableRecord> getRecordsForSync() {

        List<AssetTableRecord> assetRecords = null;
        dbHelper = DBHelper.getInstance(context);

        Calendar calSyncTime = Calendar.getInstance();
        // calSyncTime.add(Calendar.MINUTE, -SYNC.ELIGIBLE_PRIOR_PERIOD); // set time

        try {

            Dao<AssetTableRecord, Integer> daoContact = dbHelper.getDao(AssetTableRecord.class);

            QueryBuilder<AssetTableRecord, Integer> qb = daoContact.queryBuilder();
//            Where where = qb.where();
            Logger.showFilteredLog(TAG, "calSyncTime:: " + calSyncTime.getTime().toString());
//            where.lt(DBConstants.ASSET_TABLE_RECORD.DATE_TIME, calSyncTime.getTime()); // where condition for find records with date-time earlier than current time
            PreparedQuery<AssetTableRecord> preparedQuery = qb.prepare();
            assetRecords = daoContact.query(preparedQuery);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            dbHelper.close();
        }

        return assetRecords;

    }



    public boolean deleteAll() {

        dbHelper = DBHelper.getInstance(context);

        boolean isDeleted = false;
        // remove record using ORMLite delete builder
        try {
            // Initialise dao object for Asset Table
            Dao<AssetTableRecord, Integer> daoTasks = dbHelper.getDao(AssetTableRecord.class);

            DeleteBuilder<AssetTableRecord, Integer> deleteBuilder = daoTasks.deleteBuilder();
            // only delete the rows where id as requested
//            deleteBuilder.where().eq(DBConstants.ASSET_TABLE_RECORD.ID, recordId);

            //prepare delete with condition and execute
            PreparedDelete<AssetTableRecord> dpd = deleteBuilder.prepare();
            daoTasks.delete(dpd);

            isDeleted = true;

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            dbHelper.close();
        }

        Logger.showFilteredLog(TAG, "isDeleted: " + isDeleted);

        return isDeleted;
    }

    /**
     * Save(insert) Assets details as bundle
     * @param listAssets
     * @return boolean action status
     */
    public boolean saveAssets(ArrayList<AssetTableRecord> listAssets) {
        boolean actionStatus = false;
        dbHelper = DBHelper.getInstance(context);

        Calendar cal = Calendar.getInstance();
        Date dateTime = cal.getTime();
        // String dateTime = DateUtils.getFormattedDate(cal, DateUtils.DATE_DD_MMM_YYYY_HH_mm_ss);
        try {
            for (AssetTableRecord asset : listAssets) {
//                asset.dateTime = dateTime;
                // insert record ORMLite DAO object
                Dao<AssetTableRecord, Integer> daoAsset = dbHelper.getDao(AssetTableRecord.class);
                daoAsset.create(asset);
                actionStatus = true;
            }

        }
        catch (Exception e) {
            Logger.showFilteredLog(TAG, "Error :: " + e.getMessage());
            e.printStackTrace();

        }

        dbHelper.close();

        return actionStatus;
    }

}
