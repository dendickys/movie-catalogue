package id.dendickys.moviecatalogue.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import java.util.Objects;

import id.dendickys.moviecatalogue.helper.FavMoviesHelper;
import id.dendickys.moviecatalogue.ui.fragment.MoviesFavFragment;

import static id.dendickys.moviecatalogue.db.DatabaseContract.AUTHORITY;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.CONTENT_URI;
import static id.dendickys.moviecatalogue.db.DatabaseContract.FavMoviesColumns.TABLE_NAME;

public class FavMoviesProvider extends ContentProvider {

    private static final int NOTE = 1;
    private static final int NOTE_ID = 2;
    private FavMoviesHelper favMoviesHelper;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, NOTE);
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME + "/#", NOTE_ID);
    }

    @Override
    public boolean onCreate() {
        favMoviesHelper = FavMoviesHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        favMoviesHelper.open();
        Cursor cursor;
        switch (sUriMatcher.match(uri)) {
            case NOTE:
                cursor = favMoviesHelper.queryAll();
                break;
            case NOTE_ID:
                cursor = favMoviesHelper.queryById(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        favMoviesHelper.open();
        long added;
        switch (sUriMatcher.match(uri)) {
            case NOTE:
                added = favMoviesHelper.insert(values);
                break;
            default:
                added = 0;
                break;
        }
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(CONTENT_URI, new MoviesFavFragment.DataObserver(new Handler(), getContext()));

        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        favMoviesHelper.open();
        int updated;
        switch (sUriMatcher.match(uri)) {
            case NOTE_ID:
                updated = favMoviesHelper.update(uri.getLastPathSegment(), values);
                break;
            default:
                updated = 0;
                break;
        }
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(CONTENT_URI, new MoviesFavFragment.DataObserver(new Handler(), getContext()));

        return updated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int deleted;
        switch (sUriMatcher.match(uri)) {
            case NOTE_ID:
                deleted = favMoviesHelper.deleteById(uri.getLastPathSegment());
                break;
            default:
                deleted = 0;
                break;
        }
        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(CONTENT_URI, new MoviesFavFragment.DataObserver(new Handler(), getContext()));

        return deleted;
    }
}
