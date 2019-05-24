package mx.edu.upqroo.kristenandroid.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.adapters.GradesItemAdapter
import mx.edu.upqroo.kristenandroid.data.database.entities.Grade
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.viewModels.GradesViewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class GradesFragment : Fragment() {

    private lateinit var mViewModel: GradesViewModel
    private lateinit var mRecyclerGrade: RecyclerView
    private lateinit var mGradeAdapter: GradesItemAdapter
    private lateinit var mProgress: ProgressBar
    private lateinit var mImageEmptyGrades: ConstraintLayout
    private lateinit var mSwipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(GradesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_grades, container, false)
        mRecyclerGrade = v.findViewById(R.id.recycler_grades)
        mRecyclerGrade.setHasFixedSize(true)
        mRecyclerGrade.layoutManager = LinearLayoutManager(context)
        mRecyclerGrade.visibility = View.GONE
        mProgress = v.findViewById(R.id.progress_grades)
        mProgress.visibility = View.VISIBLE

        mImageEmptyGrades = v.findViewById(R.id.image_empty_grades)

        mGradeAdapter = GradesItemAdapter(context, ArrayList<Grade>())
        mRecyclerGrade.adapter = mGradeAdapter

        mSwipeContainer = v.findViewById(R.id.refreshLayout_grades)
        mSwipeContainer.setOnRefreshListener { mViewModel.updateGradesFromService() }
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker)

        mViewModel.getGrades(SessionManager.getInstance().session.userId)
                .observe(this, Observer<List<Grade>> { grades ->
                    if (grades.isEmpty()) {
                        mImageEmptyGrades.visibility = View.VISIBLE
                    } else {
                        mRecyclerGrade.visibility = View.VISIBLE
                        mGradeAdapter.setData(grades)
                        mImageEmptyGrades.visibility = View.GONE
                    }
                    mSwipeContainer.isRefreshing = false
                    mProgress.visibility = View.GONE
                })
        return v
    }
}
