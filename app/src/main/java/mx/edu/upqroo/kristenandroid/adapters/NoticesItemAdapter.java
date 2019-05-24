package mx.edu.upqroo.kristenandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice;
import mx.edu.upqroo.kristenandroid.util.KristenDateUtils;

public class NoticesItemAdapter extends PagedListAdapter<Notice, NoticesItemAdapter.NoticeViewHolder> {
    private LayoutInflater mInflater;

    private static DiffUtil.ItemCallback<Notice> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Notice>() {
                @Override
                public boolean areItemsTheSame(Notice oldItem, Notice newItem) {
                    return oldItem.getNoticeId().equals(newItem.getNoticeId());
                }

                @Override
                public boolean areContentsTheSame(Notice oldItem, @NotNull Notice newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public NoticesItemAdapter(Context context) {
        super(DIFF_CALLBACK);
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_notice, parent,false);
        return new NoticeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        Notice notice = getItem(position);
        if (notice != null) {
            holder.title.setText(notice.getTitle());
            holder.body.setText(notice.getBody());
            holder.date.setText(KristenDateUtils.INSTANCE.formatDate(notice.getDate()));
        }
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView body;
        TextView date;

        NoticeViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_notice_title);
            body = itemView.findViewById(R.id.text_notice_body);
            date = itemView.findViewById(R.id.text_notice_date);
        }
    }
}
