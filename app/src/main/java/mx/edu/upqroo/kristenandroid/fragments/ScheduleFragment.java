package mx.edu.upqroo.kristenandroid.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.ScheduleItemAdapter;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.models.Day;
import mx.edu.upqroo.kristenandroid.service.ApiServices;
import mx.edu.upqroo.kristenandroid.service.messages.ScheduleMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    private RecyclerView recyclerViewSchedule;
    private ScheduleItemAdapter adaptadorSchedule;
    private List<Day> mDaysList;

    public ScheduleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);

        recyclerViewSchedule = v.findViewById(R.id.recycler_schedule);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(v.getContext()));

        mDaysList = new ArrayList<>();
        adaptadorSchedule = new ScheduleItemAdapter(mDaysList);
        recyclerViewSchedule.setAdapter(adaptadorSchedule);
        ApiServices.getSchedule(SessionHelper.getInstance().getSession().getUserId());
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
        mDaysList.addAll(event.getDays());
        adaptadorSchedule.notifyDataSetChanged();
    }
}

