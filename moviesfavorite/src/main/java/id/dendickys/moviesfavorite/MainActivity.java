package id.dendickys.moviesfavorite;

import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import id.dendickys.moviesfavorite.adapter.FavMoviesAdapter;
import id.dendickys.moviesfavorite.model.FavMovies;

import static id.dendickys.moviesfavorite.db.DatabaseContract.FavMoviesColumns.CONTENT_URI;
import static id.dendickys.moviesfavorite.helper.MappingHelper.mapCursorToArrayList;

public class MainActivity extends AppCompatActivity implements LoadFavMoviesCallback {

    private FavMoviesAdapter favMoviesAdapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private static final String EXTRA_STATE = "EXTRA_STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindView();
        setUpRecyclerView();

        HandlerThread handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());

        DataObserver myObserver = new DataObserver(handler, this);
        getContentResolver().registerContentObserver(CONTENT_URI, true, myObserver);

        if (savedInstanceState == null) {
            new LoadFavMoviesAsync(this, this).execute();
        } else {
            ArrayList<FavMovies> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                favMoviesAdapter.setListFavMovies(list);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, favMoviesAdapter.getListFavMovies());
    }

    @Override
    public void postExecute(Cursor favMovies) {
        progressBar.setVisibility(View.INVISIBLE);
        ArrayList<FavMovies> list = mapCursorToArrayList(favMovies);
        if (list.size() > 0) {
            favMoviesAdapter.setListFavMovies(list);
        } else {
            favMoviesAdapter.setListFavMovies(new ArrayList<FavMovies>());
            Snackbar.make(recyclerView, "Tidak ada data saat ini", Snackbar.LENGTH_SHORT).show();
        }
    }

    private static class LoadFavMoviesAsync extends AsyncTask<Void, Void, Cursor> {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadFavMoviesCallback> weakCallback;

        LoadFavMoviesAsync(Context context, LoadFavMoviesCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            Context context = weakContext.get();
            return context.getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor data) {
            super.onPostExecute(data);
            weakCallback.get().postExecute(data);
        }
    }

    static class DataObserver extends ContentObserver {

        final Context context;

        DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            new LoadFavMoviesAsync(context, (MainActivity) context).execute();
        }
    }

    private void setUpRecyclerView() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 6));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }
        recyclerView.setHasFixedSize(true);
        favMoviesAdapter = new FavMoviesAdapter(this);
        recyclerView.setAdapter(favMoviesAdapter);
    }

    private void bindView() {
        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.rv_movies_favorites);
    }
}

interface LoadFavMoviesCallback {
    void postExecute(Cursor data);
}