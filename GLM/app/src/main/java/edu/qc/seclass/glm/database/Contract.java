package edu.qc.seclass.glm.database;

import android.provider.BaseColumns;

public class Contract {

    private Contract(){}

    //table Item
    public static class DBItem implements BaseColumns{
        public static final String TABLE_NAME = "item";
        public static final String COLUMN_NAME_UNIQUE_NAME = "unique_name";
        //public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_ITEM_TYPE_ID = "item_type_id";

        public static final String QUERY_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + _ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_NAME_UNIQUE_NAME + " TEXT NOT NULL UNIQUE COLLATE NOCASE , "
                        + COLUMN_NAME_ITEM_TYPE_ID + " INTEGER NOT NULL"
 //                       + COLUMN_NAME_NAME + " TEXT NOT NULL "
                        + ")";

        public static final String QUERY_DROP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    //table item type
    public static class DbItemType implements BaseColumns {
        public static final String TABLE_NAME = "item_types";
        public static final String COLUMN_NAME_NAME = "name";

        public static final String QUERY_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "("
                        + _ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
                        +COLUMN_NAME_NAME + " TEXT NOT NULL UNIQUE"
                        + ")";

        public static final String QUERY_DROP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }


}
