package mx.edu.upqroo.kristenandroid.fragments;

import android.app.Fragment;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.ScheduleItemAdapter;
import mx.edu.upqroo.kristenandroid.adapters.SubjectItemAdapter;
import mx.edu.upqroo.kristenandroid.models.Subject;

public class SubjectFragment extends Fragment {
    private RecyclerView recyclerViewSubject;
    private SubjectItemAdapter adaptadorSubject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);

        recyclerViewSubject = v.findViewById(R.id.recycler_subject);
        recyclerViewSubject.setLayoutManager(new LinearLayoutManager(v.getContext()));

        adaptadorSubject = new SubjectItemAdapter(obtenerMateria());
        recyclerViewSubject.setAdapter(adaptadorSubject);
        return v;
    }

    public List<Subject> obtenerMateria() {
        List<Subject> materia = new ArrayList<>();
        materia.add(new Subject("INGLES","07:00-7:50"));
        materia.add(new Subject("ADMON PROYECTOS SOFTWARE","07:50-8:40"));
        materia.add(new Subject("DESARROLLO SIS INTELIGENTES","08:40-9:30"));
        materia.add(new Subject("SIS DE INFORMACION","09:40-10:30"));
        materia.add(new Subject("ADMON DE TICS","10:30-11:20"));
        materia.add(new Subject("DESARROLLO DE APPS MOVILES","11:20-12:10"));
        materia.add(new Subject("SOA","12:10-13:00"));

        return materia;
    }
}
