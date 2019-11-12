package id.dendickys.moviecatalogue.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import id.dendickys.moviecatalogue.R;
import id.dendickys.moviecatalogue.reminder.DailyReminderReceiver;
import id.dendickys.moviecatalogue.reminder.ReleaseTodayReceiver;
import id.dendickys.moviecatalogue.reminder.SettingPreference;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {

    private Toolbar toolbar;
    private SwitchCompat switchDailyReminder, switchReleaseToday;
    private DailyReminderReceiver dailyReminderReceiver;
    private ReleaseTodayReceiver releaseTodayReceiver;
    private SettingPreference settingPreference;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onBind(view);
        setUpToolbar();
        dailyReminderReceiver = new DailyReminderReceiver();
        releaseTodayReceiver = new ReleaseTodayReceiver();
        settingPreference = new SettingPreference(Objects.requireNonNull(getContext()));

        switchDailyReminder.setOnClickListener(this);
        switchReleaseToday.setOnClickListener(this);

        checkReminderStatus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_daily_reminder:
                if (switchDailyReminder.isChecked()) {
                    settingPreference.saveBoolean(SettingPreference.STATUS_DAILY_REMINDER, true);
                    dailyReminderReceiver.setDailyReminder(getContext(), "07:00");
                    Toast.makeText(getContext(), getString(R.string.daily_reminder_enabled), Toast.LENGTH_SHORT).show();
                } else {
                    settingPreference.saveBoolean(SettingPreference.STATUS_DAILY_REMINDER, false);
                    dailyReminderReceiver.cancelDailyReminder(Objects.requireNonNull(getContext()));
                    Toast.makeText(getContext(), getString(R.string.daily_reminder_disabled), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.switch_release_today:
                if (switchReleaseToday.isChecked()) {
                    settingPreference.saveBoolean(SettingPreference.STATUS_RELEASE_TODAY, true);
                    releaseTodayReceiver.setReleaseTodayReminder(getContext(), "23:46");
                    Toast.makeText(getContext(), getString(R.string.release_today_enabled), Toast.LENGTH_SHORT).show();
                } else {
                    settingPreference.saveBoolean(SettingPreference.STATUS_RELEASE_TODAY, false);
                    releaseTodayReceiver.cancelReleaseTodayReminder(Objects.requireNonNull(getContext()));
                    Toast.makeText(getContext(), getString(R.string.release_today_disabled), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void checkReminderStatus() {
        if (settingPreference.getStatusDailyReminder()) {
            switchDailyReminder.setChecked(true);
        } else {
            switchDailyReminder.setChecked(false);
        }

        if (settingPreference.getStatusReleaseToday()) {
            switchReleaseToday.setChecked(true);
        } else {
            switchReleaseToday.setChecked(false);
        }
    }

    private void setUpToolbar() {
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.title_settings);
        }
    }

    private void onBind(View view) {
        toolbar = view.findViewById(R.id.toolbar_settings);
        switchDailyReminder = view.findViewById(R.id.switch_daily_reminder);
        switchReleaseToday = view.findViewById(R.id.switch_release_today);
    }
}
