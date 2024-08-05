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

public class MSc_Registration extends AppCompatActivity {

    EditText inputEmail,inputPassword, inputCConformPassword;
    Button reg;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msc_registration);
        inputEmail = findViewById(R.id.Reg_email);
        inputPassword = findViewById(R.id.Reg_pass);
        inputCConformPassword = findViewById(R.id.Reconfirm_pass);

        reg = findViewById(R.id.Reg_but);

        progressDialog=new ProgressDialog(this);

        mAuth= FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }

            private void PerformAuth() {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                String confirmpassword = inputCConformPassword.getText().toString();

                if(!email.matches(emailPattern)){
                    inputEmail.setError("Enter Context Email");
                }else if(password.isEmpty() || password.length()<6){
                    inputEmail.setError("Enter Proper Password");
                }else if(!password.equals(confirmpassword)){
                    inputCConformPassword.setError("Password not match both field.");
                }else{
                    progressDialog.setMessage("Please wait while Registration......");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(email, password).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){
                                        progressDialog.dismiss();
                                        sendUserToNextActivity();
                                        Toast.makeText(MSc_Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    }else{
                                        progressDialog.dismiss();
                                        Toast.makeText(MSc_Registration.this, ""+task.getException(), Toast.LENGTH_SHORT).show();

                                    }
                                }


                            });

                }
            }
            private void sendUserToNextActivity() {
                Intent intent = new Intent(MSc_Registration.this, MSc_Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void Login_MSc(View view) {
        Intent intent =new Intent(this, MSc_Login.class);
        startActivity(intent);
    }
}