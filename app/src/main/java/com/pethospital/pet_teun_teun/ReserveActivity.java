package com.pethospital.pet_teun_teun;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class ReserveActivity extends AppCompatActivity {
    private Spinner doctorSpinner;
    private TextView reserveText;
    private TextView locationText;
    private TextView phoneNum;

    private ImageButton backBtn;

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        intent=getIntent();

        doctorSpinner=(Spinner)findViewById(R.id.reserve_doctor_dropdown);
        reserveText=findViewById(R.id.reservation_hospital_name);
        locationText=findViewById(R.id.reserve_hospital_location);
        phoneNum=findViewById(R.id.reserve_hospital_tel);
        backBtn=findViewById(R.id.reserve_back_button);

        String[] str=getResources().getStringArray(R.array.doctor_list);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_doctor,str);
        //adapter.setDropDownViewResource(R.layout.spinner_doctor);
        doctorSpinner.setAdapter(adapter);

        doctorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //i가 선택된 index,l이 id?
                Log.i("내용",doctorSpinner.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.i("내용","없다.");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
