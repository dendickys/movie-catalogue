package id.dendickys.moviecatalogue.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Objects;

import id.dendickys.moviecatalogue.entity.Movies;
import id.dendickys.moviecatalogue.entity.ResultsMovies;
import id.dendickys.moviecatalogue.interfaces.RetrofitInterface;
import id.dendickys.moviecatalogue.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesViewModel extends ViewModel {

    private MutableLiveData<List<Movies>> moviesList;

    public LiveData<List<Movies>> getAllMovies() {
        if (moviesList == null) {
            moviesList = new MutableLiveData<>();
            loadMovies();
        }
        return moviesList;
    }

    public void loadMovies() {
        RetrofitInterface api = RetrofitClient.getClient().create(RetrofitInterface.class);
        Call<ResultsMovies> call = api.getAllMovies();

        call.enqueue(new Callback<ResultsMovies>() {
            @Override
            public void onResponse(Call<ResultsMovies> call, Response<ResultsMovies> response) {
                assert response.body() != null;
                moviesList.setValue(response.body().getListMovies());
            }

            @Override
            public void onFailure(Call<ResultsMovies> call, Throwable t) {
                Log.d("onFailure: ", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public void searchMovie(String query) {
        RetrofitInterface api = RetrofitClient.getClient().create(RetrofitInterface.class);
        Call<ResultsMovies> call = api.getMovies(query);

        call.enqueue(new Callback<ResultsMovies>() {
            @Override
            public void onResponse(Call<ResultsMovies> call, Response<ResultsMovies> response) {
                assert response.body() != null;
                moviesList.setValue(response.body().getListMovies());
            }

            @Override
            public void onFailure(Call<ResultsMovies> call, Throwable t) {
                Log.d("onFailure: ", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
