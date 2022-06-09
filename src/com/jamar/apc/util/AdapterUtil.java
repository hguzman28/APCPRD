package com.jamar.apc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

public class AdapterUtil {

	public static String getCharFromBoolean(Boolean bool) {
		if (bool.booleanValue()) {
			return "V";
		} else {
			return "F";
		}

	}

	public static Date toDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return new Date(calendar.toGregorianCalendar().getTimeInMillis());
	}
	
	public static Date stringToDate(String fecha) {
		SimpleDateFormat format =new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aaa");
		
		try {
			return format.parse(fecha);
		} catch (ParseException e) {
			format =new SimpleDateFormat("MM/dd/yyyy");
			try{
				return format.parse(fecha);
			}catch(ParseException e1){
				e1.printStackTrace();
				return null;
			}
		}
		
		
	}
	
	public static Date stringToDateDayFirst(String fecha) {
		SimpleDateFormat format =new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aaa");
		
		try {
			return format.parse(fecha);
		} catch (ParseException e) {
			format =new SimpleDateFormat("dd/MM/yyyy");
			try{
				return format.parse(fecha);
			}catch(ParseException e1){
				e1.printStackTrace();
				return null;
			}
		}
		
		
	}	
	
	
	
	
	
	
	public static Date stringNoHourToDate(String fecha) {
		SimpleDateFormat format =new SimpleDateFormat("MM/dd/yyyy");
		try {
			return format.parse(fecha);
		} catch (ParseException e) {
			return stringToDate(fecha);
		}
		
		
	}

}
