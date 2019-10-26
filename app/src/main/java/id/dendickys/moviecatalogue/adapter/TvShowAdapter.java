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

import java.util.ArrayList;

import id.dendickys.moviecatalogue.DetailTvShowActivity;
import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.entity.ItemTvShow;

import static id.dendickys.moviecatalogue.interfaces.ApiClient.BASE_URL_POSTER;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private ArrayList<ItemTvShow> listTvShow;

    public TvShowAdapter(ArrayList<ItemTvShow> listTvShow) {
        this.listTvShow = listTvShow;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_show, parent, false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        holder.bind(listTvShow.get(position));
    }

    @Override
    public int getItemCount() {
        return listTvShow.size();
    }

    class TvShowViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvStar;

        TvShowViewHolder(@NonNull final View itemView) {
            super(itemView);

            imgPoster = itemView.findViewById(R.id.img_poster_tvshow);
            tvStar = itemView.findViewById(R.id.tv_vote_tvshow);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(itemView.getContext(), DetailTvShowActivity.class);
                    intent.putExtra(DetailTvShowActivity.TV_SHOW_ID, listTvShow.get(position));
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        void bind(ItemTvShow itemTvShow) {
            Glide.with(itemView.getContext())
                    .load(BASE_URL_POSTER + "w185/" + itemTvShow.getPoster_path())
                    .apply(new RequestOptions()).override(100, 150)
                    .into(imgPoster);
            tvStar.setText(itemTvShow.getVote_average());
        }
    }
}
