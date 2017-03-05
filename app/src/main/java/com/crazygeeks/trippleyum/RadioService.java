package com.crazygeeks.trippleyum;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.view.Gravity;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RadioService extends IntentService {
    public static MediaPlayer mp;
    public static String radioPath;
    public static String[] yirumaPaths;
    public static int YIRUMA_CHECK = 0;
    @Override
    protected void onHandleIntent(Intent intent) {

    }

    public RadioService() {
        super("RadioService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mp = new MediaPlayer();
        try {
            if(YIRUMA_CHECK == 1) {
                mp.setDataSource(yirumaPaths[0]);
            }else{
                mp.setDataSource(radioPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                try {
                    mp.stop();
                    mp = new MediaPlayer();
                    mp.setDataSource(yirumaPaths[1]);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                    }
                });
            }
        });
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
    }
}
