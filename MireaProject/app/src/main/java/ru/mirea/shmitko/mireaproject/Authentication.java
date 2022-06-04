package ru.mirea.shmitko.mireaproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication
        extends AppCompatActivity
        implements View.OnClickListener {
    private static final String TAG = Authentication.class.getSimpleName();
    private EditText mEmailField;
    private EditText mPasswordField;
    // START declare_auth
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        // Views
        mEmailField = findViewById(R.id.txtEditEmail);
        mPasswordField = findViewById(R.id.txtEditPassword);
        // Buttons
        findViewById(R.id.btnSignIn).setOnClickListener(this);
        findViewById(R.id.btnCreateAccount).setOnClickListener(this);
        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnCreateAccount) {
            createAccount(
                    mEmailField.getText().toString(),
                    mPasswordField.getText().toString()
            );
        } else if (i == R.id.btnSignIn) {
            signIn(
                    mEmailField.getText().toString(),
                    mPasswordField.getText().toString()
            );
        }
    }

    private void sendEmailVerification() {
        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification();
        // [END send_email_verification]
    }

    private boolean validateForm() {
        boolean valid = true;
        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }
        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }
        return valid;
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent main = new Intent(
                                this,
                                MainActivity.class
                        );
                        startActivity(main);
                        finish();
                    } else {
                        Log.w(
                                TAG,
                                "signInWithEmail:failure",
                                task.getException()
                        );
                        Toast.makeText(
                                Authentication.this,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(
                                TAG,
                                "createUserWithEmail:failure",
                                task.getException()
                        );
                        Toast.makeText(
                                Authentication.this,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }
}
