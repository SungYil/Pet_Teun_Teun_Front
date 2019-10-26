package com.pethospital.pet_teun_teun;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ReserveActivity extends AppCompatActivity {
    private Spinner doctorSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        doctorSpinner=(Spinner)findViewById(R.id.reserve_doctor_dropdown);
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
    }

}
