package com.pethospital.pet_teun_teun;

import com.pethospital.pet_teun_teun.items.Post;

import java.util.ArrayList;

public class SampleMange {
    private ArrayList<Post> postList;
    private ArrayList<Comment> commentList;
    public SampleMange() {
        postList=new ArrayList<Post>();
        commentList=new ArrayList<Comment>();
        //임시로 만들어서 넣자.ㅋㅋㅋ
        //(id,index,title,writerNickname,date,viewCnt,commentCnt,imgUrl);
        for(int i=0;i<10;i++){
            postList.add(new Post("id1",i,"제목1","작성자1","작성날짜1",20,30,"이미지URL1"));

        }
        //String commentId,String writerImg,String writerNickname,String comment,String date
        for(int i=0;i<10;i++){
            commentList.add(new Comment("id","작성자썸네일","작성자별명","댓글내용","댓글날짜"));
        }

    }

    public void setPostList(ArrayList<Post> postList) {
        this.postList = postList;
    }

    public ArrayList<Post> getPostList() {
        return postList;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }
}
