package com.example.bastiqui.moviesapp.database;

public class DatabaseOptions {

    static final String DB_NAME = "local.db";
    static final int DB_VERSION = 1;

    static final String RECENT_TABLE = "recent";
    static final String WATCHLIST_TABLE = "watchlist";

    public static final String ID = "id";
    static final String NAME = "name";
    static final String IMAGE = "image";
    static final String TYPE = "type";
    static final String VOTE_AVERAGE = "vote_average";
    static final String DATE = "date";

    static final String CREATE_RECENT_TABLE_ =
            "CREATE TABLE " + RECENT_TABLE + "(" +
                ID + " TEXT NOT NULL," +
                NAME + " TEXT NOT NULL," +
                IMAGE + " TEXT NOT NULL," +
                TYPE + " TEXT NOT NULL," +
                VOTE_AVERAGE + " TEXT NOT NULL," +
                DATE + " TEXT NOT NULL);";

    static final String CREATE_WATCHLIST_TABLE_ =
            "CREATE TABLE " + WATCHLIST_TABLE + "(" +
                    ID + " TEXT NOT NULL," +
                    NAME + " TEXT NOT NULL," +
                    IMAGE + " TEXT NOT NULL," +
                    TYPE + " TEXT NOT NULL," +
                    VOTE_AVERAGE + " TEXT NOT NULL," +
                    DATE + " TEXT NOT NULL);";
}