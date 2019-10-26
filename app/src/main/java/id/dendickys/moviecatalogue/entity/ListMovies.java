package id.dendickys.moviecatalogue.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListMovies {
    @SerializedName("results")
    private ArrayList<ItemMovies> listMovies;

    public ArrayList<ItemMovies> getListMovies() {
        return listMovies;
    }

    public void setListMovies(ArrayList<ItemMovies> listMovies) {
        this.listMovies = listMovies;
    }
}
