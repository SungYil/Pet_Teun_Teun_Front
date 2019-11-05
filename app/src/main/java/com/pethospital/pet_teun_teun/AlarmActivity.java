package com.pethospital.pet_teun_teun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.pethospital.pet_teun_teun.adapters.AlarmAdapter;
import com.pethospital.pet_teun_teun.items.AlarmItem;

import java.util.ArrayList;

public class AlarmActivity extends AppCompatActivity {
    private ListView alarmList;
    private AlarmAdapter alarmAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //alarm 리스트뷰 찾기
        alarmList= findViewById(R.id.alarm_alarm_list_view);
        //어뎁터 생성
        alarmAdapter=new AlarmAdapter();
        //어뎁터 연결
        alarmList.setAdapter(alarmAdapter);
        //임시데이터 추가
        addData();
    }
    void test(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        AlarmItem temp=(AlarmItem) alarmAdapter.getItem(2);
        builder.setTitle("테스트").setMessage(temp.getTitle()+temp.getDate());
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    void addData(){
        ArrayList<AlarmItem> sample=new SampleMange().getAlarmList();
        for(int i=0;i<sample.size();i++){
            alarmAdapter.addItem(sample.get(i));
        }
    }
}
