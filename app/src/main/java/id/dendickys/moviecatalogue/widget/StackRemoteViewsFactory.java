package id.dendickys.moviecatalogue.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;

import java.util.List;

import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.entity.Fav.FavMovies;

import static id.dendickys.moviecatalogue.helper.Constant.BASE_URL_POSTER;
import static id.dendickys.moviecatalogue.ui.activity.MainActivity.favMoviesDb;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private List<FavMovies> mWidgetItems;
    private final Context mContext;

    StackRemoteViewsFactory(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {
        mWidgetItems = favMoviesDb.favMoviesDao().getAllFavMovies();
    }

    @Override
    public void onDataSetChanged() {
        mWidgetItems = favMoviesDb.favMoviesDao().getAllFavMovies();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.item_widget);

        if (mWidgetItems.size() > 0) {
            FavMovies favMovies = mWidgetItems.get(position);

            try {
                Bitmap bitmap = Glide.with(mContext)
                        .asBitmap()
                        .load(BASE_URL_POSTER + "w185/" + favMovies.getPoster_path())
                        .submit(480, 640)
                        .get();

                rv.setImageViewBitmap(R.id.imageView, bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Bundle extras = new Bundle();
            extras.putString(FavoriteMovieWidget.EXTRA_ITEM, favMovies.getTitle());
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);

            rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);

            return rv;
        } else {
            return null;
        }
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
