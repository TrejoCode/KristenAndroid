package mx.edu.upqroo.kristenandroid.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.ScheduleItemAdapter;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.data.database.entities.Day;
import mx.edu.upqroo.kristenandroid.viewModels.ScheduleViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    private ScheduleViewModel mViewModel;
    private Observer<List<Day>> mDayObserver;

    private RecyclerView recyclerViewSchedule;
    private ScheduleItemAdapter mScheduleAdapter;
    private ProgressBar mProgress;

    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View model instance
        mViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        // Observer declaration
        mDayObserver = days -> {
            recyclerViewSchedule.setVisibility(View.VISIBLE);
            mScheduleAdapter.setData(days);
            mProgress.setVisibility(View.GONE);
        };
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);
        recyclerViewSchedule = v.findViewById(R.id.recycler_schedule);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerViewSchedule.setVisibility(View.VISIBLE);
        if (getActivity() != null) {
            List<Day> daysList = new ArrayList<>();
            mScheduleAdapter = new ScheduleItemAdapter(daysList, getActivity().getApplication());
            recyclerViewSchedule.setAdapter(mScheduleAdapter);
        }
        mProgress = v.findViewById(R.id.progress_schedule);
        mProgress.setVisibility(View.VISIBLE);
        mViewModel.getDays(SessionManager.getInstance().getSession().getUserId())
                .observe(this, mDayObserver);
        return v;
    }
}

