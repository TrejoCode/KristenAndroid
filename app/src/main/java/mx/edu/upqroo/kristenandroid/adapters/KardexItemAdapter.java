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

public class KardexItemAdapter extends RecyclerView.Adapter<KardexItemAdapter.KardexViewHolder>{

    private List<Grades> gradeList;
    private LayoutInflater mInflater;

    public KardexItemAdapter(Context context, List<Grades> gradeList) {
        this.mInflater = LayoutInflater.from(context);
        this.gradeList = gradeList;
    }

    @NonNull
    @Override
    public KardexItemAdapter.KardexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = mInflater.inflate(R.layout.item_kardex, parent,false);
        return new KardexItemAdapter.KardexViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull KardexItemAdapter.KardexViewHolder holder, int position) {
        holder.code.setText(gradeList.get(position).getCode());
        holder.subject.setText(gradeList.get(position).getSubject());
        holder.generalGrade.setText(gradeList.get(position).getGeneralGrade());

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

    public class KardexViewHolder extends RecyclerView.ViewHolder {

        TextView code;
        TextView subject;
        TextView generalGrade;

        public KardexViewHolder(View itemView) {
            super(itemView);

            code = itemView.findViewById(R.id.text_item_kardex_code);
            subject = itemView.findViewById(R.id.text_item_kardex_subject);
            generalGrade = itemView.findViewById(R.id.text_item_kardex_generalGrade);

        }
    }
}