package com.pmbox.pm.service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateOperation {
	public static String getCurrentTime(){
		Date d = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy kk:mm:ss ");
		//MM-month,dd-day,yyyy-year;kk-hour,mm-minute,ss-second;		
		String time=sdf.format(d);
		return time;
	}
	
	public static String getCurrentDate(){
		Date d = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		//MM-month,dd-day,yyyy-year;kk-hour,mm-minute,ss-second;		
		String time=sdf.format(d);
		return time;
	}
	
	// Test main
	public static void main(String[] args){
		
		String str=DateOperation.getCurrentDate();

		System.out.println("The current date is : "+str);
	}
}
