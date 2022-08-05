package com.example.musicapp1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class activity_login extends AppCompatActivity {
    TextView username ;
    TextView password ;
    Button loginbtn ;
    ImageView google_img;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        google_img = findViewById(R.id.googleimg);
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        //if an account is logged in, instead of going the the sign in page, go straight to home
        if(account != null){
            HomeActivty();
        }
        //set the google image onclclick to sign in
        google_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });
    }

    private void signIn() {

        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == 100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                HomeActivty();
            } catch (ApiException e) {
                Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void HomeActivty() {

        finish();
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }


    public void gotomainactivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
            //if the username and password is correct, it will login
            startActivity(intent);
            Log.d("penis","LOGIN");
            Toast.makeText(activity_login.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
        }else
            //if the username or password is incorrect, it will not login
            Log.d("penis","no LOGIN");
        Toast.makeText(activity_login.this, "USERNAME OR PASSWORD IS INCORRECT", Toast.LENGTH_SHORT).show();
    }







}
