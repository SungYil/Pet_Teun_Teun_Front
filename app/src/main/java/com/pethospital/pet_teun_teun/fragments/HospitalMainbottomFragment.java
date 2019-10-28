package com.pethospital.pet_teun_teun.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.SampleMange;
import com.pethospital.pet_teun_teun.adapters.ReserveCheckAdapter;
import com.pethospital.pet_teun_teun.items.ReserveCheckItem;

import java.util.ArrayList;

public class HospitalMainbottomFragment extends Fragment {
    private ListView listView;
    private ReserveCheckAdapter reserveCheckAdapter;

    private ImageButton cunsulBtn;
    private ImageButton reserBtn;
    private ImageButton alarmBtn;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_hospital_main, container, false);

        listView=v.findViewById(R.id.hospital_main_reserve_list_view);

        cunsulBtn=v.findViewById(R.id.hospital_consulting_btn);
        reserBtn=v.findViewById(R.id.hospital_resver_confirm_btn);
        alarmBtn=v.findViewById(R.id.hospital_alarm_btn);

        reserveCheckAdapter=new ReserveCheckAdapter();
        listView.setAdapter(reserveCheckAdapter);
        addData();

        return inflater.inflate(R.layout.activity_hospital_main, container, false);
    }
    void addData(){
        ArrayList<ReserveCheckItem> sample=new SampleMange().getReserveList();
        for(int i=0;i<sample.size();i++){
            reserveCheckAdapter.addItem(sample.get(i));
        }
    }
}
