package id.dendickys.moviecatalogue.entity.Fav;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tvshow_favorites")
public class FavTvShow {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "poster_path")
    private String poster_path;
    @ColumnInfo(name = "name")
    private String title;
    @ColumnInfo(name = "first_air_date")
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
}
