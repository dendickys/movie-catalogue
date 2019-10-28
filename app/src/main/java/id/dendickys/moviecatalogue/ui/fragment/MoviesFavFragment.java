package id.dendickys.moviecatalogue.ui.fragment;


import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.adapter.FavMoviesAdapter;
import id.dendickys.moviecatalogue.entity.Fav.FavMovies;

import static id.dendickys.moviecatalogue.ui.activity.MainActivity.favMoviesDb;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFavFragment extends Fragment {

    private RecyclerView recyclerView;

    public MoviesFavFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies_fav, container, false);
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

        getFavMovies();
    }

    private void onBind(View view) {
        recyclerView = view.findViewById(R.id.rv_movies_favorites);
    }

    private void getFavMovies() {
        List<FavMovies> favMovies = favMoviesDb.favMoviesDao().getAllFavMovies();
        FavMoviesAdapter favMoviesAdapter = new FavMoviesAdapter(favMovies, getContext());
        recyclerView.setAdapter(favMoviesAdapter);
    }
}
