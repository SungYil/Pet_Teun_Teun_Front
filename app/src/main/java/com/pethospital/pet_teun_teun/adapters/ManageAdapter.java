package com.pethospital.pet_teun_teun.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.items.ManageViewItem;

import java.util.ArrayList;

public class ManageAdapter extends BaseAdapter {
    private ArrayList<ManageViewItem> mItems;

    public ManageAdapter(){
        mItems=new ArrayList<ManageViewItem>();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public ManageViewItem getItem(int position){
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        /* 'list_item' Layout을 inflate하여 convertView 참조 획득 */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_pet_manage_list_item, parent, false);
        }

        /* 'list_item'에 정의된 위젯에 대한 참조 획득 */
        ImageView listImage = (ImageView) convertView.findViewById(R.id.list_image) ;
        TextView listName = (TextView) convertView.findViewById(R.id.list_item_name) ;
        TextView listSubName = (TextView) convertView.findViewById(R.id.list_item_sub_name) ;
        TextView listContents=(TextView)convertView.findViewById(R.id.list_item_content);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        ManageViewItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        listImage.setImageDrawable(myItem.getIcon());
        listName.setText(myItem.getName());
        listSubName.setText(myItem.getSubname());
        listContents.setText(myItem.getContent());

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }
    public boolean addItem(Drawable img,String name,String subName,String contents){
        return this.mItems.add(new ManageViewItem(img,name,subName,contents));
    }
}
