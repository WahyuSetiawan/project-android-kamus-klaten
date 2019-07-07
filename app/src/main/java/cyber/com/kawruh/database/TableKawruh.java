package cyber.com.kawruh.database;


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

import cyber.com.kawruh.adapter.AdapterSearch;
import cyber.com.kawruh.database.parent.Table;
import cyber.com.kawruh.model.Kamus;
import cyber.com.kawruh.model.SearchResult;
import cyber.com.kawruh.view.fragment.FragmentSearch;

public class TableKawruh extends Table {
    public static final String INDONESIA = "indonesia";
    public static final String JAWA = "jawa";
    public static final String KATEGORI = "category";
    public static final String ID = "id";

    public TableKawruh(Context context, SQLiteDatabase database) {
        super(context, database, "kamus");
    }

    public void insertKamus(Kamus kamus) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(INDONESIA, kamus.getIndonesia());
        contentValues.put(JAWA, kamus.getJawa());
        contentValues.put(KATEGORI, kamus.getCategory());

        db.insert(table, null, contentValues);
    }

    public ArrayList<Kamus> selectAll() {
        ArrayList<Kamus> kamuses = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + table, new String[]{});
        if (cursor.moveToFirst()) {
            do {
                kamuses.add(
                        new Kamus(
                                cursor.getInt(cursor.getColumnIndex(ID)),
                                cursor.getString(cursor.getColumnIndex(INDONESIA)),
                                cursor.getString(cursor.getColumnIndex(JAWA)),
                                cursor.getString(cursor.getColumnIndex(KATEGORI))
                        )
                );
            } while (cursor.moveToNext());
        }

        return kamuses;
    }

    public ArrayList<SearchResult> selectAllToHashMapGroup(AdapterSearch.SearchFrom searchFrom) {
        return selectAllToHas(searchFrom, "");
    }

    public ArrayList<SearchResult> selectAllSectionFormatFromCategory(AdapterSearch.SearchFrom searchFrom, String category) {
        return selectAllToHas(searchFrom, category);
    }

    public ArrayList<SearchResult> selectAllToHas(AdapterSearch.SearchFrom searchFrom, String category) {
        ArrayList<SearchResult> searchResults = new ArrayList<>();
        String column = (searchFrom.equals(AdapterSearch.SearchFrom.fromIndonesia) ? INDONESIA : JAWA);

        String query_section = "select" +
                " distinct(upper(substr(" + column + ",1,1))) as a" +
                " from " + table + "" +
                ((!category.isEmpty()) ? " where upper(trim(" + KATEGORI + ")) = upper(trim('" + category + "')) " : "") +
                " order by a asc";

        Cursor cursor = db.rawQuery(query_section, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                String section = cursor.getString(0).toLowerCase();
                String string = "select * from " + table +
                        " where " +
                        "upper(substr(trim(" + column + "), 1, 1)) " +
                        " like '" + section.toUpperCase() + "%' " +
                        ((!category.isEmpty()) ? " and upper(trim(" + KATEGORI + ")) = upper(trim('" + category + "')) " : "");

                searchResults.add(new SearchResult(section));

                Cursor cursor_data = db.rawQuery(string, new String[]{});

                if (cursor_data.moveToFirst()) {
                    do {
                        searchResults.add(new SearchResult(new Kamus(
                                        cursor_data.getInt(cursor_data.getColumnIndex(ID)),
                                        cursor_data.getString(cursor_data.getColumnIndex(INDONESIA)),
                                        cursor_data.getString(cursor_data.getColumnIndex(JAWA)),
                                        cursor_data.getString(cursor_data.getColumnIndex(KATEGORI))
                                ))
                        );
                    }
                    while (cursor_data.moveToNext());
                }
            } while (cursor.moveToNext());
        }
        return searchResults;
    }

    public ArrayList<Kamus> getKuis() {
        ArrayList<Kamus> kuis = new ArrayList<>();
        String string = "select * from  " + table + " limit 10";
        Cursor cursor = db.rawQuery(string, new String[]{});
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                kuis.add(new Kamus(
                                cursor.getInt(cursor.getColumnIndex(ID)),
                                cursor.getString(cursor.getColumnIndex(INDONESIA)),
                                cursor.getString(cursor.getColumnIndex(JAWA)),
                                cursor.getString(cursor.getColumnIndex(KATEGORI))
                        )
                );
            }
        }
        return kuis;
    }

    @Override
    public void onInsertData() {
        try {
            InputStream file = context.getAssets().open("kamus.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                insertKamus(new Kamus(split[0].trim(), split[1].trim(), split[2].trim()));

                Log.e(FragmentSearch.class.getSimpleName(), "onViewCreated: " + split[0] + " " + split[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateTable() {
        db.execSQL("create table " + table + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                INDONESIA + " text not null, " +
                JAWA + " text not null, " +
                KATEGORI + " text not null)");

    }

}