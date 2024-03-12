package com.example.firebaseemailauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
private static final String TAG="MainActivity";
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       firebaseAuth =FirebaseAuth.getInstance();

      EditText emailedit =findViewById(R.id.editTextTextEmailAddress);
     EditText passwordedit =findViewById(R.id.editTextTextPassword2);

     findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             String email=emailedit.getText().toString();
             String password=passwordedit.getText().toString();
             firebaseAuth.signInWithEmailAndPassword(email, password)
                     .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()) {
                                 // Sign in success, update UI with the signed-in user's information
                                 Log.d(TAG, "signInWithEmail:success");
                                 FirebaseUser user = firebaseAuth.getCurrentUser();
                                 updateUI(user);
                             } else {
                                 // If sign in fails, display a message to the user.
                                 Log.w(TAG, "signInWithEmail:failure", task.getException());
                                 Toast.makeText(MainActivity.this, "Authentication failed.",
                                         Toast.LENGTH_SHORT).show();
                                 updateUI(null);
                             }
                         }
                     });

         }
     });


       findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String email=emailedit.getText().toString();
               String password=passwordedit.getText().toString();


firebaseAuth.createUserWithEmailAndPassword(email,password)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                   Toast.makeText(MainActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
           }
       });
    }

    private void updateUI(FirebaseUser user) {
if(user!=null){

    Intent intent=new Intent(MainActivity.this,HomeActivity.class);
    startActivity(intent);
}
    }
}