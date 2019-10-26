package id.dendickys.moviecatalogue.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import id.dendickys.moviecatalogue.entity.Fav.FavMovies;

@Dao
public interface FavMoviesDao {
    @Insert
    void addFavMovies(FavMovies favMovies);

    @Query("SELECT * FROM movies_favorites")
    List<FavMovies> getAllFavMovies();

    @Query("SELECT EXISTS (SELECT 1 FROM movies_favorites WHERE id=:id)")
    int isFavorite(int id);

    @Delete
    void deleteFavMovies(FavMovies favMovies);
}
