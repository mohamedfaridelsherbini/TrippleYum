package com.crazygeeks.trippleyum.Fragments;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.crazygeeks.trippleyum.Activities.MainActivity;
import com.crazygeeks.trippleyum.R;
import com.crazygeeks.trippleyum.RadioReciever;
import com.crazygeeks.trippleyum.RadioService;

/**
 * Created by mymac on 3/1/17.
 */

public class RadioFragment extends Fragment {

    private ImageView ivStartStream , ivStopStream;
    private Button btPiano , btBeethoven , btMozart ,btYiruma ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radio_fragment, container, false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets() , "Diamond Dust - PERSONAL USE ONLY.ttf");

        ivStartStream = (ImageView) view.findViewById(R.id.btStartStream);
        ivStopStream = (ImageView) view.findViewById(R.id.btStopStream);
        btPiano = (Button) view.findViewById(R.id.btPiano);
        btBeethoven = (Button) view.findViewById(R.id.btBeethoven);
        btMozart = (Button) view.findViewById(R.id.btMozart);
        btYiruma = (Button) view.findViewById(R.id.btYiruma);

        btPiano.setTypeface(typeface);
        btBeethoven.setTypeface(typeface);
        btMozart.setTypeface(typeface);
        btYiruma.setTypeface(typeface);

        if (isMyServiceRunning(RadioService.class)){
            ivStopStream.setVisibility(View.VISIBLE);
            ivStartStream.setVisibility(View.INVISIBLE);
        }
        ivStartStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMyServiceRunning(RadioService.class)) {
                    StopService();
                }
                playRadio();
                createNotification();
                ivStopStream.setVisibility(View.VISIBLE);
                ivStartStream.setVisibility(View.INVISIBLE);
            }
        });
        ivStopStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StopService();
                ivStopStream.setVisibility(View.INVISIBLE);
                ivStartStream.setVisibility(View.VISIBLE);
            }
        });

        btPiano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStation("http://pianosolo.streamguys.net/live");
            }
        });

        btBeethoven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startStation("http://streaming.radionomy.com/BeethovenRadio-?lang=en-US%2cen%3bq%3d0.8");
            }
        });

        btMozart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStation("http://streaming.radionomy.com/Mozart?lang=en-US%2cen%3bq%3d0.8");
            }
        });
        btYiruma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isMyServiceRunning(RadioService.class)) {
                    StopService();
                }
                RadioService.YIRUMA_CHECK = 1;
                RadioService.yirumaPaths = new String[]{"https://ia600208.us.archive.org/10/items/RiverFlowsInYou_706/Yiruma-riverFlowsInYou.mp3",
                "https://ia600504.us.archive.org/15/items/KissTheRain_Yiruma/KissTheRain-yiruma.mp3"};
                playRadio();
            }
        });
        return view;
    }
    public void playRadio(){
            ivStopStream.setVisibility(View.VISIBLE);
            ivStartStream.setVisibility(View.INVISIBLE);
            Intent i=new Intent(getActivity(), RadioReciever.class);
            i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            getActivity().sendBroadcast(i);

    }

    private void StopService(){
        getActivity().stopService(new Intent(getActivity(), RadioService.class));
        NotificationManager mNotificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
    }

    private void startStation(String stationUrl){

        if (isMyServiceRunning(RadioService.class)) {
            getActivity().stopService(new Intent(getActivity(), RadioService.class));
        }
        RadioService.YIRUMA_CHECK = 0;
        RadioService.radioPath = stationUrl;
        playRadio();
        createNotification();

    }

    private void createNotification(){


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getActivity())
                        .setSmallIcon(R.drawable.ic_radio)
                        .setContentTitle(getActivity().getResources().getString(R.string.app_name))
                        .setContentText("Radio is playing")
                        .setOngoing(true)
                        .setAutoCancel(false);
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(getActivity(), MainActivity.class);
        resultIntent.putExtra("Radio_Playing" , 10);
// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);


        NotificationManager mNotificationManager =
                (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());

    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
