package com.example.bastiqui.moviesapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, DatabaseOptions.DB_NAME, null, DatabaseOptions.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table
        db.execSQL(DatabaseOptions.CREATE_RECENT_TABLE_);
        db.execSQL(DatabaseOptions.CREATE_IMAGE_TABLE_);
        db.execSQL(DatabaseOptions.CREATE_WATCHLIST_TABLE_);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseOptions.RECENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseOptions.IMAGE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseOptions.WATCHLIST_TABLE);
        // Create tables again
        onCreate(db);
    }

    public LiveData<List<WatchlistModel>> getAllWatchlist() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<WatchlistModel> watchlistModels = new ArrayList<>();
        WatchlistModel watchlistModel;

        final  MutableLiveData<List<WatchlistModel>> lista = new MutableLiveData<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseOptions.WATCHLIST_TABLE + " ORDER BY DATE DESC", null);

        if (cursor != null) cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String id = cursor.getString(cursor.getColumnIndex(DatabaseOptions.ID));
                    String name = cursor.getString(cursor.getColumnIndex(DatabaseOptions.NAME));
                    String type = cursor.getString(cursor.getColumnIndex(DatabaseOptions.TYPE));
                    String vote = cursor.getString(cursor.getColumnIndex(DatabaseOptions.VOTE_AVERAGE));
                    String date = cursor.getString(cursor.getColumnIndex(DatabaseOptions.DATE));

                    watchlistModel = new WatchlistModel(id, name, type, vote, date);
                    watchlistModels.add(watchlistModel);
                    cursor.moveToNext();
                }

                lista.setValue(watchlistModels);
                cursor.close();
            }
        }

        return lista;
    }

    public void addWatchlist (WatchlistModel watchlistModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseOptions.ID, watchlistModel.getId());
        values.put(DatabaseOptions.NAME, watchlistModel.getName());
        values.put(DatabaseOptions.TYPE, watchlistModel.getType());
        values.put(DatabaseOptions.VOTE_AVERAGE, watchlistModel.getVote_average());
        values.put(DatabaseOptions.DATE, watchlistModel.getDate());

        // Inserting Row
        db.insert(DatabaseOptions.WATCHLIST_TABLE, null, values);
        db.close(); // Closing database connection
    }

    public LiveData<List<Recent_History>> getAllRecent() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Recent_History> recentHistoryList = new ArrayList<>();
        Recent_History recentHistory;

        final MutableLiveData<List<Recent_History>> lista = new MutableLiveData<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseOptions.RECENT_TABLE + " ORDER BY DATE DESC", null);

        if (cursor != null) cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String id = cursor.getString(cursor.getColumnIndex(DatabaseOptions.ID));
                    String name = cursor.getString(cursor.getColumnIndex(DatabaseOptions.NAME));
                    String type = cursor.getString(cursor.getColumnIndex(DatabaseOptions.TYPE));
                    String vote = cursor.getString(cursor.getColumnIndex(DatabaseOptions.VOTE_AVERAGE));
                    String date = cursor.getString(cursor.getColumnIndex(DatabaseOptions.DATE));

                    recentHistory = new Recent_History(id, name, type, vote, date);
                    recentHistoryList.add(recentHistory);
                    cursor.moveToNext();
                }
            }

            lista.setValue(recentHistoryList);
            cursor.close();
        }

        return lista;
    }

    public void addRecent (Recent_History recent_history) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseOptions.ID, recent_history.getId());
        values.put(DatabaseOptions.NAME, recent_history.getName());
        values.put(DatabaseOptions.TYPE, recent_history.getType());
        values.put(DatabaseOptions.VOTE_AVERAGE, recent_history.getVote_average());
        values.put(DatabaseOptions.DATE, recent_history.getDate());

        // Inserting Row
        db.insert(DatabaseOptions.RECENT_TABLE, null, values);
        db.close(); // Closing database connection
    }

    public void addImage (StoreImage storeImage, Bitmap img) {
        SQLiteDatabase db = this.getWritableDatabase();

        byte[] data = getBitmapAsByteArray(img);

        ContentValues values = new ContentValues();
        values.put(DatabaseOptions.ID, storeImage.getId());
        values.put(DatabaseOptions.IMAGE, data);

        // Inserting Row
        db.insert(DatabaseOptions.IMAGE_TABLE, null, values);
        db.close(); // Closing database connection
    }

    private static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    public Bitmap getImage (String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT image FROM " + DatabaseOptions.IMAGE_TABLE + " WHERE ID=" + id;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            byte[] bitmap = cursor.getBlob(cursor.getColumnIndex("image"));
            cursor.close();
            return BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length);
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        return null;
    }
}
