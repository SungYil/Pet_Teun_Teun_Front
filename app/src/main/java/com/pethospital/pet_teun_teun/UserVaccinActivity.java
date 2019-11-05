package com.pethospital.pet_teun_teun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.pethospital.pet_teun_teun.adapters.CommitReservationAdapter;
import com.pethospital.pet_teun_teun.adapters.VaccineAdapter;

public class UserVaccinActivity extends AppCompatActivity {

    private ImageButton backButton;

    private ListView vaccineList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_vaccine_activity);

        backButton= findViewById(R.id.vaccin_back_button);

        vaccineList= findViewById(R.id.user_vaccine_list);

        dataSetting();

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
    }

    private void dataSetting(){
        VaccineAdapter myAdap=new VaccineAdapter();

        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.hospital_icon),"19/19","xx병원","내용","17000");
        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.hospital_icon),"19/19","yy병원","내용","20000");
        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.hospital_icon),"19/19","ww병원","내용","30000");
        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.hospital_icon),"19/19","qq병원","내용","40000");
        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.hospital_icon),"19/19","zz병원","내용","50000");

        vaccineList.setAdapter(myAdap);
    }
}
