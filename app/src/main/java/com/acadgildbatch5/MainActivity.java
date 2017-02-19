package com.acadgildbatch5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.animation.AlphaAnimation;

public class MainActivity extends AppCompatActivity {

    String vdlkf ="hello";
    int i =10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this,"on Create called",Toast.LENGTH_SHORT).show();

        Log.v("ACADGILD ","I am on acticty main and pree the on create method");
    }


    public void onClickCalled(View view){


        System.out.println(" Value of i"+i);
        int sum = i + 50;
        Log.v("ACADGILD ","I am on cick"+sum);

        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(MainActivity.this,"On start called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(MainActivity.this,"On resume called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(MainActivity.this,"on Restart called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(MainActivity.this,"onPause called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(MainActivity.this,"onStop called",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(MainActivity.this,"oonDestroy called",Toast.LENGTH_SHORT).show();
    }
}
