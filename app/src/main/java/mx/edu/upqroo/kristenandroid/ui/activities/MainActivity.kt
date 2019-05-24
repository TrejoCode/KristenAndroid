package mx.edu.upqroo.kristenandroid.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.api.kristen.KristenApiServices
import mx.edu.upqroo.kristenandroid.api.kristen.messages.CalendarUrlMessage
import mx.edu.upqroo.kristenandroid.data.repositories.UserInformationRepository
import mx.edu.upqroo.kristenandroid.managers.FragmentManager
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.ui.fragments.CalendarFragment
import mx.edu.upqroo.kristenandroid.util.setupWithNavController
import mx.edu.upqroo.kristenandroid.widget.DataWidgetManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : ThemeActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavController.OnDestinationChangedListener {
    private lateinit var mToolbar: Toolbar
    private lateinit var mSession: SessionManager
    private lateinit var mNavigationView: NavigationView
    private lateinit var mBottomNavigationView: BottomNavigationView
    private lateinit var mDrawerLayout: DrawerLayout

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSession = SessionManager.getInstance()
        //region widget update
        DataWidgetManager.updateWidgetAsync(applicationContext)
        //endregion
        //region toolbar setup
        setContentView(R.layout.activity_main)
        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        //endregion
        //region drawer layout setup
        mDrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        mNavigationView = findViewById(R.id.nav_view)
        val navHeader = mNavigationView.inflateHeaderView(R.layout.nav_header_news)
        val mNavHeaderProfileName = navHeader.findViewById<TextView>(R.id.text_nav_header_title)
        val mNavHeaderProfileEmail = navHeader.findViewById<TextView>(R.id.text_nav_header_subtitle)
        if (mNavHeaderProfileName != null && mNavHeaderProfileEmail != null) {
            mNavHeaderProfileName.text = mSession.session.name
            mNavHeaderProfileEmail.text = mSession.session.email
        }

        mNavigationView.setNavigationItemSelectedListener(this)

        if (!mSession.sessionAlive()) {
            mNavigationView.menu
                    .getItem(mNavigationView.menu.size() - 1)
                    .setTitle(R.string.button_login)
        }
        //endregion
        //region bottom navigation setup
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
        //endregion
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            if (currentNavController?.value?.popBackStack() != true) {
                super.onBackPressed()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.news_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (val id = item.itemId) {
            R.id.nav_logout -> {
                if (mSession.sessionAlive()) {
                    showLogoutDialog()
                    mDrawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    val loginIntent = Intent(this, LoginActivity::class.java)
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(loginIntent)
                    mDrawerLayout.closeDrawer(GravityCompat.START)
                }
            }
            R.id.nav_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                mDrawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_contact -> {
                openDetailActivity(FragmentManager.CONTACT_FRAGMENT.name)
                mDrawerLayout.closeDrawer(GravityCompat.START)
            }
            else -> {
                if (!mSession.sessionAlive()) {
                    showLoginMessage()
                } else {
                    when (id) {
                        R.id.nav_user -> openDetailActivity(FragmentManager.USER_FRAGMENT.name)
                        R.id.nav_kardex -> openDetailActivity(FragmentManager.KARDEX_FRAGMENT.name)
                        R.id.nav_notices -> openDetailActivity(FragmentManager.NOTICES_FRAGMENT.name)
                    }
                    mDrawerLayout.closeDrawer(GravityCompat.START)
                }
            }
        }
        return false
    }

    override fun onDestinationChanged(controller: NavController,
                                      destination: NavDestination,
                                      arguments: Bundle?) {
        if (destination.id == R.id.calendarFragment) {
            KristenApiServices.getInstance().getCalendarUrl()
        }
    }

    private fun setupBottomNavigationBar() {
        mBottomNavigationView = findViewById(R.id.bottom_navigation)
        val navGraphIds = listOf(R.navigation.news, R.navigation.schedule, R.navigation.calendar,
                R.navigation.grades)

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = mBottomNavigationView.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.nav_host_fragment,
                intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(this, navController)
            navController.addOnDestinationChangedListener(this)
        })
        currentNavController = controller
    }

    private fun openDetailActivity(fragmentToShow: String) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.FRAGMENT_TO_SHOW, fragmentToShow)
        startActivity(intent)
    }

    private fun showLoginMessage() {
        Snackbar.make(findViewById(R.id.bottom_navigation),
                R.string.login_message,
                Snackbar.LENGTH_LONG)
                .setAction(R.string.button_login) { startActivity(
                        Intent(applicationContext, LoginActivity::class.java)) }
                .show()
    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.exit_confirmation_message))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes_option)) { _, _ ->
                    mSession.logout()
                    DataWidgetManager.deleteSchedule(applicationContext)
                    UserInformationRepository.getInstance(application).deleteAll()
                    val loginIntent = Intent(applicationContext, LoginActivity::class.java)
                    loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(loginIntent)
                }
                .setNegativeButton(getString(R.string.no_option)) { dialog, _ -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }
}
