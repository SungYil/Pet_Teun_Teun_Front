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
        textView = findViewById(R.id.t_title);
        Button btn_login = findViewById(R.id.login_btn_login);
        Button btn_forget = findViewById(R.id.login_btn_forget);

        //TODO : 통신 및 이것저것

        check = 0;

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                String id = edit_email.getText().toString();
                String password = edit_password.getText().toString();


                ContentValues values = new ContentValues();
                values.put("id", id);
                values.put("password", password);
                String url="http://122.46.53.223:8080/Pet_Tuen_Tuen/login.do";
                NetworkTask networkTask=new NetworkTask(url,values);
                networkTask.execute();

                if(check==1) {
                    startActivity(intent);
                }
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
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection(getApplicationContext());
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
