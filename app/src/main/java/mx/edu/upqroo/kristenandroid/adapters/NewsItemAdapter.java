package mx.edu.upqroo.kristenandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.ui.activities.NewsDetailActivity;
import mx.edu.upqroo.kristenandroid.util.KristenDateUtils;
import mx.edu.upqroo.kristenandroid.util.Serializer;
import mx.edu.upqroo.kristenandroid.data.models.News;

public class NewsItemAdapter extends PagedListAdapter<News, NewsItemAdapter.ViewHolder> {
    //region Fields
    private LayoutInflater mInflater;

    private static DiffUtil.ItemCallback<News> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<News>() {
                @Override
                public boolean areItemsTheSame(News oldItem, News newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(News oldItem, @NotNull News newItem) {
                    return oldItem.equals(newItem);
                }
            };
    //endregion
    //region Constructors
    public NewsItemAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.mInflater = LayoutInflater.from(context);
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
        holder.news = getItem(position);
        if (holder.news != null) {
            Picasso.get()
                    .load(holder.news.getCoverUrl())
                    .error(R.drawable.android_menu)
                    .placeholder(R.drawable.side_nav_bar)
                    .into(holder.imageNews);
            holder.textTitle.setText(holder.news.getTitle());
            holder.textSubtitle.setText(holder.news.getDescription());
            holder.textDate.setText(KristenDateUtils.INSTANCE.formatDate(holder.news.getDate()));
        } else {
            holder.textTitle.setText("");
            holder.textSubtitle.setText("");
            holder.textDate.setText("");
        }
    }
    //endregion
    //region Class ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {
        //region Fields
        News news;
        MaterialCardView mCard;
        ImageView imageNews;
        TextView textTitle;
        TextView textSubtitle;
        TextView textDate;
        Button buttonReadMore;
        //endregion

        //region Constructor
        ViewHolder(View itemView) {
            super(itemView);
            mCard = itemView.findViewById(R.id.card_news);
            imageNews = itemView.findViewById(R.id.image_item_news);
            textTitle = itemView.findViewById(R.id.text_item_title_news);
            textSubtitle = itemView.findViewById(R.id.text_item_subtitle_news);
            textDate = itemView.findViewById(R.id.text_item_date_news);
            buttonReadMore = itemView.findViewById(R.id.button_item_readmore);

            buttonReadMore.setOnClickListener(v -> openDetails(v.getContext()));
            mCard.setOnClickListener(v -> openDetails(v.getContext()));
        }
        //endregion

        private void openDetails(Context c) {
            Intent intent = new Intent(c, NewsDetailActivity.class);
            intent.putExtra(NewsDetailActivity.EXTRA_NEWS, Serializer.INSTANCE.serialize(news));
            c.startActivity(intent);
        }
    }
    //endregion
}