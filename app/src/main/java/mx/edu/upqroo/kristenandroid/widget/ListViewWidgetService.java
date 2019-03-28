package mx.edu.upqroo.kristenandroid.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.database.entities.Subject;
import mx.edu.upqroo.kristenandroid.services.sie.SieApiServices;
import mx.edu.upqroo.kristenandroid.services.sie.messages.ScheduleMessage;

public class ListViewWidgetService extends RemoteViewsService {

    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListViewRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

class ListViewRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private ArrayList<Subject> records;

    public ListViewRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
    }

    // Initialize the data set.
    public void onCreate() {
        // In onCreate() you set up any connections / cursors to your data source. Heavy lifting,
        // for example downloading or creating content etc, should be deferred to onDataSetChanged()
        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.
        records = new ArrayList<>();
        //generateDays(records);
    }

    // Given the position (index) of a WidgetItem in the array, use the item's text value in
    // combination with the app widget item XML file to construct a RemoteViews object.
    public RemoteViews getViewAt(int position) {
        // position will always range from 0 to getCount() - 1.
        // Construct a RemoteViews item based on the app widget item XML file, and set the
        // text based on the position.
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.item_widget);
        // feed row
        String name = records.get(position).getName();
        String time = records.get(position).getTime();
        rv.setTextViewText(R.id.text_name_widget, name);
        rv.setTextViewText(R.id.text_time_widget, time);
        // end feed row
        // Next, set a fill-intent, which will be used to fill in the pending intent template
        // that is set on the collection view in ListViewWidgetProvider.
        Bundle extras = new Bundle();
        extras.putInt(ScheduleWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtra("widget_name", name);
        fillInIntent.putExtra("widget_time", time);
        fillInIntent.putExtras(extras);
        // Make it possible to distinguish the individual on-click
        // action of a given item
        rv.setOnClickFillInIntent(R.id.list_widget, fillInIntent);
        // Return the RemoteViews object.
        return rv;
    }

    public int getCount(){
        return records.size();
    }

    public void onDataSetChanged(){
        generateDays();
    }

    public int getViewTypeCount(){
        return 1;
    }

    public long getItemId(int position) {
        return position;
    }

    public void onDestroy(){
        records.clear();
    }

    public boolean hasStableIds() {
        return true;
    }

    public RemoteViews getLoadingView() {
        return null;
    }

    private void generateDays() {
        SieApiServices.getSchedule(SessionHelper.getInstance().getSession().getUserId(),
                SessionHelper.getInstance().getSession().getConfig().getUserToken());
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ScheduleMessage event) {
        if (event.isSuccessful()) {
            records.clear();
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            try {
                switch (day) {
                    case Calendar.MONDAY:
                        records.addAll(event.getDays().get(0).getSubjects());
                        break;
                    case Calendar.TUESDAY:
                        records.addAll(event.getDays().get(1).getSubjects());
                        break;
                    case Calendar.WEDNESDAY:
                        records.addAll(event.getDays().get(2).getSubjects());
                        break;
                    case Calendar.THURSDAY:
                        records.addAll(event.getDays().get(3).getSubjects());
                        break;
                    case Calendar.FRIDAY:
                        records.addAll(event.getDays().get(4).getSubjects());
                        break;
                }
                onDataSetChanged();
            } catch (Exception ex) {
                //
            }
        } else {
            //todo set visible a text view saying that there was an error
        }

        EventBus.getDefault().unregister(this);
    }
}
