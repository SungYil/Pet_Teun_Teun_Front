package com.pethospital.pet_teun_teun;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pethospital.pet_teun_teun.servers.HttpClient;
import com.pethospital.pet_teun_teun.servers.RequestHttpURLConnection;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText edit_email;
    private EditText edit_password;

    private TextView textView;
    private int check;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        edit_email = findViewById(R.id.login_edit_email);
        edit_password = findViewById(R.id.login_edit_password);
        textView=findViewById(R.id.t_title);
        Button btn_login = findViewById(R.id.login_btn_login);
        Button btn_forget = findViewById(R.id.login_btn_forget);

        //TODO : 통신 및 이것저것

        check=0;

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                String id=edit_email.getText().toString();
                String password=edit_password.getText().toString();

                /*Map<String,String> values=new HashMap<String,String>();
                values.put("id",id);
                values.put("password",password);*/

                ContentValues values=new ContentValues();
                values.put("id",id);
                values.put("password",password);

                NetworkTask net=new NetworkTask("http://122.46.53.223:8080/Pet_Tuen_Tuen/login.do",values);
                net.execute();

                //new NetworkTask2().execute(values);

                if(check==1) {
                    startActivity(intent);
                }
            }
        });
    }

    public class NetworkTask2 extends AsyncTask<Map<String, String>, Integer, String> {
        @Override
        protected String doInBackground(Map<String, String>... maps) { // 내가 전송하고 싶은 파라미터

// Http 요청 준비 작업
            HttpClient.Builder http = new HttpClient.Builder("POST", "http://10.0.2.2:8080/Pet_Tuen_Tuen/login.do");
// Parameter 를 전송한다.
            http.addAllParameters(maps[0]);

//Http 요청 전송
            HttpClient post = http.create();
            post.request();

// 응답 상태코드 가져오기
            int statusCode = post.getHttpStatusCode();

// 응답 본문 가져오기
            String body = post.getBody();

            return body;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("------------------",s);
        }
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        String url;
        ContentValues values;

        NetworkTask(String url, ContentValues values){
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
            String result;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values);
            return result; // 결과가 여기에 담깁니다. 아래 onPostExecute()의 파라미터로 전달됩니다.
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // 통신이 완료되면 호출됩니다.
            // 결과에 따른 UI 수정 등은 여기서 합니다.

            try {

                textView.setText(result);
                Log.println(Log.DEBUG, "------------------", result);
                check=1;
            }catch (Exception e){
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        }
    }


}
