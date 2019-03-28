package mx.edu.upqroo.kristenandroid.fragments;


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
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.ScheduleItemAdapter;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.database.entities.Day;
import mx.edu.upqroo.kristenandroid.services.sie.SieApiServices;
import mx.edu.upqroo.kristenandroid.services.sie.messages.ScheduleMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    private RecyclerView recyclerViewSchedule;
    private ScheduleItemAdapter adaptadorSchedule;
    private List<Day> mDaysList;
    private ProgressBar mProgress;

    public ScheduleFragment() {
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);

        recyclerViewSchedule = v.findViewById(R.id.recycler_schedule);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerViewSchedule.setVisibility(View.VISIBLE);

        mDaysList = new ArrayList<>();
        adaptadorSchedule = new ScheduleItemAdapter(mDaysList);
        recyclerViewSchedule.setAdapter(adaptadorSchedule);
        mProgress = v.findViewById(R.id.progress_schedule);
        mProgress.setVisibility(View.VISIBLE);
        SieApiServices.getSchedule(SessionHelper.getInstance().getSession().getUserId(),
                SessionHelper.getInstance().getSession().getConfig().getUserToken());
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
    public void onMessageEvent(ScheduleMessage event) {
        if (event.isSuccessful()) {
            recyclerViewSchedule.setVisibility(View.VISIBLE);
            mDaysList.addAll(event.getDays());
            adaptadorSchedule.notifyDataSetChanged();
        }
        mProgress.setVisibility(View.GONE);
    }
}

