package mx.edu.upqroo.kristenandroid.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.adapters.NewsDetailContentAdapter
import mx.edu.upqroo.kristenandroid.ui.activities.NewsDetailActivity

class NewsDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_news_detail, container, false)
        val mRecycler = view.findViewById<RecyclerView>(R.id.recycler_news_detail)
        mRecycler.layoutManager = LinearLayoutManager(context)
        val mContentList = NewsDetailActivity.NEWS_CONTENT
        val mAdapter = mContentList?.let {
            context?.let { it1 -> NewsDetailContentAdapter(it1, it, this) }
        }
        mRecycler.adapter = mAdapter
        return view
    }

    companion object {
        fun newInstance(): NewsDetailFragment {
            return NewsDetailFragment()
        }
    }
}
