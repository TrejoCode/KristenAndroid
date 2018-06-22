package mx.edu.upqroo.kristenandroid.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.activities.NewsDetailActivity;
import mx.edu.upqroo.kristenandroid.adapters.NewsItemAdapter;
import mx.edu.upqroo.kristenandroid.common.Serializer;
import mx.edu.upqroo.kristenandroid.models.News;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends Fragment {
    RecyclerView mRecyclerNews;
    NewsItemAdapter mNewsAdapter;
    ArrayList<News> mNewsList;

    public NewsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news_list, container, false);

        mNewsList = new ArrayList<>();
        fillNewsList(mNewsList);
        mRecyclerNews = v.findViewById(R.id.recycler_news);
        mRecyclerNews.setHasFixedSize(true);
        mRecyclerNews.setLayoutManager(new LinearLayoutManager(v.getContext()));
        mNewsAdapter = new NewsItemAdapter(v.getContext(), mNewsList);
        mNewsAdapter.setClickListener(new NewsItemAdapter.ItemClickListener() {
            @Override
            public void onNewsItemClick(View view, int position) {
                Intent intent = new Intent(view.getContext(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.EXTRA_NEWS,
                        Serializer.Serialize(mNewsAdapter.getItem(position)));
                startActivity(intent);
            }
        });
        mRecyclerNews.setAdapter(mNewsAdapter);
        return v;
    }

    private void fillNewsList(ArrayList<News> news){
        news.add(new News("Important new 1", "Generic subtitle",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis felis sapien. Duis aliquam consectetur mauris, ut malesuada sapien pulvinar vitae. Vestibulum ut mauris in risus cursus pharetra. Pellentesque rutrum, odio ac vehicula rutrum, sapien dolor mattis urna, in ultricies dui ante ut quam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nam semper, orci in congue vehicula, odio nulla imperdiet lorem, nec vehicula ipsum erat vel lorem.",
                "Noticia",
                "https://i.pinimg.com/originals/1a/36/a7/1a36a7d0bba0eeaef587dbf29980e931.jpg"));
        news.add(new News("Important new 2", "Generic subtitle",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis felis sapien. Duis aliquam consectetur mauris, ut malesuada sapien pulvinar vitae. Vestibulum ut mauris in risus cursus pharetra. Pellentesque rutrum, odio ac vehicula rutrum, sapien dolor mattis urna, in ultricies dui ante ut quam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nam semper, orci in congue vehicula, odio nulla imperdiet lorem, nec vehicula ipsum erat vel lorem.",
                "Aviso",
                "http://www.babymetal-darake.com/wp-content/uploads/2018/05/31428231_1686186171497482_3686882304359137280_n-768x575.jpg"));
        news.add(new News("Important new 3", "Generic subtitle",
                "Donec et ligula purus. Morbi purus dolor, commodo vitae ipsum nec, rhoncus finibus augue. Maecenas malesuada, risus ac vestibulum vehicula, ante quam tincidunt elit, eget dictum est nulla quis ligula. Donec euismod quis lacus sit amet lobortis. In non arcu quis orci placerat lacinia eu ut odio. Donec ac sodales purus. Pellentesque sagittis nunc libero, lobortis blandit enim blandit a. Duis efficitur elit risus, eu dictum orci rhoncus nec. Suspendisse at auctor nibh, eu dictum ligula. Morbi quis aliquam mi. Integer convallis, arcu eu pellentesque rutrum, mauris lacus venenatis risus, in vehicula tellus lectus non felis. Aliquam vehicula pulvinar dapibus. Maecenas mauris mauris, vestibulum a nulla non, dictum rutrum ligula.",
                "Noticia",
                "https://cdnx.natalie.mu/media/news/music/2018/0522/_9Y1A2573_fixw_730_hq.jpg"));
        news.add(new News("Important new 4",
                "Generic subtitle",
                "Praesent cursus arcu vel rhoncus convallis. Aenean vulputate purus eget felis pharetra venenatis. Donec iaculis ac nunc non suscipit. Fusce nec congue metus, nec consequat justo. Aenean sit amet nunc ex. Curabitur quis turpis quis diam condimentum consequat. Fusce feugiat finibus mauris, a fermentum nibh mollis id. Donec felis tortor, ornare sit amet nisi vitae, rutrum bibendum sapien.",
                "Noticia",
                "http://cdn-ak.f.st-hatena.com/images/fotolife/o/onigashima/20160406/20160406003728.jpg"));
        news.add(new News("Dominando el mundo", "Las chinas van a dominar al mundo",
                "Nam ante sem, rutrum ac sollicitudin ut, suscipit et ligula. Phasellus erat ex, consectetur a quam et, luctus volutpat elit. Suspendisse sit amet placerat enim, eget aliquam risus. Nunc posuere pellentesque ligula, vitae porta nulla auctor sed. Donec eget bibendum libero. Pellentesque vehicula ligula nisi, eu condimentum ante congue ac. Integer fermentum lectus non diam volutpat, congue tincidunt tellus eleifend. Pellentesque quis nisl lorem. Phasellus sit amet risus enim. Suspendisse id mauris tempus, imperdiet magna at, elementum ante. ",
                "Aviso",
                "https://image.jimcdn.com/app/cms/image/transf/dimension=910x10000:format=jpg/path/sfeb3e2f9ecd74f84/image/i51e21e0d8017312a/version/1421125812/image.jpg"));
    }

}
