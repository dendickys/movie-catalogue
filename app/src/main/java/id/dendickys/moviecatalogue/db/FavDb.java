package id.dendickys.moviecatalogue.db;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import id.dendickys.moviecatalogue.entity.Fav.FavMovies;
import id.dendickys.moviecatalogue.entity.Fav.FavTvShow;

@Database(entities = {FavMovies.class, FavTvShow.class}, version = 1)
public abstract class FavDb extends RoomDatabase {

    public abstract FavMoviesDao favMoviesDao();

    public abstract FavTvShowDao favTvShowDao();

    public static FavDb sInstance;

    public static synchronized FavDb getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room.
                    databaseBuilder(context.getApplicationContext(), FavDb.class, "db_favorites_movies")
                    .build();
        }
        return sInstance;
    }
}
