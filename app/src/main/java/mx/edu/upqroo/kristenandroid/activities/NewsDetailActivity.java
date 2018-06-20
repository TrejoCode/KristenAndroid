package mx.edu.upqroo.kristenandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.Serializer;
import mx.edu.upqroo.kristenandroid.models.News;

public class NewsDetailActivity extends AppCompatActivity {
    public static final String EXTRA_NEWS = "KEY_NEWS";
    News mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        if (getIntent().hasExtra(EXTRA_NEWS)) {
            mNews = Serializer.Deserialize(getIntent().getStringExtra(EXTRA_NEWS), News.class);
        } else {
            throw new IllegalArgumentException("Activity cannot find  extras " + EXTRA_NEWS);
        }

        TextView title = findViewById(R.id.text_detail_title);
        title.setText(mNews.getTitle());
    }
}
