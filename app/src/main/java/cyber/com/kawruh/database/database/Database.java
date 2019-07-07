package cyber.com.kawruh.database.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import cyber.com.kawruh.database.TableCategory;
import cyber.com.kawruh.database.TableKawruh;
import cyber.com.kawruh.database.TableKuis;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DATABASE_KAMUS";

    private static final int VERSION = 9;
    private final Context context;

    private TableCategory tableCategory;
    private TableKawruh tableKawruh;
    private TableKuis tableKuis;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        new TableCategory(context, db).onCreateTable();
        new TableKawruh(context, db).onCreateTable();
        new TableKuis(context, db).onCreateTable();

        new TableCategory(context, db).onInsertData();
        new TableKawruh(context, db).onInsertData();
        new TableKuis(context, db).onInsertData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        new TableCategory(context, db).onUpdate();
        new TableKawruh(context, db).onUpdate();
        new TableKuis(context, db).onUpdate();

        onCreate(db);
    }

    public TableCategory getTableCategory() {
        if (tableCategory == null) {
            tableCategory = new TableCategory(context, this.getWritableDatabase());
        }

        return tableCategory;
    }

    public TableKawruh getTableKawruh() {
//        if (tableKawruh == null) {
//            tableKawruh = new TableKawruh(context, this.getWritableDatabase());
//        }

        return new TableKawruh(context, this.getWritableDatabase());
    }

    public TableKuis getTableKuis() {
//        if (tableKuis == null) {
//            tableKuis = new TableKuis(context, this.getWritableDatabase());
//        }

        return new TableKuis(context, this.getWritableDatabase());
    }
}
