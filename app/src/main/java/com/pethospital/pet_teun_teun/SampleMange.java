package com.pethospital.pet_teun_teun;

import com.pethospital.pet_teun_teun.items.AlarmItem;
import com.pethospital.pet_teun_teun.items.Comment;
import com.pethospital.pet_teun_teun.items.Post;

import java.util.ArrayList;

public class SampleMange {
    private ArrayList<Post> postList;
    private ArrayList<Comment> commentList;
    private ArrayList<AlarmItem> alarmList;
    public SampleMange() {
        postList=new ArrayList<Post>();
        commentList=new ArrayList<Comment>();
        alarmList=new ArrayList<AlarmItem>();

        //임시로 만들어서 넣자.ㅋㅋㅋ
        //(id,index,title,writerNickname,date,viewCnt,commentCnt,imgUrl);
        for(int i=0;i<10;i++){
            postList.add(new Post("id1",i,"제목1","작성자1","작성날짜1",20,30,"이미지URL1"));

        }
        //String commentId,String writerImg,String writerNickname,String comment,String date
        for(int i=0;i<10;i++){
            commentList.add(new Comment("id","작성자썸네일","작성자별명","댓글내용","댓글날짜"));
        }
        //String id,String imgUrl,String title,String contents,String date
        for(int i=0;i<10;i++){
            alarmList.add(new AlarmItem("id","임시url","예약이 접수되었습니다.","xx병원예약 접수되었다.","2019/09/20"));
        }

    }


    public ArrayList<Post> getPostList() {
        return postList;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public ArrayList<AlarmItem> getAlarmList() {
        return alarmList;
    }

}
