package com.pethospital.pet_teun_teun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.pethospital.pet_teun_teun.adapters.PostAdapter;
import com.pethospital.pet_teun_teun.items.Post;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {
    private ListView listView;
    private PostAdapter postAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_page);

        listView= findViewById(R.id.board_post_list_view);
        postAdapter=new PostAdapter();
        listView.setAdapter(postAdapter);

        addData();
    }
    void addData(){
        SampleMange sample=new SampleMange();
        ArrayList<Post> temp=sample.getPostList();

        for(int i=0;i<temp.size();i++){
            postAdapter.addItem(temp.get(i));
        }
    }
}
