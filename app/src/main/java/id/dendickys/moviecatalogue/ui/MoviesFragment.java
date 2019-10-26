package id.dendickys.moviecatalogue.ui;


import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.adapter.MoviesAdapter;
import id.dendickys.moviecatalogue.entity.ItemMovies;
import id.dendickys.moviecatalogue.viewmodels.MoviesViewModel;

public class MoviesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private ProgressBar progressBar;

    public MoviesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindData(view);
        recyclerView.setHasFixedSize(true);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 6));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        }

        MoviesViewModel moviesViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MoviesViewModel.class);
        showLoading(true);

        moviesViewModel.getAllMovies().observe(this, new Observer<ArrayList<ItemMovies>>() {
            @Override
            public void onChanged(ArrayList<ItemMovies> movies) {
                if (movies != null) {
                    moviesAdapter = new MoviesAdapter(movies);
                    moviesAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(moviesAdapter);
                    showLoading(false);
                }
            }
        });
    }

    private void bindData(View view) {
        progressBar = view.findViewById(R.id.progressBar_movies);
        recyclerView = view.findViewById(R.id.rv_movies);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
