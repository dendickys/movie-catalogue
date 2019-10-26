package id.dendickys.moviecatalogue.ui;


import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.adapter.FavTvShowAdapter;
import id.dendickys.moviecatalogue.entity.Fav.FavTvShow;

import static id.dendickys.moviecatalogue.MainActivity.favTvShowDb;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFavFragment extends Fragment {

    private RecyclerView recyclerView;

    public TvShowFavFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBind(view);
        recyclerView.setHasFixedSize(true);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 6));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        }

        getFavTvShow();
    }

    private void onBind(View view) {
        recyclerView = view.findViewById(R.id.rv_tvshow_favorites);
    }

    private void getFavTvShow() {
        List<FavTvShow> favTvShows = favTvShowDb.favTvShowDao().getAllFavTvShow();
        FavTvShowAdapter favTvShowAdapter = new FavTvShowAdapter(favTvShows, getContext());
        recyclerView.setAdapter(favTvShowAdapter);
    }
}
