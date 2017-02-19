package com.acadgildbatch5;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Divyanshu on 20-11-2016.
 */

public class AcadgildActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback{

    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    String uservalueInserted;
    EditText userData;
    Button submit;
    Button implictClick;
    Button cameraBTN;
    ImageView imageview;

    public final static int  CAMERA_CALL = 200;

    int i =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acadgild);

        userData = (EditText)findViewById(R.id.edit1);
        submit = (Button)findViewById(R.id.button1);
        cameraBTN = (Button)findViewById(R.id.cameraButton);
        imageview = (ImageView)findViewById(R.id.imageview);



        implictClick = (Button)findViewById(R.id.implicitButton);
        implictClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent balaIntent  = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.acadgild.com"));
                startActivity(balaIntent);
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uservalueInserted = userData.getText().toString();

                Intent openActivty = new Intent(AcadgildActivity.this,SecondActivity.class);
                openActivty.putExtra("USERINPUT",uservalueInserted);
                openActivty.putExtra("USERINPUT","yepeeeee");
                openActivty.putExtra("isValid",true);
                openActivty.putExtra("SALaRy",25000);

                Bundle dataBundle = new Bundle();
                dataBundle.putString("USERINPUT","May");
//                dataBundle.putInt("Age",28);
                openActivty.putExtras(dataBundle);


                startActivity(openActivty);
            }
        });



        cameraBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    askForPermissions();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(AcadgildActivity.this,"ERROR OCCURED Please contact admin",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_CALL && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imageview.setImageBitmap(bitmap);
        }

    }



    //###################################################################################################################################3


    private void askForPermissions() {
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.CAMERA))
            permissionsNeeded.add("CAMERA");
        if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsNeeded.add("WRITE_EXTERNAL_STORAGE");

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
//                showMessageOKCancel(message,
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
//                                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
//                            }
//                        });
                return;
            }
            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return;
        }

        Intent cameraInt = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraInt,CAMERA_CALL);
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(AcadgildActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

//

        if (requestCode == REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS) {
            Map<String, Integer> perms = new HashMap<String, Integer>();
            // Initial
            perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
            perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
            // Fill with results
            for (int i = 0; i < permissions.length; i++)
                perms.put(permissions[i], grantResults[i]);
            // Check for ACCESS_FINE_LOCATION
            if (perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                // All Permissions Granted
                Intent cameraInt = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraInt,CAMERA_CALL);
            } else {
                // Permission Denied
                Toast.makeText(AcadgildActivity.this, "Some Permission is Denied", Toast.LENGTH_SHORT)
                        .show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //#####################################################################################################################################


}
