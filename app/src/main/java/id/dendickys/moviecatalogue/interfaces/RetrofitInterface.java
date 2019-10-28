package id.dendickys.moviecatalogue.interfaces;

import java.util.List;

import id.dendickys.moviecatalogue.entity.ResultsMovies;
import id.dendickys.moviecatalogue.entity.ResultsTvShow;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static android.app.SearchManager.QUERY;
import static id.dendickys.moviecatalogue.helper.Constant.API_KEY;

public interface RetrofitInterface {

    @GET("discover/movie?api_key=" + API_KEY + "&language=en-US")
    Call<ResultsMovies> getAllMovies();

    @GET("discover/tv?api_key=" + API_KEY + "&language=en-US")
    Call<ResultsTvShow> getAllTvShow();

    @GET("search/movie?api_key=" + API_KEY + "&language=en-US")
    Call<ResultsMovies> getMovies(@Query("query") String query);

    @GET("search/tv?api_key=" + API_KEY + "&language=en-US")
    Call<ResultsTvShow> getTvShow(@Query("query") String query);
}
