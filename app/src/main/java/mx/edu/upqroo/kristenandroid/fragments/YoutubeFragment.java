package mx.edu.upqroo.kristenandroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.flipkart.youtubeview.YouTubePlayerView;
import com.flipkart.youtubeview.listener.YouTubeEventListener;
import com.flipkart.youtubeview.models.ImageLoader;
import com.flipkart.youtubeview.models.YouTubePlayerType;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import mx.edu.upqroo.kristenandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YoutubeFragment extends Fragment {

    private static String API_KEY = "AIzaSyBVfk5yVrwNzDFPsLjKu_HF7DiG_fwd3HE";
    public static final String VIDEO_ID = "1ce456Nnkt8";
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_youtube, container, false);
        YouTubePlayerView playerView;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            playerView = view.findViewById(R.id.youtube_player_view);
            //playerView = new YouTubePlayerView(this.getContext());
            playerView.initPlayer(API_KEY, VIDEO_ID, "https://cdn.rawgit.com/flipkart-incubator/inline-youtube-view/60bae1a1/youtube-android/youtube_iframe_player.html",
                    YouTubePlayerType.STRICT_NATIVE, new YouTubeEventListener() {
                        @Override
                        public void onReady() {
                            Toast.makeText(getContext(), "Video ready", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onPlay(int i) {

                        }

                        @Override
                        public void onPause(int i) {

                        }

                        @Override
                        public void onStop(int i, int i1) {

                        }

                        @Override
                        public void onBuffering(int i, boolean b) {

                        }

                        @Override
                        public void onSeekTo(int i, int i1) {

                        }

                        @Override
                        public void onInitializationFailure(String s) {

                        }

                        @Override
                        public void onNativeNotSupported() {

                        }

                        @Override
                        public void onCued() {

                        }
                    }, this, imageLoader);
        }
        return view;
    }

}
