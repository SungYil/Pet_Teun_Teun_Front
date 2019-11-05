package com.pethospital.pet_teun_teun;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.pethospital.pet_teun_teun.adapters.CommitReservationAdapter;
import com.pethospital.pet_teun_teun.adapters.ManageAdapter;
import com.pethospital.pet_teun_teun.items.ManageViewItem;

import java.util.List;

public class ReservationCommitActivity extends AppCompatActivity {

    private ImageButton backButton;

    private ListView commitList;

    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_commit_list_activity);

        backButton= findViewById(R.id.reservation_back_button);

        commitList= findViewById((R.id.reservation_commit_list));


        bundle= getIntent().getBundleExtra("data");
        dataSetting();

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
    }

    private void dataSetting(){
        CommitReservationAdapter comit=new CommitReservationAdapter();

        List<ManageViewItem> ary= bundle.getParcelableArrayList("list");

        if(ary!=null){
            for(int i=0;i<ary.size();++i){
                ManageViewItem item=ary.get(i);
                comit.addItem(item.getId(),item.getIcon(),"2010/10/10",item.getName(),item.getSubname(),item.getContent());
            }
        }

        commitList.setAdapter(comit);
    }
}
