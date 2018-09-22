package mx.edu.upqroo.kristenandroid.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;

public class ListElementItemAdapter extends RecyclerView.Adapter<ListElementItemAdapter.ListElementViewHolder> {
    private final LayoutInflater mInflater;
    private List<String> mList;

    ListElementItemAdapter(List<String> images, Context context) {
        this.mList = images;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListElementItemAdapter.ListElementViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        View vista = mInflater.inflate(R.layout.item_content_list_element, parent,false);
        return new ListElementItemAdapter.ListElementViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ListElementItemAdapter.ListElementViewHolder holder,
                                 int position) {
        holder.mTextView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ListElementViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ListElementViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_list_element_item);
        }
    }
}