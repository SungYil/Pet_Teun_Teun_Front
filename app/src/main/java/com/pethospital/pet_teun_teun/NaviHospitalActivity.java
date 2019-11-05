package com.pethospital.pet_teun_teun;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PathOverlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.pethospital.pet_teun_teun.servers.SearchLocation;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NaviHospitalActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;

    private FusedLocationSource locationSource;

    @Nullable
    private LocationManager locationManager;

    private MapView mapView;
    private ListView list;

    private Double lati;
    private Double longti;
    private List<LatLng> loads;
    private TextView empText;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_hospital_activity);
        String name=getIntent().getStringExtra("name");
        TextView title=findViewById(R.id.hospital_navi_title);
        title.setText(name);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(permissionCheck == PackageManager.PERMISSION_DENIED){
            // 권한 없음
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else{

        }

        mapView=findViewById(R.id.map_view);
        empText=findViewById(R.id.empty_text);
        list=findViewById(R.id.navi_hospital_list);

        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        //현재위치
        Location lo=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        String provider=lo.getProvider();
        double longitude=lo.getLongitude();
        double latitude=lo.getLatitude();

        naverMapBasicSettings();

        /**
         * 테스팅
         */
        SearchLocation s=new SearchLocation();
        ArrayList<JSONObject> ary=s.searchLocation(name,longitude,latitude);
        try {
            String lng = ary.get(0).getString("x");
            String lat = ary.get(0).getString("y");
            lati=Double.parseDouble(lat);
            longti=Double.parseDouble(lng);

            loads=s.searchDirection(longitude,latitude,longti,lati);
            ArrayList<String> guideList=s.searchDirectionGuide(longitude,latitude,longti,lati);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,guideList);
            list.setAdapter(arrayAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void naverMapBasicSettings() {
        mapView.getMapAsync(this);
        // 내위치 찾기 위한 source
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // grantResults[0] 거부 -> -1
        // grantResults[0] 허용 -> 0 (PackageManager.PERMISSION_GRANTED)

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // ACCESS_FINE_LOCATION 에 대한 권한 획득.

        } else {
            // ACCESS_FINE_LOCATION 에 대한 권한 거부.

        }
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);

        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(lati, longti));
        naverMap.moveCamera(cameraUpdate);

        Marker marker = new Marker();
        marker.setPosition(new LatLng(lati, longti));
        marker.setMap(naverMap);
        //[127.0965297,37.6046797],[127.0965256,37.6046112],[127.0965181,37.6045255],[127.0966167,37.6045115],[127.0967222,37.6044974]

        PathOverlay path = new PathOverlay();
        /*Arrays.asList(
                new LatLng(37.6050055, 127.0963818),
                new LatLng(37.6050211, 127.0964735),
                new LatLng(37.6055977, 127.0963763),
                new LatLng(37.6056553, 127.0963692),
                new LatLng(37.6056890, 127.0967429),
                new LatLng(37.6057391, 127.0971562),
                new LatLng(37.6057626, 127.0974291),
                new LatLng(37.6057961, 127.0977416),
                new LatLng(37.6058175, 127.0979386)*/
        path.setCoords(loads);
        path.setWidth(30);
        path.setMap(naverMap);

    }
}