package com.istvanbalint.eletbiztositas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsuranceActivity extends AppCompatActivity {
    private EditText editTextFirstname;
    private EditText editTextLastName;
    private EditText age;
    private TextView userEmailTextView; //
    private Button button2;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("users");
        mAuth=FirebaseAuth.getInstance() ;
        button2 = findViewById(R.id.button2);
        editTextFirstname = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        age =findViewById(R.id.editTextAge);

        userEmailTextView = findViewById(R.id.userEmailTextView);

        userEmailTextView.setText(mAuth.getCurrentUser().getEmail());
    }

    public void sendData(View v){
        String userAge = age.getText().toString();
        if (Integer.parseInt(userAge)<18||Integer.parseInt(userAge)>55){
            Toast.makeText(InsuranceActivity.this, "Sajnos 18 és 55 éves kor között regisztrálhatsz csak.", Toast.LENGTH_LONG).show();
        }else{
            UserDAO userDAO = new UserDAO();
            User u = new User(FirebaseAuth.getInstance().getUid(),editTextFirstname.getText().toString(),editTextLastName.getText().toString(),mAuth.getCurrentUser().getEmail(),Integer.parseInt(userAge));
            userDAO.add(u);
            Intent intent = new Intent(this,ApplyInsurances.class);
            startActivity(intent);
        }
    }
    public void logout(View v){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this,WelcomeActivity.class);
        startActivity(intent);
    }
}