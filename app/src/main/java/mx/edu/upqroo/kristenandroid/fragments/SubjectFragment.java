package mx.edu.upqroo.kristenandroid.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.EndlessRecyclerViewScrollListener;
import mx.edu.upqroo.kristenandroid.models.News;

public class SubjectFragment extends Fragment {
    private RecyclerView mRecyclerNews;
    private ArrayList<News> mNewsList;
    private EndlessRecyclerViewScrollListener mScrollListener;


    public SubjectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);


        return v;
    }
}
