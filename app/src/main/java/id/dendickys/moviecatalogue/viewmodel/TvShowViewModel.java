package id.dendickys.moviecatalogue.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Objects;

import id.dendickys.moviecatalogue.entity.ResultsMovies;
import id.dendickys.moviecatalogue.entity.ResultsTvShow;
import id.dendickys.moviecatalogue.entity.TvShow;
import id.dendickys.moviecatalogue.interfaces.RetrofitInterface;
import id.dendickys.moviecatalogue.helper.Constant;
import id.dendickys.moviecatalogue.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShowViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TvShow>> tvShowList;

    public LiveData<ArrayList<TvShow>> getAllTvShow() {
        if (tvShowList == null) {
            tvShowList = new MutableLiveData<ArrayList<TvShow>>();
            loadTvShow();
        }
        return tvShowList;
    }

    private void loadTvShow() {
        RetrofitInterface api = RetrofitClient.getClient().create(RetrofitInterface.class);
        Call<ResultsTvShow> call = api.getAllTvShow();

        call.enqueue(new Callback<ResultsTvShow>() {
            @Override
            public void onResponse(Call<ResultsTvShow> call, Response<ResultsTvShow> response) {
                assert response.body() != null;
                tvShowList.setValue(response.body().getListTvShow());
            }

            @Override
            public void onFailure(Call<ResultsTvShow> call, Throwable t) {
                Log.d("onFailure: ", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public void searchTvShow(String query) {
        RetrofitInterface api = RetrofitClient.getClient().create(RetrofitInterface.class);
        Call<ResultsTvShow> call = api.getTvShow(query);

        call.enqueue(new Callback<ResultsTvShow>() {
            @Override
            public void onResponse(Call<ResultsTvShow> call, Response<ResultsTvShow> response) {
                assert response.body() != null;
                tvShowList.setValue(response.body().getListTvShow());
            }

            @Override
            public void onFailure(Call<ResultsTvShow> call, Throwable t) {
                Log.d("onFailure: ", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
