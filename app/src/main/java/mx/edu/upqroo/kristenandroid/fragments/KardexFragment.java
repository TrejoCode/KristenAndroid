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
import mx.edu.upqroo.kristenandroid.adapters.GradesItemAdapter;
import mx.edu.upqroo.kristenandroid.adapters.KardexItemAdapter;
import mx.edu.upqroo.kristenandroid.models.Grades;

/**
 * A simple {@link Fragment} subclass.
 */
public class KardexFragment extends Fragment {

    ArrayList<Grades> mKardexList;
    RecyclerView mRecyclerKardex;
    KardexItemAdapter mKardexAdapter;
    public KardexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kardex, container, false);
        mKardexList = new ArrayList<>();
        fillGradeList(mKardexList);

        mRecyclerKardex =  v.findViewById(R.id.recycler_kardex);
        mRecyclerKardex.setHasFixedSize(true);
        mRecyclerKardex.setLayoutManager(new LinearLayoutManager(v.getContext()));

        mKardexAdapter = new KardexItemAdapter(v.getContext(), mKardexList);
        mRecyclerKardex.setAdapter(mKardexAdapter);
        return v;
    }

    public void fillGradeList(ArrayList<Grades> grade) {
        grade.add(new Grades("ADTA","ADMON DE TIC","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("AOSA","ADMON PROY SOFT","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("DAMAA","DESARROLLO DE SISTEM","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("1234566788909","INGLES IX","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("AOSA","ADMON PROY SOFT","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("DAMAA","DESARROLLO DE SISTEM","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("INGIX2A","INGLES IX","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("AOSA","ADMON PROY SOFT","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("DAMAA","DESARROLLO DE SISTEM","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("INGIX2A","INGLES IX","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("AOSA","ADMON PROY SOFT","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("DAMAA","DESARROLLO DE SISTEM","9.5","9.0","9.0","8.0","8.0"));
        grade.add(new Grades("INGIX2A","INGLES IX","9.5","9.0","9.0","8.0","8.0"));
    }

}
