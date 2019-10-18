package com.pethospital.pet_teun_teun.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.adapters.ManageAdapter;
import com.pethospital.pet_teun_teun.adapters.MatchingMapAdapter;
import com.pethospital.pet_teun_teun.items.MatchingViewItem;

public class MatchingMapFragment extends Fragment {

    private ListView mapList;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.matching_map_fragment, container, false);
        mapList=(ListView)v.findViewById(R.id.matching_map_list);

        dataSetting(v);
        
        return v;
    }
    private void dataSetting(View v){
        MatchingMapAdapter myAdap=new MatchingMapAdapter();

        myAdap.addItem("xx병원","0km","010-xxx-xxxx","10시~11시",1);
        myAdap.addItem("yy병원","0km","010-xxx-xxxx","10시~11시",1);
        myAdap.addItem("ww병원","0km","010-xxx-xxxx","10시~11시",1);
        myAdap.addItem("qq병원","0km","010-xxx-xxxx","10시~11시",1);
        myAdap.addItem("zz병원","0km","010-xxx-xxxx","10시~11시",1);

        mapList.setAdapter(myAdap);
    }
}
