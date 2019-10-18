package com.pethospital.pet_teun_teun.items;

public class Post {
    private String id;//식별자가 게시글 번호 아닐까?
    private int index;//게시글 번호
    private String title;
    private String writer;//이건 아마 안쓸거같다.게시글에 작성자id나오는건 보안상문제
    private String writerNickname;
    private String date;
    private int viewCnt;
    private int commentCnt;
    private String imgUrl;

    public Post() {
    }

    //test에 편하게 하려고 넣은 임시?
    public Post(Post item){
        this.id = item.getId();
        this.index = item.getIndex();
        this.title = item.getTitle();
        this.writerNickname = item.getWriterNickname();
        this.date = item.getDate();
        this.viewCnt = item.getViewCnt();
        this.commentCnt = item.getCommentCnt();
        this.imgUrl = item.getImgUrl();
    }
    public Post(String id, int index, String title, String writerNickname, String date, int viewCnt, int commentCnt, String imgUrl) {
        this.id = id;
        this.index = index;
        this.title = title;
        this.writerNickname = writerNickname;
        this.date = date;
        this.viewCnt = viewCnt;
        this.commentCnt = commentCnt;
        this.imgUrl = imgUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setWriterNickname(String writerNickname) {
        this.writerNickname = writerNickname;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public void setCommentCnt(int commentCnt) {
        this.commentCnt = commentCnt;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getDate() {
        return date;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public int getCommentCnt() {
        return commentCnt;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getWriterNickname() {
        return writerNickname;
    }
}
