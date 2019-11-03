package com.pethospital.pet_teun_teun;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PetInfoModifyActivity extends AppCompatActivity {
    private EditText edit_pet_name;
    private EditText edit_pet_birth;
    private EditText edit_adopt_day;
    private EditText edit_age;
    private EditText edit_species;
    private Button joinBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_user2_activity);
        Intent intent = getIntent();

        edit_pet_name = findViewById(R.id.join_user2_edit_name);
        edit_pet_birth = findViewById(R.id.join_user2_edit_birth);
        edit_adopt_day = findViewById(R.id.join_user2_edit_adopt);
        edit_age = findViewById(R.id.join_user2_edit_age);
        edit_species = findViewById(R.id.join_user2_edit_species);
        joinBtn = findViewById(R.id.join_user2_btn_submit);


        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("!!!!!!!!!!", intent.getStringExtra("id") + intent.getStringExtra("password") + intent.getStringExtra("name") + intent.getStringExtra("phone"));
                Log.d("!!!!!!!!!!!2", edit_pet_name.getText().toString() + edit_pet_birth.getText().toString() + edit_adopt_day.getText().toString() + edit_age.getText().toString() + edit_species.getText().toString());

                ContentValues values = new ContentValues();
                values.put("pet_name", edit_pet_name.getText().toString());
                values.put("pet_birth", edit_pet_birth.getText().toString());
                values.put("adopt_day", edit_adopt_day.getText().toString());
                values.put("pet_age", edit_age.getText().toString());
                values.put("species", edit_species.getText().toString());

                String url = getString(R.string.url) + "join.do";
                //JoinUser2Activity.NetworkTask networkTask = new JoinUser2Activity.NetworkTask(url, values);
                //networkTask.execute();
            }
        });
    }
}
