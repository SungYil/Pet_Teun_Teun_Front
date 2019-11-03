package com.pethospital.pet_teun_teun;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class UserSettingActivity extends AppCompatActivity {

    private ImageButton backBtn;
    private Button petAddBtn;
    private Button petModifyBtn;
    private Button petDelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_settion_activity);

        backBtn=findViewById(R.id.user_setting_back_btn);
        petAddBtn=findViewById(R.id.user_pet_add_btn);
        petModifyBtn=findViewById(R.id.user_pet_modify_btn);
        petDelBtn=findViewById(R.id.user_pet_delete_btn);

        backBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
        petModifyBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
    }
}
