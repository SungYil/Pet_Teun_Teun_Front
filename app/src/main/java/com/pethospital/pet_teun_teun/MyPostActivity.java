package com.pethospital.pet_teun_teun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.pethospital.pet_teun_teun.adapters.PostAdapter;
import com.pethospital.pet_teun_teun.items.Post;

import java.util.ArrayList;

public class MyPostActivity extends AppCompatActivity {

    private ListView listView;
    private PostAdapter postAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

        postAdapter=new PostAdapter();

        listView=(ListView)findViewById(R.id.my_post_post_list_view);
        listView.setAdapter(postAdapter);

        SampleMange sample=new SampleMange();
        ArrayList<Post> temp=sample.getPostList();

        for(int i=0;i<temp.size();i++){
            postAdapter.addItem(temp.get(i));
        }
    }
}
