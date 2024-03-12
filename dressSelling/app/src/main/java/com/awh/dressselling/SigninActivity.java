package com.awh.dressselling;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.awh.dressselling.databinding.ActivitySigninBinding;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SigninActivity extends AppCompatActivity {
    private static final String TAG = "eshop";
FirebaseAuth firebaseAuth;
EditText emailedit;
EditText passwordedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_DressSelling_FullScreen);
        super.onCreate(savedInstanceState);

        firebaseAuth=FirebaseAuth.getInstance();
        emailedit=findViewById(R.id.signin_emailvalue);
        passwordedit=findViewById(R.id.signin_passwordvalue);
        findViewById(R.id.signin_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


             String email =  emailedit.getText().toString();
             String password=passwordedit.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i(TAG, "singInWithEmail:success");
                        FirebaseUser user= firebaseAuth.getCurrentUser();
                            updateUI(user);
                        }else{
                            Log.w(TAG, "singInWithEmail:failure", task.getException());
                            Toast.makeText(SigninActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                }

        });

findViewById(R.id.signup_button).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(SigninActivity.this,SignupActivity.class);
        startActivity(intent);
    }
});

    }


    public  void updateUI(FirebaseUser user){
        Intent intent=new Intent(SigninActivity.this,MainActivity.class);
        startActivity(intent);
    }

}
