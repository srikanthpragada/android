package com.st.devicefeaturesdemo;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class PlayVideoActivity extends Activity
         implements OnCompletionListener {
    MediaPlayer mp;
    VideoView videoView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);
        videoView = (VideoView) findViewById(R.id.videoDemo);
        File root =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        videoView.setVideoPath( root  + "/" +  "test.mp4");
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.setOnCompletionListener(this);
        videoView.requestFocus();
        videoView.start();
    }
    
    @Override
    public void onCompletion(MediaPlayer  mp) {
       Toast.makeText(this,"Video Completed!", Toast.LENGTH_LONG).show();	
    }
}


