package com.crazygeeks.trippleyum.Fragments;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ApplicationErrorReport;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.crazygeeks.trippleyum.R;
import com.crazygeeks.trippleyum.RadioReciever;
import com.crazygeeks.trippleyum.RadioService;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by mymac on 3/1/17.
 */

public class RadioFragment extends Fragment {

    private ImageView ivStartStream , ivStopStream;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radio_fragment, container, false);

        ivStartStream = (ImageView) view.findViewById(R.id.btStartStream);
        ivStopStream = (ImageView) view.findViewById(R.id.btStopStream);

        if (isMyServiceRunning(RadioService.class)){
            ivStopStream.setVisibility(View.VISIBLE);
            ivStartStream.setVisibility(View.INVISIBLE);
        }
        ivStartStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playRadio();
                ivStopStream.setVisibility(View.VISIBLE);
                ivStartStream.setVisibility(View.INVISIBLE);
            }
        });
        ivStopStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().stopService(new Intent(getActivity(), RadioService.class));
                ivStopStream.setVisibility(View.INVISIBLE);
                ivStartStream.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }
    public void playRadio(){
            Intent i=new Intent(getActivity(), RadioReciever.class);
            i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            getActivity().sendBroadcast(i);

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
