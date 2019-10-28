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
import id.dendickys.moviecatalogue.entity.Fav.FavTvShow;

import static id.dendickys.moviecatalogue.helper.Constant.BASE_URL_POSTER;

public class FavTvShowAdapter extends RecyclerView.Adapter<FavTvShowAdapter.FavTvShowViewHolder> {

    private List<FavTvShow> favTvShows;
    private Context mContext;

    public FavTvShowAdapter(List<FavTvShow> favTvShows, Context context) {
        this.favTvShows = favTvShows;
        this.mContext = context;
    }

    @NonNull
    @Override
    public FavTvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_show, parent, false);
        return new FavTvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavTvShowViewHolder holder, int position) {
        holder.bind(favTvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return favTvShows.size();
    }

    class FavTvShowViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvStar;

        FavTvShowViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.img_poster_tvshow);
            tvStar = itemView.findViewById(R.id.tv_vote_tvshow);
        }

        void bind(FavTvShow favTvShow) {
            Glide.with(itemView.getContext())
                    .load(BASE_URL_POSTER + "w185/" + favTvShow.getPoster_path())
                    .apply(new RequestOptions()).override(100, 150)
                    .into(imgPoster);
            tvStar.setText(favTvShow.getVote_average());
        }
    }
}
