package mx.edu.upqroo.kristenandroid.adapters;


import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.ViewHelper;
import mx.edu.upqroo.kristenandroid.models.Day;
import mx.edu.upqroo.kristenandroid.models.Subject;

public class ScheduleItemAdapter extends RecyclerView.Adapter<ScheduleItemAdapter.ViewHolderDatos>{
    private List<Day> mDaysList;

    public ScheduleItemAdapter(List<Day> daysList) {
        mDaysList = daysList;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule,parent,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder._dayOfWeek.setText(mDaysList.get(position).getName());
        SubjectItemAdapter adapter = new SubjectItemAdapter(mDaysList.get(position).getSubjects());
        holder.mRecycler.setAdapter(adapter);

        if (position + 1 == getItemCount()) {
            ViewHelper.SetBottomMargin(holder.itemView, (int) (84 * Resources.getSystem().getDisplayMetrics().density));
        } else {
            // reset bottom margin back to zero. (your value may be different)
            ViewHelper.SetBottomMargin(holder.itemView, 0);
        }
    }

    @Override
    public int getItemCount() {
        return mDaysList.size();
    }

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
}
