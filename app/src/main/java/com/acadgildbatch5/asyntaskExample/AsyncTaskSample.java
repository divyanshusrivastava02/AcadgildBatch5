package com.acadgildbatch5.asyntaskExample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.acadgildbatch5.R;

import android.os.Handler;
/**
 * Created by Divyanshu on 07-01-2017.
 */

public class AsyncTaskSample extends Activity{
    ImageView imageView;
    ProgressBar mProgressBar;
    int mDelay = 500;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        imageView = (ImageView)findViewById(R.id.imageView);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);

        mHandler = new Handler();

        Button button = (Button)findViewById(R.id.loadBTN);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new loadIconTask().execute(R.drawable.moon);
                new Thread(new TaskUpadate()).start();
            }
        });
    }




    class loadIconTask extends AsyncTask<Integer,Integer, Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Integer... params) {
           final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),params[0]);
            for (int i =1; i<11;i++){
                sleep();
                publishProgress(i*10);
            }


//            AsyncTaskSample.this.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    imageView.setImageBitmap(bitmap);
//                }
//            });
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mProgressBar.setVisibility(View.INVISIBLE);
            imageView.setImageBitmap(bitmap);
        }

        public void sleep(){
            try {
                Thread.sleep(mDelay);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }





    class TaskUpadate implements Runnable{

        @Override
        public void run() {
            for (int i=0; i<=100 ; i++){
                final int val = i;
                try{
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mProgressBar.setProgress(val);
                    }
                });
            }
        }
    }


}
