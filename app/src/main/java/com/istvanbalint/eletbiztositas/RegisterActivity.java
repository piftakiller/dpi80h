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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {
    private static final String LOG_TAG=RegisterActivity.class.getName();
    private static final String PREF_KEY=RegisterActivity.class.getPackage().toString();
    EditText userEmailEditText;
    EditText passwordEditText;
    EditText passwordConfirmEditText ;
    private static final int SECRET_KEY=99;

    private SharedPreferences preferences;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Log.i(LOG_TAG,"Hi");
        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);
        if (secret_key!=99){
            Log.i(LOG_TAG,"pápá");
            finish();
        }
        userEmailEditText=findViewById(R.id.userEmailEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        passwordConfirmEditText=findViewById(R.id.passwordConfirmEditText);
        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String email = preferences.getString("email", "");

        userEmailEditText.setText(email);
        mAuth=FirebaseAuth.getInstance() ;
    }
    public void register(View v){
        String email=userEmailEditText.getText().toString();
        String password=passwordEditText.getText().toString();
        String passwordConfirm=passwordConfirmEditText.getText().toString();
        if (!password.equals(passwordConfirm)) {
            Log.e(LOG_TAG, "Nem egyenlő a jelszó és a megerősítése.");
            Toast.makeText(RegisterActivity.this,"Nem egyenlő a jelszó és a megerősítése",Toast.LENGTH_LONG).show();
            return;
        }
        if (!(password.length()>7&&passwordConfirm.length()>7)){
            Log.e(LOG_TAG, "Nem egyenlő a jelszó és a megerősítése.");
            Toast.makeText(RegisterActivity.this,"Jelszó minimum 8 karaketer hosszú legyen",Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(LOG_TAG,"user created");
                    Toast.makeText(RegisterActivity.this,"Felhasználó létrehozva",Toast.LENGTH_LONG).show();
                    startLogin();
                }else{
                    Log.d(LOG_TAG,"user wasn't created", task.getException());
                    Toast.makeText(RegisterActivity.this,"Sikertelen regisztráció: "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void startLogin() {
        Intent intent = new Intent(this,InsuranceActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    public void logIn(View v){
        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("SECRET_KEY",SECRET_KEY);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    public void cancel(View v){
        Intent intent = new Intent(this,WelcomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("email",userEmailEditText.getText().toString());
        editor.apply();
        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "onRestart");
    }
}