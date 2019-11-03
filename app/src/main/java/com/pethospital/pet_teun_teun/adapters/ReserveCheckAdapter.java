package com.pethospital.pet_teun_teun.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pethospital.pet_teun_teun.R;
import com.pethospital.pet_teun_teun.items.ReserveCheckItem;
import com.pethospital.pet_teun_teun.servers.RequestHttpURLConnection;

import java.util.ArrayList;


public class ReserveCheckAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ReserveCheckItem> reservesList;

    // PostAdapter 생성자
    public ReserveCheckAdapter() {
        reservesList=new ArrayList<ReserveCheckItem>();
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return reservesList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        // "post" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.reserve_check_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView nameTv= convertView.findViewById(R.id.reserve_check_item_customer);
        TextView typeTv= convertView.findViewById(R.id.reserve_check_item_type);
        TextView dateTv= convertView.findViewById(R.id.reserve_check_item_time);
        TextView careTypeTv= convertView.findViewById(R.id.reserve_check_item_care_type);
        Button cancelBtn= convertView.findViewById(R.id.reserve_check_item_cancel_btn);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ReserveCheckItem item=reservesList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        nameTv.setText(item.getName()+"님");
        typeTv.setText(item.getType());
        dateTv.setText(item.getTime());
        careTypeTv.setText(item.getCareType());

        cancelBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String removeID=reservesList.get(position).getId();//삭제할아이디
                ContentValues values=new ContentValues();
                SharedPreferences preferences=context.getSharedPreferences("login",Context.MODE_PRIVATE);
                String memberID=preferences.getString("id","");
                values.put("reserveID",removeID);
                values.put("memberID",memberID);
                //통신해서 서버에 해당 id의 예약이 사라졋다고 알려주자.
                String url=context.getString(R.string.url)+"cancel.do";
                new NetworkProcss(url,values,position).execute();
            }
        });
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
        return reservesList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
   public void addItem(ReserveCheckItem reserveCheckItem) {

        ReserveCheckItem item=new ReserveCheckItem(reserveCheckItem);

        reservesList.add(item);
    }

    private class NetworkProcss extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;
        private int removeIndex;

        public NetworkProcss(String url, ContentValues values,int removeIndex) {
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
            if(s.equals("ok")== true){
                reservesList.remove(removeIndex);
                notifyDataSetChanged();
            }
        }

    }
}
