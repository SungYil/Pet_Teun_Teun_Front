package com.pethospital.pet_teun_teun;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.pethospital.pet_teun_teun.adapters.CommitReservationAdapter;
import com.pethospital.pet_teun_teun.adapters.PetStateAdapter;

public class PetStateViewActivity extends AppCompatActivity {

    private ImageButton backButton;

    private ListView petList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_state_activity);

        backButton= findViewById(R.id.pet_state_back_button);

        petList= findViewById(R.id.pet_state_list);

        dataSetting();

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
    }
    private void dataSetting(){
        PetStateAdapter myAdap=new PetStateAdapter();

        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buy_icon),"19/19","e마트","내용");
        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buy_icon),"19/19","y마트","내용");
        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buy_icon),"19/19","z마트","내용");
        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buy_icon),"19/19","q마트","내용");
        myAdap.addItem(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buy_icon),"19/19","w마트","내용");

        petList.setAdapter(myAdap);
    }
}
