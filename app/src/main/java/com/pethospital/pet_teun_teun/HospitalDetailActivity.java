package com.pethospital.pet_teun_teun;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pethospital.pet_teun_teun.servers.RequestHttpURLConnection;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class HospitalDetailActivity extends AppCompatActivity {

    private Button roadBtn;
    private Button reserBtn;
    private Button chatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_detail_activity);

        String name=getIntent().getStringExtra("hosName");
        roadBtn=findViewById(R.id.detail_hospital_road_btn);
        reserBtn=findViewById(R.id.detail_hospital_reservation_btn);
        chatBtn=findViewById(R.id.detail_hospital_chat_btn);

        roadBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(getApplicationContext(), NaviHospitalActivity.class);
                intent.putExtra("name",getIntent().getStringExtra("hosName"));
                startActivity(intent);
            }
        });


        ContentValues values = new ContentValues();
        values.put("hospitalName", name);
        String url=getString(R.string.url)+"hosDetail.do";
        NetworkTask networkTask=new NetworkTask(url,values);
        networkTask.execute();

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
            com.pethospital.pet_teun_teun.servers.RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection(getApplicationContext());
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            TextView name=(TextView)findViewById(R.id.hospital_detail_title);
            TextView loca=(TextView)findViewById(R.id.hospital_detail_location);
            TextView phone=(TextView)findViewById(R.id.hospital_detail_phone);
            try {
                JSONObject json = new JSONObject(s);
                if("ok".equals(json.getString("msg"))){
                    if("ok".equals(json.getString("existence"))){
                        name.setText(json.getString("name"));
                        loca.setText(json.getString("location"));
                        phone.setText(json.getString("phone"));
                        reserBtn.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                Intent intent=new Intent(getApplicationContext(), ReserveActivity.class);
                                startActivity(intent);
                            }
                        });
                    }else{
                        name.setText(getIntent().getStringExtra("hosName"));
                        loca.setText(getIntent().getStringExtra("phone"));
                        phone.setText(getIntent().getStringExtra("location"));
                        reserBtn.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v){
                                Toast.makeText(getApplicationContext(), "가입이 되있지 않은 병원입니다", Toast.LENGTH_LONG).show();
                            }
                        });

                        Toast.makeText(getApplicationContext(), "찾기 실패", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "통신 실패", Toast.LENGTH_LONG).show();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}