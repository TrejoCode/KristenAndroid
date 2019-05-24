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
import mx.edu.upqroo.kristenandroid.adapters.KardexItemAdapter
import mx.edu.upqroo.kristenandroid.data.database.entities.Kardex
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.viewModels.KardexViewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class KardexFragment : Fragment() {

    private lateinit var mViewModel: KardexViewModel

    private lateinit var mRecyclerKardex: RecyclerView
    private lateinit var mKardexAdapter: KardexItemAdapter
    private lateinit var mProgress: ProgressBar
    private lateinit var mImageEmptyKardex: ConstraintLayout

    private lateinit var mSwipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(KardexViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_kardex, container, false)
        mRecyclerKardex = v.findViewById(R.id.recycler_kardex)
        mRecyclerKardex.setHasFixedSize(true)
        mRecyclerKardex.layoutManager = LinearLayoutManager(v.context)
        mRecyclerKardex.visibility = View.GONE
        mProgress = v.findViewById(R.id.progress_kardex)
        mProgress.visibility = View.VISIBLE
        mKardexAdapter = KardexItemAdapter(v.context, ArrayList<Kardex>())
        mRecyclerKardex.adapter = mKardexAdapter

        mImageEmptyKardex = v.findViewById(R.id.image_empty_kardex)

        mSwipeContainer = v.findViewById(R.id.refreshLayout_kardex)
        mSwipeContainer.setOnRefreshListener { mViewModel.updateKardexFromService() }
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker)

        mViewModel.getKardex(SessionManager.instance.session.userId)
                .observe(this, Observer<List<Kardex>> { kardexList ->
                    if (kardexList.isEmpty()) {
                        mImageEmptyKardex.visibility = View.VISIBLE
                    } else {
                        mRecyclerKardex.visibility = View.VISIBLE
                        mKardexAdapter.setData(kardexList)
                        mImageEmptyKardex.visibility = View.GONE
                    }
                    mSwipeContainer.isRefreshing = false
                    mProgress.visibility = View.GONE
                })

        return v
    }
}
