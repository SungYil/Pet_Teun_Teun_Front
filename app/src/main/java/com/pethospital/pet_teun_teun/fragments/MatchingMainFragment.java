package com.pethospital.pet_teun_teun.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pethospital.pet_teun_teun.R;

public class MatchingMainFragment extends Fragment {

    private FragmentManager fragManager;
    private UserPetInfoFragment userInfo;
    private MatchingMapFragment matchingMap;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragManager=getChildFragmentManager();
        FragmentTransaction transaction = fragManager.beginTransaction();

        userInfo=new UserPetInfoFragment();
        matchingMap=new MatchingMapFragment();

        transaction.replace(R.id.top_matching_main,userInfo);
        transaction.replace(R.id.bottom_matching_main,matchingMap).commitAllowingStateLoss();

        return inflater.inflate(R.layout.matching_main_fragment, container, false);
    }
}
