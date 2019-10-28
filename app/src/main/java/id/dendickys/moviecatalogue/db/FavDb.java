package id.dendickys.moviecatalogue.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import id.dendickys.moviecatalogue.entity.Fav.FavMovies;
import id.dendickys.moviecatalogue.entity.Fav.FavTvShow;
import id.dendickys.moviecatalogue.interfaces.FavMoviesDao;
import id.dendickys.moviecatalogue.interfaces.FavTvShowDao;

@Database(entities = {FavMovies.class, FavTvShow.class}, version = 1)
public abstract class FavDb extends RoomDatabase {
    public abstract FavMoviesDao favMoviesDao();

    public abstract FavTvShowDao favTvShowDao();
}
