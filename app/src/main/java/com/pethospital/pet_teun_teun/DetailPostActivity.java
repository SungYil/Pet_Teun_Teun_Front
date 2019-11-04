package com.pethospital.pet_teun_teun;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pethospital.pet_teun_teun.adapters.CommentAdapter;
import com.pethospital.pet_teun_teun.items.Comment;
import com.pethospital.pet_teun_teun.servers.RequestHttpURLConnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailPostActivity extends AppCompatActivity {
    private ListView listView;
    private CommentAdapter commentAdapter;
    private TextView titleTv;
    private TextView writerNicNameTv;
    private TextView dateTv;
    private TextView contentsTv;
    private TextView commentCntTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_post);
        setting();
        Intent intent=getIntent();
        String postID=intent.getStringExtra("postID");
        Toast.makeText(getApplicationContext(),postID,Toast.LENGTH_SHORT).show();
        new NetworkProcess(getApplicationContext(),postID).execute();
    }
    void setting(){
        listView= findViewById(R.id.detail_comment_list_view);
        commentAdapter=new CommentAdapter();
        listView.setAdapter(commentAdapter);
        titleTv=(TextView)findViewById(R.id.detail_post_title);
        writerNicNameTv=(TextView)findViewById(R.id.detail_post_writer_nickname);
        contentsTv=(TextView)findViewById(R.id.detail_post_contents_area);
        dateTv=(TextView)findViewById(R.id.detail_post_write_date);
        commentCntTv=(TextView)findViewById(R.id.detail_post_comment_cnt);
    }
    void addData(){
        ArrayList<Comment> sample=new SampleMange().getCommentList();
        for(int i=0;i<sample.size();i++){
            commentAdapter.addItem(sample.get(i));
        }
    }
    private class NetworkProcess extends AsyncTask<Void,Void,String> {
        private Context context;
        private String postID;
        public NetworkProcess(Context context,String postID) {
            this.context=context;
            this.postID=postID;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result=null;
            RequestHttpURLConnection requestHttpURLConnection=new RequestHttpURLConnection(context);
            String url=getString(R.string.url)+"getPostDetail.do";
            //Toast.makeText(context,url,Toast.LENGTH_SHORT).show();
            ContentValues values=new ContentValues();
            values.put("postID",postID);
            result=requestHttpURLConnection.request(url,values);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
            try{
                JSONObject jsonObject=new JSONObject(s);
                JSONArray ary=jsonObject.getJSONArray("comments");
                titleTv.setText(jsonObject.getString("title"));
                writerNicNameTv.setText(jsonObject.getString("writerNickname"));
                dateTv.setText(jsonObject.getString("date"));
                contentsTv.setText(jsonObject.getString("contents"));
                commentCntTv.setText(jsonObject.getString("commentCnt"));
                for(int i=0;i<ary.length();i++){
                    JSONObject object=ary.getJSONObject(i);
                    Toast.makeText(context,object.toString(),Toast.LENGTH_SHORT).show();
                }
            }
            catch(Exception e) {
                Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }
}
