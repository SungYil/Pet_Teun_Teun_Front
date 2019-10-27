package com.pethospital.pet_teun_teun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.pethospital.pet_teun_teun.adapters.ReserveCheckAdapter;
import com.pethospital.pet_teun_teun.items.ReserveCheckItem;

import java.util.ArrayList;

public class HospitalMainActivity extends AppCompatActivity {
    private ListView listView;
    private TextView temp;
    private ReserveCheckAdapter reserveCheckAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_main);

        listView=findViewById(R.id.hospital_main_reserve_list_view);
        reserveCheckAdapter=new ReserveCheckAdapter();
        listView.setAdapter(reserveCheckAdapter);
        addData();

        String url="https://www.naver.com/";
        NetworkTask networkTask=new NetworkTask(url,null);
        networkTask.execute();
        temp=(TextView)findViewById(R.id.test_text);
    }
    void addData(){
        ArrayList<ReserveCheckItem> sample=new SampleMange().getReserveCheckItems();
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
            temp.setText(s);
        }
    }
}
