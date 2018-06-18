package mx.edu.upqroo.kristenandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.models.NewsDetail;

public class NewsDetailActivity extends AppCompatActivity {
    NewsDetail mNewsDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        mNewsDetail = new NewsDetail("Noticia", "Kawaii", "Cuerpo de la noticias",
                "https://mariskalrock.com/wp-content/uploads/2018/05/babymetal-18-promo.jpg");
    }
}
