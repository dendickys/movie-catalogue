package id.dendickys.moviecatalogue.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import id.dendickys.moviecatalogue.entity.Fav.FavMovies;
import id.dendickys.moviecatalogue.interfaces.FavMoviesDao;

@Database(entities = {FavMovies.class}, version = 1)
public abstract class FavMoviesDb extends RoomDatabase {
    public abstract FavMoviesDao favMoviesDao();
}
