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
import mx.edu.upqroo.kristenandroid.adapters.KardexItemAdapter;
import mx.edu.upqroo.kristenandroid.models.Grades;

/**
 * A simple {@link Fragment} subclass.
 */
public class KardexFragment extends Fragment {

    private ArrayList<Grades> mKardexList;
    private RecyclerView mRecyclerKardex;
    private KardexItemAdapter mKardexAdapter;
    private SmoothRefreshLayout mRefreshLayout;

    public KardexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kardex, container, false);

        mRefreshLayout = v.findViewById(R.id.refreshLayout_KardexList);

        mRefreshLayout.setHeaderView(new ClassicHeader(getContext()));
        mRefreshLayout.setOnRefreshListener(new RefreshingListenerAdapter() {
            @Override
            public void onRefreshBegin(boolean isRefresh) {
                Toast.makeText(getContext(), "Refreshing", Toast.LENGTH_LONG).show();
            }
        });

        mKardexList = new ArrayList<>();
        fillGradeList(mKardexList);

        mRecyclerKardex =  v.findViewById(R.id.recycler_kardex);
        mRecyclerKardex.setHasFixedSize(true);
        mRecyclerKardex.setLayoutManager(new LinearLayoutManager(v.getContext()));

        mKardexAdapter = new KardexItemAdapter(v.getContext(), mKardexList);
        mRecyclerKardex.setAdapter(mKardexAdapter);
        return v;
    }

    private void fillGradeList(ArrayList<Grades> grade) {
        grade.add(new Grades("ADTA","ADMON DE TIC","9.5","9.0","9.0","8.0","8.0", "0"));
        grade.add(new Grades("AOSA","ADMON PROY SOFT","9.5","9.0","9.0","8.0","8.0", "0"));
        grade.add(new Grades("DAMAA","DESARROLLO DE SISTEM","9.5","9.0","9.0","8.0","8.0", "0"));
        grade.add(new Grades("1234566788909","INGLES IX","9.5","9.0","9.0","8.0","8.0", "0"));
        grade.add(new Grades("AOSA","ADMON PROY SOFT","9.5","9.0","9.0","8.0","8.0", "0"));
        grade.add(new Grades("DAMAA","DESARROLLO DE SISTEM","9.5","9.0","9.0","8.0","8.0", "0"));
        grade.add(new Grades("INGIX2A","INGLES IX","9.5","9.0","9.0","8.0","8.0", "0"));
        grade.add(new Grades("AOSA","ADMON PROY SOFT","9.5","9.0","9.0","8.0","8.0", "0"));
        grade.add(new Grades("DAMAA","DESARROLLO DE SISTEM","9.5","9.0","9.0","8.0","8.0", "0"));
        grade.add(new Grades("INGIX2A","INGLES IX","9.5","9.0","9.0","8.0","8.0", "0"));
        grade.add(new Grades("AOSA","ADMON PROY SOFT","9.5","9.0","9.0","8.0","8.0", "0"));
        grade.add(new Grades("DAMAA","DESARROLLO DE SISTEM","9.5","9.0","9.0","8.0","8.0", "0"));
        grade.add(new Grades("INGIX2A","INGLES IX","9.5","9.0","9.0","8.0","8.0", "0"));
    }

}
