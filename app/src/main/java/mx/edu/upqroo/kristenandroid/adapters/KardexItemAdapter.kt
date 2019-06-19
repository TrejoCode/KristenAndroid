package mx.edu.upqroo.kristenandroid.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.adapters.KardexItemAdapter.KardexViewHolder
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex

class KardexItemAdapter(context: Context, private var gradeList: List<Kardex>?)
    : RecyclerView.Adapter<KardexViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): KardexViewHolder {
        val view = mInflater.inflate(R.layout.item_kardex, parent, false)
        return KardexViewHolder(view)
    }

    override fun onBindViewHolder(holder: KardexViewHolder, position: Int) {
        holder.code.text = gradeList!![position].quarter
        holder.subject.text = gradeList!![position].subject
        holder.generalGrade.text = gradeList!![position].grade
    }

    override fun getItemCount(): Int {
        return gradeList!!.size
    }

    fun setData(data: List<Kardex>) {
        gradeList = data
        notifyDataSetChanged()
    }

    inner class KardexViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var code: TextView = itemView.findViewById(R.id.text_item_kardex_code)
        var subject: TextView = itemView.findViewById(R.id.text_item_kardex_subject)
        var generalGrade: TextView = itemView.findViewById(R.id.text_item_kardex_generalGrade)
    }
}