package com.example.musicapp1;

import androidx.annotation.LongDef;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class UserProfile extends AppCompatActivity {
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
    TextView name,email ;
    Button signOutBtn ;
    ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        signOutBtn = findViewById(R.id.signout);
        profilePic = findViewById(R.id.profilepic);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            //shows the account name, email and profile picture
            String userName = account.getDisplayName();
            String userEmail = account.getEmail();
            Uri userProfilePic = account.getPhotoUrl();
            name.setText(userName);
            email.setText(userEmail);

            if (userProfilePic== null){
                profilePic.setImageResource(R.drawable.ic_baseline_person_outline_24);
            }else{
                Picasso.get().load(userProfilePic).into(profilePic);
            }



//set the signout btn to signout
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });



    }else{
            name.setText("admin");
            email.setText("admin@gmail.com");
            profilePic.setImageResource(R.drawable.ic_baseline_person_outline_24);


            signOutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signOut();
                }
            });
        }
   }
//code for signing out and going back to the login page
    private void signOut() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(UserProfile.this);
        if(account!=null) {
            //the code below signs the user out of their google account
            Task<Void> voidTask = googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(Task<Void> task) {
                    finish();
                    startActivity(new Intent(UserProfile.this, activity_login.class));
                }
            });
        }else{
            Intent intent = new Intent(UserProfile.this,activity_login.class);
            finish();
            startActivity(intent);
        }
    }
}