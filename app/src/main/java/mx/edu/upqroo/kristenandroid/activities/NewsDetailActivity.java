package mx.edu.upqroo.kristenandroid.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import androidx.appcompat.widget.Toolbar;
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

        Toolbar mToolbar = findViewById(R.id.toolbarNewsDetail);
        setSupportActionBar(mToolbar);

        if (getIntent().hasExtra(EXTRA_NEWS)) {
            mNews = Serializer.Deserialize(getIntent().getStringExtra(EXTRA_NEWS), News.class);
        } else {
            Toast.makeText(this, "La noticia que buscabas no se encuentra disponible",
                    Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, NewsActivity.class));
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle(mNews.getTitle());

        TextView subtitle = findViewById(R.id.text_detail_subtitle);
        TextView description = findViewById(R.id.text_detail_description);
        TextView category = findViewById(R.id.text_detail_category);
        TextView content = findViewById(R.id.text_detail_content);
        ImageView cover = findViewById(R.id.image_detail_cover);

        subtitle.setText(mNews.getSubtitle());
        description.setText(mNews.getDescription());
        content.setText(mNews.getContent());
        category.setText(mNews.getCategory());
        Picasso.get()
                .load(mNews.getCoverUrl())
                .placeholder(R.drawable.side_nav_bar)
                .error(R.drawable.side_nav_bar)
                .into(cover);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
