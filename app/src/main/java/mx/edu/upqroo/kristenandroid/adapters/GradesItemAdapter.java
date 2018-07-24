package mx.edu.upqroo.kristenandroid.adapters;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.ViewHelper;
import mx.edu.upqroo.kristenandroid.models.Grades;

/**
 * Created by RafaelKoh on 08/07/2018.
 */

public class GradesItemAdapter extends RecyclerView.Adapter<GradesItemAdapter.GradeViewHolder>{

    private List<Grades> gradeList;
    private LayoutInflater mInflater;

    public GradesItemAdapter(Context context, List<Grades> gradeList) {
        this.mInflater = LayoutInflater.from(context);
        this.gradeList = gradeList;
    }

    @NonNull
    @Override
    public GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = mInflater.inflate(R.layout.item_grades,parent,false);
        return new GradeViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeViewHolder holder, int position) {
        holder.code.setText(gradeList.get(position).getCode());
        holder.subject.setText(gradeList.get(position).getSubject());
        holder.generalGrade.setText(gradeList.get(position).getGeneralGrade());
        holder.gradeOne.setText(gradeList.get(position).getGradeOne());
        holder.gradeTwo.setText(gradeList.get(position).getGradeTwo());
        holder.gradeThree.setText(gradeList.get(position).getGradeThree());
        holder.gradeFour.setText(gradeList.get(position).getGradeFour());
        holder.gradeFive.setText(gradeList.get(position).getGradeFive());

        if (position + 1 == getItemCount()) {
            ViewHelper.SetBottomMargin(holder.itemView, (int) (84 * Resources.getSystem().getDisplayMetrics().density));
        } else {
            // reset bottom margin back to zero. (your value may be different)
            ViewHelper.SetBottomMargin(holder.itemView, 0);
        }
    }

    @Override
    public int getItemCount() {
        return gradeList.size();
    }

    public class GradeViewHolder extends RecyclerView.ViewHolder {

        TextView code;
        TextView subject;
        TextView generalGrade;
        TextView gradeOne;
        TextView gradeTwo;
        TextView gradeThree;
        TextView gradeFour;
        TextView gradeFive;

        public GradeViewHolder(View itemView) {
            super(itemView);

            code = itemView.findViewById(R.id.text_item_code);
            subject = itemView.findViewById(R.id.text_item_subject);
            generalGrade = itemView.findViewById(R.id.text_item_generalGrade);
            gradeOne = itemView.findViewById(R.id.text_item_gradeOne);
            gradeTwo = itemView.findViewById(R.id.text_item_gradeTwo);
            gradeThree = itemView.findViewById(R.id.text_item_gradeThree);
            gradeFour = itemView.findViewById(R.id.text_item_gradeFour);
            gradeFive = itemView.findViewById(R.id.text_item_gradeFive);
        }
    }
}
