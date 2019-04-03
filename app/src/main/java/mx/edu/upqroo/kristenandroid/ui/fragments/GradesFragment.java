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
import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.GradesItemAdapter;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade;
import mx.edu.upqroo.kristenandroid.services.sie.SieApiServices;
import mx.edu.upqroo.kristenandroid.services.sie.messages.GradesListMessage;
import mx.edu.upqroo.kristenandroid.viewModels.GradesViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GradesFragment extends Fragment {

    private GradesViewModel mViewModel;

    private RecyclerView mRecyclerGrade;
    private GradesItemAdapter mGradeAdapter;
    private ProgressBar mProgress;

    public static GradesFragment newInstance() {
        return new GradesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View model instance
        mViewModel = ViewModelProviders.of(this).get(GradesViewModel.class);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_grades, container, false);
        mRecyclerGrade =  v.findViewById(R.id.recycler_grades);
        mRecyclerGrade.setHasFixedSize(true);
        mRecyclerGrade.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerGrade.setVisibility(View.GONE);
        mProgress = v.findViewById(R.id.progress_grades);
        mProgress.setVisibility(View.VISIBLE);

        mGradeAdapter = new GradesItemAdapter(getContext(), new ArrayList<>());
        mRecyclerGrade.setAdapter(mGradeAdapter);

        mViewModel.getGrades(SessionManager.getInstance().getSession().getUserId())
                .observe(this, grades -> {
                    mRecyclerGrade.setVisibility(View.VISIBLE);
                    mProgress.setVisibility(View.GONE);
                    mGradeAdapter.setData(grades);
        });
        return v;
    }
}
