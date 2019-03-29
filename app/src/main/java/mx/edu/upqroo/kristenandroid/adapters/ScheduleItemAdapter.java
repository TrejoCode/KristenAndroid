package mx.edu.upqroo.kristenandroid.adapters;


import android.app.Application;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.helpers.ViewHelper;
import mx.edu.upqroo.kristenandroid.data.database.entities.Day;
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject;
import mx.edu.upqroo.kristenandroid.repositories.SubjectRepository;

public class ScheduleItemAdapter extends RecyclerView.Adapter<ScheduleItemAdapter.ViewHolderSchedule>{
    private List<Day> mDaysList;
    private Application mApp;

    public ScheduleItemAdapter(List<Day> daysList, Application app) {
        mDaysList = daysList;
        mApp = app;
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
        holder._dayOfWeek.setText(mDaysList.get(position).getName());
        AsyncTask.execute(() -> {
            List<Subject> subjects = SubjectRepository.getInstance(mApp)
                    .getSubjectsByDayId(mDaysList.get(position).getDayId());
            SubjectItemAdapter adapter = new SubjectItemAdapter(subjects);
            holder.mRecycler.setAdapter(adapter);
        });

        if (position + 1 == getItemCount()) {
            ViewHelper.SetBottomMargin(holder.itemView,
                    (int) (84 * Resources.getSystem().getDisplayMetrics().density));
        } else {
            // reset bottom margin back to zero. (your value may be different)
            ViewHelper.SetBottomMargin(holder.itemView, 0);
        }
    }

    public void setData(List<Day> data) {
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

        ViewHolderSchedule(View itemView) {
            super(itemView);
            _dayOfWeek = itemView.findViewById(R.id.titSche);
            mRecycler = itemView.findViewById(R.id.recycler_subject);
            mRecycler.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }

    }
}
