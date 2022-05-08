package com.istvanbalint.eletbiztositas;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class UserDAO {
    private DatabaseReference mDatabase;
    public UserDAO(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void add(User u){
             mDatabase.child("users").setValue(u.getuID());
        mDatabase.child("users").child(u.getuID()).setValue(u);

    }

}
