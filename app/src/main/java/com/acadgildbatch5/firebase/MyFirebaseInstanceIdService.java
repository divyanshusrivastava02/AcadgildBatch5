package com.acadgildbatch5.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Divyanshu on 05-02-2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFireabeIDSerivce";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshToken = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG,"Refreshed toke:"+refreshToken);
       // sendRegistrationToken(refreshToken);
    }

    private void sendRegistrationToken(String token){
        // THIS IS TO PASS YOUR SERVER
    }
}
