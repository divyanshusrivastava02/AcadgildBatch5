package com.acadgildbatch5.animationExample;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 07-01-2017.
 */

public class AnimationSample extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_sample);

        ImageView iv = (ImageView)findViewById(R.id.anim_image);
//        ((AnimationDrawable)iv.getBackground()).start();
//        Animation blinkAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
//        iv.startAnimation(blinkAnim);


//        ObjectAnimator animator= ObjectAnimator.ofFloat(iv,"rotationY",0.0f,360.0f);
        ObjectAnimator animator= ObjectAnimator.ofFloat(iv,"alpha",1.0f,0.25f,0.75f,0.15f,0.05f,0.0f);
        animator.setDuration(400);
        animator.setRepeatCount(100);
        animator.start();
    }
}
