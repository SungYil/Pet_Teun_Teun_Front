package com.pethospital.pet_teun_teun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        EditText edit_email = findViewById(R.id.login_edit_email);
        EditText edit_password = findViewById(R.id.login_edit_password);
        Button btn_login = findViewById(R.id.login_btn_login);
        Button btn_forget = findViewById(R.id.login_btn_forget);

        //TODO : 통신 및 이것저것

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
