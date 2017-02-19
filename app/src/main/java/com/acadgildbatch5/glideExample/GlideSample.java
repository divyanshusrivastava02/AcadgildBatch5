package com.acadgildbatch5.glideExample;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ImageView;

import com.acadgildbatch5.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by Divyanshu on 12-02-2017.
 */

public class GlideSample  extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_glide);

        ImageView imageView = (ImageView) findViewById(R.id.iv_glid);

        Glide.with(this)
                // 画像URL
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4gc0UqHRJi7R1bnKl96wQv95eTeEtfPfYiF_QTdzEl_o365LjFA")
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String s, Target<GlideDrawable> glideDrawableTarget, boolean b) {
                        Log.d("Glide", "Error in Glide listener");
                        if (e != null) {
                            e.printStackTrace();
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> glideDrawableTarget, boolean b, boolean b2) {
                        return false;
                    }
                })
                .override(600, 600)
                .placeholder(android.R.drawable.ic_menu_call)
                .error(android.R.drawable.ic_delete)
                .dontAnimate()
                .into(imageView);
    }
    }



