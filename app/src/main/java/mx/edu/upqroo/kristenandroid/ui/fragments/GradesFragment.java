package mx.edu.upqroo.kristenandroid.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.GradesItemAdapter;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.data.models.Grades;
import mx.edu.upqroo.kristenandroid.services.sie.SieApiServices;
import mx.edu.upqroo.kristenandroid.services.sie.messages.GradesListMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class GradesFragment extends Fragment {
    private ArrayList<Grades> mGradeList;
    private RecyclerView mRecyclerGrade;
    private GradesItemAdapter mGradeAdapter;
    private ProgressBar mProgress;

    public GradesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_grades, container, false);

        mGradeList = new ArrayList<>();

        mRecyclerGrade =  v.findViewById(R.id.recycler_grades);
        mRecyclerGrade.setHasFixedSize(true);
        mRecyclerGrade.setLayoutManager(new LinearLayoutManager(v.getContext()));
        mRecyclerGrade.setVisibility(View.GONE);
        mProgress = v.findViewById(R.id.progress_grades);
        mProgress.setVisibility(View.VISIBLE);

        mGradeAdapter = new GradesItemAdapter(v.getContext(),mGradeList);
        mRecyclerGrade.setAdapter(mGradeAdapter);
        SieApiServices.getInstance(Objects.requireNonNull(getActivity()).getApplication())
                .getGradesList(SessionManager.getInstance()
                                .getSession().getUserId(),
                        SessionManager.getInstance()
                                .getSession().getConfig().getUserToken());
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GradesListMessage event) {
        if (event.isSuccessful()) {
            mGradeList.addAll(event.getGradesList());
            mGradeAdapter.notifyDataSetChanged();
            mRecyclerGrade.setVisibility(View.VISIBLE);
        }
        mProgress.setVisibility(View.GONE);
    }
}