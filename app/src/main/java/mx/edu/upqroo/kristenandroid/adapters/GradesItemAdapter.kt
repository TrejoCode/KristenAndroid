package mx.edu.upqroo.kristenandroid.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade

class GradesItemAdapter(context: Context, private var gradeList: List<Grade>?)
    : RecyclerView.Adapter<GradesItemAdapter.GradeViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val vista = mInflater.inflate(R.layout.item_grades, parent, false)
        return GradeViewHolder(vista)
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        holder.code.text = gradeList!![position].code
        holder.subject.text = gradeList!![position].subject
        holder.generalGrade.text = gradeList!![position].generalGrade
        if (gradeList!![position].gradeOne != "0") {
            holder.gradeOne.visibility = View.VISIBLE
            holder.gradeOne.text = gradeList!![position].gradeOne
        }
        if (gradeList!![position].gradeTwo != "0") {
            holder.gradeTwo.visibility = View.VISIBLE
            holder.gradeTwo.text = gradeList!![position].gradeTwo
        }
        if (gradeList!![position].gradeThree != "0") {
            holder.gradeThree.visibility = View.VISIBLE
            holder.gradeThree.text = gradeList!![position].gradeThree
        }
        if (gradeList!![position].gradeFour != "0") {
            holder.gradeFour.visibility = View.VISIBLE
            holder.gradeFour.text = gradeList!![position].gradeFour
        }
        if (gradeList!![position].gradeFive != "0") {
            holder.gradeFive.visibility = View.VISIBLE
            holder.gradeFive.text = gradeList!![position].gradeFive
        }
    }

    override fun getItemCount(): Int {
        return gradeList!!.size
    }

    fun setData(data: List<Grade>) {
        gradeList = data
        notifyDataSetChanged()
    }

    inner class GradeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var code: TextView = itemView.findViewById(R.id.text_item_code)
        var subject: TextView = itemView.findViewById(R.id.text_item_subject)
        var generalGrade: TextView = itemView.findViewById(R.id.text_item_generalGrade)
        var gradeOne: TextView = itemView.findViewById(R.id.text_item_gradeOne)
        var gradeTwo: TextView = itemView.findViewById(R.id.text_item_gradeTwo)
        var gradeThree: TextView = itemView.findViewById(R.id.text_item_gradeThree)
        var gradeFour: TextView = itemView.findViewById(R.id.text_item_gradeFour)
        var gradeFive: TextView = itemView.findViewById(R.id.text_item_gradeFive)

        init {
            gradeOne.visibility = View.GONE
            gradeTwo.visibility = View.GONE
            gradeThree.visibility = View.GONE
            gradeFour.visibility = View.GONE
            gradeFive.visibility = View.GONE
        }
    }
}
