package id.dendickys.moviecatalogue.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.entity.Fav.FavMovies;
import id.dendickys.moviecatalogue.entity.Movies;
import id.dendickys.moviecatalogue.viewmodel.MoviesViewModel;
import id.dendickys.moviecatalogue.widget.FavoriteMovieWidget;

import static id.dendickys.moviecatalogue.helper.Constant.BASE_URL_POSTER;
import static id.dendickys.moviecatalogue.ui.activity.MainActivity.favMoviesDb;

public class DetailMoviesActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView mPoster, mFavorite;
    private TextView mReleaseDate, mVoteAverage, mOverview;
    private ProgressBar progressBar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    public static final String MOVIE_ID = "id";
    private int id;
    private String poster_path, title, release_date, vote_average, overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        bindData();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.white));

        MoviesViewModel detailMovieViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MoviesViewModel.class);
        showLoading(true);

        detailMovieViewModel.getAllMovies().observe(this, new Observer<List<Movies>>() {
            @Override
            public void onChanged(List<Movies> movies) {
                if (movies != null) {
                    setDetailMovie();
                    showLoading(false);
                }
            }
        });

        addToFavorite();
    }

    private void bindData() {
        toolbar = findViewById(R.id.toolbar_movie);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_movie);
        mPoster = findViewById(R.id.img_poster_movie);
        mReleaseDate = findViewById(R.id.tv_release_date_movie);
        mVoteAverage = findViewById(R.id.tv_vote_average_movie);
        mOverview = findViewById(R.id.tv_overview_movie);
        progressBar = findViewById(R.id.progressBar_detail_movie);
        mFavorite = findViewById(R.id.img_favorite);
    }

    private void setDetailMovie() {
        Movies movie = getIntent().getParcelableExtra(MOVIE_ID);
        assert movie != null;
        collapsingToolbarLayout.setTitle(movie.getTitle());
        Glide.with(getApplicationContext())
                .load(BASE_URL_POSTER + "w500/" + movie.getPoster_path())
                .into(mPoster);
        mReleaseDate.setText(movie.getRelease_date());
        mVoteAverage.setText(movie.getVote_average());
        mOverview.setText(movie.getOverview());

        id = Integer.valueOf(movie.getId());
        poster_path = movie.getPoster_path();
        title = movie.getTitle();
        release_date = movie.getRelease_date();
        vote_average = movie.getVote_average();
        overview = movie.getOverview();

        if (favMoviesDb.favMoviesDao().isFavorite(id) == 1) {
            mFavorite.setImageResource(R.drawable.ic_favorite_pink);
        } else {
            mFavorite.setImageResource(R.drawable.ic_favorite_border_black);
        }
    }

    private void addToFavorite() {
        mFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FavMovies favMovies = new FavMovies();

                favMovies.setId(id);
                favMovies.setPoster_path(poster_path);
                favMovies.setTitle(title);
                favMovies.setRelease_date(release_date);
                favMovies.setVote_average(vote_average);
                favMovies.setOverview(overview);

                if (favMoviesDb.favMoviesDao().isFavorite(id) == 1) {
                    mFavorite.setImageResource(R.drawable.ic_favorite_border_black);
                    favMoviesDb.favMoviesDao().deleteFavMovies(favMovies);
                    FavoriteMovieWidget.updateWidget(getApplicationContext());
                    Toast.makeText(DetailMoviesActivity.this, R.string.removed_from_favorite, Toast.LENGTH_SHORT).show();
                } else {
                    mFavorite.setImageResource(R.drawable.ic_favorite_pink);
                    favMoviesDb.favMoviesDao().addFavMovies(favMovies);
                    FavoriteMovieWidget.updateWidget(getApplicationContext());
                    Toast.makeText(DetailMoviesActivity.this, R.string.added_to_favorite, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
