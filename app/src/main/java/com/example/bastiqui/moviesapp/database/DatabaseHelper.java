package com.example.bastiqui.moviesapp.database;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
        db.execSQL(DatabaseOptions.CREATE_WATCHLIST_TABLE_);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseOptions.RECENT_TABLE);
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
                    String image = cursor.getString(cursor.getColumnIndex(DatabaseOptions.IMAGE));
                    String type = cursor.getString(cursor.getColumnIndex(DatabaseOptions.TYPE));
                    String vote = cursor.getString(cursor.getColumnIndex(DatabaseOptions.VOTE_AVERAGE));
                    String date = cursor.getString(cursor.getColumnIndex(DatabaseOptions.DATE));

                    watchlistModel = new WatchlistModel(id, name, image, type, vote, date);
                    watchlistModels.add(watchlistModel);
                    cursor.moveToNext();
                }

                lista.setValue(watchlistModels);
                cursor.close();
            }
        }

        return lista;
    }

    private boolean queryCheckWatchlist(WatchlistModel watchListModel) {
        SQLiteDatabase db = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.query(DatabaseOptions.WATCHLIST_TABLE,
                new String[]{DatabaseOptions.ID},
                DatabaseOptions.ID + "=?",
                new String[]{watchListModel.getId()}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        return cursor != null && cursor.getCount() > 0;
    }

    public void addWatchlist (WatchlistModel watchlistModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean check = queryCheckWatchlist(watchlistModel);

        ContentValues values = new ContentValues();

        if (check) {
            values.put(DatabaseOptions.DATE, watchlistModel.getDate());

            db.update(DatabaseOptions.WATCHLIST_TABLE, values, "id=" + watchlistModel.getId(), null);
        } else {
            values.put(DatabaseOptions.ID, watchlistModel.getId());
            values.put(DatabaseOptions.NAME, watchlistModel.getName());
            values.put(DatabaseOptions.TYPE, watchlistModel.getType());
            values.put(DatabaseOptions.IMAGE, watchlistModel.getImage());
            values.put(DatabaseOptions.VOTE_AVERAGE, watchlistModel.getVote_average());
            values.put(DatabaseOptions.DATE, watchlistModel.getDate());

            // Inserting Row
            db.insert(DatabaseOptions.WATCHLIST_TABLE, null, values);
        }

        db.close(); // Closing database connection
    }

    public void removeWatchlist (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + DatabaseOptions.WATCHLIST_TABLE + " WHERE id=\'" + id + "\'";

        db.execSQL(query);
    }

    private boolean queryCheckRecent(RecentHistory recentHistory) {
        SQLiteDatabase db = this.getReadableDatabase();

        @SuppressLint("Recycle") Cursor cursor = db.query(DatabaseOptions.RECENT_TABLE,
                new String[]{DatabaseOptions.ID},
                DatabaseOptions.ID + "=?",
                new String[]{recentHistory.getId()}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        return cursor != null && cursor.getCount() > 0;
    }

    public LiveData<List<RecentHistory>> getAllRecent() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<RecentHistory> recentHistoryList = new ArrayList<>();
        RecentHistory recentHistory;

        final MutableLiveData<List<RecentHistory>> lista = new MutableLiveData<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseOptions.RECENT_TABLE + " ORDER BY DATE DESC", null);

        if (cursor != null) cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    String id = cursor.getString(cursor.getColumnIndex(DatabaseOptions.ID));
                    String name = cursor.getString(cursor.getColumnIndex(DatabaseOptions.NAME));
                    String image = cursor.getString(cursor.getColumnIndex(DatabaseOptions.IMAGE));
                    String type = cursor.getString(cursor.getColumnIndex(DatabaseOptions.TYPE));
                    String vote = cursor.getString(cursor.getColumnIndex(DatabaseOptions.VOTE_AVERAGE));
                    String date = cursor.getString(cursor.getColumnIndex(DatabaseOptions.DATE));

                    recentHistory = new RecentHistory(id, name, image, type, vote, date);
                    recentHistoryList.add(recentHistory);
                    cursor.moveToNext();
                }
            }

            lista.setValue(recentHistoryList);
            cursor.close();
        }

        return lista;
    }

    public void addRecent (RecentHistory recentHistory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        boolean check = queryCheckRecent(recentHistory);

        if (check) {
            values.put(DatabaseOptions.DATE, recentHistory.getDate());

            db.update(DatabaseOptions.RECENT_TABLE, values, "id=" + recentHistory.getId(), null);
        } else {
            values.put(DatabaseOptions.ID, recentHistory.getId());
            values.put(DatabaseOptions.NAME, recentHistory.getName());
            values.put(DatabaseOptions.IMAGE, recentHistory.getImage());
            values.put(DatabaseOptions.TYPE, recentHistory.getType());
            values.put(DatabaseOptions.VOTE_AVERAGE, recentHistory.getVote_average());
            values.put(DatabaseOptions.DATE, recentHistory.getDate());

            // Inserting Row
            db.insert(DatabaseOptions.RECENT_TABLE, null, values);
        }

        db.close(); // Closing database connection
    }
}
