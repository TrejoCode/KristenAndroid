package mx.edu.upqroo.kristenandroid.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.upqroo.kristenandroid.R

class ListElementItemAdapter internal constructor(private val mList: List<String>,
                                                          context: Context)
    : RecyclerView.Adapter<ListElementItemAdapter.ListElementViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ListElementViewHolder {
        val vista = mInflater.inflate(R.layout.item_content_list_element, parent, false)
        return ListElementViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ListElementViewHolder,
                                  position: Int) {
        holder.mTextView.text = mList[position]
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ListElementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextView: TextView = itemView.findViewById(R.id.text_list_element_item)
    }
}