package mx.edu.upqroo.kristenandroid.widget

import android.content.Intent
import android.widget.RemoteViewsService

class ListViewWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return ListViewRemoteViewsFactory(applicationContext)
    }
}
