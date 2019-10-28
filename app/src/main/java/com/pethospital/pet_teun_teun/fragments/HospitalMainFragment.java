package com.pethospital.pet_teun_teun.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pethospital.pet_teun_teun.HospitalSettingActivity;
import com.pethospital.pet_teun_teun.PetStateViewActivity;
import com.pethospital.pet_teun_teun.R;

public class HospitalMainFragment extends Fragment {

    private FragmentManager fragManager;
    private HospitalInfoFragment hospitalInfo;
    private HospitalMainbottomFragment bottom;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.hospital_info_fragment, container, false);
        fragManager=getChildFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();

        hospitalInfo=new HospitalInfoFragment();
        bottom=new HospitalMainbottomFragment();

        transaction.replace(R.id.top_user_main,hospitalInfo);
        transaction.replace(R.id.bottom_user_main,bottom).commitAllowingStateLoss();

        return inflater.inflate(R.layout.hospital_info_fragment, container, false);
    }
}
