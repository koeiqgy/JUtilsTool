package com.koei.utilsTool.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class CompanyXy extends BaseRowModel {
    @ExcelProperty(value = "中支编码", index = 0)
    private String departCode;

    @ExcelProperty(value = "中支名称", index = 1)
    private String departName;

    @ExcelProperty(value = "中支地址", index = 2)
    private String departAddr;

    @ExcelProperty(value = "分公司编码", index = 3)
    private String companyCode;

    @ExcelProperty(value = "分公司名称", index = 4)
    private String companyName;

    @ExcelProperty(value = "分公司地址", index = 5)
    private String companyAddr;

    @ExcelProperty(value = "纬度", index = 6)
    private String lat;

    @ExcelProperty(value = "经度", index = 7)
    private String lon;

    @ExcelProperty(value = "城市代码", index = 8)
    private String cityCode;

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getDepartAddr() {
        return departAddr;
    }

    public void setDepartAddr(String departAddr) {
        this.departAddr = departAddr;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
