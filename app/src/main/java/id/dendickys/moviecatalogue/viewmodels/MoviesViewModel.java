package id.dendickys.moviecatalogue.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Objects;

import id.dendickys.moviecatalogue.entity.ItemMovies;
import id.dendickys.moviecatalogue.entity.ListMovies;
import id.dendickys.moviecatalogue.interfaces.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<ItemMovies>> moviesList;

    public LiveData<ArrayList<ItemMovies>> getAllMovies() {
        if (moviesList == null) {
            moviesList = new MutableLiveData<ArrayList<ItemMovies>>();
            loadMovies();
        }
        return moviesList;
    }

    private void loadMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient api = retrofit.create(ApiClient.class);
        Call<ListMovies> call = api.getAllMovies();

        call.enqueue(new Callback<ListMovies>() {
            @Override
            public void onResponse(Call<ListMovies> call, Response<ListMovies> response) {
                assert response.body() != null;
                moviesList.setValue(response.body().getListMovies());
            }

            @Override
            public void onFailure(Call<ListMovies> call, Throwable t) {
                Log.d("onFailure: ", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
