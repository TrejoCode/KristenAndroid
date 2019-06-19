package mx.edu.upqroo.kristenandroid.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.api.sie.messages.LoginMessage
import mx.edu.upqroo.kristenandroid.data.repositories.UserInformationRepository
import mx.edu.upqroo.kristenandroid.helpers.FirebaseNotificationsHelper
import mx.edu.upqroo.kristenandroid.managers.PreferencesManager
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LoginActivity : ThemeActivity() {
    private lateinit var mUserId: TextView
    private lateinit var mPassword: TextView
    private lateinit var mLinearOverlay: LinearLayoutCompat
    private lateinit var mButtonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLinearOverlay = findViewById(R.id.linear_overlay_login)
        mLinearOverlay.visibility = View.VISIBLE
        mUserId = findViewById(R.id.field_user_id)
        mPassword = findViewById(R.id.field_password)
        mButtonLogin = findViewById(R.id.button_login)
        val mButtonLoginNoSession = findViewById<Button>(R.id.button_login_no_session)
        mLinearOverlay.visibility = View.GONE

        mButtonLogin.setOnClickListener {
            mUserId.isEnabled = false
            mPassword.isEnabled = false
            mLinearOverlay.visibility = View.VISIBLE
            mButtonLogin.isClickable = false
            SessionManager.instance.login(
                    mUserId.text.toString(),
                    mPassword.text.toString(),
                    application)
        }

        mButtonLoginNoSession.setOnClickListener {
            SessionManager.instance.createDefaultSession()
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }

    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageLogin(event: LoginMessage) {
        if (event.isResult) {
            UserInformationRepository.getInstance(application).insert(event.student)
            SessionManager.instance.createNewSession(event.student)
            PreferencesManager.instance.saveSession(event.student.userId,
                    event.student.password)

            val notificationLoaded = PreferencesManager
                    .instance
                    .loadNotificationsPreference()
            if (notificationLoaded.isGeneral) {
                FirebaseNotificationsHelper
                        .subscribeNotifications(SessionManager.instance.session
                                .config
                                .generalTopic)
            }
            if (notificationLoaded.isCareer) {
                FirebaseNotificationsHelper
                        .subscribeNotifications(SessionManager.instance.session
                                .config
                                .userTopic)
            }
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(mainIntent)
        } else {
            Toast.makeText(this, event.message, Toast.LENGTH_LONG).show()
        }
        mUserId.isEnabled = true
        mPassword.isEnabled = true
        mButtonLogin.isClickable = true
        mLinearOverlay.visibility = View.GONE
    }
}
