package zjut.utils;

import zjut.po.ScraprecordEntity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter {
    private String date_string;
    private Date date_util;

    private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TimeConverter(String date) throws ParseException {
        date_string=date;
        date_util=sdf.parse(date_string);
    }

    public TimeConverter(Date date){
        date_util=date;
        date_string=sdf.format(date_util);
    }

    public Date getDate(){
        return date_util;
    }

    public String getDate_Str(){
        return date_string;
    }

    public static Date getDate(String date_str){
        Date result=null;
        try{
            result=sdf.parse(date_str);
        }catch (ParseException pe){
            pe.printStackTrace();
        }
        return result;
    }

    public static String getDate_Str(Date date_util){
        return sdf.format(date_util);
    }

    public static void main(String[] args){
        Date test_date=new Date();
        // util.date 转 string 测试
        System.out.println(test_date);
        System.out.println(getDate_Str(test_date));
        // string 转 util.date 测试
        String test_date_str="2021-1-5 19:48:20";
        System.out.println(getDate(test_date_str));
    }
}
