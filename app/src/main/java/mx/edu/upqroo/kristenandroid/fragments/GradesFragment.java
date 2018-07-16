package mx.edu.upqroo.kristenandroid.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import me.dkzwm.widget.srl.RefreshingListenerAdapter;
import me.dkzwm.widget.srl.SmoothRefreshLayout;
import me.dkzwm.widget.srl.extra.header.ClassicHeader;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.GradesItemAdapter;
import mx.edu.upqroo.kristenandroid.adapters.NewsItemAdapter;
import mx.edu.upqroo.kristenandroid.models.Grades;

/**
 * A simple {@link Fragment} subclass.
 */
public class GradesFragment extends Fragment {
    private ArrayList<Grades> mGradeList;
    private RecyclerView mRecyclerGrade;
    private GradesItemAdapter mGradeAdapter;
    private SmoothRefreshLayout mRefreshLayout;

    public GradesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_grades, container, false);

        mRefreshLayout = v.findViewById(R.id.refreshLayout_GradesList);

        mRefreshLayout.setHeaderView(new ClassicHeader(getContext()));
        mRefreshLayout.setOnRefreshListener(new RefreshingListenerAdapter() {
            @Override
            public void onRefreshBegin(boolean isRefresh) {
                Toast.makeText(getContext(), "Refreshing", Toast.LENGTH_LONG).show();
            }
        });

        mGradeList = new ArrayList<>();
        fillGradeList(mGradeList);

        mRecyclerGrade =  v.findViewById(R.id.recycler_grades);
        mRecyclerGrade.setHasFixedSize(true);
        mRecyclerGrade.setLayoutManager(new LinearLayoutManager(v.getContext()));

        mGradeAdapter = new GradesItemAdapter(v.getContext(),mGradeList);
        mRecyclerGrade.setAdapter(mGradeAdapter);
        return v;
    }

    private void fillGradeList(ArrayList<Grades> grade){
        grade.add(new Grades("ADTA","ADMON DE TIC","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("AOSA","ADMON PROY SOFT","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("DAMAA","DESARROLLO DE SISTEM","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("INGIX2A","INGLES IX","9.5","9.0","9.0","8.0","8.0"));
    }
}
