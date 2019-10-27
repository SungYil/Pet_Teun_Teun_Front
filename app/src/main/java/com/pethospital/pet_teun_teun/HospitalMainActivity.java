package com.pethospital.pet_teun_teun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pethospital.pet_teun_teun.adapters.ReserveCheckAdapter;
import com.pethospital.pet_teun_teun.items.ReserveCheckItem;

import java.util.ArrayList;

public class HospitalMainActivity extends AppCompatActivity {
    private ListView listView;
    private ReserveCheckAdapter reserveCheckAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_main);

        listView=findViewById(R.id.hospital_main_reserve_list_view);
        reserveCheckAdapter=new ReserveCheckAdapter();
        listView.setAdapter(reserveCheckAdapter);
        addData();

        //아이디,비밀번호 저장한 객체
        ContentValues test=new ContentValues();
        test.put("id","jr30jr");
        test.put("pwd","qs1933qs");
        String url="http://1.231.53.49:2200/Pet_Tuen_Tuen/login.do";
        NetworkTask networkTask=new NetworkTask(url,test);
        networkTask.execute();
    }
    void addData(){
        ArrayList<ReserveCheckItem> sample=new SampleMange().getReserveList();
        for(int i=0;i<sample.size();i++){
            reserveCheckAdapter.addItem(sample.get(i));
        }
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }
}
