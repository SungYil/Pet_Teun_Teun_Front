package com.pethospital.pet_teun_teun.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pethospital.pet_teun_teun.items.Post;
import com.pethospital.pet_teun_teun.R;

import java.util.ArrayList;

// iv ==ImageView,tv ==TextView

public class PostAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<Post> postList;

    // PostAdapter 생성자
    public PostAdapter() {
        postList = new ArrayList<Post>() ;
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return postList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        // "post" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.post, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView indextv =(TextView)convertView.findViewById(R.id.post_index);
        TextView titletv = (TextView) convertView.findViewById(R.id.post_title) ;
        TextView writertv = (TextView) convertView.findViewById(R.id.post_writer_nickname) ;
        TextView datetv = (TextView)convertView.findViewById(R.id.post_date);
        TextView commentCnttv= (TextView)convertView.findViewById(R.id.post_comment_cnt);
        TextView viewCnttv = (TextView)convertView.findViewById(R.id.post_view_cnt);
        ImageView iconiv = (ImageView) convertView.findViewById(R.id.post_img_view) ;
       // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        Post item = postList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        indextv.setText(item.getIndex()+"");
        titletv.setText(item.getTitle());
        writertv.setText(item.getWriterNickname());
        datetv.setText(item.getDate());
        commentCnttv.setText(item.getCommentCnt()+"");
        viewCnttv.setText(item.getCommentCnt()+"");
        //url을 가져와서 수정하는걸로 나중에 바꿔야한다.
        iconiv.setImageResource(R.drawable.sample);

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
        return postList.get(position) ;
    }
    /*
    Post info
    this.id = id;
        this.index = index;
        this.title = title;
        this.writerNickname = writerNickname;
        this.date = date;
        this.viewCnt = viewCnt;
        this.commentCnt = commentCnt;
        this.imgUrl = imgUrl;
     */
    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    //String id,int index,String title,String writerNickname,String date,int viewCnt,int commentCnt,String imgUrl
    public void addItem(Post post) {

        Post item = new Post(post);

        postList.add(item);
    }
}
