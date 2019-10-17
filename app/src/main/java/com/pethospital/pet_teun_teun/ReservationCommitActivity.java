package com.pethospital.pet_teun_teun;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.pethospital.pet_teun_teun.adapters.CommitReservationAdapter;

public class ReservationCommitActivity extends AppCompatActivity {

    private ImageButton backButton;

    private ListView commitList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_commit_list_activity);

        backButton=(ImageButton)findViewById(R.id.reservation_back_button);

        commitList=(ListView)findViewById((R.id.reservation_commit_list));

        dataSetting();

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
    }

    private void dataSetting(){
        CommitReservationAdapter myAdap=new CommitReservationAdapter();

        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.hospital_icon),"19/19/19","xx병원","내용","이상무");
        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.hospital_icon),"19/19/19","yy병원","내용","이상무");
        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.hospital_icon),"19/19/19","ww병원","내용","이상무");
        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.hospital_icon),"19/19/19","qq병원","내용","이상무");
        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.hospital_icon),"19/19/19","zz병원","내용","이상무");

        commitList.setAdapter(myAdap);
    }
}
