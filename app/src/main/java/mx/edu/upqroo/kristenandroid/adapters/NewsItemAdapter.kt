package mx.edu.upqroo.kristenandroid.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.data.models.News
import mx.edu.upqroo.kristenandroid.ui.activities.NewsDetailActivity
import mx.edu.upqroo.kristenandroid.util.KristenDateUtils
import mx.edu.upqroo.kristenandroid.util.Serializer

class NewsItemAdapter (context: Context)
    : PagedListAdapter<News, NewsItemAdapter.ViewHolder>(DIFF_CALLBACK) {
    //region Fields
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    //endregion
    //region onCreateViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    //endregion
    //region onBindViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.news = getItem(position)
        if (holder.news != null) {
            Picasso.get()
                    .load(holder.news!!.coverUrl)
                    .error(R.drawable.android_menu)
                    .placeholder(R.drawable.side_nav_bar)
                    .into(holder.imageNews)
            holder.textTitle.text = holder.news!!.title
            holder.textSubtitle.text = holder.news!!.description
            holder.textDate.text = KristenDateUtils.formatDate(holder.news!!.date!!)
        } else {
            holder.textTitle.text = ""
            holder.textSubtitle.text = ""
            holder.textDate.text = ""
        }
    }

    //endregion
    //region Class ViewHolder
    inner class ViewHolder
    //endregion

    //region Constructor
    (itemView: View) : RecyclerView.ViewHolder(itemView) {
        //region Fields
        var news: News? = null
        var mCard: MaterialCardView = itemView.findViewById(R.id.card_news)
        var imageNews: ImageView = itemView.findViewById(R.id.image_item_news)
        var textTitle: TextView = itemView.findViewById(R.id.text_item_title_news)
        var textSubtitle: TextView = itemView.findViewById(R.id.text_item_subtitle_news)
        var textDate: TextView = itemView.findViewById(R.id.text_item_date_news)
        var buttonReadMore: Button = itemView.findViewById(R.id.button_item_readmore)

        init {

            buttonReadMore.setOnClickListener { v -> openDetails(v.context) }
            mCard.setOnClickListener { v -> openDetails(v.context) }
        }
        //endregion

        private fun openDetails(c: Context) {
            val intent = Intent(c, NewsDetailActivity::class.java)
            intent.putExtra(NewsDetailActivity.EXTRA_NEWS, news?.let { Serializer.serialize(it) })
            c.startActivity(intent)
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }
    //endregion
}