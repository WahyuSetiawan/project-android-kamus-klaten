package cyber.com.kamus.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DATABASE_KAMUS";

    private static final int VERSION = 8;
    private final Context context;

    private TableCategory tableCategory;
    private TableKamus tableKamus;
    private TableKuis tableKuis;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        new TableCategory(context, db).onCreateTable();
        new TableKamus(context, db).onCreateTable();
        new TableKuis(context, db).onCreateTable();

        new TableCategory(context, db).onInsertData();
        new TableKamus(context, db).onInsertData();
        new TableKuis(context, db).onInsertData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        new TableCategory(context, db).onUpdate();
        new TableKamus(context, db).onUpdate();
        new TableKuis(context, db).onUpdate();

        onCreate(db);
    }

    public TableCategory getTableCategory() {
        if (tableCategory == null) {
            tableCategory = new TableCategory(context, this.getWritableDatabase());
        }

        return tableCategory;
    }

    public TableKamus getTableKamus() {
        if (tableKamus == null) {
            tableKamus = new TableKamus(context, this.getWritableDatabase());
        }

        return tableKamus;
    }

    public TableKuis getTableKuis() {
        if (tableKuis == null) {
            tableKuis = new TableKuis(context, this.getWritableDatabase());
        }

        return tableKuis;
    }
}
