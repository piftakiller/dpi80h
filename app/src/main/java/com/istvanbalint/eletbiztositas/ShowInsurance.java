package com.istvanbalint.eletbiztositas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowInsurance extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseDatabase db;

    TextView insName;
    TextView insText;
    TextView insPrice;
    private String  csomagnev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference("csomagok");
        mAuth=FirebaseAuth.getInstance() ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_insurance);
        insName = findViewById(R.id.insName);
        insText = findViewById(R.id.insText);
        insPrice = findViewById(R.id.insPrice);

        Bundle extras = getIntent().getExtras();
        String uid=extras.getString("which","alap");
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("csomagok").child(uid);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Csomag cs = dataSnapshot.getValue(Csomag.class);
                insName.setText(cs.getNev());
                csomagnev=cs.getNev();
                insText.setText(cs.getLeiras());
                insPrice.setText(cs.getFt());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);
    }
    public void applyIns(View v){
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("users").child(uid);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                user.setinsuranceType(csomagnev);
                UserDAO userDAO = new UserDAO();
                userDAO.add(user);
                vissza(v);
                back(v);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);
    }
    private void vissza(View v) {
        Notification mNotification = new Notification(this);
        mNotification.send("Kötöttél egy új "+csomagnev+" biztosítást.");
    }
    public void back(View v){
        Intent i=new Intent(this, ApplyInsurances.class);
        startActivity(i);
    }
}