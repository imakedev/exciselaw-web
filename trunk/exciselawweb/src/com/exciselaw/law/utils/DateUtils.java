package com.exciselaw.law.utils;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.chrono.BuddhistChronology;

public class DateUtils implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static String getStringDateByDate(long dateTime){
		String date = "";
		if(dateTime > 0){
			DateTime dtISO = new DateTime(dateTime);
			date = dtISO.getDayOfMonth()+"/"+dtISO.getMonthOfYear()+"/"+dtISO.getYear();
		}
		return date;
	}
	
	public static String getStringDateByDateTh(long dateTime){
		String dateTh = "";
		if(dateTime > 0){
			DateTime dtISOTh = new DateTime(dateTime, BuddhistChronology.getInstance());
			dateTh = dtISOTh.getDayOfMonth()+"/"+dtISOTh.getMonthOfYear()+"/"+dtISOTh.getYear();
		}
		return dateTh;
	}
	

	public static Date getDateByStringDate(String date){
		Date dtISO = null;
		if(date != null && !"".equals(date)){
			String[] day = date.split("/");
			dtISO = new DateTime(Integer.parseInt(day[2]), Integer.parseInt(day[1]),Integer.parseInt(day[0]) , 0, 0, 0, 0).toDate();
		}
		return dtISO;
	}

	public static Date getDateByStringDateTh(String date){
		Date dtISOTh = null;
		if(date != null && !"".equals(date)){
			String[] day = date.split("/");
			dtISOTh = new DateTime(Integer.parseInt(day[2]), Integer.parseInt(day[1]),Integer.parseInt(day[0]) , 0, 0, 0, 0, BuddhistChronology.getInstance()).toDate();
		}
		return dtISOTh;
	}
	
	public static String getStringDateByStringDate(String date){
		String dtISO = "";
		if(date != null && !"".equals(date)){
			String[] day = date.split("/");
			dtISO = Integer.parseInt(day[0])+"/"+Integer.parseInt(day[1])+"/"+(Integer.parseInt(day[2])-543);
		}
		return dtISO;
	}
	
	public static void main (String arg[]){
		System.out.println("14/09/2554 : "+getDateByStringDate("14/09/2554"));
		System.out.println("14/09/2011 : "+getDateByStringDate("14/09/2011"));
	}
}
