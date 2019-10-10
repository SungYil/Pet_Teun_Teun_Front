package com.pethospital.pet_teun_teun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pethospital.pet_teun_teun.fragments.BoardFragment;
import com.pethospital.pet_teun_teun.fragments.MatchingMainFragment;
import com.pethospital.pet_teun_teun.fragments.UserMainFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragManager;
    private BoardFragment boardFrag;
    private UserMainFragment userMainFrag;
    private MatchingMainFragment matchingMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //프래그먼트 관리
        fragManager=getSupportFragmentManager();

        //버튼에 추가할 프래그먼트들 생성
        boardFrag=new BoardFragment();
        userMainFrag=new UserMainFragment();
        matchingMain=new MatchingMainFragment();

        //프래그먼트 시작.
        FragmentTransaction transaction=fragManager.beginTransaction();
        //프래그먼트로 교체.
        transaction.replace(R.id.frameLayout,userMainFrag).commitAllowingStateLoss();

        BottomNavigationView bottomView=findViewById(R.id.navigationView);
        bottomView.setOnNavigationItemSelectedListener(new ItemSelectedListener(){

        });

    }
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.boardItem:
                    transaction.replace(R.id.frameLayout, boardFrag).commitAllowingStateLoss();

                    break;
                case R.id.userMainItem:
                    transaction.replace(R.id.frameLayout, userMainFrag).commitAllowingStateLoss();
                    break;
                case R.id.hospitalItem:
                    transaction.replace(R.id.frameLayout,matchingMain).commitAllowingStateLoss();
            }
            return true;
        }
    }
}
