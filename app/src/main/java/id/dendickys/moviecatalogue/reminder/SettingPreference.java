package id.dendickys.moviecatalogue.reminder;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingPreference {
    private static final String PREFS_NAME = "setting_preference";
    public static final String STATUS_DAILY_REMINDER = "daily_reminder";
    public static final String STATUS_RELEASE_TODAY = "release_today";

    private final SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SettingPreference(Context mContext) {
        sharedPreferences = mContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public Boolean getStatusDailyReminder() {
        return sharedPreferences.getBoolean(STATUS_DAILY_REMINDER, false);
    }

    public Boolean getStatusReleaseToday() {
        return sharedPreferences.getBoolean(STATUS_RELEASE_TODAY, false);
    }

    public void saveBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }
}
