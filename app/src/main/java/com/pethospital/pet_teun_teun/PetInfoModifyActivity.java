package com.pethospital.pet_teun_teun;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class PetInfoModifyActivity extends AppCompatActivity {
    private EditText edit_pet_name;
    private EditText edit_pet_birth;
    private EditText edit_adopt_day;
    private EditText edit_age;
    private EditText edit_species;
    private Button joinBtn;
    private Button cancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_info_modify_activity);
        Intent intent = getIntent();

        edit_pet_name = findViewById(R.id.pet_modify_info_name);
        edit_pet_birth = findViewById(R.id.pet_modify_info_birth);
        edit_adopt_day = findViewById(R.id.pet_modify_info_adopt);
        edit_age = findViewById(R.id.pet_modify_info_age);
        edit_species = findViewById(R.id.pet_modify_info_type);
        joinBtn = findViewById(R.id.pet_modify_info_modify_btn);
        cancel=findViewById(R.id.pet_modify_info_cancel_btn);

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                SharedPreferences preferences=getApplicationContext().getSharedPreferences("login", Context.MODE_PRIVATE);
                String memberID=preferences.getString("id","");
                values.put("id",memberID);
                values.put("pet_name", edit_pet_name.getText().toString());
                values.put("pet_birth", edit_pet_birth.getText().toString());
                values.put("adopt_day", edit_adopt_day.getText().toString());
                values.put("pet_age", edit_age.getText().toString());
                values.put("species", edit_species.getText().toString());

                String url = getString(R.string.url) + "petModify.do";
                NetworkTask networkTask = new NetworkTask(url, values);
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
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection(getApplicationContext());
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.


            try {
                JSONObject att=new JSONObject(s);
                if("ok".equals(att.get("msg"))){
                    onBackPressed();
                }else{
                    Toast.makeText(getApplicationContext(), "수정실패", Toast.LENGTH_LONG).show();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
