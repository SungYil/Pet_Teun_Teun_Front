package com.pethospital.pet_teun_teun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HospitalSettingActivity extends AppCompatActivity {

    private ImageButton backBtn;

    private Button reserSetBtn;
    private Button consulSetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_setting);

        backBtn= findViewById(R.id.hospital_setting_back_btn);
        reserSetBtn= findViewById(R.id.hosptal_setting_reserve_term_btn);
        consulSetBtn= findViewById(R.id.hospital_setting_consert_btn);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        reserSetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(), ReserveTermSettingActivity.class);
                startActivity(intent);
            }
        });
        consulSetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(), ConsultSettingActivity.class);
                startActivity(intent);
            }
        });

    }
}
