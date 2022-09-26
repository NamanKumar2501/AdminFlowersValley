package com.example.adminflowersvalley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class UserLoginActivity extends AppCompatActivity {

    private static final String TAG = "";
    private AppCompatEditText etMobile;
    private AppCompatButton loginbtn;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        etMobile=findViewById(R.id.mobile);
        loginbtn=findViewById(R.id.sand_otp_btn);


        mAuth = FirebaseAuth.getInstance();
        mAuth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(false);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etMobile.getText().toString().trim().equalsIgnoreCase("")) {
                    Snackbar.make(loginbtn, "Please Enter Mobile Number.", Snackbar.LENGTH_SHORT).show();
                    etMobile.requestFocus();
                } else if (etMobile.getText().toString().length() != 10) {
                    Snackbar.make(loginbtn, "Please Enter Valid Mobile Number.", Snackbar.LENGTH_SHORT).show();
                    etMobile.requestFocus();
                } else {
                    String mobileNumber = "+91" +etMobile.getText().toString().trim();
                    sendOTP(mobileNumber);
                }
            }
        });
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Snackbar.make(loginbtn, "OTP Not Sent.", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Log.i(TAG, "onCodeSent: " + s);

                Bundle bundle = new Bundle();
                bundle.putString("token", s);

                Intent intent=new Intent(UserLoginActivity.this,VerifyOtpActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        };


    }

    private void sendOTP(String mobile) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mobile)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}

