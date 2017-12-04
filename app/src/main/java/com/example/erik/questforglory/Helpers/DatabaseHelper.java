package com.example.erik.questforglory.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "gear_Inventory_db3";
    public static final String TABLE_NAME = "gear_Inventory_table3";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ISEQUIPED";
    public static final String COL_3 = "NAME";
    public static final String COL_4 = "RARITY";
    public static final String COL_5 = "STATS";
    public static final String COL_6 = "LEVEL";
    public static final String COL_7 = "HEALTH";
    public static final String COL_8 = "DEFENSE";
    public static final String COL_9 = "DAMAGE";
    public static final String COL_10 = "BLOCKCHANCE";
    public static final String COL_11 = "CRITCHANCE";
    public static final String COL_12 = "GOLDWORTH";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" +
                "ID INTEGER PRIMARY KEY, " +
                "ISEQUIPED INTEGER, " +
                "NAME TEXT, " +
                "RARITY TEXT, " +
                "STATS TEXT, " +
                "LEVEL REAL, " +
                "HEALTH REAL, " +
                "DEFENSE REAL, " +
                "DAMAGE REAL, " +
                "BLOCKCHANCE REAL, " +
                "CRITCHANCE REAL, " +
                "GOLDWORTH REAL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(int isEquiped, String name, String rarity, String stats, float level, float health, float defense, float damage, float blockChance, float critChance, float goldWorth) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, isEquiped);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, rarity);
        contentValues.put(COL_5, stats);
        contentValues.put(COL_6, level);
        contentValues.put(COL_7, health);
        contentValues.put(COL_8, defense);
        contentValues.put(COL_9, damage);
        contentValues.put(COL_10, blockChance);
        contentValues.put(COL_11, critChance);
        contentValues.put(COL_12, goldWorth);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    public boolean updateData(String id, int isEquiped, String name, String rarity, String stats, float level, float health, float defense, float damage, float blockChance, float critChance, float goldWorth) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, isEquiped);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, rarity);
        contentValues.put(COL_5, stats);
        contentValues.put(COL_6, level);
        contentValues.put(COL_7, health);
        contentValues.put(COL_8, defense);
        contentValues.put(COL_9, damage);
        contentValues.put(COL_10, blockChance);
        contentValues.put(COL_11, critChance);
        contentValues.put(COL_12, goldWorth);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { id });
        return true;
    }
    public void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME + " where ID = '" + id + "'");
        db.execSQL("UPDATE " + TABLE_NAME + " set ID = (ID - 1) WHERE ID > " + id);
    }
    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);
    }
}