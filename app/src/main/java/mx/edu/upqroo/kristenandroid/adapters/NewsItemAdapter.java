package mx.edu.upqroo.kristenandroid.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.models.News;

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ViewHolder> {
    //region Fields
    private ArrayList<News> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    //endregion

    //region Constructors
    public NewsItemAdapter(Context context, ArrayList<News> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    //endregion

    //region onCreateViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }
    //endregion
    //region onBindViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News actualNews = mData.get(position);
        Picasso.get()
                .load(actualNews.getCoverUrl())
                .error(R.drawable.side_nav_bar)
                .into(holder.imageNews);
        holder.textTitle.setText(actualNews.getTitle());
        holder.textSubtitle.setText(actualNews.getSubtitle());
        if (position + 1 == getItemCount()) {
            setBottomMargin(holder.itemView, (int) (72 * Resources.getSystem().getDisplayMetrics().density));
        } else {
            // reset bottom margin back to zero. (your value may be different)
            setBottomMargin(holder.itemView, 0);
        }
    }
    //endregion
    //region getItemCount
    @Override
    public int getItemCount() {
        return mData.size();
    }
    //endregion
    //region getItem
    public News getItem(int id) {
        return mData.get(id);
    }
    //endregion
    //region setClickListener
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    //endregion

    //region Interface ItemClickListener
    public interface ItemClickListener {
        void onNewsItemClick(View view, int position);
    }
    //endregion
    //region setBottomMargin
    private static void setBottomMargin(View view, int bottomMargin) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, bottomMargin);
            view.requestLayout();
        }
    }
    //endregion
    //region Class ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //region Fields
        ImageView imageNews;
        TextView textTitle;
        TextView textSubtitle;
        //TextView mCategoryTittle;
        //endregion

        //region Constructor
        ViewHolder(View itemView) {
            super(itemView);
            imageNews = itemView.findViewById(R.id.image_item_news);
            textTitle = itemView.findViewById(R.id.text_item_title_news);
            textSubtitle = itemView.findViewById(R.id.text_item_subtitle_news);
            itemView.setOnClickListener(this);
        }
        //endregion
        //region onClick
        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onNewsItemClick(view, getAdapterPosition());
        }
        //endregion
    }
    //endregion
}