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
import com.pethospital.pet_teun_teun.items.PetStateViewItem;
import com.pethospital.pet_teun_teun.items.VaccineViewItem;

import java.util.ArrayList;

public class VaccineAdapter extends BaseAdapter {
    private ArrayList<VaccineViewItem> myItems;

    public VaccineAdapter() {
        myItems = new ArrayList<VaccineViewItem>();
    }

    @Override
    public int getCount() {
        return myItems.size();
    }

    @Override
    public VaccineViewItem getItem(int position) {
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
            convertView = inflater.inflate(R.layout.user_vaccine_list_item, parent, false);

        }
        /* 'list_item'에 정의된 위젯에 대한 참조 획득 */
        ImageView listImage = convertView.findViewById(R.id.vaccin_img);
        TextView listDate = convertView.findViewById(R.id.vaccin_date);
        TextView listName = convertView.findViewById(R.id.vaccin_name);
        TextView listSubName = convertView.findViewById(R.id.vaccin_sub_name);
        TextView listWon= convertView.findViewById(R.id.vaccin_won);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        VaccineViewItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        listImage.setImageDrawable(myItem.getIcon());
        listDate.setText(myItem.getDate());
        listName.setText(myItem.getName());
        listSubName.setText(myItem.getSubName());
        listWon.setText(myItem.getWon());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }

    public boolean addItem(Drawable img, String date, String name, String subName,String won) {
        return this.myItems.add(new VaccineViewItem(img, date, name, subName,won));

    }
}