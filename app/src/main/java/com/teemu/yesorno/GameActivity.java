package com.teemu.yesorno;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.Builder;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Handler;
import android.provider.MediaStore.Audio;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    TextView textView2, textView;
    private Handler mHandler;

    private SoundPool soundPool;
    private int soundIDY, soundIDN;
    boolean loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        constraintLayout = findViewById(R.id.constraintLayout);
        textView2 = findViewById(R.id.textView2);
        textView = findViewById(R.id.textView);
        mHandler = new Handler();

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        // Load the sound
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);


        soundIDY = soundPool.load(this, R.raw.yes, 1);
        soundIDN = soundPool.load(this, R.raw.no, 1);

        constraintLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setVisibility(View.GONE);
                textView2.setText(null);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        constraintLayout.setBackgroundColor(Color.GREEN);

                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                constraintLayout.setBackgroundColor(Color.RED);

                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        constraintLayout.setBackgroundColor(Color.GREEN);

                                        mHandler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                constraintLayout.setBackgroundColor(Color.RED);

                                                mHandler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        constraintLayout.setBackgroundColor(Color.GREEN);

                                                        int[] androidColors = getResources().getIntArray(R.array.androidcolors);
                                                        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];

                                                        constraintLayout.setBackgroundColor(randomAndroidColor);

                                                        int color = Color.TRANSPARENT;
                                                        Drawable background = constraintLayout.getBackground();
                                                        if (background instanceof ColorDrawable)
                                                            color = ((ColorDrawable) background).getColor();
                                                            if(color == Color.RED) {
                                                                textView2.setText("No");
                                                                textView2.setTextColor(Color.WHITE);

                                                                AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                                                                float actualVolume = (float) audioManager
                                                                        .getStreamVolume(AudioManager.STREAM_MUSIC);
                                                                float maxVolume = (float) audioManager
                                                                        .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                                                                float volume = actualVolume / maxVolume;
                                                                soundPool.play(soundIDN, volume, volume, 1, 0, 1f);
                                                            } else {
                                                                textView2.setText("Yes");
                                                                textView2.setTextColor(Color.BLACK);

                                                                AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                                                                float actualVolume = (float) audioManager
                                                                        .getStreamVolume(AudioManager.STREAM_MUSIC);
                                                                float maxVolume = (float) audioManager
                                                                        .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                                                                float volume = actualVolume / maxVolume;
                                                                soundPool.play(soundIDY, volume, volume, 1, 0, 1f);
                                                            }
                                                    }
                                                }, 500);
                                            }
                                        }, 425);
                                    }
                                }, 350);
                            }
                        }, 175);
                    }
                }, 100);


            }
        });

    }
}