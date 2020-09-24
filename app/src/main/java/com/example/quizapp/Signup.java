package com.example.quizapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

import static java.lang.Thread.sleep;

public class Signup extends AppCompatActivity {
    LoginButton fbbutton;
    SignInButton signInButton;
    CircleImageView imageView;
    TextView name,signupemil;
    CallbackManager callbackManager;
    String first_name;
    String last_name;
    String email;
    String img_url;
    String gender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        imageView=findViewById(R.id.circleImageView);
        name=findViewById(R.id.username);
        signupemil=findViewById(R.id.email_signup);
        fbbutton=findViewById(R.id.login_button);
        // Set the dimensions of the sign-in button.
         signInButton= findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        //findViewById(R.id.sign_in_button).setOnClickListener(Signup.this);
        callbackManager = CallbackManager.Factory.create();
        fbbutton.setReadPermissions(Arrays.asList("email","public_profile"));
        checkloginUser();
        fbbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    AccessTokenTracker accessTokenTracker=new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
          if(currentAccessToken==null) {
              name.setText("");
              imageView.setImageResource(0);
              Toast.makeText(Signup.this, "user has logout", Toast.LENGTH_SHORT).show();
          }else
              loaduserdata(currentAccessToken);
        }
    };
    private  void loaduserdata(AccessToken accessToken){
        GraphRequest graphRequest=GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                   // gender=object.getString("gender");
                    first_name=object.getString("first_name");
                      last_name=object.getString("last_name");
                     //middle_name=object.getString("middle_name");
                     email=object.getString("email");
                     String id=object.getString("id");
                    img_url="https://graph.facebook.com/"+id+"/picture?type=large&width=720&height=720";
                    name.setText(first_name.toUpperCase()+"  "+last_name.toUpperCase());
                    signupemil.setText(email);
                    RequestOptions requestOptions=new RequestOptions();
                    //requestOptions.dontAnimate();
                    Glide.with(Signup.this).load(img_url).into(imageView);
                    fbbutton.setVisibility(View.GONE);
                    signInButton.setVisibility(View.GONE);
                   Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent intent=new Intent(Signup.this, Quiz_descrption.class);
                           // intent.putExtra("f_name",first_name);
                           // intent.putExtra("gender",gender);
                            //intent.putExtra("l_name",last_name);
                            //intent.putExtra("img_url",img_url);
                            //intent.putExtra("email",email);
                            startActivity(intent);
                            finish();
                        }
                    },5000);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameter=new Bundle();
        parameter.putString("fields","first_name,last_name,email,id,gender");
        graphRequest.setParameters(parameter);
        graphRequest.executeAsync();
    }
    public void checkloginUser(){
            if(AccessToken.getCurrentAccessToken()!=null)
                loaduserdata(AccessToken.getCurrentAccessToken());
    }
    /*private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }
   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
   /* @Override
    /*public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            // ...
        }
    }
   /* private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }*/
}