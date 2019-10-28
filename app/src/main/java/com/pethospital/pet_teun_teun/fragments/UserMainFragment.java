package com.pethospital.pet_teun_teun.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pethospital.pet_teun_teun.R;

public class UserMainFragment extends Fragment {

    private FragmentManager fragManager;
    private UserPetInfoFragment userInfo;
    private UserPetManageFragment userManage;

    private Fragment info;
    private Fragment manage;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragManager=getChildFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();

        Bundle bundle=getArguments();

        if(bundle!=null){
            Log.d("=================",bundle.getString("type"));
            if(bundle.getString("type").equals("hospital")){
                info=new HospitalInfoFragment();
                manage=new HospitalMainbottomFragment();
            }else{
                info=new UserPetInfoFragment();
                manage=new UserPetManageFragment();
            }
        }

        transaction.replace(R.id.top_user_main,info);
        transaction.replace(R.id.bottom_user_main,manage).commitAllowingStateLoss();

        return inflater.inflate(R.layout.user_main_fragment, container, false);
    }

}
