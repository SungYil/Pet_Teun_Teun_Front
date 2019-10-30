package com.pethospital.pet_teun_teun.fragments;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.servers.RequestHttpURLConnection;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class UserPetInfoFragment extends Fragment {
    private TextView petName;
    private TextView adopt;
    private TextView birth;
    private TextView species;
    private TextView detailSpecies;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.user_pet_info_fragment, container, false);
        petName=v.findViewById(R.id.user_pet_name);
        adopt=v.findViewById(R.id.user_pet_getDay);
        birth=v.findViewById(R.id.user_pet_birth);
        species=v.findViewById(R.id.user_pet_kinds);

        Bundle bundle=getArguments();

        petName.setText(bundle.getString("petName"));
        adopt.setText(bundle.getString("adopt"));
        birth.setText(bundle.getString("birth"));
        species.setText(bundle.getString("species"));


        return v;
    }


}
