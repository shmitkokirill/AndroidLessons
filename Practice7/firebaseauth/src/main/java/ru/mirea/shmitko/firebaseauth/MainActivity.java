package ru.mirea.shmitko.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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


public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mStatusTextView;
    private TextView mDetailTextView;
    private EditText mEmailField;
    private EditText mPasswordField;
    // START declare_auth
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Views
        mStatusTextView = findViewById(R.id.txtViewStatus);
        mDetailTextView = findViewById(R.id.txtViewDetail);
        mEmailField = findViewById(R.id.txtEditEmail);
        mPasswordField = findViewById(R.id.txtEditPassword);
        // Buttons
        findViewById(R.id.btnSignIn).setOnClickListener(this);
        findViewById(R.id.btnCreateAccount).setOnClickListener(this);
        findViewById(R.id.btnLogOut).setOnClickListener(this);
        findViewById(R.id.btnVerify).setOnClickListener(this);
        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
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
        } else if (i == R.id.btnLogOut) {
            signOut();
            ((Button) findViewById(R.id.btnSignIn)).setEnabled(true);
        } else if (i == R.id.btnVerify) {
            sendEmailVerification();
        }
    }

    private void sendEmailVerification() {
        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification();
        // [END send_email_verification]
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            mStatusTextView.setText(getString(
                    R.string.emailpassword_status_fmt,
                    user.getEmail(),
                    user.isEmailVerified()
            ));
            mDetailTextView.setText(
                    getString(
                            R.string.firebase_status_fmt, user.getUid()
                    ));

            findViewById(R.id.btnSignIn).setEnabled(false);
            findViewById(R.id.btnVerify).setEnabled(!user.isEmailVerified());
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);
        }
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
                        updateUI(user);
                    } else {
                        Log.w(
                                TAG,
                                "signInWithEmail:failure",
                                task.getException()
                        );
                        Toast.makeText(
                                MainActivity.this,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT
                        ).show();
                        updateUI(null);
                    }
                    if (!task.isSuccessful()) {
                        mStatusTextView.setText(R.string.auth_failed);
                    }
                });
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
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
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(
                                TAG,
                                "createUserWithEmail:failure",
                                task.getException()
                        );
                        Toast.makeText(
                                MainActivity.this,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT
                        ).show();
                        updateUI(null);
                    }
                });
    }
}