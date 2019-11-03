package com.pethospital.pet_teun_teun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JoinUserActivity extends AppCompatActivity {
    EditText edit_id;
    EditText edit_password;
    EditText edit_name;
    EditText edit_phone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_user_activity);

        edit_id = findViewById(R.id.join_hospital_edit_id);
        edit_password = findViewById(R.id.join_hospital_edit_password);
        edit_name = findViewById(R.id.join_hospital_edit_name);
        edit_phone = findViewById(R.id.join_hospital_edit_phone);
        Button btn_next = findViewById(R.id.join_hospital_btn_submit);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinUser2Activity.class);
                intent.putExtra("id", edit_id.getText().toString());
                intent.putExtra("password", edit_password.getText().toString());
                intent.putExtra("name", edit_name.getText().toString());
                intent.putExtra("phone", edit_phone.getText().toString());
                startActivity(intent);
            }
        });
    }
}
