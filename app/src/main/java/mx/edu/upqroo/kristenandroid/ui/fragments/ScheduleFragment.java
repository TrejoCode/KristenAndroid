package mx.edu.upqroo.kristenandroid.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.fabric.sdk.android.services.concurrency.AsyncTask;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.ScheduleItemAdapter;
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject;
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.data.database.entities.Day;
import mx.edu.upqroo.kristenandroid.viewModels.ScheduleViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    private ScheduleViewModel mViewModel;

    private RecyclerView recyclerViewSchedule;
    private ScheduleItemAdapter mScheduleAdapter;
    private ProgressBar mProgress;
    private SwipeRefreshLayout mSwipeContainer;
    private ConstraintLayout mImageEmptySchedule;

    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View model instance
        mViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        recyclerViewSchedule = v.findViewById(R.id.recycler_schedule);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerViewSchedule.setVisibility(View.VISIBLE);
        mScheduleAdapter = new ScheduleItemAdapter(new ArrayList<>());
        recyclerViewSchedule.setAdapter(mScheduleAdapter);
        mProgress = v.findViewById(R.id.progress_schedule);
        mProgress.setVisibility(View.VISIBLE);
        mImageEmptySchedule = v.findViewById(R.id.image_empty_schedule);

        mSwipeContainer = v.findViewById(R.id.refreshLayout_schedule);
        // Setup refresh listener which triggers new data loading
        mSwipeContainer.setOnRefreshListener(() -> {
            /*trigger the load of the data to the service*/
            AsyncTask.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mSwipeContainer.setRefreshing(false);
            });
        });
        // Configure the refreshing colors
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker);

        mViewModel.getDays(SessionManager.getInstance().getSession().getUserId())
                .observe(this, scheduleSubjects -> {
                    if (scheduleSubjects.size() == 0) {
                        mImageEmptySchedule.setVisibility(View.VISIBLE);
                    } else {
                        recyclerViewSchedule.setVisibility(View.VISIBLE);
                        mScheduleAdapter.setData(scheduleSubjects);
                        mImageEmptySchedule.setVisibility(View.GONE);
                    }
                    mProgress.setVisibility(View.GONE);
                });
        return v;
    }
}

