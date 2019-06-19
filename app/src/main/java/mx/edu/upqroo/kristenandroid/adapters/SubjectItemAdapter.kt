package mx.edu.upqroo.kristenandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.data.database.entities.Subject

class SubjectItemAdapter internal constructor(private val subjectList: List<Subject>)
    : RecyclerView.Adapter<SubjectItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_subject, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.subject.text = subjectList[position].name
        holder.professor.text = subjectList[position].professor
        holder.time.text = subjectList[position].time
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subject: TextView = itemView.findViewById(R.id.subjecttit)
        val professor: TextView = itemView.findViewById(R.id.subject_professor)
        val time: TextView = itemView.findViewById(R.id.horasubject)
    }
}
