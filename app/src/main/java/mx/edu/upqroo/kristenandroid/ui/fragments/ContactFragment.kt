package mx.edu.upqroo.kristenandroid.ui.fragments

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProviders

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import java.util.ArrayList

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.adapters.ContactItemAdapter
import mx.edu.upqroo.kristenandroid.data.database.entities.Contact
import mx.edu.upqroo.kristenandroid.viewModels.ContactViewModel

class ContactFragment : Fragment() {

    private lateinit var mViewModel: ContactViewModel
    private lateinit var mRecyclerContact: RecyclerView
    private lateinit var mContactAdapter: ContactItemAdapter
    private lateinit var mProgress: ProgressBar
    private lateinit  var mSwipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_contact, container, false)
        mRecyclerContact = v.findViewById(R.id.recycler_contacts)
        mRecyclerContact.setHasFixedSize(true)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerContact.layoutManager = LinearLayoutManager(context)
        } else {
            mRecyclerContact.layoutManager = GridLayoutManager(context, 2)
        }
        mRecyclerContact.visibility = View.GONE
        mProgress = v.findViewById(R.id.progress_contacts)
        mProgress.visibility = View.VISIBLE

        mContactAdapter = ContactItemAdapter(context, ArrayList<Contact>())
        mRecyclerContact.adapter = mContactAdapter

        mSwipeContainer = v.findViewById(R.id.refreshLayout_contacts)
        mSwipeContainer.setOnRefreshListener { mViewModel.updateFromService() }
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker)

        mViewModel.contacts.observe(this, Observer<List<Contact>> {
            contacts -> mRecyclerContact.visibility = View.VISIBLE
            mContactAdapter.setData(contacts)
            mSwipeContainer.isRefreshing = false
            mProgress.visibility = View.GONE
        })
        return v
    }
}
