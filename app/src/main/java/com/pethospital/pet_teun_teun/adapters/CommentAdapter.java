package com.pethospital.pet_teun_teun.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.items.Comment;

import java.util.ArrayList;

// iv ==ImageView,tv ==TextView

public class CommentAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<Comment> commentList;

    // PostAdapter 생성자
    public CommentAdapter() {
        commentList = new ArrayList<Comment>();
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return commentList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        // "post" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comment, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
       ImageView writerImg= convertView.findViewById(R.id.comment_writer_img);
        TextView writerNicknametv= convertView.findViewById(R.id.comment_writer_nickname);
        TextView commenttv= convertView.findViewById(R.id.comment_contents_area);
        TextView datetv= convertView.findViewById(R.id.comment_date);
       // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Comment item = commentList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        //url을 가져와서 수정하는걸로 나중에 바꿔야한다.
        writerImg.setImageResource(R.drawable.sample);
        writerNicknametv.setText(item.getWriterNickname());
        commenttv.setText("heello");
        datetv.setText(item.getDate());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return commentList.get(position) ;
    }
    /*

     */
    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    //String id,int index,String title,String writerNickname,String date,int viewCnt,int commentCnt,String imgUrl
    public void addItem(Comment comment) {

        Comment item = new Comment(comment);

        commentList.add(item);
    }
}
