package id.dendickys.moviecatalogue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.entity.Fav.FavMovies;

import static id.dendickys.moviecatalogue.interfaces.ApiClient.BASE_URL_POSTER;

public class FavMoviesAdapter extends RecyclerView.Adapter<FavMoviesAdapter.FavMoviesViewHolder> {

    private List<FavMovies> favMovies;
    private Context mContext;

    public FavMoviesAdapter(List<FavMovies> favMovies, Context context) {
        this.favMovies = favMovies;
        this.mContext = context;
    }

    @NonNull
    @Override
    public FavMoviesAdapter.FavMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new FavMoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMoviesAdapter.FavMoviesViewHolder holder, int position) {
        holder.bind(favMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return favMovies.size();
    }

    class FavMoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvStar;

        FavMoviesViewHolder(@NonNull View itemView) {
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
