package com.koei.utilsTool.main;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.koei.utilsTool.dto.CompanyXy;
import com.koei.utilsTool.excel.ExcelListener;
import com.koei.utilsTool.map.GaodeMap;
import com.koei.utilsTool.string.StringTool;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{
        testExcel2003WithReflectModel();
    }
    public static void testExcel2003WithReflectModel() {
        //InputStream inputStream = getInputStream("loan1.xls");
        FileInputStream inputStream =null;
        try {
            inputStream = new FileInputStream("C:\\Users\\hld20190131\\Documents\\WeChat Files\\koei1990\\FileStorage\\File\\2019-08\\分公司中支.xlsx");
            // 解析每行结果在listener中处理
            AnalysisEventListener listener = new ExcelListener();

            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);

            excelReader.read(new Sheet(1, 1, CompanyXy.class));
            List<Object> list=((ExcelListener) listener).getDatas();
            List<CompanyXy> listWrite=new ArrayList<CompanyXy>();
            for(Object obj:list){
                CompanyXy companyXy=(CompanyXy)obj;
                String code=companyXy.getDepartCode();
                String name=companyXy.getDepartName();
                String addr=companyXy.getDepartAddr();
                if(StringTool.isNotEmpty(addr)){
                    addr=addr.replaceAll("#","");
                    JSONObject jsonObj= GaodeMap.getMapData(addr);
                    if(jsonObj!=null){
                        //如果请求高德返回的不为空则更新城市代码 和 经纬度坐标
                        Object cityObj=(Object)jsonObj.get("city");
                        String cityStr="";
                        if(cityObj instanceof JSONArray){
                            JSONArray city=(JSONArray)jsonObj.get("city");
                            if(city.size()>0){
                                System.out.println(city.get(0));
                                cityStr=(String)city.get(0);
                            }else{
                                System.out.println("city size ==0");
                            }
                        }else if (cityObj instanceof  String){
                            String city=(String)jsonObj.get("city");
                            cityStr=city;
                        }
                        JSONObject jsonObj2= GaodeMap.getMapData(cityStr);
                        String location=(String)jsonObj.get("location");
                        if(StringTool.isNotEmpty(location)){
                            String []str=location.split(",");
                            if(str!=null && str.length==2){
                                companyXy.setLon(str[0]);
                                companyXy.setLat(str[1]);
                            }
                        }
                        String adcode=(String)jsonObj2.get("adcode");
                        companyXy.setCityCode(adcode);
                        System.out.println("经纬度"+location);
                        System.out.println("cityCode"+adcode);
                    }
                }
                listWrite.add(companyXy);
            }

            writeExcel(listWrite);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeExcel(List<CompanyXy> list) throws FileNotFoundException {
        OutputStream out = new FileOutputStream("C:\\App\\workSpace\\xy2.xlsx");
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet1 = new Sheet(1, 0,CompanyXy.class);
            writer.write(list, sheet1);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
