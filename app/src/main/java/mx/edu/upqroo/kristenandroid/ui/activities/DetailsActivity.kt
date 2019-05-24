package mx.edu.upqroo.kristenandroid.ui.activities

import android.os.Bundle
import androidx.navigation.Navigation
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.managers.FragmentManager

class DetailsActivity : ThemeActivity() {
    companion object {
        const val FRAGMENT_TO_SHOW: String = "FRAGMENT_TO_SHOW"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val destination: Int = when (intent.getStringExtra(FRAGMENT_TO_SHOW)) {
            FragmentManager.NOTICES_FRAGMENT.name -> R.id.noticesFragment
            FragmentManager.USER_FRAGMENT.name -> R.id.userFragment
            FragmentManager.KARDEX_FRAGMENT.name -> R.id.kardexFragment
            FragmentManager.CONTACT_FRAGMENT.name -> R.id.contactFragment
            else -> R.id.userFragment
        }

        val navController = Navigation.findNavController(this, R.id.nav_host_detail_fragment)
        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.details)
        graph.startDestination = destination
        navController.graph = graph
    }
}
