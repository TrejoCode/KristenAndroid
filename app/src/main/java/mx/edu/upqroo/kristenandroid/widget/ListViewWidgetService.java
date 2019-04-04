package mx.edu.upqroo.kristenandroid.widget;

import android.appwidget.AppWidgetManager;
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
import java.util.List;

import mx.edu.upqroo.kristenandroid.Application;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject;
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject;
import mx.edu.upqroo.kristenandroid.data.repositories.DayRepository;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.services.sie.SieApiServices;
import mx.edu.upqroo.kristenandroid.services.sie.containers.Semana;
import mx.edu.upqroo.kristenandroid.services.sie.messages.ScheduleMessage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListViewRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

class ListViewRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private List<Subject> records;
    private int mAppWidgetId;

    ListViewRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        // In onCreate() you set up any connections / cursors to your data source. Heavy lifting,
        // for example downloading or creating content etc, should be deferred to onDataSetChanged()
        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.
        records = new ArrayList<>();
    }

    @Override
    public void onDestroy(){
        records.clear();
    }

    @Override
    public int getCount(){
        return records.size();
    }

    // Given the position (index) of a WidgetItem in the array, use the item's text value in
    // combination with the app widget item XML file to construct a RemoteViews object.
    @Override
    public RemoteViews getViewAt(int position) {
        generateDays();
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.item_widget);
        String name = records.get(position).getName();
        String time = records.get(position).getTime();
        rv.setTextViewText(R.id.text_name_widget, name);
        rv.setTextViewText(R.id.text_time_widget, time);

        Bundle extras = new Bundle();
        extras.putInt(ScheduleWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtra("widget_name", name);
        fillInIntent.putExtra("widget_time", time);
        fillInIntent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.list_widget, fillInIntent);

        // You can do heaving lifting in here, synchronously. For example, if you need to
        // process an image, fetch something from the network, etc., it is ok to do it here,
        // synchronously. A loading view will show up in lieu of the actual contents in the
        // interim.

        // Return the RemoteViews object.
        return rv;
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

    public boolean hasStableIds() {
        return true;
    }

    public RemoteViews getLoadingView() {
        return null;
    }

    private void generateDays() {
        DayRepository repo = DayRepository.getInstance();
        if (repo != null) {
            List<ScheduleSubject> scheduleSubjects = repo.getDayByUserIdSync(
                    SessionManager.getInstance().getSession().getUserId());
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            try {
                switch (day) {
                    case Calendar.MONDAY:
                        records = scheduleSubjects.get(0).getSubjects();
                        break;
                    case Calendar.TUESDAY:
                        records = scheduleSubjects.get(1).getSubjects();
                        break;
                    case Calendar.WEDNESDAY:
                        records = scheduleSubjects.get(2).getSubjects();
                        break;
                    case Calendar.THURSDAY:
                        records = scheduleSubjects.get(3).getSubjects();
                        break;
                    case Calendar.FRIDAY:
                        records = scheduleSubjects.get(4).getSubjects();
                        break;
                }
            } catch (Exception ex) {
                //
            }
        }
    }
}
