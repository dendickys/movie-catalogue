package id.dendickys.moviecatalogue.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResultsMovies {
    @SerializedName("results")
    private List<Movies> listMovies;

    public List<Movies> getListMovies() {
        return listMovies;
    }

    public void setListMovies(List<Movies> listMovies) {
        this.listMovies = listMovies;
    }
}

