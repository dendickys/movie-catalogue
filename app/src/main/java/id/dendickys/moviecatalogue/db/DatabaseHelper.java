package id.dendickys.moviecatalogue.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.OVERVIEW;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.POSTER_PATH;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.RELEASE_DATE;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.TABLE_NAME;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.TITLE;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.VOTE_AVERAGE;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns._ID;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "db_favorites_movies";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_NAME,
            _ID,
            POSTER_PATH,
            TITLE,
            RELEASE_DATE,
            VOTE_AVERAGE,
            OVERVIEW);

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
