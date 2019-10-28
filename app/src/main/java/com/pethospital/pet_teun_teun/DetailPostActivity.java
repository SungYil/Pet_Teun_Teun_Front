package com.pethospital.pet_teun_teun;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.pethospital.pet_teun_teun.adapters.CommentAdapter;
import com.pethospital.pet_teun_teun.items.Comment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailPostActivity extends AppCompatActivity {
    private ListView listView;
    private CommentAdapter commentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_post);

        listView=(ListView)findViewById(R.id.detail_comment_list_view);
        commentAdapter=new CommentAdapter();

        listView.setAdapter(commentAdapter);

        ArrayList<Comment> sample=new SampleMange().getCommentList();
        for(int i=0;i<sample.size();i++){
            commentAdapter.addItem(sample.get(i));
        }
    }

}
