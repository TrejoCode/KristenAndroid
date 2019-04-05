package mx.edu.upqroo.kristenandroid.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class ListViewWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new ListViewRemoteViewsFactory(getApplicationContext(), intent);
    }
}
