package mx.edu.upqroo.kristenandroid.widget

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService

import java.util.ArrayList
import java.util.Calendar

import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject

internal class ListViewRemoteViewsFactory(private val mContext: Context)
    : RemoteViewsService.RemoteViewsFactory {
    private var records: MutableList<Subject>? = null

    override fun onCreate() {
        // In onCreate() you set up any connections / cursors to your data source. Heavy lifting,
        // for example downloading or creating content etc, should be deferred to onDataSetChanged()
        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.
        records = ArrayList()
    }

    override fun onDestroy() {
        records!!.clear()
    }

    override fun getCount(): Int {
        return records!!.size
    }

    // Given the position (index) of a WidgetItem in the array, use the item's text value in
    // combination with the app widget item XML file to construct a RemoteViews object.
    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.item_widget)
        val name = records!![position].name
        val time = records!![position].time
        rv.setTextViewText(R.id.text_name_widget, name)
        rv.setTextViewText(R.id.text_time_widget, time)

        val extras = Bundle()
        extras.putInt(ScheduleWidgetProvider.EXTRA_ITEM, position)
        val fillInIntent = Intent()
        fillInIntent.putExtra("widget_name", name)
        fillInIntent.putExtra("widget_time", time)
        fillInIntent.putExtras(extras)
        rv.setOnClickFillInIntent(R.id.list_widget, fillInIntent)
        return rv
    }

    override fun onDataSetChanged() {
        generateDays()
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    private fun generateDays() {
        records!!.clear()
        val scheduleSubjects = DataWidgetManager.getSchedule(mContext)
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_WEEK)
        try {
            when (day) {
                Calendar.MONDAY -> records = scheduleSubjects[0].subjects
                Calendar.TUESDAY -> records = scheduleSubjects[1].subjects
                Calendar.WEDNESDAY -> records = scheduleSubjects[2].subjects
                Calendar.THURSDAY -> records = scheduleSubjects[3].subjects
                Calendar.FRIDAY -> records = scheduleSubjects[4].subjects
            }
        } catch (ex: Exception) {
            //
        }

    }
}
