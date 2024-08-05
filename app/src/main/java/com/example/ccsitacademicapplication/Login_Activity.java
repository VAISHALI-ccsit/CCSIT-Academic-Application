package com.example.ccsitacademicapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activity extends AppCompatActivity {

    EditText inputEmail,inputPassword;
    Button btnLogin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail=findViewById(R.id.login_username);
        inputPassword=findViewById(R.id.login_password);

        btnLogin = findViewById(R.id.login_button);

        progressDialog=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();

        if(user!=null){
            //String userID = FirebaseAuth.getInstance().getCurrentUser().getUid()
            //AllDataModelin_Prinicipal alldata_principal = new AllDataModelin_Prinicipal(userID,"BCA", "MCA", "B.Tech", "M.Tech", "B.Sc", "M.Sc.");
            //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            //databaseReference.child("All Data").child(userID).setValue(alldata_principal);//.setValue("Notification");
           // databaseReference.child("All Data").child("BCA").child("Notification");


        }else {
            Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show();
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (!email.matches(emailPattern)) {
            inputEmail.setError("Enter Context Email");
        } else if (password.isEmpty() || password.length() < 6) {
            inputEmail.setError("Enter Proper Password");
        } else {
            progressDialog.setMessage("Please wait while Login......");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToActivity();
                        Toast.makeText(Login_Activity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(Login_Activity.this, " "+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToActivity() {
        Intent intent = new Intent(Login_Activity.this, Principal_Dashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void Register(View view) {
        Intent intent = new Intent(Login_Activity.this, Register_Principal.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Choose_Page.class);
        startActivity(intent);
        super.onBackPressed();
    }
}