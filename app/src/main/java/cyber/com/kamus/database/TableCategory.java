package cyber.com.kamus.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cyber.com.kamus.database.parent.Table;
import cyber.com.kamus.model.Kategori;

public class TableCategory extends Table {
    public static final String GAMBAR = "gambar";
    public static final String NAMA = "nama";

    public TableCategory(Context context, SQLiteDatabase database) {
        super(context, database, "kategori");
    }

    @Override
    public void onCreateTable() {
        db.execSQL("create table " + table + " (" +
                NAMA + " text not null," +
                GAMBAR + " text not null)");
    }

    @Override
    public void onInsertData() {
        try {
            InputStream file = context.getAssets().open("category.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                insertCategory(new Kategori(split[0].trim(), split[1].trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertCategory(Kategori kategori) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAMA, kategori.getName());
        contentValues.put(GAMBAR, kategori.getDrawable());

        db.insert(table, null, contentValues);

        Log.i(getClass().getSimpleName(), kategori.getName());
    }

    public ArrayList<Kategori> getCategories() {
        ArrayList<Kategori> kategoris = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + table, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                kategoris.add(
                        new Kategori(
                                cursor.getString(cursor.getColumnIndex(NAMA)),
                                cursor.getString(cursor.getColumnIndex(GAMBAR))
                        )
                );
            } while (cursor.moveToNext());
        }

        return kategoris;
    }
}
