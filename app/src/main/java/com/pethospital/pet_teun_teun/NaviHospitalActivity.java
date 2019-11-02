package com.pethospital.pet_teun_teun;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
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

import java.util.Arrays;

public class NaviHospitalActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;

    private FusedLocationSource locationSource;

    @Nullable
    private LocationManager locationManager;

    private MapView mapView;

    private TextView empText;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navi_hospital_activity);

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

        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Location lo=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        String provider=lo.getProvider();
        double longitude=lo.getLongitude();
        double latitude=lo.getLatitude();

        empText.setText(longitude+"     "+latitude);

        naverMapBasicSettings();


        /**
         * 테스팅
         */
        SearchLocation s=new SearchLocation();
        s.searchLocation("중랑구청",longitude,latitude);
        s.searchDirection(longitude,latitude,127.1058342,37.359708);
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

        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(37.5666102, 126.9783881));
        naverMap.moveCamera(cameraUpdate);

        Marker marker = new Marker();
        marker.setPosition(new LatLng(37.5670135, 126.9783740));
        marker.setMap(naverMap);
        //[127.0965297,37.6046797],[127.0965256,37.6046112],[127.0965181,37.6045255],[127.0966167,37.6045115],[127.0967222,37.6044974]

        PathOverlay path = new PathOverlay();
        path.setCoords(Arrays.asList(
                new LatLng(37.6050055, 127.0963818),
                new LatLng(37.6050211, 127.0964735),
                new LatLng(37.6055977, 127.0963763),
                new LatLng(37.6056553, 127.0963692),
                new LatLng(37.6056890, 127.0967429),
                new LatLng(37.6057391, 127.0971562),
                new LatLng(37.6057626, 127.0974291),
                new LatLng(37.6057961, 127.0977416),
                new LatLng(37.6058175, 127.0979386)

        ));
        path.setWidth(30);
        path.setMap(naverMap);

    }
}