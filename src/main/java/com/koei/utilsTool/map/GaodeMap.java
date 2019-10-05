package com.koei.utilsTool.map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GaodeMap {
    private static final  String key="b8ac3c53958caafffd547a3184e6fd14";
    public static void main(String[] args)  throws Exception{
        String addr="常州市九洲新家园24号、39号、40号";
        String addr2="济源市沁园路南段御驾综合办公楼";
        JSONObject obj=getMapData(addr);
        String location=(String)obj.get("location");
        String adcode=(String)obj.get("adcode");
        System.out.println("经纬度"+location);
        System.out.println("cityCode"+adcode);
        Object cityObj=(Object)obj.get("city");
        if(cityObj instanceof JSONArray){
            JSONArray city=(JSONArray)obj.get("city");
            if(city.size()>0){
                System.out.println(city.get(0));
            }
        }else if (cityObj instanceof  String){
            String city=(String)obj.get("city");
            System.out.println(city);
        }

    }


    public static JSONObject getMapData(String addr){
        //String addr="北京市朝阳区阜通东大街6号";
        String url = "https://restapi.amap.com/v3/geocode/geo?address="+addr+"&output=JSON&key="+key;

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);

            HttpResponse response = client.execute(request);
            Thread.sleep(500);
/*        System.out.println("Response Code: " +
                response.getStatusLine().getStatusCode());*/

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            String str = "";
            char []charArr=new char[2048];
            rd.read(charArr,0,charArr.length);
            str=new String(charArr);
/*        while((str = rd.readLine()) != null) {
            System.out.println(str);
            System.out.println(str.length());
        }*/
            JSONObject jsonObject = JSONObject.parseObject(str);
            JSONArray jsonArr= (JSONArray)jsonObject.get("geocodes");
            if(jsonArr==null){
                System.out.println("null的地址 "+addr+ " null的地图数据"+str);
                return new JSONObject();
            }else{
                JSONObject obj=(JSONObject)jsonArr.get(0);
                return obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return    null;
        }

/*        String city=(String)obj.get("city");
        String location=(String)obj.get("location");
        System.out.println("city"+city);
        System.out.println("location"+location);*/
    }

}
