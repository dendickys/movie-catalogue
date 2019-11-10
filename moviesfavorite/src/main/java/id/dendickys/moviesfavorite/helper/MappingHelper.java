package id.dendickys.moviesfavorite.helper;

import android.database.Cursor;

import java.util.ArrayList;

import id.dendickys.moviesfavorite.model.FavMovies;

import static android.provider.BaseColumns._ID;
import static id.dendickys.moviesfavorite.db.DatabaseContract.FavMoviesColumns.OVERVIEW;
import static id.dendickys.moviesfavorite.db.DatabaseContract.FavMoviesColumns.POSTER_PATH;
import static id.dendickys.moviesfavorite.db.DatabaseContract.FavMoviesColumns.RELEASE_DATE;
import static id.dendickys.moviesfavorite.db.DatabaseContract.FavMoviesColumns.TITLE;
import static id.dendickys.moviesfavorite.db.DatabaseContract.FavMoviesColumns.VOTE_AVERAGE;

public class MappingHelper {
    public static ArrayList<FavMovies> mapCursorToArrayList(Cursor cursor) {
        ArrayList<FavMovies> favMoviesArrayList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            String posterPath = cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(TITLE));
            String releaseDate = cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE));
            String voteAverage = cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVERAGE));
            String overview = cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW));
            favMoviesArrayList.add(new FavMovies(id, posterPath, title, releaseDate, voteAverage, overview));
        }
        return favMoviesArrayList;
    }
}
