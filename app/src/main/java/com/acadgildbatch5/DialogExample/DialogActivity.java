package com.acadgildbatch5.DialogExample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 25-12-2016.
 */

public class DialogActivity extends FragmentActivity {

    Button dButton, alertFragment;
FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        alertFragment = (Button)findViewById(R.id.alertFragment);
        dButton =(Button)findViewById(R.id.dButton);

        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(DialogActivity.this)

                        .setIcon(R.drawable.greencheck)
                        .setTitle("Acadgild Dialog Title")
                        .setMessage("This is the message we want to show the user!")

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                                Toast.makeText(DialogActivity.this,"dButton clicked  POSITIVE",Toast.LENGTH_SHORT).show();
                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Toast.makeText(DialogActivity.this,"dButton clicked  Negative",Toast.LENGTH_SHORT).show();
                            }
                        })
                .show();

            }
        });


        alertFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDFragment alertFragment = new AlertDFragment();
                alertFragment.show(fm,"Alert Dialog fragment");
            }
        });
    }
}
