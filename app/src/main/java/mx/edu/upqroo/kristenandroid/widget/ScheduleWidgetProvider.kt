package mx.edu.upqroo.kristenandroid.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import androidx.core.app.TaskStackBuilder
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.ui.activities.MainActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * Implementation of App Widget functionality.
 */
class ScheduleWidgetProvider : AppWidgetProvider() {

    private val dateText: String
        get() {
            val date = java.util.Calendar.getInstance().time
            val formatter = SimpleDateFormat(
                    "EEEE, MMMM d", Locale.getDefault())
            return formatter.format(date)
        }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            //updateAppWidget(context, appWidgetManager, appWidgetId);
            val views = RemoteViews(context.packageName, R.layout.schedule_widget)

            // Set text tp title
            views.setTextViewText(R.id.appwidget_text, dateText)
            // click event handler for the title, launches the app when the user clicks on title
            val titleIntent = Intent(context, MainActivity::class.java)
            val titlePendingIntent = PendingIntent.getActivity(context,
                    0,
                    titleIntent,
                    0)
            views.setOnClickPendingIntent(R.id.appwidget_text, titlePendingIntent)

            //Create an Intent with the AppWidgetManager.ACTION_APPWIDGET_UPDATE action//

            val intentUpdate = Intent(context, ScheduleWidgetProvider::class.java)
            intentUpdate.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE

            //Update the current widget instance only, by creating an array that contains the widget’s unique ID//

            val idArray = intArrayOf(appWidgetId)
            intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray)

            //Wrap the intent as a PendingIntent, using PendingIntent.getBroadcast()//

            val pendingUpdate = PendingIntent.getBroadcast(
                    context, appWidgetId, intentUpdate,
                    PendingIntent.FLAG_UPDATE_CURRENT)

            //Send the pending intent in response to the user tapping the ‘Update’ TextView//

            views.setOnClickPendingIntent(R.id.button_refresh_widget, pendingUpdate)


            val intent = Intent(context, ListViewWidgetService::class.java)
            views.setRemoteAdapter(R.id.list_widget, intent)
            views.setEmptyView(R.id.list_widget, R.id.empty_view)

            // template to handle the click listener for each item
            val clickIntentTemplate = Intent(context, MainActivity::class.java)
            val clickPendingIntentTemplate = TaskStackBuilder.create(context)
                    .addNextIntentWithParentStack(clickIntentTemplate)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
            views.setPendingIntentTemplate(R.id.list_widget, clickPendingIntentTemplate)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
        //scheduleNextUpdate(context);
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        if (action != null && action == AppWidgetManager.ACTION_APPWIDGET_UPDATE) {
            // refresh all your widgets
            val mgr = AppWidgetManager.getInstance(context)
            val cn = ComponentName(context, ScheduleWidgetProvider::class.java)
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.list_widget)
        }
        super.onReceive(context, intent)
    }

    companion object {
        const val EXTRA_ITEM = "EXTRA_ITEM"

        fun sendRefreshBroadcast(context: Context) {
            val intent = Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
            intent.component = ComponentName(context, ScheduleWidgetProvider::class.java)
            context.sendBroadcast(intent)
        }
    }
}

