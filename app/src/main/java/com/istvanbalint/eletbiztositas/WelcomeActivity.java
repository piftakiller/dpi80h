package com.istvanbalint.eletbiztositas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {
    private static final String LOG_TAG=WelcomeActivity.class.getName();
    private static final String PREF_KEY=WelcomeActivity.class.getPackage().toString();
    private static final int SECRET_KEY=99;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        preferences=getSharedPreferences(PREF_KEY,MODE_PRIVATE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(this,ApplyInsurances.class);
            intent.putExtra("SECRET_KEY",SECRET_KEY);

            startActivity(intent);
        }
    }

    public void login(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        intent.putExtra("SECRET_KEY",SECRET_KEY);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        Log.i(LOG_TAG,"");

    }
    public void register(View view){
        Intent intent = new Intent(this,RegisterActivity.class);
        intent.putExtra("SECRET_KEY",SECRET_KEY);

        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        Log.i(LOG_TAG,"");

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