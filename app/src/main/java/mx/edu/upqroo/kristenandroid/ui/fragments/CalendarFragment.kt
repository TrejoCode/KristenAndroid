package mx.edu.upqroo.kristenandroid.ui.fragments


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.fragment.app.Fragment
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.api.kristen.messages.CalendarUrlMessage
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * A simple [Fragment] subclass.
 */
class CalendarFragment : Fragment() {
    private lateinit var mWebView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_calendar, container, false)
        mWebView = v.findViewById(R.id.webViewCalendar)
        mWebView.webChromeClient = WebChromeClient()
        mWebView.settings.javaScriptEnabled = true
        mWebView.settings.builtInZoomControls = true
        return v
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun calendarServiceResponse(message: CalendarUrlMessage) {
        if (message.calendarUrl.isNotEmpty()) {
            mWebView.loadUrl(
                    "https://docs.google.com/gview?embedded=true&url=" + message.calendarUrl)
        }
    }

}
