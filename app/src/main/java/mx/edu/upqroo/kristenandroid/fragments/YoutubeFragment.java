package mx.edu.upqroo.kristenandroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.flipkart.youtubeview.YouTubePlayerView;
import com.flipkart.youtubeview.models.ImageLoader;
import com.flipkart.youtubeview.models.YouTubePlayerType;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import mx.edu.upqroo.kristenandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YoutubeFragment extends Fragment {

    public static String API_KEY = "AIzaSyBVfk5yVrwNzDFPsLjKu_HF7DiG_fwd3HE";
    private ImageLoader imageLoader = new ImageLoader() {
        @Override
        public void loadImage(@NonNull ImageView imageView, @NonNull String url, int height, int width) {
            Picasso.get().load(url).resize(width, height).centerCrop().into(imageView);
        }
    };

    public YoutubeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_youtube, container, false);
        YouTubePlayerView playerView;
        String videoId;
        try {
            videoId = getArguments().getString("videoId", "bOwsLtwa2Ts");
        } catch (NullPointerException ex) {
            videoId = "bOwsLtwa2Ts";
        }
        playerView = view.findViewById(R.id.youtube_player_view);
        playerView.initPlayer(API_KEY, videoId, "https://cdn.rawgit.com/flipkart-incubator/inline-youtube-view/60bae1a1/youtube-android/youtube_iframe_player.html",
                YouTubePlayerType.STRICT_NATIVE, null, this, imageLoader);
        return view;
    }

}
