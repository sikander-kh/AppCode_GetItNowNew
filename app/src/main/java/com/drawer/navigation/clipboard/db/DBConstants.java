package com.drawer.navigation.clipboard.db;

/**
 * Database related constants
 * @author Thana
 * @since 0.1
 *
 */
public class DBConstants {
    // common 
     public static final String DATABASE_NAME = "clipboard.db";
//     public static final String DATABASE_NAME = "/sdcard/sddfdb.db";
    public static final int DATABASE_VERSION = 1;

    /**
     * Grouped constants related to Asset Table Record
     */
    protected static class ASSET_TABLE_RECORD {

        protected static final String ID = "id";
        protected static final String TEXT_DATA = "textData";


    }

}
