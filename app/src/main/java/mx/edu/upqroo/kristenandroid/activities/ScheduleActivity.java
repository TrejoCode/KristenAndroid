package mx.edu.upqroo.kristenandroid.activities;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.ScheduleItemAdapter;
import mx.edu.upqroo.kristenandroid.fragments.ScheduleFragment;

public class ScheduleActivity extends AppCompatActivity {

    /*ArrayList<String> ListaDatos;
    RecyclerView recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recycler_schedule);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ListaDatos = new ArrayList<String>();

        for (int i = 0; i <= 5; i++){
            ListaDatos.add("Dato # " +i+ "");
        }
        ScheduleItemAdapter adapter = new ScheduleItemAdapter(ListaDatos);
        recycler.setAdapter(adapter);

    }
    */

    private RecyclerView recyclerViewSchedule;
    private ScheduleItemAdapter adaptadorSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewSchedule = (RecyclerView)findViewById(R.id.recycler_schedule);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(this));

        adaptadorSchedule = new ScheduleItemAdapter(obtenerDays());
        recyclerViewSchedule.setAdapter(adaptadorSchedule);

    }
    public List<ScheduleFragment> obtenerDays(){
        List<ScheduleFragment> _dayOfWeek = new ArrayList<>();
        _dayOfWeek.add(new ScheduleFragment("Lunes","Entra a la tarjeta para ver tus materias", R.drawable.ic_schedule));
        _dayOfWeek.add(new ScheduleFragment("Martes","Entra a la tarjeta para ver tus materias", R.drawable.ic_schedule));
        _dayOfWeek.add(new ScheduleFragment("Miercoles","Entra a la tarjeta para ver tus materias", R.drawable.ic_schedule));
        _dayOfWeek.add(new ScheduleFragment("Jueves","Entra a la tarjeta para ver tus materias", R.drawable.ic_schedule));
        _dayOfWeek.add(new ScheduleFragment("Viernes","Entra a la tarjeta para ver tus materias", R.drawable.ic_schedule));

        return _dayOfWeek;
    }
}
