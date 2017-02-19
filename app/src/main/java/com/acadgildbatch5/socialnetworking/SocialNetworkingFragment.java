package com.acadgildbatch5.socialnetworking;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acadgildbatch5.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by Divyanshu on 04-02-2017.
 */

public class SocialNetworkingFragment extends Fragment implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    LoginButton loginButton;
    CallbackManager callbackManager;
    TextView first_name, last_name;
    GoogleApiClient mGoogleApiClient;
    SignInButton  signInButton;
    private static final int RC_SIGN_IN = 9001;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_socialnetworking,container,false);
        first_name = (TextView)view.findViewById(R.id.first_name);
        last_name = (TextView)view.findViewById(R.id.last_name);


        loginButton = (LoginButton)view.findViewById(R.id.login_button);
        loginButton.setFragment(this);
        loginButton.setOnClickListener(this);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .enableAutoManage((FragmentActivity) getActivity(),this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signInButton = (SignInButton)view.findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setScopes(gso.getScopeArray());
        signInButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();

        try{
            PackageInfo info = getActivity().getPackageManager().getPackageInfo("com.acadgildbatch5ind", PackageManager.GET_SIGNATURES);
            for(Signature signature:info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("HASH KEY:::::" , Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }

    private Bundle getFacebookDate(JSONObject object){
        Bundle bundle = null;
        try{

            progressDialog.dismiss();
            bundle = new Bundle();
            String id  = object .getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "picture?width=200&height=150");
                Log.i("Profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
            }catch (MalformedURLException e){
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook",id);
            if(object.has("first_name"))
                bundle.putString("first_name",object.getString("first_name"));

            if(object.has("last_name"))
                bundle.putString("last_name",object.getString("last_name"));

            if(object.has("email"))
                bundle.putString("email",object.getString("email"));

            if(object.has("birthday"))
                bundle.putString("birthday",object.getString("birthday"));
            if(object.has("gender"))
                bundle.putString("gender",object.getString("gender"));

            if(object.has("location"))
                bundle.putString("location",object.getJSONObject("location").getString("name"));

        }catch (Exception e){
            e.printStackTrace();
        }
        return  bundle;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            GoogleSignInResult result= Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }else {
            callbackManager.onActivityResult(requestCode,resultCode,data);
        }
    }

    private void handleSignInResult(GoogleSignInResult result){
        if(result.isSuccess()){
            GoogleSignInAccount acct = result.getSignInAccount();
            first_name.setText("Display Name: "+acct.getDisplayName());
            last_name.setText("Given Name: "+acct.getGivenName());
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sign_in_button:
                signIN();
                break;

            case R.id.login_button:
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email","public_profile"));
                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setMessage("LOGING TO FACEBOOK");
                        progressDialog.show();

                        String accessToken = loginResult.getAccessToken().getToken();
                        Log.i("accessToken",accessToken);

                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Bundle bFacekoook = getFacebookDate(object);
                                first_name.setText("First Name "+ bFacekoook.getString("first_name"));
                                last_name.setText("Last Name"+bFacekoook.getString("last_name"));
                            }
                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields","id, first_name, last_name");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {

                        System.out.println("Cancel facebook");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        System.out.println("Error occured facebook");
                    }
                });
                break;
        }

    }

    private void signIN(){
        Intent signInInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInInIntent,RC_SIGN_IN);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        mGoogleApiClient.disconnect();
    }
}
