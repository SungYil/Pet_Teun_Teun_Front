package com.pethospital.pet_teun_teun.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.items.AlarmItem;

import org.w3c.dom.Text;

import java.util.ArrayList;

// iv ==ImageView,tv ==TextView

public class AlarmAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<AlarmItem> alarmList;

    // PostAdapter 생성자
    public AlarmAdapter() {
        alarmList = new ArrayList<AlarmItem>() ;
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return alarmList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        // "post" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.alarm_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView titletv=(TextView)convertView.findViewById(R.id.alarm_item_title);
        TextView contnetstv=(TextView)convertView.findViewById(R.id.alarm_item_contents);
        TextView datetv=(TextView)convertView.findViewById(R.id.alarm_item_date);
        ImageView imgView=(ImageView)convertView.findViewById(R.id.alarm_item_img);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        AlarmItem item = alarmList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        titletv.setText(item.getTitle());
        datetv.setText(item.getDate());
        contnetstv.setText(item.getContents());
        //url을 가져와서 수정하는걸로 나중에 바꿔야한다.
        imgView.setImageResource(R.drawable.sample);

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
        return alarmList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
   public void addItem(AlarmItem alarmItem) {

        AlarmItem item=new AlarmItem(alarmItem);

        alarmList.add(item);
    }
}
