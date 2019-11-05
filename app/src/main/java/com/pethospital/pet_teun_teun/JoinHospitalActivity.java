package com.pethospital.pet_teun_teun;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pethospital.pet_teun_teun.servers.RequestHttpURLConnection;

import org.json.JSONObject;

public class JoinHospitalActivity extends AppCompatActivity {

    EditText edit_id;
    EditText edit_password;
    EditText edit_name;
    EditText edit_phone;
    Button btn_submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_hospital_activity);

        edit_id = findViewById(R.id.join_hospital_edit_id);
        edit_password = findViewById(R.id.join_hospital_edit_password);
        edit_name = findViewById(R.id.join_hospital_edit_name);
        edit_phone = findViewById(R.id.join_hospital_edit_phone);
        btn_submit = findViewById(R.id.join_hospital_btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put("id", edit_id.getText().toString());
                values.put("password", edit_password.getText().toString());
                values.put("name",edit_name.getText().toString());
                values.put("phoneNum",edit_phone.getText().toString());

                String url=getString(R.string.url)+"joinHos.do";
                NetworkTask networkTask=new NetworkTask(url,values);
                networkTask.execute();
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
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_LONG).show();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
