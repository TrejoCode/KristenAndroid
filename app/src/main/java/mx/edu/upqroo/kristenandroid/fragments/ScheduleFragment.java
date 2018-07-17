package mx.edu.upqroo.kristenandroid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.ScheduleItemAdapter;
import mx.edu.upqroo.kristenandroid.common.EndlessRecyclerViewScrollListener;
import mx.edu.upqroo.kristenandroid.models.News;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    private RecyclerView mRecyclerNews;
    private ScheduleItemAdapter myAdapter;
    private ArrayList<News> mNewsList;
    private EndlessRecyclerViewScrollListener mScrollListener;


    public ScheduleFragment() {
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
