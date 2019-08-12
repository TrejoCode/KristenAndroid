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
import androidx.lifecycle.ViewModelProvider
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
import mx.edu.upqroo.kristenandroid.helpers.ScrollToTop
import mx.edu.upqroo.kristenandroid.viewModels.NewsViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
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
    private lateinit var mRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
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

        mImageEmptyNews = v.findViewById(R.id.image_empty_news)

        val mTextErrorMessage = v.findViewById<TextView>(R.id.text_error_message)
        mTextErrorMessage.visibility = View.INVISIBLE

        mRecycler = v.findViewById(R.id.recycler_news)
        mRecycler.setHasFixedSize(true)

        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecycler.layoutManager = LinearLayoutManager(context)
        } else {
            mRecycler.layoutManager = GridLayoutManager(context, 2)
        }
        mAdapter = context?.let { NewsItemAdapter(it) }!!
        mRecycler.adapter = mAdapter

        mViewModel.news.observe(this, Observer<PagedList<News>> { news ->
            mAdapter.submitList(news)
            mSwipeContainer.isRefreshing = false
            mProgressBar.visibility = View.GONE
        })
        return v
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun scrollOnTop(who: ScrollToTop) {
        if (who.id == R.id.news) {
            mRecycler.smoothScrollToPosition(0)
        }
    }
}
