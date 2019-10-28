package com.pethospital.pet_teun_teun.items;

public class Comment {
    private String commentId;
    private String writerImg;
    private String writerNickname;
    private String comment;
    private String date;

    public Comment() {
    }
    public Comment(String commentId,String writerImg,String writerNickname,String comment,String date){
        this.commentId=commentId;
        this.writerImg=writerImg;
        this.writerNickname=writerNickname;
        this.comment=comment;
        this.date=date;
    }
    public Comment(Comment comment){
        setCommentId(comment.getCommentId());
        setWriterImg(comment.getWriterImg());
        setWriterNickname(comment.getWriterNickname());
        setComment(comment.getComment());
        setDate(comment.getDate());
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public void setWriterImg(String writerImg) {
        this.writerImg = writerImg;
    }

    public void setWriterNickname(String writerNickname) {
        this.writerNickname = writerNickname;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCommentId() {
        return commentId;
    }

    public String getWriterImg() {
        return writerImg;
    }

    public String getWriterNickname() {
        return writerNickname;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }
}
