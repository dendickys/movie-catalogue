package id.dendickys.moviecatalogue.adapter;

import android.content.Intent;
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

import id.dendickys.moviecatalogue.ui.activity.DetailMoviesActivity;
import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.entity.Movies;

import static id.dendickys.moviecatalogue.helper.Constant.BASE_URL_POSTER;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<Movies> listMovies;

    public MoviesAdapter(List<Movies> listMovies) {
        this.listMovies = listMovies;
    }

    @NonNull
    @Override
    public MoviesAdapter.MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MoviesViewHolder holder, int position) {
        holder.bind(listMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvStar;

        MoviesViewHolder(@NonNull final View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.img_poster_movie);
            tvStar = itemView.findViewById(R.id.tv_vote_movies);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(itemView.getContext(), DetailMoviesActivity.class);
                    intent.putExtra(DetailMoviesActivity.MOVIE_ID, listMovies.get(position));
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        void bind(Movies resultsMovies) {
            if (resultsMovies.getPoster_path() != null) {
                Glide.with(itemView.getContext())
                        .load(BASE_URL_POSTER + "w185/" + resultsMovies.getPoster_path())
                        .apply(new RequestOptions()).override(100, 150)
                        .into(imgPoster);
            } else {
                Glide.with(itemView.getContext())
                        .load(R.drawable.ic_no_image_white)
                        .apply(new RequestOptions()).override(100, 150)
                        .into(imgPoster);
            }
            tvStar.setText(resultsMovies.getVote_average());
        }
    }
}
