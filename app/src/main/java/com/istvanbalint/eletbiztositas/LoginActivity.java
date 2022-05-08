package com.istvanbalint.eletbiztositas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG=WelcomeActivity.class.getName();
    private static final String PREF_KEY=WelcomeActivity.class.getPackage().toString();
    private static final int SECRET_KEY=99;
    EditText loginNameEditText;
    EditText loginPasswordEditText;
    private SharedPreferences preferences;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        loginNameEditText=findViewById(R.id.loginNameEditText);
        loginPasswordEditText=findViewById(R.id.loginPasswordEditText);
        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String email = preferences.getString("email", "");

        loginNameEditText.setText(email);
        mAuth=FirebaseAuth.getInstance();
    }
    public void login(View v){
        String username= loginNameEditText.getText().toString();
        String password= loginPasswordEditText.getText().toString();
        if (username.length()==0&&password.length()==0){Toast.makeText(LoginActivity.this,"Felhasználónév/jelszó kitöltése kötelező",Toast.LENGTH_LONG).show();}
        else {
            mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startLifeInsurance();
                    } else {
                        Toast.makeText(LoginActivity.this, "User wasn't logged in." + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void startLifeInsurance() {
        Intent i = new Intent(this, ApplyInsurances.class);
        startActivity(i);
    }

    public void register(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        intent.putExtra("SECRET_KEY",SECRET_KEY);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        Log.i(LOG_TAG,"");

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", loginNameEditText.getText().toString());
        editor.apply();

        Log.i(LOG_TAG, "onPause");
    }
}