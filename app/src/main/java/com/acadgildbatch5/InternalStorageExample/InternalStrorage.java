package com.acadgildbatch5.InternalStorageExample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.acadgildbatch5.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Divyanshu on 25-12-2016.
 */

public class InternalStrorage extends Activity {

    EditText txtmsg;

    static final int READ_BLOCK_SIZE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internalstorage);
        txtmsg = (EditText)findViewById(R.id.edit1);
    }



    public void WriteBtn(View v){
        try{
            FileOutputStream fileOutputStream = openFileOutput("acadgild.txt",MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(txtmsg.getText().toString());
            outputStreamWriter.close();

            Toast.makeText(getBaseContext(),"File send successfully",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void ReadFile(View view){
        try{

            FileInputStream fileInputStream = openFileInput("acadgild.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            char[] inputBuffer  = new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while((charRead =inputStreamReader.read(inputBuffer))>0){
                String readString = String.copyValueOf(inputBuffer,0,charRead);
                s +=readString;
            }

            inputStreamReader.close();
            Toast.makeText(getBaseContext(),"File read successfully" + s,Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
