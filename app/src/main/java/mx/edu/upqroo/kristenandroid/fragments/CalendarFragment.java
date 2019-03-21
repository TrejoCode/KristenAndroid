package mx.edu.upqroo.kristenandroid.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import mx.edu.upqroo.kristenandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {
    private String url;
    public static final String URL_KEY = "url_key";


    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            url = bundle.getString(URL_KEY, "http://new.upqroo.edu.mx/notfound");
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);
        WebView webView = v.findViewById(R.id.webViewCalendar);
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + url);
        return v;
    }

}
