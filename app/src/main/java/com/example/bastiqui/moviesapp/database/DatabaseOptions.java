package com.example.bastiqui.moviesapp.database;

public class DatabaseOptions {

    public static final String DB_NAME = "local.db";
    public static final int DB_VERSION = 1;

    public static final String RECENT_TABLE = "recent";
    public static final String WATCHLIST_TABLE = "watchlist";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String IMAGE = "image";
    public static final String TYPE = "type";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String DATE = "date";

    public static final String CREATE_RECENT_TABLE_ =
            "CREATE TABLE " + RECENT_TABLE + "(" +
                ID + " TEXT NOT NULL," +
                NAME + " TEXT NOT NULL," +
                IMAGE + " TEXT NOT NULL," +
                TYPE + " TEXT NOT NULL," +
                VOTE_AVERAGE + " TEXT NOT NULL," +
                DATE + " TEXT NOT NULL);";

    public static final String CREATE_WATCHLIST_TABLE_ =
            "CREATE TABLE " + WATCHLIST_TABLE + "(" +
                    ID + " TEXT NOT NULL," +
                    NAME + " TEXT NOT NULL," +
                    IMAGE + " TEXT NOT NULL," +
                    TYPE + " TEXT NOT NULL," +
                    VOTE_AVERAGE + " TEXT NOT NULL," +
                    DATE + " TEXT NOT NULL);";
}