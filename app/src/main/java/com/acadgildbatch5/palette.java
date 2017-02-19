package com.acadgildbatch5;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Divyanshu on 31-12-2016.
 */

public class palette extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        final LinearLayout layout = (LinearLayout)findViewById(R.id.mainLL);
        final TextView t1 = (TextView)findViewById(R.id.title);
        final TextView t2 = (TextView)findViewById(R.id.body_text);


        Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.moon);

        Palette.from(image).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vSwatch = palette.getVibrantSwatch();
                if(vSwatch!=null){
                    layout.setBackgroundColor(vSwatch.getRgb());
                    t1.setTextColor(vSwatch.getTitleTextColor());
                    t2.setTextColor(vSwatch.getBodyTextColor());
                }
            }
        });
    }
}
