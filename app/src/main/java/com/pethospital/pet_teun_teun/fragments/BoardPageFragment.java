package com.pethospital.pet_teun_teun.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.SampleMange;
import com.pethospital.pet_teun_teun.adapters.PostAdapter;
import com.pethospital.pet_teun_teun.items.Post;

import java.util.ArrayList;

public class BoardPageFragment extends Fragment {
    private ListView listView;
    private PostAdapter postAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.board_page, container, false);
        listView= v.findViewById(R.id.board_post_list_view);
        postAdapter=new PostAdapter();
        listView.setAdapter(postAdapter);

        addData();
        return v;
    }
    void addData(){
        SampleMange sample=new SampleMange();
        ArrayList<Post> temp=sample.getPostList();

        for(int i=0;i<temp.size();i++){
            postAdapter.addItem(temp.get(i));
        }
    }
}