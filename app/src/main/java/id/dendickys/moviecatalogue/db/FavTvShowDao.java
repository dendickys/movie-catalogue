package id.dendickys.moviecatalogue.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.dendickys.moviecatalogue.entity.Fav.FavTvShow;

@Dao
public interface FavTvShowDao {
    @Insert
    void addFavTvShow(FavTvShow favTvShow);

    @Query("SELECT * FROM tvshow_favorites")
    List<FavTvShow> getAllFavTvShow();

    @Query("SELECT EXISTS (SELECT 1 FROM tvshow_favorites WHERE id=:id)")
    int isFavorite(int id);

    @Delete
    void deleteFavTvShow(FavTvShow favTvShow);
}
