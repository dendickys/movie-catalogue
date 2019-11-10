package id.dendickys.moviecatalogue.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import id.dendickys.moviecatalogue.db.DatabaseHelper;

import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.TABLE_NAME;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns._ID;

public class FavMoviesHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private final DatabaseHelper databaseHelper;
    private static FavMoviesHelper INSTANCE;

    private SQLiteDatabase database;

    private FavMoviesHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static FavMoviesHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavMoviesHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }

    public Cursor queryAll() {
        return database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , BaseColumns._ID + " ASC");
    }

    public Cursor queryById(String id) {
        return database.query(DATABASE_TABLE, null
                , BaseColumns._ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    public long insert(ContentValues contentValues) {
        return database.insert(DATABASE_TABLE, null, contentValues);
    }

    public int update(String id, ContentValues contentValues) {
        return database.update(DATABASE_TABLE, contentValues, _ID + " = ?", new String[]{id});
    }

    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }
}
