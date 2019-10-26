package id.dendickys.moviecatalogue.ui;


import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.adapter.TvShowAdapter;
import id.dendickys.moviecatalogue.entity.ItemTvShow;
import id.dendickys.moviecatalogue.viewmodels.TvShowViewModel;

public class TvShowFragment extends Fragment {

    private RecyclerView recyclerView;
    private TvShowAdapter tvShowAdapter;
    private ProgressBar progressBar;

    public TvShowFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
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

        TvShowViewModel tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        showLoading(true);

        tvShowViewModel.getAllTvShow().observe(this, new Observer<ArrayList<ItemTvShow>>() {
            @Override
            public void onChanged(ArrayList<ItemTvShow> tvShows) {
                if (tvShows != null) {
                    tvShowAdapter = new TvShowAdapter(tvShows);
                    tvShowAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(tvShowAdapter);
                    showLoading(false);
                }
            }
        });
    }

    private void bindData(View view) {
        progressBar = view.findViewById(R.id.progressBar_tv_show);
        recyclerView = view.findViewById(R.id.rv_tv_show);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
