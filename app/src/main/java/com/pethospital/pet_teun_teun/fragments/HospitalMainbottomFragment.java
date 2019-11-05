package com.pethospital.pet_teun_teun.fragments;

import android.content.Intent;
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

import com.pethospital.pet_teun_teun.AlarmActivity;
import com.pethospital.pet_teun_teun.ConsultSettingActivity;
import com.pethospital.pet_teun_teun.HospitalSettingActivity;
import com.pethospital.pet_teun_teun.PetStateViewActivity;
import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.SampleMange;
import com.pethospital.pet_teun_teun.adapters.ManageAdapter;
import com.pethospital.pet_teun_teun.adapters.ReserveCheckAdapter;
import com.pethospital.pet_teun_teun.items.ManageViewItem;
import com.pethospital.pet_teun_teun.items.ReserveCheckItem;

import java.util.ArrayList;
import java.util.List;

public class HospitalMainbottomFragment extends Fragment {
    private ListView listView;
    private ReserveCheckAdapter reserveCheckAdapter;

    private ImageButton cunsulBtn;
    private ImageButton reserBtn;
    private ImageButton systemBtn;

    private Bundle bundle;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.activity_hospital_main, container, false);

        bundle=getArguments();

        listView=v.findViewById(R.id.hospital_main_reserve_list_view);

        cunsulBtn=v.findViewById(R.id.hospital_consulting_btn);
        reserBtn=v.findViewById(R.id.hospital_resver_confirm_btn);
        systemBtn=v.findViewById(R.id.hospital_main_setting_btn);

        cunsulBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), ConsultSettingActivity.class);
                startActivity(intent);
            }
        });
        reserBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), AlarmActivity.class);
                startActivity(intent);
            }
        });
        systemBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), HospitalSettingActivity.class);
                startActivity(intent);
            }
        });

        reserveCheckAdapter=new ReserveCheckAdapter();
        listView.setAdapter(reserveCheckAdapter);
        //addData();

        dataSetting(v);

        return v;
    }
    private void dataSetting(View v){
        List<ReserveCheckItem> ary= bundle.getParcelableArrayList("list");

        if(ary!=null){
            for(int i=0;i<ary.size();++i){
                reserveCheckAdapter.addItem(ary.get(i));
            }
        }
    }

    void addData(){
        ArrayList<ReserveCheckItem> sample=new SampleMange().getReserveList();
        for(int i=0;i<sample.size();i++){
            reserveCheckAdapter.addItem(sample.get(i));
        }
    }
}
