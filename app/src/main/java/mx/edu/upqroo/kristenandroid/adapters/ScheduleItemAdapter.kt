package mx.edu.upqroo.kristenandroid.adapters


import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.data.models.ScheduleSubject

class ScheduleItemAdapter(private var mDaysList: List<ScheduleSubject>?)
    : RecyclerView.Adapter<ScheduleItemAdapter.ViewHolderSchedule>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderSchedule {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_schedule, parent, false)
        return ViewHolderSchedule(view)
    }

    override fun onBindViewHolder(holder: ViewHolderSchedule, position: Int) {
        val scheduleSubject = mDaysList!![position]
        holder.dayOfWeek.text = scheduleSubject.day.name

        holder.mAdapter = SubjectItemAdapter(scheduleSubject.subjects)
        holder.mRecycler.adapter = holder.mAdapter
    }

    fun setData(data: List<ScheduleSubject>) {
        mDaysList = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mDaysList!!.size
    }

    inner class ViewHolderSchedule(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dayOfWeek: TextView = itemView.findViewById(R.id.titSche)
        val mRecycler: RecyclerView = itemView.findViewById(R.id.recycler_subject)
        var mAdapter: SubjectItemAdapter? = null

        init {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT_WATCH) {
                mRecycler.layoutManager = object : LinearLayoutManager(itemView.context,
                        VERTICAL, false) {
                    override fun canScrollHorizontally(): Boolean {
                        return false
                    }

                    override fun canScrollVertically(): Boolean {
                        return false
                    }
                }
            } else {
                mRecycler.layoutManager = LinearLayoutManager(itemView.context)
            }

        }
    }
}
