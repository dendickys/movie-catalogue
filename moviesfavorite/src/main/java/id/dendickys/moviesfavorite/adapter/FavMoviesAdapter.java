package id.dendickys.moviesfavorite.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import id.dendickys.moviesfavorite.R;
import id.dendickys.moviesfavorite.model.FavMovies;

import static id.dendickys.moviesfavorite.helper.Constant.BASE_URL_POSTER;

public class FavMoviesAdapter extends RecyclerView.Adapter<FavMoviesAdapter.FavMoviesViewHolder> {

    private final ArrayList<FavMovies> favMovies = new ArrayList<>();
    private final Activity activity;

    public FavMoviesAdapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<FavMovies> getListFavMovies() {
        return favMovies;
    }

    public void setListFavMovies(ArrayList<FavMovies> listFavMovies) {
        this.favMovies.clear();
        this.favMovies.addAll(listFavMovies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavMoviesAdapter.FavMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new FavMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMoviesAdapter.FavMoviesViewHolder holder, int position) {
        holder.bind(getListFavMovies().get(position));
    }

    @Override
    public int getItemCount() {
        return favMovies.size();
    }

    class FavMoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvStar;

        FavMoviesViewHolder(@NonNull final View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.img_poster_movie);
            tvStar = itemView.findViewById(R.id.tv_vote_movies);
        }

        void bind(FavMovies favMovies) {
            Glide.with(itemView.getContext())
                    .load(BASE_URL_POSTER + "w185/" + favMovies.getPoster_path())
                    .apply(new RequestOptions()).override(100, 150)
                    .into(imgPoster);
            tvStar.setText(favMovies.getVote_average());
        }
    }
}
