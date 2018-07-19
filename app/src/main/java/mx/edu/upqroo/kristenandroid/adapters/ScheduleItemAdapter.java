package mx.edu.upqroo.kristenandroid.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.fragments.ScheduleFragment;

public class ScheduleItemAdapter extends RecyclerView.Adapter<ScheduleItemAdapter.ViewHolderDatos>{

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        private TextView _dayOfWeek, _message;
        ImageView _imgSchedule;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            _dayOfWeek = (TextView) itemView.findViewById(R.id.titSche);
            _message = (TextView) itemView.findViewById(R.id.contSche);
            _imgSchedule = (ImageView) itemView.findViewById(R.id.imgSubject);

        }

    }

    public List<ScheduleFragment> DaysList;

    public ScheduleItemAdapter(List<ScheduleFragment> daysList) {
        DaysList = daysList;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_schedule,parent,false);
        ViewHolderDatos viewHolder = new ViewHolderDatos(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder._dayOfWeek.setText(DaysList.get(position).get_dayOfWeek());
        holder._message.setText(DaysList.get(position).get_message());
        holder._imgSchedule.setImageResource(DaysList.get(position).get_imgSchedule());
    }

    @Override
    public int getItemCount() {
        return DaysList.size();
    }
}
