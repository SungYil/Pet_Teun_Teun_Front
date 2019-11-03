package com.pethospital.pet_teun_teun.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.items.MatchingViewItem;
import com.pethospital.pet_teun_teun.items.PetStateViewItem;

import java.util.ArrayList;

public class PetStateAdapter extends BaseAdapter {
    private ArrayList<PetStateViewItem> myItems;

    public PetStateAdapter() {
        myItems = new ArrayList<PetStateViewItem>();
    }

    @Override
    public int getCount() {
        return myItems.size();
    }

    @Override
    public PetStateViewItem getItem(int position) {
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
            convertView = inflater.inflate(R.layout.pet_state_list_item, parent, false);

        }
        /* 'list_item'에 정의된 위젯에 대한 참조 획득 */
        ImageView listImage = convertView.findViewById(R.id.pet_state_icon);
        TextView listDate = convertView.findViewById(R.id.pet_state_date);
        TextView listName = convertView.findViewById(R.id.pet_state_name);
        TextView listSubName = convertView.findViewById(R.id.pet_state_sub_name);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        PetStateViewItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        listImage.setImageDrawable(myItem.getIcon());
        listDate.setText(myItem.getDate());
        listName.setText(myItem.getName());
        listSubName.setText(myItem.getSubName());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }

    public boolean addItem(Drawable img, String date, String name, String subName) {
        return this.myItems.add(new PetStateViewItem(img, date, name, subName));

    }
}