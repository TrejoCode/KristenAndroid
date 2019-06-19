package mx.edu.upqroo.kristenandroid.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice
import mx.edu.upqroo.kristenandroid.util.KristenDateUtils

class NoticesItemAdapter(context: Context) : PagedListAdapter<Notice, NoticesItemAdapter.NoticeViewHolder>(DIFF_CALLBACK) {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val v = mInflater.inflate(R.layout.item_notice, parent, false)
        return NoticeViewHolder(v)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val notice = getItem(position)
        if (notice != null) {
            holder.title.text = notice.title
            holder.body.text = notice.body
            holder.date.text = KristenDateUtils.formatDate(notice.date)
        }
    }

    inner class NoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.text_notice_title)
        var body: TextView = itemView.findViewById(R.id.text_notice_body)
        var date: TextView = itemView.findViewById(R.id.text_notice_date)

    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Notice>() {
            override fun areItemsTheSame(oldItem: Notice, newItem: Notice): Boolean {
                return oldItem.noticeId == newItem.noticeId
            }

            override fun areContentsTheSame(oldItem: Notice, newItem: Notice): Boolean {
                return oldItem == newItem
            }
        }
    }
}
