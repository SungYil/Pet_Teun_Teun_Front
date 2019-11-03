package com.pethospital.pet_teun_teun.servers;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.naver.maps.geometry.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SearchLocation {
    private String clientID;
    private String clientSecret;

    public SearchLocation(){
        this.clientID="k9wjago0ne";
        this.clientSecret="KehzEENgrzC3ODbnsV2fSTRYPV4kRLKG4Fqtt43T";
    }

    public ArrayList<JSONObject> searchLocation(String location,Double lon,Double lat){
        String apiURL="https://naveropenapi.apigw.ntruss.com/map-place/v1/search?query=동물병원&coordinate=127.12345,37.12345";
        try {
            String text = URLEncoder.encode(location, "UTF-8");
            String coor = URLEncoder.encode(lon + "," + lat, "UTF-8");
            apiURL = "https://naveropenapi.apigw.ntruss.com/map-place/v1/search?query=" + text + "&coordinate=" + coor;
        }catch(Exception e){
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put("url", apiURL);
        values.put("location",location);
        values.put("type","location");
      //  String url=getString(R.string.url)+"mainView.do";
        NetworkTask networkTask=new NetworkTask(values);

        ArrayList<JSONObject> value=null;
        try{
            String ob=networkTask.execute().get();
            JSONObject obj=new JSONObject(ob);
            int count=obj.getJSONObject("meta").getInt("count");
            if(count!=0){
                value=new ArrayList<JSONObject>();
                JSONArray ary=obj.getJSONArray("places");
                for(int i=0;i<ary.length();++i){
                    value.add(ary.getJSONObject(i));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
    public ArrayList<LatLng> searchDirection(Double stLng, Double stLat, Double edLng, Double edLat){
        ArrayList<LatLng> list=new ArrayList<LatLng>();

        String apiURL="https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start="+stLng+","+stLat+"&goal=127.12345,37.12345&option=trafast";
        try {
            String text = URLEncoder.encode(stLng+","+stLat, "UTF-8");
            String coor = URLEncoder.encode(edLng + "," + edLat, "UTF-8");
            apiURL = "https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=" + text + "&goal=" + coor+"&option=trafast";

            ContentValues values = new ContentValues();
            values.put("url", apiURL);
            NetworkTask networkTask=new NetworkTask(values);
            String load=networkTask.execute().get();
            JSONObject obj=new JSONObject(load);
            if(0==obj.getInt("code")){
                JSONArray loads=obj.getJSONObject("route").getJSONArray("trafast").getJSONObject(0).getJSONArray("path");

                for(int i=0;i<loads.length();++i) {
                    JSONArray spot=loads.getJSONArray(i);
                    list.add(new LatLng(spot.getDouble(1),spot.getDouble(0)));
                }
            }
            //결과값 롱 랫 순서
            //뿌릴땐 랫 롱
        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<String> searchDirectionGuide(Double stLng, Double stLat, Double edLng, Double edLat){
        ArrayList<String> list=new ArrayList<String>();

        String apiURL="https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start="+stLng+","+stLat+"&goal=127.12345,37.12345&option=trafast";
        try {
            String text = URLEncoder.encode(stLng+","+stLat, "UTF-8");
            String coor = URLEncoder.encode(edLng + "," + edLat, "UTF-8");
            apiURL = "https://naveropenapi.apigw.ntruss.com/map-direction-15/v1/driving?start=" + text + "&goal=" + coor+"&option=trafast";

            ContentValues values = new ContentValues();
            values.put("url", apiURL);
            NetworkTask networkTask=new NetworkTask(values);
            String load=networkTask.execute().get();
            JSONObject obj=new JSONObject(load);
            if(0==obj.getInt("code")){
                JSONArray loads=obj.getJSONObject("route").getJSONArray("trafast").getJSONObject(0).getJSONArray("guide");

                for(int i=0;i<loads.length();++i) {
                    JSONObject spot=loads.getJSONObject(i);
                    list.add(spot.getString("instructions"));
                }
            }
            //결과값 롱 랫 순서
            //뿌릴땐 랫 롱
        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }


    public class NetworkTask extends AsyncTask<Void, Void, String> {

       // private String url;
        private ContentValues values;

        public NetworkTask(ContentValues values) {

           // this.url = url;
            this.values = values;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progress bar를 보여주는 등등의 행위
        }

        @Override
        protected String doInBackground(Void... params) {

            String result=""; // 요청 결과를 저장할 변수.

            try {
                //String apiURL = "https://openapi.naver.com/v1/search/local.json?query="+ text; // json 결과
                //String apiURL = "https://openapi.naver.com/v1/search/loaction.xml?query="+ text; // xml 결과
                URL url = new URL(values.get("url").toString());
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientID);
                con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if(responseCode==200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                result=response.toString();
                Log.d("@@@@@@",response.toString());
            } catch (Exception e) {
                Log.d("@@@@@@","searching fail");
                e.printStackTrace();
            }

            //result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.


            try {

                JSONObject obj=new JSONObject(s);
                //JSONArray items=obj.getJSONArray("items");

                //JSONObject loca=items.getJSONObject(0);

                //Log.d("aaaaaaaaaaaaaaaa",loca.getString("mapx"));

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


}
