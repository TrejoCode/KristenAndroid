package mx.edu.upqroo.kristenandroid.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.activities.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class ScheduleWidget extends AppWidgetProvider {
    public static final String UPDATE_MEETING_ACTION = "APPWIDGET_UPDATE";
    public static final String EXTRA_ITEM = "EXTRA_ITEM";
    private static final String ACTION_SCHEDULED_UPDATE = "SCHEDULED_UPDATE";


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Date date = java.util.Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, d MMMM", Locale.US);
        CharSequence widgetText = formatter.format(date);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.schedule_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Set up the intent that starts the ListViewService, which will
        // provide the views for this collection.
        Intent intent = new Intent(context, ListViewWidgetService.class);
        // Add the app widget ID to the intent extras.
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        views.setRemoteAdapter(R.id.list_widget, intent);

        Intent startActivityIntent = new Intent(context, MainActivity.class);

        PendingIntent startActivityPendingIntent
                = PendingIntent.getActivity(context, 0,
                startActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setPendingIntentTemplate(R.id.list_widget, startActivityPendingIntent);
        views.setEmptyView(R.id.list_widget, R.id.empty_view);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager mgr = AppWidgetManager.getInstance(context);
        if (Objects.requireNonNull(intent.getAction()).equals(UPDATE_MEETING_ACTION)) {
            int appWidgetIds[] = mgr.getAppWidgetIds(new ComponentName(context, ScheduleWidget.class));
            mgr.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.list_widget);
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        scheduleNextUpdate(context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static void scheduleNextUpdate(Context context) {
        AlarmManager alarmManager =
                (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        // Substitute AppWidget for whatever you named your AppWidgetProvider subclass
        Intent intent = new Intent(context, ScheduleWidget.class);
        intent.setAction(ACTION_SCHEDULED_UPDATE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        // Get a calendar instance for midnight tomorrow.
        Calendar midnight = Calendar.getInstance();
        midnight.set(Calendar.HOUR_OF_DAY, 0);
        midnight.set(Calendar.MINUTE, 0);
        // Schedule one second after midnight, to be sure we are in the right day next time this
        // method is called.  Otherwise, we risk calling onUpdate multiple times within a few
        // milliseconds
        midnight.set(Calendar.SECOND, 1);
        midnight.set(Calendar.MILLISECOND, 0);
        midnight.add(Calendar.DAY_OF_YEAR, 1);

        // For API 19 and later, set may fire the intent a little later to save battery,
        // setExact ensures the intent goes off exactly at midnight.
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, midnight.getTimeInMillis(), pendingIntent);
        }
    }
}

