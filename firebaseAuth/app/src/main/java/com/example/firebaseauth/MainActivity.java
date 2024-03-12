package com.example.firebaseauth;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.GetServiceRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;


GoogleSignInOptions gsc;
private SignInClient gsi;

private  final ActivityResultLauncher<IntentSenderRequest> signLauncher=registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(), new ActivityResultCallback<ActivityResult>() {
    @Override
    public void onActivityResult(ActivityResult result) {
        Toast.makeText(getApplicationContext(),result.getResultCode(),Toast.LENGTH_SHORT).show();
    }
});

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gsi= Identity.getSignInClient(getApplicationContext());

        firebaseAuth= FirebaseAuth.getInstance();
        findViewById(R.id.btnGoogle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           signin();
            }
        });

    }
    private  void signin(){

      GetSignInIntentRequest signInIntentRequest = GetSignInIntentRequest.builder().setServerClientId(getString(R.string.web_client_id)).build();
       Task<PendingIntent> signInIntent =gsi.getSignInIntent(signInIntentRequest);

       signInIntent.addOnSuccessListener(new OnSuccessListener<PendingIntent>() {
           @Override
           public void onSuccess(PendingIntent pendingIntent) {

               launchSignin(pendingIntent);
            }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
           }
       });

    }
    private void launchSignin(PendingIntent pendingIntent){
              IntentSenderRequest intentSenderRequest   = new IntentSenderRequest.Builder(pendingIntent).build();
signLauncher.launch(intentSenderRequest);
    }
}