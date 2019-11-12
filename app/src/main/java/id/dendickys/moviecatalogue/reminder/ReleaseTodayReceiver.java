package id.dendickys.moviecatalogue.reminder;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.entity.Movies;
import id.dendickys.moviecatalogue.entity.NotificationItem;
import id.dendickys.moviecatalogue.entity.ResultsMovies;
import id.dendickys.moviecatalogue.interfaces.RetrofitInterface;
import id.dendickys.moviecatalogue.network.RetrofitClient;
import id.dendickys.moviecatalogue.ui.activity.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReleaseTodayReceiver extends BroadcastReceiver {
    public static final String TYPE_RELEASE_TODAY = "Release Today";
    private int idNotification = 0;
    private List<Movies> stackNotif = new ArrayList<>();

    private static final CharSequence CHANNEL_NAME = "dendickys channel";
    private final static String GROUP_KEY_EMAILS = "group_key_emails";
    private final static int NOTIFICATION_REQUEST_CODE = 200;
    private static final int MAX_NOTIFICATION = 2;

    @Override
    public void onReceive(Context context, Intent intent) {
        idNotification++;
        getReleaseMoviesToday(context);
    }

    private void showReleaseTodayNotification(Context context) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_movie_filter_black);
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder mBuilder;

        String CHANNEL_ID = "channel_01";
        if (idNotification < MAX_NOTIFICATION) {
            mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle(stackNotif.get(idNotification).getTitle())
                    .setContentText(stackNotif.get(idNotification).getOverview())
                    .setSmallIcon(R.drawable.ic_movie_filter_black)
                    .setLargeIcon(largeIcon)
                    .setGroup(GROUP_KEY_EMAILS)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);
        } else {
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                    .addLine(stackNotif.get(idNotification).getTitle())
                    .addLine(stackNotif.get(idNotification - 1).getTitle())
                    .setBigContentTitle(idNotification + " new emails")
                    .setSummaryText("Movies Released Today");

            mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setContentTitle(idNotification + " new movies")
                    .setContentText("Movies Released Today")
                    .setSmallIcon(R.drawable.ic_movie_filter_black)
                    .setGroup(GROUP_KEY_EMAILS)
                    .setGroupSummary(true)
                    .setContentIntent(pendingIntent)
                    .setStyle(inboxStyle)
                    .setAutoCancel(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            mBuilder.setChannelId(CHANNEL_ID);

            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        }

        Notification notification = mBuilder.build();

        if (mNotificationManager != null) {
            mNotificationManager.notify(idNotification, notification);
        }
    }

    public void setReleaseTodayReminder(Context context, String time) {
        String TIME_FORMAT = "HH:mm";
        if (isDateInvalid(time, TIME_FORMAT)) return;

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseTodayReceiver.class);

        String[] timeArray = time.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
        calendar.set(Calendar.SECOND, 0);

        int ID_RELEASE_TODAY = 101;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ID_RELEASE_TODAY, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    public void cancelReleaseTodayReminder(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseTodayReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_REQUEST_CODE, intent, 0);
        pendingIntent.cancel();

        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

    private void getReleaseMoviesToday(final Context context) {
        String DATE_FORMAT = "yyyy-MM-dd";
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        String currentDate = df.format(new Date());

        RetrofitInterface api = RetrofitClient.getClient().create(RetrofitInterface.class);
        Call<ResultsMovies> call = api.getMoviesReleaseToday(currentDate);

        call.enqueue(new Callback<ResultsMovies>() {
            @Override
            public void onResponse(Call<ResultsMovies> call, Response<ResultsMovies> response) {
                assert response.body() != null;
                stackNotif = Objects.requireNonNull(response.body()).getListMovies();
                //int index = new Random().nextInt(listMovies.size());

                /*Movies movies = listMovies.get(index);
                String title = listMovies.get(index).getTitle();
                String overview = listMovies.get(index).getOverview();*/

                showReleaseTodayNotification(context);
            }

            @Override
            public void onFailure(Call<ResultsMovies> call, Throwable t) {

            }
        });
    }

    /*private void getReleaseMoviesToday(final Context context) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        String currentDate = dateFormat.format(new Date());

        String url = BASE_URL + "discover/movie?api_key=" + API_KEY + "&primary_release_date.gte=" + currentDate + "&primary_release_date.lte=" + currentDate;
        asyncHttpClient.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray array = jsonObject.getJSONArray("result");

                    message = "";
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject releaseMovie = array.getJSONObject(i);
                        message = message + releaseMovie.getString("title") + ", ";
                    }

                    showReminderNotification(context, title, message);
                } catch (Exception e) {
                    Log.d("MovieReleaseError", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("NoConnection", Objects.requireNonNull(error.getMessage()));
            }
        });
    }*/

    private boolean isDateInvalid(String time, String time_format) {
        try {
            DateFormat df = new SimpleDateFormat(time_format, Locale.getDefault());
            df.setLenient(false);
            df.parse(time);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }
}
