package com.drawer.navigation.clipboard.db;

import java.util.Date;

import com.drawer.navigation.clipboard.db.DBConstants.ASSET_TABLE_RECORD;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * ORMLite Database Table for 'Asset' (Bin) to store related info including beacon attached to the Asset
 * @author Thana
 * @since 0.1
 * @see DatabaseTable
 * @see DatabaseField
 *
 */
@DatabaseTable(tableName = "Asset")
public class AssetTableRecord {

    @DatabaseField(generatedId = true)
    public Integer id; // auto id

    public String getTextData() {
        return textData;
    }

    @DatabaseField(columnName = ASSET_TABLE_RECORD.TEXT_DATA)
    public String textData; // mac address



    public AssetTableRecord() {
    }

    /**
     * @param textData
     */
    public AssetTableRecord(String textData) {
        this.textData = textData;

    }



}
