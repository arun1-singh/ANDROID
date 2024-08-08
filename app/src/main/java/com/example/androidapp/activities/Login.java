package com.example.androidapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText mEmail,mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    ProgressBar progressBar;


    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressbar);
        mCreateBtn = findViewById(R.id.createtxt);
        mLoginBtn= findViewById(R.id.loginBtn);
        fAuth = FirebaseAuth.getInstance();


        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required");
                }

                if (password.length() < 6){
                    mPassword.setError("Password must be >= 6 characters");
                    return;

                }
                progressBar.setVisibility(View.VISIBLE);
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else{

                            Toast.makeText(getApplicationContext(),"Error"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


    }
}