package id.dendickys.moviecatalogue.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import id.dendickys.moviecatalogue.entity.Fav.FavTvShow;
import id.dendickys.moviecatalogue.interfaces.FavTvShowDao;

@Database(entities = {FavTvShow.class}, version = 1)
public abstract class FavTvShowDb extends RoomDatabase {
    public abstract FavTvShowDao favTvShowDao();
}
