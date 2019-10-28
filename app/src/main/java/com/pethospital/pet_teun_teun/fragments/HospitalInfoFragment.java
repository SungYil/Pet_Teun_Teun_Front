package com.pethospital.pet_teun_teun.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pethospital.pet_teun_teun.HospitalSettingActivity;
import com.pethospital.pet_teun_teun.R;

public class HospitalInfoFragment extends Fragment {

    private ImageButton setBtn;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.hospital_info_fragment, container, false);
        setBtn=(ImageButton)v.findViewById(R.id.hospital_setting_btn);

        setBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), HospitalSettingActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
}
