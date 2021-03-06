package com.mobila.project.today.model.dataProviding.dataAccess.databank;

public class SemesterTable {
    public static final String TABLE_NAME = "semesters";

    public static final String COLUMN_ID = "semesterID";
    public static final String COLUMN_NR = "semesterNr";

    public static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_NR};

    static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_NR + " INTEGER);";

    static final String SQL_DELETE =
            "DROP TABLE " + TABLE_NAME;
}
