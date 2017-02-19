package com.acadgildbatch5.WebService;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.acadgildbatch5.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Divyanshu on 15-01-2017.
 */

public class WebserviceActivity extends ListActivity {

    private ProgressDialog progressDialog;
//    private static String url = "https://api.myjson.com/bins/1fub9z";//
    private static String url = "http://api.themoviedb.org/3/tv/top_rated?api_key=8496be0b2149805afa458ab8ec27560c";

    private static final String TAG_RESULT = "results";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_VOTEAVG = "vote_average";

    JSONArray tvShow = null;
    ArrayList<HashMap<String,String>>  tvShowList;
    ListView lv;
    OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvShowList = new ArrayList<HashMap<String, String>>();
         client = new OkHttpClient();
        lv = getListView();
        new getMovideDetails().execute();
    }



    private class getMovideDetails extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(WebserviceActivity.this);
            progressDialog.setMessage("Bro I am downloadingg... plz wait....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i("My VALue 1","I m in 1");
            Log.d("My VALue 2","I m in 2");
            Log.v("My VALue 3","I m in 3");
            ServiceHandler sh = new ServiceHandler();
           String response =  sh.makeServiceCall(url);
//            String response="";
//            try {
//                 response =  run(url);
//            }catch (IOException e){
//                e.printStackTrace();
//            }




            try{
                if(response!=null){
                    JSONObject jsonObject = new JSONObject(response);
                    tvShow = jsonObject.getJSONArray(TAG_RESULT);

                    for(int i=0; i<tvShow.length() ; i++){
                        JSONObject c = tvShow.getJSONObject(i);

                        String id= c.getString(TAG_ID);
                        String name  =  c.getString(TAG_NAME);
                        String voteAvg = c.getString("vote_average");


                        HashMap<String,String > tvShows= new HashMap<String, String>();
                        tvShows.put(TAG_ID,id);
                        tvShows.put(TAG_NAME,name);
                        tvShows.put(TAG_VOTEAVG,voteAvg);

                        tvShowList.add(tvShows);
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();

            ListAdapter adapter = new SimpleAdapter(WebserviceActivity.this,tvShowList, R.layout.webservice_list_item,new String[]{TAG_ID,TAG_NAME,TAG_VOTEAVG},new int[]{R.id.no,R.id.name,R.id.voteAvg});
            setListAdapter(adapter);
        }
    }






    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
