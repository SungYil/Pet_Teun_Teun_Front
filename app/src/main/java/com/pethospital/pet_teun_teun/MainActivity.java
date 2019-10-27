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
import com.pethospital.pet_teun_teun.fragments.BoardPageFragment;
import com.pethospital.pet_teun_teun.fragments.MatchingMainFragment;
import com.pethospital.pet_teun_teun.fragments.MoreViewFragment;
import com.pethospital.pet_teun_teun.fragments.UserMainFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragManager;
    private BoardPageFragment boardFrag;
    private UserMainFragment userMainFrag;
    private MatchingMainFragment matchingMain;
    private MoreViewFragment moreViewFrag;

    private BottomNavigationView bottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //프래그먼트 관리
        fragManager=getSupportFragmentManager();

        //버튼에 추가할 프래그먼트들 생성
        boardFrag=new BoardPageFragment();
        userMainFrag=new UserMainFragment();
        matchingMain=new MatchingMainFragment();
        moreViewFrag=new MoreViewFragment();

        //프래그먼트 시작.
        FragmentTransaction transaction=fragManager.beginTransaction();
        //프래그먼트로 교체.
        transaction.replace(R.id.frameLayout,userMainFrag).commitAllowingStateLoss();

        bottomView=findViewById(R.id.navigationView);
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
                    break;
                case R.id.moreViewItem:
                    transaction.replace(R.id.frameLayout, moreViewFrag).commitAllowingStateLoss();
                    break;
                case R.id.noticeItem:
                    /* TODO : 알림 프래그먼트 */
                    break;
            }
            return true;
        }
    }

    /**
     * 다른 프래그먼트에서 버튼 클릭 시 이 메소드를 통해 프래그먼트 교체가능.
     * @param fragment
     */
    public void replaceFragment(Fragment fragment,int id){
        //프래그먼트 시작.
        FragmentTransaction transaction=fragManager.beginTransaction();
        //프래그먼트로 교체.
        transaction.replace(R.id.frameLayout,fragment).commitAllowingStateLoss();
        bottomView.setSelectedItemId(id);
    }
}
