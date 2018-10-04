package cyber.com.kamus.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import cyber.com.kamus.adapter.AdapterSearch;
import cyber.com.kamus.model.Kamus;
import cyber.com.kamus.model.Kategori;
import cyber.com.kamus.model.SearchResult;
import cyber.com.kamus.view.fragment.FragmentSearch;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DATABASE_KAMUS";

    private static final int VERSION = 4;
    private final Context context;
    private TableCategory tableCategory;
    private TableKamus tableKamus;

    public TableCategory getTableCategory() {
        return tableCategory;
    }

    public TableKamus getTableKamus() {
        return tableKamus;
    }

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        tableCategory = new TableCategory(this.getWritableDatabase());
        tableKamus = new TableKamus(this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TableCategory.TABLE + " (" +
                TableCategory.NAMA + " text not null," +
                TableCategory.GAMBAR + " text not null)");

        db.execSQL("create table " + TableKamus.TABLE + "(" +
                TableKamus.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TableKamus.INDONESIA + " text not null, " +
                TableKamus.JAWA + " text not null, " +
                TableKamus.KATEGORI + " text not null)");

        try {
            InputStream file = context.getAssets().open("category.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                new TableCategory(db).insertCategory(new Kategori(split[0].trim(), split[1].trim()));

                Log.e(FragmentSearch.class.getSimpleName(), "onViewCreated: " + split[0] + " " + split[1]);
            }

            file = context.getAssets().open("kamus.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(file));

            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                new TableKamus(db).insertKamus(new Kamus(split[0].trim(), split[1].trim(), split[2].trim()));

                Log.e(FragmentSearch.class.getSimpleName(), "onViewCreated: " + split[0] + " " + split[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public class TableCategory {
        public static final String TABLE = "kategori";
        public static final String GAMBAR = "gambar";
        public static final String NAMA = "nama";
        private final SQLiteDatabase database;

        public TableCategory(SQLiteDatabase database) {
            this.database = database;
        }

        public void insertCategory(Kategori kategori) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(NAMA, kategori.getName());
            contentValues.put(GAMBAR, kategori.getDrawable());

            this.database.insert(TABLE, null, contentValues);
        }

        public ArrayList<Kategori> getCategories() {
            ArrayList<Kategori> kategoris = new ArrayList<>();

            Cursor cursor = this.database.rawQuery("select * from " + TABLE, new String[]{});

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                kategoris.add(
                        new Kategori(
                                cursor.getString(cursor.getColumnIndex(NAMA)),
                                cursor.getString(cursor.getColumnIndex(GAMBAR))
                        )
                );
                cursor.moveToNext();
            }

            return kategoris;
        }
    }

    public class TableKamus {
        public static final String TABLE = "kamus";
        public static final String INDONESIA = "indonesia";
        public static final String JAWA = "jawa";
        public static final String KATEGORI = "category";
        public static final String ID = "id";

        private final SQLiteDatabase database;

        public TableKamus(SQLiteDatabase database) {
            this.database = database;
        }

        public void insertKamus(Kamus kamus) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(INDONESIA, kamus.getIndonesia());
            contentValues.put(JAWA, kamus.getJawa());
            contentValues.put(KATEGORI, kamus.getCategory());

            this.database.insert(TABLE, null, contentValues);
        }

        public ArrayList<Kamus> selectAll() {
            ArrayList<Kamus> kamuses = new ArrayList<>();

            Cursor cursor = this.database.rawQuery("select * from " + TABLE, new String[]{});

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                kamuses.add(
                        new Kamus(
                                cursor.getInt(cursor.getColumnIndex(ID)),
                                cursor.getString(cursor.getColumnIndex(INDONESIA)),
                                cursor.getString(cursor.getColumnIndex(JAWA)),
                                cursor.getString(cursor.getColumnIndex(KATEGORI))
                        )
                );
                Log.d(getClass().getSimpleName(), "selectAll: " + cursor.getString(cursor.getColumnIndex(JAWA)));

                cursor.moveToNext();
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

            String query_section = "select" +
                    " distinct(upper(substr(" + (searchFrom.equals(AdapterSearch.SearchFrom.fromIndonesia) ? INDONESIA : JAWA) + ",1,1))) as a" +
                    " from " + TABLE + "" +
                    ((!category.isEmpty()) ? " where upper(trim(" + KATEGORI + ")) = upper(trim('" + category + "')) " : "") +
                    " order by a asc";

            Cursor cursor = this.database.rawQuery(query_section, new String[]{});

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String section = cursor.getString(0).toLowerCase();

                //menambahkan section baru
                searchResults.add(new SearchResult(section));

                String string = "select * from " + TABLE +
                        " where " +
                        "upper(substr(trim(" + (searchFrom.equals(AdapterSearch.SearchFrom.fromIndonesia) ? INDONESIA : JAWA) + "), 1, 1)) " +
                        " like '" + section.toUpperCase() + "%' " +
                        ((!category.isEmpty()) ? " and upper(trim(" + KATEGORI + ")) = upper(trim('" + category + "')) " : "");

                Cursor cursor_data = database.rawQuery(string, new String[]{});

                cursor_data.moveToFirst();
                while (!cursor_data.isAfterLast()) {
                    searchResults.add(new SearchResult(new Kamus(
                                    cursor_data.getInt(cursor_data.getColumnIndex(ID)),
                                    cursor_data.getString(cursor_data.getColumnIndex(INDONESIA)),
                                    cursor_data.getString(cursor_data.getColumnIndex(JAWA)),
                                    cursor_data.getString(cursor_data.getColumnIndex(KATEGORI))
                            ))
                    );
                    cursor_data.moveToNext();
                }
                cursor.moveToNext();
            }

            return searchResults;
        }
    }
}
