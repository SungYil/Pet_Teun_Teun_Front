package com.pethospital.pet_teun_teun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pethospital.pet_teun_teun.fragments.BoardFragment;
import com.pethospital.pet_teun_teun.fragments.UserMainFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragManager;
    private BoardFragment boardFrag;
    private UserMainFragment userMainFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragManager=getSupportFragmentManager();
        boardFrag=new BoardFragment();
        userMainFrag=new UserMainFragment();

        FragmentTransaction transaction=fragManager.beginTransaction();
        transaction.replace(R.id.frameLayout,userMainFrag).commitAllowingStateLoss();

        BottomNavigationView bottomView=findViewById(R.id.navigationView);
        bottomView.setOnNavigationItemSelectedListener(new ItemSelectedListener(){

        });

    }
}
