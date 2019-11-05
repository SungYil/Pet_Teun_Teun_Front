package com.pethospital.pet_teun_teun.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.items.ManageViewItem;
import com.pethospital.pet_teun_teun.servers.RequestHttpURLConnection;

import org.json.JSONObject;

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
        ImageView listImage = convertView.findViewById(R.id.list_image);
        TextView listName = convertView.findViewById(R.id.list_item_name);
        TextView listSubName = convertView.findViewById(R.id.list_item_sub_name);
        TextView listContents= convertView.findViewById(R.id.list_item_content);
        Button cancelBtn=convertView.findViewById(R.id.user_reserve_cancel_btn);

        /* 각 리스트에 뿌려줄 아이템을 받아오는데 mMyItem 재활용 */
        ManageViewItem myItem = getItem(position);

        /* 각 위젯에 세팅된 아이템을 뿌려준다 */
        listImage.setImageDrawable(myItem.getIcon());
        listName.setText(myItem.getName());
        listSubName.setText(myItem.getSubname());
        listContents.setText(myItem.getContent());

        cancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String removeID=mItems.get(position).getId();//삭제할아이디
                ContentValues values=new ContentValues();
                SharedPreferences preferences=context.getSharedPreferences("login",Context.MODE_PRIVATE);
                String memberID=preferences.getString("id","");
                values.put("removeID",removeID);
                values.put("memberID",memberID);
                //통신해서 서버에 해당 id의 예약이 사라졋다고 알려주자.
                String url=context.getString(R.string.url)+"userCancel.do";
                new NetworkTask(url,values,position).execute();
            }
        });

        /* (위젯에 대한 이벤트리스너를 지정하고 싶다면 여기에 작성하면된다..)  */
        return convertView;
    }
    public boolean addItem(String id,Drawable img,String name,String subName,String contents){
        return this.mItems.add(new ManageViewItem(id,img,name,subName,contents));
    }
    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;
        private int removeIndex;

        public NetworkTask(String url, ContentValues values,int removeIndex) {
            this.url = url;
            this.values = values;
            this.removeIndex=removeIndex;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progress bar를 보여주는 등등의 행위
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection=new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.

            try {
                if(s.equals("ok")== true){
                    mItems.remove(removeIndex);
                    notifyDataSetChanged();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
