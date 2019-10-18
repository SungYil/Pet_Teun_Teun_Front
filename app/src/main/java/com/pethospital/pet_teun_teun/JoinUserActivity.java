package com.pethospital.pet_teun_teun;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JoinUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_user_activity);

        EditText edit_id = findViewById(R.id.join_user_edit_id);
        EditText edit_password = findViewById(R.id.join_user_edit_password);
        EditText edit_date = findViewById(R.id.join_user_edit_date);
        EditText edit_phone = findViewById(R.id.join_user_edit_phone);


    }
}
