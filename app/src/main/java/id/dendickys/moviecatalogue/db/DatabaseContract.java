package id.dendickys.moviecatalogue.db;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    public static final String AUTHORITY = "id.dendickys.moviecatalogue";
    private static final String SCHEME = "content";

    public static final class FavMoviesColumns implements BaseColumns {
        public static final String TABLE_NAME = "favorites_movies";
        public static final String _ID = "_id";
        public static final String POSTER_PATH = "poster_path";
        public static final String TITLE = "title";
        public static final String RELEASE_DATE = "release_date";
        public static final String VOTE_AVERAGE = "vote_average";
        public static final String OVERVIEW = "overview";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }
}
