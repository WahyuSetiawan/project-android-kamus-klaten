package cyber.com.kawruh.database.parent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public abstract class Table {
    protected String table;
    protected Context context;
    protected SQLiteDatabase db;

    public Table(Context context, SQLiteDatabase database, String table) {
        this.context = context;
        this.db = database;
        this.table = table;
    }

    public abstract void onCreateTable();

    public abstract void onInsertData();

    public void onUpdate() {
        db.execSQL("Drop table if exists  " + table);
    }

}
