package com.istvanbalint.eletbiztositas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ApplyInsurances extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase db;

    TextView showMyName;
    TextView insuranceType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("users");
        mAuth=FirebaseAuth.getInstance() ;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_insurances);
        showMyName=findViewById(R.id.showMyName);
        insuranceType=findViewById(R.id.insuranceType);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("users").child(uid);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                showMyName.setText(user.getFirstName());
                insuranceType.setText(user.getinsuranceType());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);
    }

    public void applyInsurance(View view){
        Intent i = new Intent(ApplyInsurances.this, ShowInsurance.class);
        i.putExtra("which","alap");
        startActivity(i);
    }
    public void applyInsurance2(View view){
        Intent i = new Intent(ApplyInsurances.this, ShowInsurance.class);
        i.putExtra("which","Kozepes");
        startActivity(i);
    }
    public void applyInsurance3(View view){
        Intent i = new Intent(ApplyInsurances.this, ShowInsurance.class);
        i.putExtra("which","Halado");
        startActivity(i);
    }
    public void modifyProfile(View view){
        Intent intent = new Intent(this,InsuranceActivity.class);
        startActivity(intent);
    }
    public void exit(View view){

            mAuth.signOut();
            Intent i = new Intent(this, WelcomeActivity.class);
            startActivity(i);
            finish();
    }
}