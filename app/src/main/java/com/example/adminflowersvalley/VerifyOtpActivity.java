package com.example.adminflowersvalley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyOtpActivity extends AppCompatActivity {

    private static final String TAG = "VerifyOtpActivity";
    String token;
    private MaterialButton btnVerify;
    private AppCompatEditText etOtp;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            token = bundle.getString("token");
        }

        btnVerify = findViewById(R.id.verify_otp_btn);
        etOtp = findViewById(R.id.otp_view);

        mAuth = FirebaseAuth.getInstance();

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etOtp.getText().toString().trim().equalsIgnoreCase("")) {
                    Snackbar.make(btnVerify, "Please Enter OTP.", Snackbar.LENGTH_SHORT).show();
                    etOtp.requestFocus();
                } else if (etOtp.getText().toString().length() != 6) {
                    Snackbar.make(btnVerify, "Please Enter 6 Digit OTP.", Snackbar.LENGTH_SHORT).show();
                    etOtp.requestFocus();
                } else {
                    String otp = etOtp.getText().toString().trim();
                    verifyOtp(otp, token);
                }
            }
        });


    }

    private void verifyOtp(String otp, String token) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(token, otp);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            Intent intent=new Intent(VerifyOtpActivity.this,MainActivity.class);
                            startActivity(intent);




                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(VerifyOtpActivity.this, "Invalid OTP.", Toast.LENGTH_SHORT).show();

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}
