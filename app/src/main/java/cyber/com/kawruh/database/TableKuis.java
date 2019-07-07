package cyber.com.kawruh.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import cyber.com.kawruh.database.parent.Table;
import cyber.com.kawruh.model.KuisAdapter;

public class TableKuis extends Table {
    public static final String ID = "id";
    public static final String STATUS = "status";
    public static final String SCORE = "score";
    public static final String TIME = "time";

    public TableKuis(Context context, SQLiteDatabase sqLiteDatabase) {
        super(context, sqLiteDatabase, "kuis");
    }

    @Override
    public void onCreateTable() {
        db.execSQL("create table if not exists " + this.table + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                STATUS + " INTEGER DEFAULT 0," +
                TIME + " INTEGER DEFAULT 0," +
                SCORE + " INTEGER DEFAULT 0)");
    }

    @Override
    public void onInsertData() {
        for (int i = 1; i <= 9; i++) {
            insert(new KuisAdapter(String.valueOf(i)));
        }
    }

    public void insert(KuisAdapter kuisAdapter) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, kuisAdapter.getLabel());

        db.insert(table, null, contentValues);
    }

    public ArrayList<KuisAdapter> selectAll() {
        ArrayList<KuisAdapter> data = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + table, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                data.add(
                        new KuisAdapter(
                                String.valueOf(cursor.getInt(cursor.getColumnIndex(ID))),
                                cursor.getInt(cursor.getColumnIndex(STATUS)) == 1,
                                cursor.getInt(cursor.getColumnIndex(SCORE)),
                                cursor.getInt(cursor.getColumnIndex(TIME))
                        )
                );
            } while (cursor.moveToNext());
        }

        return data;
    }

    public void update(KuisAdapter kuisAdapter) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, kuisAdapter.getLabel());
        contentValues.put(STATUS, (kuisAdapter.getStatus()) ? 1 : 0);
        contentValues.put(SCORE, kuisAdapter.getScore());
        contentValues.put(TIME, kuisAdapter.getTime());

        db.update(table, contentValues, " id = " + kuisAdapter.getLabel(), new String[]{});
    }
}
