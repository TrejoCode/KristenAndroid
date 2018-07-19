package mx.edu.upqroo.kristenandroid.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mx.edu.upqroo.kristenandroid.R;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolderDatos>{

    private final ArrayList<String> ListaDatos;

    public SubjectAdapter(ArrayList<String> listaDatos) {
        ListaDatos = listaDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_subject, null, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(ListaDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return ListaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;
        public ViewHolderDatos(View itemView) {
            super(itemView);
            dato = itemView.findViewById(R.id.textid);
        }

        public void asignarDatos(String datos) {
            dato.setText(datos);
        }
    }
}
