package mx.edu.upqroo.kristenandroid.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.models.Subject;

public class SubjectItemAdapter extends RecyclerView.Adapter<SubjectItemAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView materia, hora;

        public ViewHolder(View itemView) {
            super(itemView);
            materia = (TextView)itemView.findViewById(R.id.subjecttit);
            hora = (TextView)itemView.findViewById(R.id.horasubject);

        }
    }
    public List<Subject> subjectLista;

    public SubjectItemAdapter(List<Subject> subjectLista) {
        this.subjectLista = subjectLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.materia.setText(subjectLista.get(position).getMateria());
        holder.hora.setText(subjectLista.get(position).getHora());
    }

    @Override
    public int getItemCount() {
        return subjectLista.size();
    }
}
