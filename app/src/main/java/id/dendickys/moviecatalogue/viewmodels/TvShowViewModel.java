package id.dendickys.moviecatalogue.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Objects;

import id.dendickys.moviecatalogue.entity.ItemTvShow;
import id.dendickys.moviecatalogue.entity.ListTvShow;
import id.dendickys.moviecatalogue.interfaces.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TvShowViewModel extends ViewModel {
    private MutableLiveData<ArrayList<ItemTvShow>> tvShowList;

    public LiveData<ArrayList<ItemTvShow>> getAllTvShow() {
        if (tvShowList == null) {
            tvShowList = new MutableLiveData<ArrayList<ItemTvShow>>();
            loadTvShow();
        }
        return tvShowList;
    }

    private void loadTvShow() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClient api = retrofit.create(ApiClient.class);
        Call<ListTvShow> call = api.getAllTvShow();

        call.enqueue(new Callback<ListTvShow>() {
            @Override
            public void onResponse(Call<ListTvShow> call, Response<ListTvShow> response) {
                assert response.body() != null;
                tvShowList.setValue(response.body().getListTvShow());
            }

            @Override
            public void onFailure(Call<ListTvShow> call, Throwable t) {
                Log.d("onFailure: ", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
