package id.dendickys.moviecatalogue.ui.fragment;


import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.List;

import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.adapter.FavMoviesAdapter;
import id.dendickys.moviecatalogue.entity.Fav.FavMovies;
import id.dendickys.moviecatalogue.interfaces.LoadFavMoviesCallback;

import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.CONTENT_URI;
import static id.dendickys.moviecatalogue.ui.activity.MainActivity.favMoviesDb;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFavFragment extends Fragment {
    private ProgressBar progressBar;
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
        progressBar.setVisibility(View.VISIBLE);
        setUpRecyclerView();
        getFavMovies();
    }

    private static class LoadFavMovieAsync extends AsyncTask<Void, Void, Cursor> {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadFavMoviesCallback> weakCallback;

        private LoadFavMovieAsync(Context context, LoadFavMoviesCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Context context = weakContext.get();
            return context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
        }
    }

    public static class DataObserver extends ContentObserver {
        final Context context;

        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadFavMovieAsync(context, (LoadFavMoviesCallback) context).execute();
        }
    }

    private void setUpRecyclerView() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 6));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        }
        recyclerView.setHasFixedSize(true);
    }

    private void onBind(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.rv_movies_favorites);
    }

    private void getFavMovies() {
        List<FavMovies> favMovies = favMoviesDb.favMoviesDao().getAllFavMovies();
        progressBar.setVisibility(View.INVISIBLE);
        FavMoviesAdapter favMoviesAdapter = new FavMoviesAdapter(favMovies);
        recyclerView.setAdapter(favMoviesAdapter);
    }
}
