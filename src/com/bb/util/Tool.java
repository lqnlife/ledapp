package com.bb.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;



public class Tool {

	
	public static String getNowTime(){
		
//		DateFormat dateFormatterChina = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);//格式化输出
//		TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取时区
//		dateFormatterChina.setTimeZone(timeZoneChina);//设置系统时区
		
		Date curDate = new Date();	//获取系统时间
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return  format2.format(curDate) ;
	}
	
}
