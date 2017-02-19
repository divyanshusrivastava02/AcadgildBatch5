package com.acadgildbatch5.ExternalStrorageExample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acadgildbatch5.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Divyanshu on 25-12-2016.
 */

public class ExternalStrorage extends Activity{
    EditText editTextFileName, editTextDate;
    Button saveButton, readButton;

    File mFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_externalstorage);

        editTextFileName = (EditText)findViewById(R.id.editText1);
        editTextDate = (EditText)findViewById(R.id.editText2);
        saveButton = (Button) findViewById(R.id.savefile);
        readButton = (Button) findViewById(R.id.readfile);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = editTextFileName.getText().toString();
                String date = editTextDate.getText().toString();

                FileOutputStream fos;
                try{
                    File myFile = new File(Environment.getExternalStorageDirectory()+"/"+fileName+".txt");
                    myFile = myFile;
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter =  new OutputStreamWriter(fOut);
                    myOutWriter.append(date);
                    myOutWriter.close();
                    fOut.close();

                    Toast.makeText(getApplicationContext(),fileName+ " Saved",Toast.LENGTH_SHORT).show();



                }catch (FileNotFoundException e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error FileNotFoundException",Toast.LENGTH_SHORT).show();
                }
                catch (IOException ioe){
                    ioe.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error IOException",Toast.LENGTH_SHORT).show();
                }
            }
        });


        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fileName = editTextFileName.getText().toString();
                StringBuffer stringBuffer = new StringBuffer();
                String aDateFlow="";
                String aBuffer="";

                try{
                    File myFile = new File("/sdcard/"+fileName);
                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));

                    while ((aDateFlow= myReader.readLine())!=null){
                        aBuffer +=aDateFlow + "\n";
                    }
                    myReader.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),aBuffer,Toast.LENGTH_SHORT).show();

            }
        });
    }
}
