//package com.goku.tmdb.ui;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.VideoView;
//
//import com.goku.tmdb.R;
//import com.google.android.youtube.player.YouTubeBaseActivity;
//import com.google.android.youtube.player.YouTubeInitializationResult;
//import com.google.android.youtube.player.YouTubePlayer;
//import com.google.android.youtube.player.YouTubePlayerView;
//
//public class YoubuteActivity extends YouTubeBaseActivity implements
//        YouTubePlayer.OnInitializedListener{
//
//    private static final String TAG = "YoubuteActivity";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_youbute);
//
//        VideoView youTubeView = (VideoView) findViewById(R.id.youtube_videoview);
//        youTubeView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//            @Override
//            public boolean onError(MediaPlayer mp, int what, int extra) {
//                Log.d(TAG, "onError() called with: mp = [" + mp + "], what = [" + what + "], extra = [" + extra + "]");
//                return false;
//            }
//        });
//        youTubeView.setVideoPath("https://www.youtube.com/embed/Ox8_HtUicHk");
//        youTubeView.start();
//    }
//
//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
//        Log.d(TAG, "onInitializationSuccess() called with: provider = [" + provider + "], youTubePlayer = [" + youTubePlayer + "], b = [" + wasRestored + "]");
//    }
//
//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//        Log.d(TAG, "onInitializationFailure() called with: provider = [" + provider + "], youTubeInitializationResult = [" + youTubeInitializationResult + "]");
//    }
//}