package com.pethospital.pet_teun_teun.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pethospital.pet_teun_teun.NaviHospitalActivity;
import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.UserSettingActivity;


public class UserPetInfoFragment extends Fragment {
    private TextView petName;
    private TextView adopt;
    private TextView birth;
    private TextView species;
    private TextView detailSpecies;

    private ImageButton setBtn;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.user_pet_info_fragment, container, false);
        petName=v.findViewById(R.id.user_pet_name);
        adopt=v.findViewById(R.id.user_pet_getDay);
        birth=v.findViewById(R.id.user_pet_birth);
        species=v.findViewById(R.id.user_pet_kinds);
        setBtn=v.findViewById(R.id.setting_button);

        Bundle bundle=getArguments();

        Log.d("@@@@@@@@",bundle.getString("petName"));

        petName.setText(bundle.getString("petName"));
        adopt.setText(bundle.getString("adopt"));
        birth.setText(bundle.getString("birth"));
        species.setText(bundle.getString("species"));

        setBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), UserSettingActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }


}
