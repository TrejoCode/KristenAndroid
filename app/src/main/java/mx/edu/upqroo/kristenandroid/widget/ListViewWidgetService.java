package mx.edu.upqroo.kristenandroid.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.models.Subject;

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
        records.clear();
        generateDays(records);
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

    private void generateDays(ArrayList<Subject> records) {
        records.add(new Subject("Programación", "","07:00 - 09:30 AM"));
        records.add(new Subject("Redes", "", "09:30 - 10:20 AM"));
        records.add(new Subject("Sistemas de la información", "", "10:20 - 11:10 AM"));
        records.add(new Subject("Ingles X", "", "11:10 - 12:00 PM"));
        records.add(new Subject("Administración", "", "12:00 - 13:00 PM"));
    }
}
