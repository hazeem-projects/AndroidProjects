package com.awh.dressselling;

import android.content.Intent;
import android.os.Bundle;

import com.awh.dressselling.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.awh.dressselling.databinding.ActivitySignupBinding;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private EditText nameEdit, emailEdit, passwordEdit, retypeEdit;

    private AppBarConfiguration appBarConfiguration;
    private ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
setTheme(R.style.Theme_DressSelling_FullScreen);
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        nameEdit = findViewById(R.id.signup_name);
        emailEdit = findViewById(R.id.signup_email);
        passwordEdit = findViewById(R.id.signup_password);
        retypeEdit = findViewById(R.id.signup_retypepassword);


        findViewById(R.id.signup_signin_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.signup_signup_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdit.getText().toString();
                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                String reTypePass = retypeEdit.getText().toString();

                if (password.length() >= 8) {

                    if (password.equals(reTypePass)) {

                        view.setEnabled(false);

                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {

                                            FirebaseUser firebaseUser = task.getResult().getUser();
                                            User user = new User(firebaseUser.getUid(), name, firebaseUser.getEmail());
                                            firebaseFirestore.collection("users").add(user)
                                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                                            if (task.isSuccessful()){
                                                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                                                startActivity(intent);
                                                                finish();
                                                            }else {
                                                                Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });

                                        } else {
                                            Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        view.setEnabled(true);
                                    }
                                });


                    } else {
                        retypeEdit.setError("Password doesn't match!");
                    }

                } else {
                    passwordEdit.setError("Password must contain at least 8 characters");
                }
            }
        });


    }


}