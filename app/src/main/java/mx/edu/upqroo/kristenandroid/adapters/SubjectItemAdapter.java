package mx.edu.upqroo.kristenandroid.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.database.entities.Subject;

public class SubjectItemAdapter extends RecyclerView.Adapter<SubjectItemAdapter.ViewHolder> {
    private List<Subject> subjectList;

    SubjectItemAdapter(List<Subject> subjectLista) {
        this.subjectList = subjectLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subject,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.materia.setText(subjectList.get(position).getName());
        holder.professor.setText(subjectList.get(position).getProfessor());
        holder.hora.setText(subjectList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView materia, professor, hora;

        ViewHolder(View itemView) {
            super(itemView);
            professor = itemView.findViewById(R.id.subject_professor);
            materia = itemView.findViewById(R.id.subjecttit);
            hora = itemView.findViewById(R.id.horasubject);

        }
    }
}
