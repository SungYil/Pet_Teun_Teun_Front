package com.pethospital.pet_teun_teun;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pethospital.pet_teun_teun.servers.RequestHttpURLConnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class ReserveActivity extends AppCompatActivity {
    private Spinner doctorSpinner;
    private TextView reserveText;
    private TextView locationText;
    private TextView phoneNum;

    private ImageButton backBtn;

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        intent=getIntent();

        doctorSpinner=(Spinner)findViewById(R.id.reserve_doctor_dropdown);
        reserveText=findViewById(R.id.reservation_hospital_name);
        locationText=findViewById(R.id.reserve_hospital_location);
        phoneNum=findViewById(R.id.reserve_hospital_tel);
        backBtn=findViewById(R.id.reserve_back_button);

        reserveText.setText(intent.getStringExtra("name"));
        locationText.setText(intent.getStringExtra("location"));
        phoneNum.setText(intent.getStringExtra("phone"));

        String[] str=getResources().getStringArray(R.array.doctor_list);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_doctor,str);
        //adapter.setDropDownViewResource(R.layout.spinner_doctor);
        doctorSpinner.setAdapter(adapter);

        doctorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //i가 선택된 index,l이 id?
                Log.i("내용",doctorSpinner.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.i("내용","없다.");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
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

            try {
                JSONObject json = new JSONObject(s);
                if("ok".equals(json.getString("msg"))){

                    if("hospital".equals(json.getString("user"))){
                        intent.putExtra("type","hospital");
                        startActivity(intent);
                    }else if("user".equals(json.getString("user"))){
                        intent.putExtra("type","user");
                        startActivity(intent);
                    }else{

                    }

                }else{
                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_LONG).show();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
