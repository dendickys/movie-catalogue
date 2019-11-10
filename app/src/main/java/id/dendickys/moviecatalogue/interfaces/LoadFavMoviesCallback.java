package id.dendickys.moviecatalogue.interfaces;

import android.database.Cursor;

public interface LoadFavMoviesCallback {
    void preExecute();

    void postExecute(Cursor cursor);
}
