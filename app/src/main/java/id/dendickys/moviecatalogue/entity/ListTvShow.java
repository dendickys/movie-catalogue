package id.dendickys.moviecatalogue.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListTvShow {
    @SerializedName("results")
    private ArrayList<ItemTvShow> listTvShow;

    public ArrayList<ItemTvShow> getListTvShow() {
        return listTvShow;
    }

    public void setListTvShow(ArrayList<ItemTvShow> listTvShow) {
        this.listTvShow = listTvShow;
    }
}
