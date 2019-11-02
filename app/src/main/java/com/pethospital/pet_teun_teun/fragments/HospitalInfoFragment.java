package com.pethospital.pet_teun_teun.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pethospital.pet_teun_teun.HospitalSettingActivity;
import com.pethospital.pet_teun_teun.R;

public class HospitalInfoFragment extends Fragment {

    private TextView name;
    private TextView phoneNum;
    private TextView openTime;
    private TextView chatTime;
    private TextView address;
    private ImageButton setBtn;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.hospital_info_fragment, container, false);

        setBtn=(ImageButton)v.findViewById(R.id.hospital_info_setting_btn);

        name=v.findViewById(R.id.hospital_info_name);
        phoneNum=v.findViewById(R.id.hospital_info_number);
        address=v.findViewById(R.id.hospital_info_address);
        openTime=v.findViewById(R.id.hospital_info_open_time);
        chatTime=v.findViewById(R.id.hospital_info_consulting_time);

        setBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), HospitalSettingActivity.class);
                startActivity(intent);
            }
        });

        Bundle bundle=getArguments();

        name.setText(bundle.getString("name"));
        phoneNum.setText(bundle.getString("address"));
        openTime.setText(bundle.getString("openTime")+"-"+bundle.getString("closeTime"));
        chatTime.setText(bundle.getString("openTime")+"-"+bundle.getString("closeTime"));


        return v;
    }
}
