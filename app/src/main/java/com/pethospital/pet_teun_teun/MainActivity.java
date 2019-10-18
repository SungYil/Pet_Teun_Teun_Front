package com.pethospital.pet_teun_teun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

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
            }
            return true;
        }
    }
    /**
     * 다른 프래그먼트에서 버튼 클릭 시 이 메소드를 통해 프래그먼트 교체가능.
     * @param fragment
     */
    public void replaceFragment(Fragment fragment, int id){
        //프래그먼트 시작.
        FragmentTransaction transaction=fragManager.beginTransaction();
        //프래그먼트로 교체.
        transaction.replace(R.id.frameLayout,fragment).commitAllowingStateLoss();
        bottomView.setSelectedItemId(id);
    }
}
