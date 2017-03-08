package com.crazygeeks.trippleyum.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.crazygeeks.trippleyum.Anim.AppAnimation;
import com.crazygeeks.trippleyum.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        CircleImageView userIV = (CircleImageView) findViewById(R.id.user_image);
        AppAnimation.animateCircularize(userIV);
        LinearLayout lin1 = (LinearLayout) findViewById(R.id.lin1);
        Animation animation = AnimationUtils.loadAnimation(this,  R.anim.push_up_list);
        LinearLayout lin2 = (LinearLayout) findViewById(R.id.lin2);
        lin1.startAnimation(animation);
        lin2.startAnimation(animation);
    }
}
