package id.dendickys.moviecatalogue.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.db.FavDb;
import id.dendickys.moviecatalogue.entity.NotificationItem;

public class MainActivity extends AppCompatActivity {

    public static FavDb favMoviesDb, favTvShowDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        addToFavorite();
    }

    private void addToFavorite() {
        favMoviesDb = Room.databaseBuilder(getApplicationContext(), FavDb.class, "myFavMovies")
                .allowMainThreadQueries()
                .build();
        favTvShowDb = Room.databaseBuilder(getApplicationContext(), FavDb.class, "myFavTvShow")
                .allowMainThreadQueries()
                .build();
    }
}
