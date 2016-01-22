package com.st.devicefeaturesdemo;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class PlayAudioWithIntentActivity extends Activity {
    String filename = "wonderfullife.mp3";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playaudio_with_intent);
    }

    public void playAudio(View v) {
        File musicFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        Uri audio = Uri.parse("file://" + musicFolder + "/" + filename);
        Intent intent = new Intent( Intent.ACTION_VIEW);
        intent.setDataAndType(audio, "audio/*");
        startActivity(intent);
    }

}
