package com.pethospital.pet_teun_teun.fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.pethospital.pet_teun_teun.MainActivity;
import com.pethospital.pet_teun_teun.PetStateViewActivity;
import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.ReservationCommitActivity;
import com.pethospital.pet_teun_teun.UserVaccinActivity;
import com.pethospital.pet_teun_teun.adapters.ManageAdapter;
import com.pethospital.pet_teun_teun.items.ManageViewItem;
import com.pethospital.pet_teun_teun.servers.RequestHttpURLConnection;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserPetManageFragment extends Fragment {

    private ImageButton stateView;
    private ImageButton reserConfirm;
    private ImageButton vaccin;
    private ImageButton hospital;

    private ListView manageList;

    private ManageAdapter myAdap;

    private Bundle bundle;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.user_pet_manage_fragment, container, false);
        stateView= v.findViewById(R.id.state_view);
        reserConfirm= v.findViewById(R.id.reservation_confirm);
        vaccin= v.findViewById(R.id.vaccin_confirm);
        hospital= v.findViewById(R.id.hospital_match);

        manageList= v.findViewById(R.id.manage_list);

        bundle=getArguments();

        dataSetting(v);

        stateView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), PetStateViewActivity.class);
                startActivity(intent);
            }
        });

        reserConfirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), ReservationCommitActivity.class);
                startActivity(intent);
            }
        });

        vaccin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getActivity(), UserVaccinActivity.class);
                startActivity(intent);
            }
        });

        hospital.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Fragment frg=new MatchingMainFragment();
                Bundle temp=new Bundle();
                temp.putString("petName",bundle.getString("petName"));
                temp.putString("adopt",bundle.getString("adopt"));
                temp.putString("birth",bundle.getString("birth"));
                temp.putString("species",bundle.getString("species"));
                frg.setArguments(temp);
                ((MainActivity)getActivity()).replaceFragment(frg,R.id.hospitalItem);
            }
        });


        return v;
    }

    private void dataSetting(View v){
         myAdap=new ManageAdapter();

        List<ManageViewItem> ary= bundle.getParcelableArrayList("list");

        if(ary!=null){
            for(int i=0;i<ary.size();++i){
                ManageViewItem item=ary.get(i);
                myAdap.addItem(item.getIcon(),item.getName(),item.getSubname(),item.getContent());
            }
        }

        /*myAdap.addItem(ContextCompat.getDrawable(v.getContext(),R.drawable.hospital_icon),"xx병원","내용","이상무");
        myAdap.addItem(ContextCompat.getDrawable(v.getContext(),R.drawable.hospital_icon),"yy병원","내용","이상무");
        myAdap.addItem(ContextCompat.getDrawable(v.getContext(),R.drawable.hospital_icon),"ww병원","내용","이상무");
        myAdap.addItem(ContextCompat.getDrawable(v.getContext(),R.drawable.hospital_icon),"qq병원","내용","이상무");
        myAdap.addItem(ContextCompat.getDrawable(v.getContext(),R.drawable.hospital_icon),"zz병원","내용","이상무");*/

        manageList.setAdapter(myAdap);
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
                /*JSONObject json = new JSONObject(s);
                if("ok".equals(json.getString("msg"))){
                    petName.setText(json.getString("petName"));
                    adopt.setText(json.getString("adopt"));
                    birth.setText(json.getString("birth"));
                    species.setText(json.getString("species"));

                }else{
                    petName.setText("empty");
                    adopt.setText("empty");
                    birth.setText("empty");
                    species.setText("empty");

                    Toast.makeText(getActivity(), "데이터 로딩 실패", Toast.LENGTH_LONG).show();
                }
*/
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
