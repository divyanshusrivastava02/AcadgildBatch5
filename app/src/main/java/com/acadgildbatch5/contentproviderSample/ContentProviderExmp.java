package com.acadgildbatch5.contentproviderSample;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.PskKeyManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.acadgildbatch5.R;
import com.acadgildbatch5.asyntaskExample.AsyncTaskSample;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Divyanshu on 14-01-2017.
 */

public class ContentProviderExmp extends Activity  implements ActivityCompat.OnRequestPermissionsResultCallback {


    SimpleCursorAdapter mAdapter;
    MatrixCursor mMatrixCursor;
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSSION=124;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentprovider_list);

        mMatrixCursor = new MatrixCursor(new String[]{"_id","name","photo","details"});

        mAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.activity_contentprovider_view,null,new String[]{"name","photo","details"},
                new int[]{R.id.tv_name,R.id.iv_photo,R.id.tv_details},0);


        ListView listContacts = (ListView)findViewById(R.id.list_contacts);
        listContacts.setAdapter(mAdapter);

        askForPermissions();
    }



    private class ListViewContactLoader extends AsyncTask<Void,Void,Cursor> {

        @Override
        protected Cursor doInBackground(Void... params) {

            Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;

            Cursor contactCursor = getContentResolver().query(contactsUri,null,null,null,ContactsContract.Contacts.DISPLAY_NAME + " ASC ");

            if(contactCursor.moveToNext()){
                do{
                    long contactId = contactCursor.getLong(contactCursor.getColumnIndex("_ID"));
                    Uri dataUri = ContactsContract.Data.CONTENT_URI;
                    Cursor dataCursor = getContentResolver().query(dataUri,null,ContactsContract.Data.CONTACT_ID+"="+contactId,null,null);

                    String displayName="";
                    String nickName="";
                    String homePhone="";
                    String mobilePhone="";
                    String workPhone="";
                    String photoPath = ""+R.drawable.restart;
                    byte[] photoBytes = null;
                    String homeEmail="";



                    if(dataCursor.moveToFirst()){
                        displayName = dataCursor.getString(dataCursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                        do{
                            if(dataCursor.getString(dataCursor.getColumnIndex("mimetype")).equals(ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE));
                            nickName = dataCursor.getString(dataCursor.getColumnIndex("data1"));


                            if(dataCursor.getString(dataCursor.getColumnIndex("mimetype")).equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)){
                                switch (dataCursor.getInt(dataCursor.getColumnIndex("data2"))){
                                    case ContactsContract.CommonDataKinds.Phone.TYPE_HOME:
                                        homePhone = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                                        break;

                                    case ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE:
                                        mobilePhone = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                                        break;

                                    case ContactsContract.CommonDataKinds.Phone.TYPE_WORK:
                                        workPhone = dataCursor.getString(dataCursor.getColumnIndex("data1"));
                                        break;
                                }
                            }

                            if(dataCursor.getString(dataCursor.getColumnIndex("mimetype")).equals(ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)){
                                photoBytes = dataCursor.getBlob(dataCursor.getColumnIndex("data15"));

                                if(photoBytes != null){
                                    Bitmap bitmap = BitmapFactory.decodeByteArray(photoBytes,0,photoBytes.length);
                                    File cacheDirectory = getBaseContext().getCacheDir();
                                    File tempFile = new File(cacheDirectory.getPath()+"wpta_"+contactId+".png");

                                    try{
                                        FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
                                        bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                                        fileOutputStream.flush();
                                        fileOutputStream.close();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    photoPath = tempFile.getPath();
                                }

                            }



                        }while (dataCursor.moveToNext());


                        String details="";

                        if(homePhone !=null && homePhone.equals(""))
                            details = "HomePhone : "+ homePhone+"\n";

                        if(mobilePhone !=null && mobilePhone.equals(""))
                            details = "mobilePhone : "+ mobilePhone+"\n";

                        if(workPhone !=null && workPhone.equals(""))
                            details = "workPhone : "+ workPhone+"\n";
                        if(nickName !=null && nickName.equals(""))
                            details = "nickName : "+ nickName+"\n";

                        mMatrixCursor.addRow(new Object[]{Long.toString(contactId),displayName,R.drawable.restart,details});

                    }

                }while (contactCursor.moveToNext());
            }
            return mMatrixCursor;
        }


        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            mAdapter.swapCursor(cursor);
        }
    }


    private void askForPermissions(){
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String>permissionList  =new ArrayList<String>();

        if(!addPermission(permissionList, Manifest.permission.READ_CONTACTS))
            permissionsNeeded.add("READ_CONTACTS");
//        if(!addPermission(permissionList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
//            permissionsNeeded.add("WRITE_EXTERNAL_STORAGE");


        if(permissionList.size()>0){
            if(permissionsNeeded.size()>0){


                String messsage = "You need to grant access to "+permissionsNeeded.get(0);
                for(int i=0; i <permissionsNeeded.size();i++){
                    messsage =messsage+" , "+permissionsNeeded.get(i);
                    requestPermissions(permissionList.toArray(new String[permissionList.size()]),REQUEST_CODE_ASK_MULTIPLE_PERMISSSION);
                    //return;
                }
                requestPermissions(permissionList.toArray(new String[permissionList.size()]),REQUEST_CODE_ASK_MULTIPLE_PERMISSSION);
                //return;
            }


        }
        ListViewContactLoader listViewContactLoader = new ListViewContactLoader();
        listViewContactLoader.execute();

    }

    private boolean addPermission(List<String> permissionList, String permission){
        if(ContextCompat.checkSelfPermission(ContentProviderExmp.this,permission)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(permission);
            if(!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode== REQUEST_CODE_ASK_MULTIPLE_PERMISSSION){
            Map<String,Integer> parms = new HashMap<String,Integer>();

            parms.put(Manifest.permission.READ_CONTACTS,PackageManager.PERMISSION_GRANTED);
            parms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE,PackageManager.PERMISSION_GRANTED);

            for(int i = 0 ; i<permissions.length;i++){
                parms.put(permissions[i],grantResults[i]);
                if(parms.get(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED &&
                parms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                    ListViewContactLoader ListViewContactLoader = new ListViewContactLoader();
                    ListViewContactLoader.execute();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Some permission is denied",Toast.LENGTH_SHORT).show();
                }
            }

        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
}
