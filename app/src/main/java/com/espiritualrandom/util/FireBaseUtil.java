package com.espiritualrandom.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseUtil {

    protected DatabaseReference database;

    protected void getReferenceDataBase() {
        this.database = FirebaseDatabase.getInstance().getReference();
    }

}
