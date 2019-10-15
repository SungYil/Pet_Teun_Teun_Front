package com.pethospital.pet_teun_teun.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pethospital.pet_teun_teun.MainActivity;
import com.pethospital.pet_teun_teun.PetStateViewActivity;
import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.ReservationCommitActivity;
import com.pethospital.pet_teun_teun.UserVaccinActivity;

public class UserPetManageFragment extends Fragment {

    private ImageButton stateView;
    private ImageButton reserConfirm;
    private ImageButton vaccin;
    private ImageButton hospital;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.user_pet_manage_fragment, container, false);
        stateView=(ImageButton)v.findViewById(R.id.state_view);
        reserConfirm=(ImageButton)v.findViewById(R.id.reservation_confirm);
        vaccin=(ImageButton)v.findViewById(R.id.vaccin_confirm);
        hospital=(ImageButton)v.findViewById(R.id.hospital_match);

        stateView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), PetStateViewActivity.class);
                startActivity(intent);
            }
        });

        reserConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), ReservationCommitActivity.class);
                startActivity(intent);
            }
        });

        vaccin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), UserVaccinActivity.class);
                startActivity(intent);
            }
        });

        hospital.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ((MainActivity)getActivity()).replaceFragment(new MatchingMainFragment(),R.id.hospitalItem);
            }
        });
        return v;
    }
}
