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

public class JoinUser2Activity extends AppCompatActivity {
    private EditText edit_pet_name;
    private EditText edit_pet_birth;
    private EditText edit_adopt_day;
    private EditText edit_age;
    private EditText edit_species;
    private Button joinBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_user2_activity);
        Intent intent = getIntent();

        edit_pet_name=findViewById(R.id.join_user2_edit_name);
        edit_pet_birth=findViewById(R.id.join_user2_edit_birth);
        edit_adopt_day=findViewById(R.id.join_user2_edit_adopt);
        edit_age=findViewById(R.id.join_user2_edit_age);
        edit_species=findViewById(R.id.join_user2_edit_species);
        joinBtn=findViewById(R.id.join_user2_btn_submit);


        joinBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("!!!!!!!!!!",intent.getStringExtra("id")+intent.getStringExtra("password")+intent.getStringExtra("name")+intent.getStringExtra("phone"));
                Log.d("!!!!!!!!!!!2",edit_pet_name.getText().toString()+edit_pet_birth.getText().toString()+edit_adopt_day.getText().toString()+edit_age.getText().toString()+edit_species.getText().toString());

                ContentValues values = new ContentValues();
                values.put("id", intent.getStringExtra("id"));
                values.put("password", intent.getStringExtra("password"));
                values.put("name",intent.getStringExtra("name"));
                values.put("phoneNum",intent.getStringExtra("phone"));
                values.put("pet_name",edit_pet_name.getText().toString());
                values.put("pet_birth",edit_pet_birth.getText().toString());
                values.put("adopt_day",edit_adopt_day.getText().toString());
                values.put("pet_age",edit_age.getText().toString());
                values.put("species",edit_species.getText().toString());

                String url=getString(R.string.url)+"join.do";
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
                        if("ok".equals(json.getString("petMsg"))){
                            startActivity(intent);
                        }else{
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "동물정보 입력 실패", Toast.LENGTH_LONG).show();
                        }
                }else{
                    Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_LONG).show();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}
