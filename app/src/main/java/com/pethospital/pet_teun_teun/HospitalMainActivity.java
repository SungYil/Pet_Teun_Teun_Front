package com.pethospital.pet_teun_teun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pethospital.pet_teun_teun.adapters.ReserveCheckAdapter;
import com.pethospital.pet_teun_teun.items.ReserveCheckItem;

import java.util.ArrayList;

public class HospitalMainActivity extends AppCompatActivity {
    private ListView listView;
    private ReserveCheckAdapter reserveCheckAdapter;
    private ImageButton settingBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_main);
        setting();
        addData();
    }

    void setting(){
        listView=findViewById(R.id.hospital_main_reserve_list_view);
        reserveCheckAdapter=new ReserveCheckAdapter();
        listView.setAdapter(reserveCheckAdapter);
        settingBtn=(ImageButton)findViewById(R.id.hospital_main_setting);
        settingBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),HospitalSettingActivity.class);
                startActivity(intent);
            }
        });
    }


    void addData() {
        ArrayList<ReserveCheckItem> sample = new SampleMange().getReserveList();
        for (int i = 0; i < sample.size(); i++) {
            reserveCheckAdapter.addItem(sample.get(i));
        }
    }

}
