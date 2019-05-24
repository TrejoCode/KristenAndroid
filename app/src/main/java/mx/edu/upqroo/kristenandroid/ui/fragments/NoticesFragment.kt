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
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.adapters.NoticesItemAdapter
import mx.edu.upqroo.kristenandroid.data.database.entities.Notice
import mx.edu.upqroo.kristenandroid.viewModels.NoticesViewModel
import java.util.*

class NoticesFragment : Fragment() {

    private lateinit var mViewModel: NoticesViewModel
    private lateinit var mAdapter: NoticesItemAdapter

    private lateinit var mProgressBar: ProgressBar
    private lateinit var mSwipeContainer: SwipeRefreshLayout
    private lateinit var mImageEmptyNotices: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(NoticesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_notices, container, false)
        val mRecyclerNotices = v.findViewById<RecyclerView>(R.id.recycler_notices)
        mRecyclerNotices.layoutManager = LinearLayoutManager(v.context)
        mRecyclerNotices.visibility = View.VISIBLE
        mAdapter = NoticesItemAdapter(context)
        mRecyclerNotices.adapter = mAdapter
        mProgressBar = v.findViewById(R.id.progress_notices)
        mImageEmptyNotices = v.findViewById(R.id.image_empty_notices)

        mSwipeContainer = v.findViewById(R.id.refreshLayout_notices)
        mSwipeContainer.setOnRefreshListener {
            Objects.requireNonNull<PageKeyedDataSource<Int, Notice>>(
                    mViewModel.dataSourceFactory
                            .liveDataSource
                            .value)
                    .invalidate()
        }
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker)

        mViewModel.notices.observe(this, Observer<PagedList<Notice>> { notices ->
            if (notices.isEmpty()) {
                mImageEmptyNotices.visibility = View.VISIBLE
            } else {
                mAdapter.submitList(notices)
                mImageEmptyNotices.visibility = View.GONE
            }
            mProgressBar.visibility = View.GONE
            mSwipeContainer.isRefreshing = false
        })
        return v
    }
}
