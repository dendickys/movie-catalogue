package id.dendickys.moviesfavorite.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FavMovies implements Parcelable {
    private int id;
    private String poster_path;
    private String title;
    private String release_date;
    private String vote_average;
    private String overview;

    public FavMovies(int id, String poster_path, String title, String release_date, String vote_average, String overview) {
        this.id = id;
        this.poster_path = poster_path;
        this.title = title;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.overview = overview;
    }

    public FavMovies() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    /*public FavMovies(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.poster_path = getColumnString(cursor, DatabaseContract.FavMoviesColumns.POSTER_PATH);
        this.title = getColumnString(cursor, DatabaseContract.FavMoviesColumns.TITLE);
        this.release_date = getColumnString(cursor, DatabaseContract.FavMoviesColumns.RELEASE_DATE);
        this.vote_average = getColumnString(cursor, DatabaseContract.FavMoviesColumns.VOTE_AVERAGE);
        this.overview = getColumnString(cursor, DatabaseContract.FavMoviesColumns.OVERVIEW);
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.poster_path);
        dest.writeString(this.title);
        dest.writeString(this.release_date);
        dest.writeString(this.vote_average);
        dest.writeString(this.overview);
    }

    private FavMovies(Parcel in) {
        this.id = in.readInt();
        this.poster_path = in.readString();
        this.title = in.readString();
        this.release_date = in.readString();
        this.vote_average = in.readString();
        this.overview = in.readString();
    }

    public static final Parcelable.Creator<FavMovies> CREATOR = new Parcelable.Creator<FavMovies>() {
        @Override
        public FavMovies createFromParcel(Parcel source) {
            return new FavMovies(source);
        }

        @Override
        public FavMovies[] newArray(int size) {
            return new FavMovies[size];
        }
    };
}
