package com.pethospital.pet_teun_teun.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.items.CommitViewItem;
import com.pethospital.pet_teun_teun.items.ManageViewItem;

import java.util.ArrayList;

public class CommitReservationAdapter extends BaseAdapter {
    private ArrayList<CommitViewItem> myItems;

    public CommitReservationAdapter() {
        myItems = new ArrayList<CommitViewItem>();
    }

    @Override
    public int getCount() {
        return myItems.size();
    }

    @Override
    public CommitViewItem getItem(int position) {
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
        ImageView listImage = (ImageView) convertView.findViewById(R.id.reservation_list_image);
        TextView listdate = (TextView) convertView.findViewById(R.id.reservation_list_date);
        TextView listName = (TextView) convertView.findViewById(R.id.reservation_list_name);
        TextView listSubName = (TextView) convertView.findViewById(R.id.reservation_list_sub_name);
        TextView listContents = (TextView) convertView.findViewById(R.id.reservation_list_contents);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        CommitViewItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        listImage.setImageDrawable(myItem.getIcon());
        listdate.setText(myItem.getDate());
        listName.setText(myItem.getName());
        listSubName.setText(myItem.getSubName());
        listContents.setText(myItem.getContents());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }

    public boolean addItem(Drawable img, String date, String name, String subName, String contents) {
        return this.myItems.add(new CommitViewItem(img, date, name, subName, contents));

    }
}
