package com.example.streetmart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class registerActivity extends AppCompatActivity {
    EditText inputEmail,password,confirmPassW,userName;
    Button register;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        TextView btn = findViewById(R.id.LoginTxtBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registerActivity.this,MainActivity.class));
            }
        });
        inputEmail = findViewById(R.id.UserName1);
        password = findViewById(R.id.Password1);
        userName = findViewById(R.id.UserN);
        confirmPassW = findViewById(R.id.Password2);
        register = findViewById(R.id.registerBtn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });
    }
    private void PerformAuth() {
        String email = inputEmail.getText().toString();
        String UserName = userName.getText().toString();
        String password1 = password.getText().toString();
        String password2 = confirmPassW.getText().toString();

        if(!email.matches(emailPattern)){
            inputEmail.setError("Enter Valid Email");
            inputEmail.requestFocus();
        }
        else if(password1.isEmpty() || password1.length() <6){
            password.setError("Enter Proper Password");
        }
        else if(!password1.equals(password2)){
            confirmPassW.setError("Password does not match at both fields");
        }
        else{
            progressDialog.setMessage("Please wait while registration..");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(registerActivity.this, "Registration Successful ☺☺", Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(registerActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(registerActivity.this,secondActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



}