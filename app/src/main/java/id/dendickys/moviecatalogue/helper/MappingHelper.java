package id.dendickys.moviecatalogue.helper;

import android.database.Cursor;

import java.util.ArrayList;

import id.dendickys.moviecatalogue.entity.Fav.FavMovies;

import static android.provider.BaseColumns._ID;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.OVERVIEW;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.POSTER_PATH;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.RELEASE_DATE;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.TITLE;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.VOTE_AVERAGE;

public class MappingHelper {
    public static ArrayList<FavMovies> mapCursorToArraylist(Cursor cursor) {
        ArrayList<FavMovies> moviesArrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            String posterPath = cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE));
            String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE));
            String voteAverage = cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVERAGE));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW));
            moviesArrayList.add(new FavMovies(id, posterPath, title, releaseDate, voteAverage, overview));
        }
        return moviesArrayList;
    }

    public static FavMovies mapCursorToObject(Cursor cursor) {
        cursor.moveToFirst();
        int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        String posterPath = cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE));
        String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE));
        String voteAverage = cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVERAGE));
        String overview = cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW));

        return new FavMovies(id, posterPath, title, releaseDate, voteAverage, overview);
    }
}
