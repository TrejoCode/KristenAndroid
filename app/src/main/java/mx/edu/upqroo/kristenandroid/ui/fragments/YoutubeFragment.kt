package mx.edu.upqroo.kristenandroid.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.flipkart.youtubeview.YouTubePlayerView
import com.flipkart.youtubeview.models.ImageLoader
import com.flipkart.youtubeview.models.YouTubePlayerType
import com.squareup.picasso.Picasso
import androidx.fragment.app.Fragment
import mx.edu.upqroo.kristenandroid.R

/**
 * A simple [Fragment] subclass.
 */
class YoutubeFragment : Fragment() {
    private val imageLoader = ImageLoader {
        imageView, url, height, width ->
        Picasso.get()
                .load(url)
                .resize(width, height)
                .centerCrop()
                .into(imageView)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_youtube, container, false)
        val playerView: YouTubePlayerView
        val videoId: String = arguments!!.getString("videoId", "bOwsLtwa2Ts")
        playerView = view.findViewById(R.id.youtube_player_view)
        playerView.initPlayer(API_KEY, videoId, "https://cdn.rawgit.com/flipkart-incubator/inline-youtube-view/60bae1a1/youtube-android/youtube_iframe_player.html",
                YouTubePlayerType.AUTO, null, this, imageLoader)
        return view
    }

    companion object {
        var API_KEY = "AIzaSyBVfk5yVrwNzDFPsLjKu_HF7DiG_fwd3HE"
    }
}
