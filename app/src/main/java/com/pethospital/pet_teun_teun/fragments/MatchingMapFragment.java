package com.pethospital.pet_teun_teun.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pethospital.pet_teun_teun.HospitalDetailActivity;
import com.pethospital.pet_teun_teun.MainActivity;
import com.pethospital.pet_teun_teun.NaviHospitalActivity;
import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.adapters.ManageAdapter;
import com.pethospital.pet_teun_teun.adapters.MatchingMapAdapter;
import com.pethospital.pet_teun_teun.items.MatchingViewItem;
import com.pethospital.pet_teun_teun.servers.SearchLocation;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.ServiceConfigurationError;
import java.util.StringTokenizer;

public class MatchingMapFragment extends Fragment {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;

    private FragmentManager fragManager;

    private ListView mapList;
    private ImageButton bigHosBtn;
    private ImageButton tfBtn;
    private ImageButton myPetBtn;
    private ImageButton favorBtn;

    private SearchLocation searchs;

    private MatchingMapAdapter myAdap;

    private Double curLon;
    private Double curLat;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.matching_map_fragment, container, false);

        mapList= v.findViewById(R.id.matching_map_list);
        bigHosBtn=v.findViewById(R.id.big_hospital_button);
        tfBtn=v.findViewById(R.id.open_hospital_button);
        myPetBtn=v.findViewById(R.id.my_pet_hospital_button);
        favorBtn=v.findViewById(R.id.favorite_button);

        fragManager=getChildFragmentManager();

        mapList.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // adapter.getItem(position)의 return 값은 Object 형
                // 실제 Item의 자료형은 CustomDTO 형이기 때문에
                // 형변환을 시켜야 getResId() 메소드를 호출할 수 있습니다.
                MatchingViewItem item= ((MatchingViewItem)myAdap.getItem(position));

                // new Intent(현재 Activity의 Context, 시작할 Activity 클래스)
                Intent intent = new Intent(v.getContext(), HospitalDetailActivity.class);
                intent.putExtra("hosName", item.getName());
                intent.putExtra("phone",item.getPhoneNum());
                intent.putExtra("location",item.getOpenTime());
                startActivity(intent);
            }
        }));

        bigHosBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dataSetting(searchs.searchLocation("동물의료센터",curLon,curLat));
            }
        });
        tfBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dataSetting(searchs.searchLocation("24시동물병원",curLon,curLat));
            }
        });
        myPetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("species",getArguments().getString("species"));
                dataSetting(searchs.searchLocation("동물병원",curLon,curLat));
            }
        });

        searchs=new SearchLocation();
        LocationManager locationManager=(LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            // 권한 없음
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
        Location lo=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        String provider=lo.getProvider();
        curLon=lo.getLongitude();
        curLat=lo.getLatitude();

        //dataSetting(v);
        dataSetting(searchs.searchLocation("동물의료센터",curLon,curLat));
        return v;
    }
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.big_hospital_button:
                    dataSetting(searchs.searchLocation("동물의료센터",curLon,curLat));
                    break;
                case R.id.open_hospital_button:
                    dataSetting(searchs.searchLocation("24시동물병원",curLon,curLat));
                    break;
                case R.id.my_pet_hospital_button:
                    break;
                case R.id.favorite_button:
                    break;
            }
            return true;
        }
    }

    private void dataSetting(ArrayList<JSONObject> ary){
        myAdap=new MatchingMapAdapter();

        try {
            for (int i = 0; i < ary.size(); ++i) {
                JSONObject obj = ary.get(i);
                StringTokenizer token=new StringTokenizer(obj.getString("distance"),".");

                myAdap.addItem(obj.getString("name"), token.nextToken()+"m", "tel "+obj.getString("phone_number"), obj.getString("road_address"),4);
            }
        }catch (Exception e){

        }


        mapList.setAdapter(myAdap);
    }
}
