package com.pethospital.pet_teun_teun;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JoinHospitalActivity extends AppCompatActivity {

    EditText edit_id;
    EditText edit_password;
    EditText edit_name;
    EditText edit_phone;
    Button btn_submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_hospital_activity);

        edit_id = findViewById(R.id.join_hospital_edit_id);
        edit_password = findViewById(R.id.join_hospital_edit_password);
        edit_name = findViewById(R.id.join_hospital_edit_name);
        edit_phone = findViewById(R.id.join_hospital_edit_phone);
        btn_submit = findViewById(R.id.join_hospital_btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* TODO : 병원 회원 가입 통신 */
            }
        });
    }
}
