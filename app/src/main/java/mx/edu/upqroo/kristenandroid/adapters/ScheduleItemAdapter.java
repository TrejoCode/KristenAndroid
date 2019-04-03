package mx.edu.upqroo.kristenandroid.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject;

public class ScheduleItemAdapter extends RecyclerView.Adapter<ScheduleItemAdapter.ViewHolderSchedule>{
    private List<ScheduleSubject> mDaysList;

    public ScheduleItemAdapter(List<ScheduleSubject> daysList) {
        mDaysList = daysList;
    }

    @NonNull
    @Override
    public ViewHolderSchedule onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_schedule,parent,false);
        return new ViewHolderSchedule(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSchedule holder, int position) {
        ScheduleSubject scheduleSubject = mDaysList.get(position);
        holder._dayOfWeek.setText(scheduleSubject.getDay().getName());

        holder.mAdapter = new SubjectItemAdapter(scheduleSubject.getSubjects());
        holder.mRecycler.setAdapter(holder.mAdapter);
    }

    public void setData(List<ScheduleSubject> data) {
        mDaysList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDaysList.size();
    }

    class ViewHolderSchedule extends RecyclerView.ViewHolder {

        private TextView _dayOfWeek;
        private RecyclerView mRecycler;
        private SubjectItemAdapter mAdapter;

        ViewHolderSchedule(View itemView) {
            super(itemView);
            _dayOfWeek = itemView.findViewById(R.id.titSche);
            mRecycler = itemView.findViewById(R.id.recycler_subject);
            mRecycler.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
}
