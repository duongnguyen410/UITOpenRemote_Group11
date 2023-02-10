package com.example.uitopenremote_group11.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.uitopenremote_group11.Model.Asset_data;

import java.util.ArrayList;
import java.util.List;

public class Wa2_helper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Asset table name
    private static final String TABLE_ASSET = "WEATHER2";

    // Asset Table Columns names
    private static final String DATABASE_NAME = "dbAsset";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TEMP = "temperature";
    private static final String KEY_TEMP_TS = "temp_timestamp";
    private static final String KEY_HUM = "humidity";
    private static final String KEY_HUM_TS = "hum_timestamp";
    private static final String KEY_WIN_SP = "wind_speed";
    private static final String KEY_WINDS_TS = "windS_timestamp";
    private static final String KEY_DATE = "date";
    private static final String KEY_MONTH = "month";
    private static final String KEY_YEAR = "year";

    private SQLiteDatabase db;
    public Wa2_helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ASSET_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_ASSET + "("
                + KEY_ID + " TEXT," + KEY_NAME + " TEXT,"
                + KEY_TEMP + " TEXT," + KEY_TEMP_TS + " TEXT PRIMARY KEY," + KEY_HUM + " TEXT," + KEY_HUM_TS + " TEXT," + KEY_WIN_SP + " TEXT," + KEY_WINDS_TS + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_MONTH + " TEXT," + KEY_YEAR + " TEXT" + ")";
        db.execSQL(CREATE_ASSET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSET);

        // Create table again
        onCreate(db);
    }

    public void insertAssetData(Asset_data asset_data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, asset_data.getId());
        values.put(KEY_NAME, asset_data.getName());
        values.put(KEY_TEMP, asset_data.getTemperature());
        values.put(KEY_TEMP_TS, asset_data.getTemp_Timestamp());
        values.put(KEY_HUM, asset_data.getHumidity());
        values.put(KEY_HUM_TS, asset_data.getHum_timestamp());
        values.put(KEY_WIN_SP, asset_data.getWindSpeed());
        values.put(KEY_WINDS_TS, asset_data.getWindS_timestamp());
        values.put(KEY_DATE,asset_data.getDate());
        values.put(KEY_MONTH,asset_data.getMonth());
        values.put(KEY_YEAR,asset_data.getYear());
        db.insert(TABLE_ASSET, null, values);
        db.close();
    }

    public List<Asset_data> getAllAssetData(){
        List<Asset_data> asset_datas = new ArrayList<Asset_data>();
        String[] criterial = null;
        String selectQuery = "SELECT * FROM " + TABLE_ASSET;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Asset_data assetData = new Asset_data();
                assetData.setId(cursor.getString(0));
                assetData.setName(cursor.getString(1));
                assetData.setTemperature(cursor.getString(2));
                assetData.setTemp_Timestamp(cursor.getString(3));
                assetData.setHumidity(cursor.getString(4));
                assetData.setHum_timestamp(cursor.getString(5));
                assetData.setWindSpeed(cursor.getString(6));
                assetData.setWindS_timestamp(cursor.getString(7));
                assetData.setDate(cursor.getString(8));
                assetData.setMonth(cursor.getString(9));
                assetData.setYear(cursor.getString(10));
                asset_datas.add(assetData);
            }while(cursor.moveToNext());
        }
        return asset_datas;
    }

    public void deleteAssetData(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM WEATHER2 WHERE Date=?", new String[]{date});
    }

    public void deleteTableAssetData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSET);
        onCreate(db);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
    }
}
