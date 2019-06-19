package mx.edu.upqroo.kristenandroid.ui.fragments


import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.adapters.NewsItemAdapter
import mx.edu.upqroo.kristenandroid.data.models.News
import mx.edu.upqroo.kristenandroid.viewModels.NewsViewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class NewsListFragment : Fragment() {

    private lateinit var mViewModel: NewsViewModel
    private lateinit var mAdapter: NewsItemAdapter
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mSwipeContainer: SwipeRefreshLayout
    private lateinit var mImageEmptyNews: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_news_list, container, false)

        mProgressBar = v.findViewById(R.id.progress_news)
        mProgressBar.isIndeterminate = true
        mProgressBar.visibility = View.VISIBLE

        mSwipeContainer = v.findViewById(R.id.refreshLayout_news)
        mSwipeContainer.setOnRefreshListener {
            Objects.requireNonNull<PageKeyedDataSource<Int, News>>(
                    mViewModel.dataSourceFactory
                            .liveDataSource
                            !!.value)
                    .invalidate()
        }
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker)

        mImageEmptyNews = v.findViewById(R.id.image_empty_news)

        val mTextErrorMessage = v.findViewById<TextView>(R.id.text_error_message)
        mTextErrorMessage.visibility = View.INVISIBLE

        val mRecyclerNews = v.findViewById<RecyclerView>(R.id.recycler_news)
        mRecyclerNews.setHasFixedSize(true)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerNews.layoutManager = LinearLayoutManager(context)
        } else {
            mRecyclerNews.layoutManager = GridLayoutManager(context, 2)
        }
        mAdapter = context?.let { NewsItemAdapter(it) }!!
        mRecyclerNews.adapter = mAdapter

        mViewModel.news.observe(this, Observer<PagedList<News>> { news ->
            mAdapter.submitList(news)
            mSwipeContainer.isRefreshing = false
            mProgressBar.visibility = View.GONE
        })
        return v
    }
}
