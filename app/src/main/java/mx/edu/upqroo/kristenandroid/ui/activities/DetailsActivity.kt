package mx.edu.upqroo.kristenandroid.ui.activities

import android.os.Bundle
import androidx.navigation.Navigation
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.util.Fragments

class DetailsActivity : ThemeActivity() {
    companion object {
        const val FRAGMENT_TO_SHOW: String = "FRAGMENT_TO_SHOW"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val destination: Int = when (intent.getStringExtra(FRAGMENT_TO_SHOW)) {
            Fragments.NOTICES_FRAGMENT.value -> R.id.noticesFragment
            Fragments.USER_FRAGMENT.value -> R.id.userFragment
            Fragments.KARDEX_FRAGMENT.value -> R.id.kardexFragment
            Fragments.CONTACT_FRAGMENT.value -> R.id.contactFragment
            else -> R.id.userFragment
        }

        val navController = Navigation.findNavController(this, R.id.nav_host_detail_fragment)
        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.details)
        graph.startDestination = destination
        navController.graph = graph
    }
}
