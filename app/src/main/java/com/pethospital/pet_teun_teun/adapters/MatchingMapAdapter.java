package com.pethospital.pet_teun_teun.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.items.MatchingViewItem;

import java.util.ArrayList;

public class MatchingMapAdapter extends BaseAdapter {
    private ArrayList<MatchingViewItem> myItems;

    public MatchingMapAdapter() {
        myItems = new ArrayList<MatchingViewItem>();
    }

    @Override
    public int getCount() {
        return myItems.size();
    }

    @Override
    public MatchingViewItem getItem(int position) {
        return myItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        /* 'list_item' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reservation_commit_list_item, parent, false);
        }

        /* 'list_item'에 정의된 위젯에 대한 참조 획득 */
        RatingBar listRating=(RatingBar)convertView.findViewById(R.id.matching_item_rating);
        TextView listOpenTime = (TextView) convertView.findViewById(R.id.matching_item_time);
        TextView listName = (TextView) convertView.findViewById(R.id.matching_item_name);
        TextView listKm = (TextView) convertView.findViewById(R.id.matching_item_km);
        TextView listNum = (TextView) convertView.findViewById(R.id.matching_item_num);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        MatchingViewItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        listRating.setNumStars(myItem.getRating());
        listOpenTime.setText(myItem.getOpenTime());
        listName.setText(myItem.getName());
        listKm.setText(myItem.getKm());
        listNum.setText(myItem.getPhoneNum());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }

    public boolean addItem(String name, String km, String phoneNum, String openTime,int rating) {
        return this.myItems.add(new MatchingViewItem(name,km,phoneNum,openTime,rating));

    }
}