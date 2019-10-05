package com.koei.utilsTool.string;

public class StringTool {

    public static boolean isNotEmpty(String str){
        if(str==null || "".equals(str) || str.length()==0 || "null".equals(str) || "NULL".equals(str)){
            return false;
        }
        return  true;
    }
}
