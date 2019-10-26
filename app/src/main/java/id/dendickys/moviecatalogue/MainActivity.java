package id.dendickys.moviecatalogue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.dendickys.moviecatalogue.db.FavMoviesDb;
import id.dendickys.moviecatalogue.db.FavTvShowDb;

public class MainActivity extends AppCompatActivity {

    public static FavMoviesDb favMoviesDb;
    public static FavTvShowDb favTvShowDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_movies, R.id.navigation_tv_show, R.id.navigation_favorites)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        addToFavorite();
    }

    private void addToFavorite() {
        favMoviesDb = Room.databaseBuilder(getApplicationContext(), FavMoviesDb.class, "myFavMovies")
                .allowMainThreadQueries()
                .build();
        favTvShowDb = Room.databaseBuilder(getApplicationContext(), FavTvShowDb.class, "myFavTvShow")
                .allowMainThreadQueries()
                .build();
    }
}
