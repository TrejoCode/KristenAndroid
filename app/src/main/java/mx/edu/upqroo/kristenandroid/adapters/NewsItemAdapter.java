package mx.edu.upqroo.kristenandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.activities.NewsDetailActivity;
import mx.edu.upqroo.kristenandroid.common.Serializer;
import mx.edu.upqroo.kristenandroid.common.ViewHelper;
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
        holder.news = actualNews;
        if (actualNews.getCategory().equals("COVER")) {
            holder.imageNews.setVisibility(View.GONE);
            holder.buttonReadMore.setVisibility(View.GONE);
            holder.textDate.setVisibility(View.GONE);
        } else {
            holder.imageNews.setVisibility(View.VISIBLE);
            holder.buttonReadMore.setVisibility(View.VISIBLE);
            holder.textDate.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(actualNews.getCoverUrl())
                    .error(R.drawable.side_nav_bar)
                    .placeholder(R.drawable.side_nav_bar)
                    .into(holder.imageNews);
        }
        holder.textTitle.setText(actualNews.getTitle());
        holder.textSubtitle.setText(actualNews.getDescription());
        holder.textDate.setText(actualNews.getDate());
        if (position + 1 == getItemCount()) {
            ViewHelper.SetBottomMargin(holder.itemView,
                    (int) (72 * Resources.getSystem().getDisplayMetrics().density));
        } else {
            ViewHelper.SetBottomMargin(holder.itemView, 0);
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
    //region Class ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //region Fields
        News news;
        ImageView imageNews;
        TextView textTitle;
        TextView textSubtitle;
        TextView textDate;
        Button buttonReadMore;
        //endregion

        //region Constructor
        ViewHolder(View itemView) {
            super(itemView);
            imageNews = itemView.findViewById(R.id.image_item_news);
            textTitle = itemView.findViewById(R.id.text_item_title_news);
            textSubtitle = itemView.findViewById(R.id.text_item_subtitle_news);
            textDate = itemView.findViewById(R.id.text_item_date_news);
            buttonReadMore = itemView.findViewById(R.id.button_item_readmore);
                buttonReadMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!news.getCategory().equals("COVER")) {
                        Intent intent = new Intent(v.getContext(), NewsDetailActivity.class);
                        intent.putExtra(NewsDetailActivity.EXTRA_NEWS,
                                Serializer.Serialize(news));
                        v.getContext().startActivity(intent);
                    }
                }
            });
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