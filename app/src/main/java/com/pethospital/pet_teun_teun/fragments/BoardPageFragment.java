package com.pethospital.pet_teun_teun.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pethospital.pet_teun_teun.AddPostActivity;
import com.pethospital.pet_teun_teun.DetailPostActivity;
import com.pethospital.pet_teun_teun.MainActivity;
import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.SampleMange;
import com.pethospital.pet_teun_teun.SearchPostActivity;
import com.pethospital.pet_teun_teun.adapters.PostAdapter;
import com.pethospital.pet_teun_teun.items.Post;
import com.pethospital.pet_teun_teun.servers.RequestHttpURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BoardPageFragment extends Fragment {
    private ListView listView;
    private PostAdapter postAdapter;
    private ImageButton addPostBtn;
    private ImageButton searchBtn;
    private ImageButton myPostBtn;
    private View view;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.board_page, container, false);

        setting();
        setEvent();
        new NetworkProcess(view.getContext()).execute();
        return view;
    }
    void setting(){
        listView= view.findViewById(R.id.board_post_list_view);
        postAdapter=new PostAdapter();
        listView.setAdapter(postAdapter);

        //게시글 등록버튼
        addPostBtn=view.findViewById(R.id.board_page_add_post_btn);
        //내 게시글 보기버튼
        myPostBtn=view.findViewById(R.id.board_page_my_btn);
        //게시글 검색버튼
        searchBtn=view.findViewById(R.id.board_page_search_btn);
    }
    void setEvent(){
        addPostBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent addPost=new Intent(getContext(), AddPostActivity.class);
                startActivity(addPost);
            }
        });
        myPostBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SearchPostActivity.class);
                startActivity(intent);
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), SearchPostActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Post item=(Post)postAdapter.getItem(i);
                //Toast.makeText(view.getContext(), item.getId(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(view.getContext(), DetailPostActivity.class);
                intent.putExtra("postID",item.getId());
                startActivity(intent);
            }
        });
    }

    private class NetworkProcess extends AsyncTask<Void,Void,String>{
        private Context context;
        public NetworkProcess(Context context) {
            this.context=context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result=null;
            RequestHttpURLConnection requestHttpURLConnection=new RequestHttpURLConnection(getContext());
            String url=getString(R.string.url)+"getPostList.do";
            //Toast.makeText(context,url,Toast.LENGTH_SHORT).show();
            ContentValues values=new ContentValues();
            String id= getContext().getSharedPreferences("login", Context.MODE_PRIVATE).getString("id","");
            values.put("memberID",id);
            result=requestHttpURLConnection.request(url,values);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                JSONObject jsonObject=new JSONObject(s);
                JSONArray ary=jsonObject.getJSONArray("list");
                for(int i=0;i<ary.length();i++){
                    JSONObject object=ary.getJSONObject(i);
                    Toast.makeText(context,object.toString(),Toast.LENGTH_SHORT).show();
                    Post post=new Post();
                    //String id, int index, String title, String writerNickname, String date, int viewCnt, int commentCnt, String imgUrl
                    postAdapter.addItem(new Post(object.getString("id"),0,object.getString("title"),object.getString("writerNickname"),object.getString("date"),Integer.parseInt(object.getString("viewCnt")),Integer.parseInt(object.getString("commentCnt")),object.getString("imgUrl")));
                    postAdapter.notifyDataSetChanged();
                }
            }
            catch(Exception e){

            }

            //Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
        }
    }
}