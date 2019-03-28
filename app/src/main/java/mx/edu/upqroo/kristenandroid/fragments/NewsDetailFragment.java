package mx.edu.upqroo.kristenandroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.activities.NewsDetailActivity;
import mx.edu.upqroo.kristenandroid.adapters.NewsDetailContentAdapter;
import mx.edu.upqroo.kristenandroid.database.entities.Content;

public class NewsDetailFragment extends Fragment {

    public static NewsDetailFragment newInstance() {
        return new NewsDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);
        RecyclerView mRecycler = view.findViewById(R.id.recycler_news_detail);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Content> mContentList = NewsDetailActivity.NEWS_CONTENT;
        NewsDetailContentAdapter mAdapter =
                new NewsDetailContentAdapter(getContext(), mContentList, this);
        mRecycler.setAdapter(mAdapter);
        return view;
    }
}
