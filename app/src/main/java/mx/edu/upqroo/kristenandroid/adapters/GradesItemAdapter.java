package mx.edu.upqroo.kristenandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade;

public class GradesItemAdapter extends RecyclerView.Adapter<GradesItemAdapter.GradeViewHolder> {

    private List<Grade> gradeList;
    private LayoutInflater mInflater;

    public GradesItemAdapter(Context context, List<Grade> gradeList) {
        this.mInflater = LayoutInflater.from(context);
        this.gradeList = gradeList;
    }

    @NonNull
    @Override
    public GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = mInflater.inflate(R.layout.item_grades, parent,false);
        return new GradeViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeViewHolder holder, int position) {
        holder.code.setText(gradeList.get(position).getCode());
        holder.subject.setText(gradeList.get(position).getSubject());
        holder.generalGrade.setText(gradeList.get(position).getGeneralGrade());
        if (!gradeList.get(position).getGradeOne().equals("0")) {
            holder.gradeOne.setVisibility(View.VISIBLE);
            holder.gradeOne.setText(gradeList.get(position).getGradeOne());
        }
        if (!gradeList.get(position).getGradeTwo().equals("0")) {
            holder.gradeTwo.setVisibility(View.VISIBLE);
            holder.gradeTwo.setText(gradeList.get(position).getGradeTwo());
        }
        if (!gradeList.get(position).getGradeThree().equals("0")) {
            holder.gradeThree.setVisibility(View.VISIBLE);
            holder.gradeThree.setText(gradeList.get(position).getGradeThree());
        }
        if (!gradeList.get(position).getGradeFour().equals("0")) {
            holder.gradeFour.setVisibility(View.VISIBLE);
            holder.gradeFour.setText(gradeList.get(position).getGradeFour());
        }
        if (!gradeList.get(position).getGradeFive().equals("0")) {
            holder.gradeFive.setVisibility(View.VISIBLE);
            holder.gradeFive.setText(gradeList.get(position).getGradeFive());
        }
    }

    @Override
    public int getItemCount() {
        return gradeList.size();
    }

    public void setData(List<Grade> data) {
        gradeList = data;
        notifyDataSetChanged();
    }

    class GradeViewHolder extends RecyclerView.ViewHolder {

        TextView code;
        TextView subject;
        TextView generalGrade;
        TextView gradeOne;
        TextView gradeTwo;
        TextView gradeThree;
        TextView gradeFour;
        TextView gradeFive;

        GradeViewHolder(View itemView) {
            super(itemView);

            code = itemView.findViewById(R.id.text_item_code);
            subject = itemView.findViewById(R.id.text_item_subject);
            generalGrade = itemView.findViewById(R.id.text_item_generalGrade);
            gradeOne = itemView.findViewById(R.id.text_item_gradeOne);
            gradeTwo = itemView.findViewById(R.id.text_item_gradeTwo);
            gradeThree = itemView.findViewById(R.id.text_item_gradeThree);
            gradeFour = itemView.findViewById(R.id.text_item_gradeFour);
            gradeFive = itemView.findViewById(R.id.text_item_gradeFive);
            gradeOne.setVisibility(View.GONE);
            gradeTwo.setVisibility(View.GONE);
            gradeThree.setVisibility(View.GONE);
            gradeFour.setVisibility(View.GONE);
            gradeFive.setVisibility(View.GONE);
        }
    }
}
