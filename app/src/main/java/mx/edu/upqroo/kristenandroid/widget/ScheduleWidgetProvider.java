package mx.edu.upqroo.kristenandroid.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.core.app.TaskStackBuilder;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.ui.activities.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class ScheduleWidgetProvider extends AppWidgetProvider {
    public static final String EXTRA_ITEM = "EXTRA_ITEM";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            //updateAppWidget(context, appWidgetManager, appWidgetId);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.schedule_widget);

            // Set text tp title
            views.setTextViewText(R.id.appwidget_text, getDateText());
            // click event handler for the title, launches the app when the user clicks on title
            Intent titleIntent = new Intent(context, MainActivity.class);
            PendingIntent titlePendingIntent = PendingIntent.getActivity(context,
                    0,
                    titleIntent,
                    0);
            views.setOnClickPendingIntent(R.id.appwidget_text, titlePendingIntent);

            //Create an Intent with the AppWidgetManager.ACTION_APPWIDGET_UPDATE action//

            Intent intentUpdate = new Intent(context, ScheduleWidgetProvider.class);
            intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

            //Update the current widget instance only, by creating an array that contains the widget’s unique ID//

            int[] idArray = new int[]{appWidgetId};
            intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

            //Wrap the intent as a PendingIntent, using PendingIntent.getBroadcast()//

            PendingIntent pendingUpdate = PendingIntent.getBroadcast(
                    context, appWidgetId, intentUpdate,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            //Send the pending intent in response to the user tapping the ‘Update’ TextView//

            views.setOnClickPendingIntent(R.id.button_refresh_widget, pendingUpdate);



            Intent intent = new Intent(context, ListViewWidgetService.class);
            views.setRemoteAdapter(R.id.list_widget, intent);
            views.setEmptyView(R.id.list_widget, R.id.empty_view);

            // template to handle the click listener for each item
            Intent clickIntentTemplate = new Intent(context, MainActivity.class);
            PendingIntent clickPendingIntentTemplate = TaskStackBuilder.create(context)
                    .addNextIntentWithParentStack(clickIntentTemplate)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.list_widget, clickPendingIntentTemplate);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
        //scheduleNextUpdate(context);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        final String action = intent.getAction();
        if (action != null && action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            // refresh all your widgets
            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            ComponentName cn = new ComponentName(context, ScheduleWidgetProvider.class);
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.list_widget);
        }
        super.onReceive(context, intent);
    }

    public static void sendRefreshBroadcast(Context context) {
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.setComponent(new ComponentName(context, ScheduleWidgetProvider.class));
        context.sendBroadcast(intent);
    }

    private String getDateText() {
        Date date = java.util.Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "EEEE, MMMM d", Locale.getDefault());
        return formatter.format(date);
    }
}

