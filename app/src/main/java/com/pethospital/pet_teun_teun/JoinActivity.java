package com.pethospital.pet_teun_teun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_activity);

        Button btn_common_registration = findViewById(R.id.join_btn_common_registration);
        Button btn_hospital_registration = findViewById(R.id.join_btn_hospital_registration);

        btn_common_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinUserActivity.class);
                startActivity(intent);
            }
        });
        btn_hospital_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinHospitalActivity.class);
                startActivity(intent);
            }
        });
    }
}
