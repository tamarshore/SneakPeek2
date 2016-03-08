package com.studio4us.tamar.sneakpeek2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class FeedReaderContract {


    public FeedReaderContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "Feed";
        public static final String COLUMN_NAME_FEED_ID = "company";
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_TAGS = "tags";
        public static final String COLUMN_NAME_NULLABLE = "null";

    }

    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.TABLE_NAME + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_NAME_FEED_ID + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_CONTENT + TEXT_TYPE + COMMA_SEP +
                    FeedEntry.COLUMN_TAGS + TEXT_TYPE + COMMA_SEP +
                    " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;


}