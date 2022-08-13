package com.example.uasakb10119039;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// Nama   : Diva Sabila Ramadhan
// NIM    : 10119039
// Kelas  : IF-1

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth fAuth;
    private Button btnSignIn, btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inputEmail = findViewById(R.id.email);
        inputPassword =  findViewById(R.id.password);

        btnSignIn = findViewById(R.id.button_signin);
        btnSignUp = findViewById(R.id.button_signup);

        fAuth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(v -> {
            finish();
        });

        btnSignUp.setOnClickListener(v -> {
            if (inputEmail.getText().length()>0 && inputPassword.getText().length()>0) {
                register(inputEmail.getText().toString(), inputPassword.getText().toString());
            } else {
                Toast.makeText(getApplicationContext(), "Silahkan Daftarkan Akun Anda!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void register(String email, String password) {
        fAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Akun berhasil dibuat!", Toast.LENGTH_SHORT).show();
                            inputEmail.getText().clear();
                            inputPassword.getText().clear();
                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void reload() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = fAuth.getCurrentUser();
        if (currentUser != null) {
            reload();
        }
    }
}