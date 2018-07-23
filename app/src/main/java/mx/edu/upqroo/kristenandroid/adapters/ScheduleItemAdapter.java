package mx.edu.upqroo.kristenandroid.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.models.Schedule;
import mx.edu.upqroo.kristenandroid.models.Subject;

public class ScheduleItemAdapter extends RecyclerView.Adapter<ScheduleItemAdapter.ViewHolderDatos>{

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        private TextView _dayOfWeek;
        private RecyclerView mRecycler;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            _dayOfWeek = itemView.findViewById(R.id.titSche);
            mRecycler = itemView.findViewById(R.id.recycler_subject);
            mRecycler.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }

    }

    private List<Schedule> DaysList;

    public ScheduleItemAdapter(List<Schedule> daysList) {
        DaysList = daysList;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule,parent,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder._dayOfWeek.setText(DaysList.get(position).get_dayOfWeek());
        SubjectItemAdapter adapter = new SubjectItemAdapter(obtenerMateria());
        holder.mRecycler.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return DaysList.size();
    }

    private List<Subject> obtenerMateria() {
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
