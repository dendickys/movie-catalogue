package id.dendickys.moviecatalogue.entity.Fav;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies_favorites")
public class FavMovies implements Parcelable {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "poster_path")
    private String poster_path;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "release_date")
    private String release_date;
    @ColumnInfo(name = "vote_average")
    private String vote_average;
    @ColumnInfo(name = "overview")
    private String overview;

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

    public FavMovies() {
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
