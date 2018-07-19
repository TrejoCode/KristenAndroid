package mx.edu.upqroo.kristenandroid.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.ScheduleItemAdapter;
import mx.edu.upqroo.kristenandroid.models.Schedule;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    private RecyclerView recyclerViewSchedule;
    private ScheduleItemAdapter adaptadorSchedule;

    public ScheduleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schedule, container, false);

        recyclerViewSchedule = v.findViewById(R.id.recycler_schedule);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(v.getContext()));

        adaptadorSchedule = new ScheduleItemAdapter(obtenerDays());
        recyclerViewSchedule.setAdapter(adaptadorSchedule);
        return v;
    }

    private List<Schedule> obtenerDays(){
        List<Schedule> _dayOfWeek = new ArrayList<>();
        _dayOfWeek.add(new Schedule("Lunes","Entra a la tarjeta para ver tus materias", R.drawable.ic_schedule));
        _dayOfWeek.add(new Schedule("Martes","Entra a la tarjeta para ver tus materias", R.drawable.ic_schedule));
        _dayOfWeek.add(new Schedule("Miercoles","Entra a la tarjeta para ver tus materias", R.drawable.ic_schedule));
        _dayOfWeek.add(new Schedule("Jueves","Entra a la tarjeta para ver tus materias", R.drawable.ic_schedule));
        _dayOfWeek.add(new Schedule("Viernes","Entra a la tarjeta para ver tus materias", R.drawable.ic_schedule));

        return _dayOfWeek;
    }
}

