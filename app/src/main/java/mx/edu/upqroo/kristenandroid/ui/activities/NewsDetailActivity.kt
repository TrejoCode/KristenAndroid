package mx.edu.upqroo.kristenandroid.ui.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar

import com.crashlytics.android.Crashlytics
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

import java.util.ArrayList

import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.FragmentTransaction
import androidx.palette.graphics.Palette
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.helpers.PostTypeHelper
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import mx.edu.upqroo.kristenandroid.util.Serializer
import mx.edu.upqroo.kristenandroid.data.models.Content
import mx.edu.upqroo.kristenandroid.data.models.News
import mx.edu.upqroo.kristenandroid.ui.fragments.NewsDetailFragment
import mx.edu.upqroo.kristenandroid.api.kristen.KristenApiServices
import mx.edu.upqroo.kristenandroid.api.kristen.messages.NewsDetailMessage
import mx.edu.upqroo.kristenandroid.util.KristenColorUtils

class NewsDetailActivity : ThemeActivity() {
    private lateinit var mNews: News
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mCollapsingToolbar: CollapsingToolbarLayout
    private lateinit var mCoverImageView: ImageView

    companion object {
        const val EXTRA_NEWS = "KEY_NEWS"
        var NEWS_CONTENT: ArrayList<Content>? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        val mToolbar = findViewById<Toolbar>(R.id.toolbar_news_detail)
        setSupportActionBar(mToolbar)
        mToolbar.setNavigationOnClickListener { onBackPressed() }

        if (intent.hasExtra(EXTRA_NEWS)) {
            mNews = Serializer.Deserialize<News>(intent.getStringExtra(EXTRA_NEWS), News::class.java)
        } else {
            Crashlytics.log("Noticia no existente")
            onBackPressed()
        }
        mProgressBar = findViewById(R.id.progress_news_detail)
        mCoverImageView = findViewById(R.id.image_cover_news_detail)
        mCollapsingToolbar = findViewById(R.id.collapsing_news_detail)

        KristenApiServices.getInstance().getPostContent(mNews.id)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.news_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_share) {
            when {
                mNews.postType == PostTypeHelper.EVENT.id -> {
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            mNews.title + "\n" + SessionManager.getInstance().session
                                    .config.baseAddress + "evento/" + mNews.url)
                    sendIntent.type = "text/plain"
                    startActivity(Intent.createChooser(sendIntent, "Share"))
                }
                mNews.postType == PostTypeHelper.NEWS.id -> {
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            mNews.title + "\n" + SessionManager.getInstance().session
                                    .config.baseAddress + "noticia/" + mNews.url)
                    sendIntent.type = "text/plain"
                    startActivity(Intent.createChooser(sendIntent, "Share"))
                }
                mNews.postType == PostTypeHelper.WORK.id -> {
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            mNews.title + "\n" + SessionManager.getInstance().session
                                    .config.baseAddress + "trabajo/" + mNews.url)
                    sendIntent.type = "text/plain"
                    startActivity(Intent.createChooser(sendIntent, "Share"))
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessage(event: NewsDetailMessage) {
        if (event.isSuccessful) {
            setUpToolbar(mNews.coverUrl, mNews.title)
            NEWS_CONTENT = ArrayList()
            NEWS_CONTENT!!.add(Content(mNews.description))
            NEWS_CONTENT!!.addAll(event.newsDetail.contentList)
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_detail, NewsDetailFragment.newInstance())
            fragmentTransaction.commit()
        }
        mProgressBar.visibility = View.GONE
    }

    private fun setUpToolbar(coverUrl: String, title: String) {
        mCollapsingToolbar.title = title
        //region collapsing toolbar color setup
        Picasso.get()
                .load(coverUrl)
                .placeholder(R.color.colorPrimary)
                .error(R.drawable.android_menu)
                .into(object : Target {
                    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                        mCoverImageView.setImageBitmap(bitmap)
                        Palette.from(bitmap).generate { palette ->
                            var finalColor = 0
                            finalColor = KristenColorUtils.getColor(palette, finalColor)
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                window.statusBarColor = finalColor
                            }
                            mCollapsingToolbar.setContentScrimColor(ColorUtils
                                    .setAlphaComponent(finalColor, 122))
                        }
                    }

                    override fun onBitmapFailed(e: Exception, errorDrawable: Drawable) {

                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable) {

                    }
                })
        //endregion
    }
}
