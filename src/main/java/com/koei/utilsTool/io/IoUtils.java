package com.koei.utilsTool.io;


import java.io.*;

public class IoUtils {
    public static void main(String[] args) throws IOException{
        String path="./src/main/resources/人员信息.txt";
        StringBuffer chars= readChars(path);
        System.out.println(chars);
    }


    public static void readBytes(String path){
        try(InputStream ins=new FileInputStream(new File(path))){
            int availSize=ins.available();
            byte b_arr[]=new byte[availSize];
            while(ins.read(b_arr)!=-1){

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StringBuffer readChars(String path) throws IOException {
        StringBuffer sbf=new StringBuffer();
        File file=new File(path);
        try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)))){
                char c_arr[]=new char[1];
                while(br.read(c_arr)!=-1){
                   sbf.append(c_arr);
                }
        }
        return sbf;
    }
}
