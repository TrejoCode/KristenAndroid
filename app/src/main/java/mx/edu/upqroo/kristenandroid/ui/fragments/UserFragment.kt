package mx.edu.upqroo.kristenandroid.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.Objects
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.fabric.sdk.android.services.concurrency.AsyncTask
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation
import mx.edu.upqroo.kristenandroid.viewModels.UserViewModel

/**
 * A simple [Fragment] subclass.
 */
class UserFragment : Fragment() {

    private lateinit var mViewModel: UserViewModel

    private lateinit var mUserNameText: TextView
    private lateinit var mUserCareerText: TextView
    private lateinit var mUserCreditsText: TextView
    private lateinit var mUserValidityText: TextView
    private lateinit var mUserEntryPText: TextView
    private lateinit var mUserCurp: TextView
    private lateinit var mUserDoBText: TextView
    private lateinit var mUserAddressText: TextView
    private lateinit var mUserLocalPhoneText: TextView
    private lateinit var mUserMobilePhoneText: TextView
    private lateinit var mUserEmailText: TextView

    private lateinit var mSwipeContainer: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_user, container, false)
        mUserNameText = v.findViewById(R.id.text_alumn_name)
        mUserCareerText = v.findViewById(R.id.text_alumn_career)
        mUserCreditsText = v.findViewById(R.id.text_alumn_credits)
        mUserValidityText = v.findViewById(R.id.text_alumn_status)
        mUserEntryPText = v.findViewById(R.id.text_alumn_starting_period)
        mUserCurp = v.findViewById(R.id.text_alumn_curp)
        mUserDoBText = v.findViewById(R.id.text_alumn_birth)
        mUserAddressText = v.findViewById(R.id.text_alumn_address)
        mUserLocalPhoneText = v.findViewById(R.id.text_alumn_local_phone)
        mUserMobilePhoneText = v.findViewById(R.id.text_alumn_mobile_phone)
        mUserEmailText = v.findViewById(R.id.text_alumn_email)

        mSwipeContainer = v.findViewById(R.id.refreshLayout_user)
        mSwipeContainer.setOnRefreshListener {
            AsyncTask.execute {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                mSwipeContainer.isRefreshing = false
            }
        }
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker)

        mViewModel.getUser(SessionManager.getInstance().session.userId)
                .observe(this, Observer<UserInformation>{ userInformation ->
                    mUserNameText.text = userInformation.name
                    mUserCareerText.text = userInformation.enrollment
                    mUserCreditsText.text = userInformation.creditsAccumulated
                    mUserValidityText.text = userInformation.validity
                    mUserEntryPText.text = userInformation.entryPeriod
                    mUserCurp.text = userInformation.curp
                    mUserDoBText.text = userInformation.dob
                    mUserAddressText.text = userInformation.address
                    mUserLocalPhoneText.text = userInformation.phone
                    mUserMobilePhoneText.text = userInformation.mobilePhone
                    mUserEmailText.text = userInformation.email
                })
        return v
    }
}
