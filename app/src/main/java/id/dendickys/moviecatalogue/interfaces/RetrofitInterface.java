package id.dendickys.moviecatalogue.interfaces;

import id.dendickys.moviecatalogue.entity.Movies;
import id.dendickys.moviecatalogue.entity.ResultsMovies;
import id.dendickys.moviecatalogue.entity.ResultsTvShow;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

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

    @GET("discover/movie?api_key=" + API_KEY + "&primary_release_date.gte=TODAY DATE&primary_release_date.lte=TODAY DATE")
    Call<Movies> getMoviesReleaseToday(@Query("TODAY_DATE") String todayDate);
}
