package com.pethospital.pet_teun_teun.fragments;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.items.ManageViewItem;
import com.pethospital.pet_teun_teun.items.ReserveCheckItem;
import com.pethospital.pet_teun_teun.servers.RequestHttpURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserMainFragment extends Fragment {

    private FragmentManager fragManager;
    private UserPetInfoFragment userInfo;
    private UserPetManageFragment userManage;

    private Fragment info;
    private Fragment manage;

    private Bundle data;

    private FragmentTransaction transaction;

    private View v;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.user_main_fragment, container, false);

        //프래그먼트에 프래그먼트들을 세팅하기위해 매니저를 불러온다.
        fragManager=getChildFragmentManager();
        transaction = fragManager.beginTransaction();

        data=getArguments();
        //통신코드
        ContentValues values = new ContentValues();
        //values.put("", id);
        //values.put("password", password);
        String url=getString(R.string.url)+"mainView.do";
        NetworkTask networkTask=new NetworkTask(url,values);
        networkTask.execute();

        if(data!=null) {
            Log.d("=================", data.getString("type"));
            if (data.getString("type").equals("hospital")) {
                info = new HospitalInfoFragment();
                manage = new HospitalMainbottomFragment();

            } else {
                info = new UserPetInfoFragment();
                manage = new UserPetManageFragment();
            }
        }

        return v;
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progress bar를 보여주는 등등의 행위
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection(getActivity());
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();

           try {
                JSONArray json=new JSONArray(s);
                //json ary에 병원 또는 유저에따른 상단 프래그먼트 정보
                JSONObject attr=json.getJSONObject(0);
                if("ok".equals(attr.getString("msg"))){
                    if("hospital".equals(data.getString("type"))){
                        data.putString("name",attr.getString("name"));
                        data.putString("address",attr.getString("address"));
                        data.putString("openTime",attr.getString("openTime"));
                        data.putString("closeTime",attr.getString("closeTime"));

                        //하단 프래그먼트 리스트 아이템
                        List<ReserveCheckItem> ary=new ArrayList<>();

                        for(int i=1;i<json.length();++i){
                            JSONObject emp=json.getJSONObject(i);
                            ary.add(new ReserveCheckItem(emp.getString("id"),emp.getString("type"),emp.getString("time"),emp.getString("careType"),emp.getString("name")));
                        }
                        data.putParcelableArrayList("list",(ArrayList<? extends Parcelable>)ary);
                    }else{
                        data.putString("petName",attr.getString("petName"));
                        data.putString("adopt",attr.getString("adopt"));
                        data.putString("birth",attr.getString("birth"));
                        data.putString("species",attr.getString("species"));

                        //하단 프래그먼트 리스트 아이템
                        List<ManageViewItem> ary=new ArrayList<>();

                        for(int i=2;i<json.length();++i){
                            JSONObject emp=json.getJSONObject(i);
                            Log.d("============",emp.toString());
                            ary.add(new ManageViewItem(ContextCompat.getDrawable(v.getContext(),R.drawable.hospital_icon),emp.getString("name"),emp.getString("subName"),emp.getString("contents")));
                        }
                        data.putParcelableArrayList("list",(ArrayList<? extends Parcelable>)ary);
                    }

                    //데이터 세팅
                    info.setArguments(data);
                    manage.setArguments(data);


                    //세팅을 하고 커밋을 하여 실제 완료
                    transaction.replace(R.id.top_user_main,info);
                    transaction.replace(R.id.bottom_user_main,manage).commitAllowingStateLoss();
                }else{

                    Toast.makeText(getActivity(), "데이터 로딩 실패", Toast.LENGTH_LONG).show();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
