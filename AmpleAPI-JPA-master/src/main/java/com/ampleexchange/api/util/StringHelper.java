package com.ampleexchange.api.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class StringHelper {
	
  public final static boolean isInteger(String str){
	if (isNumeric(str)){
	  Pattern pattern = Pattern.compile("[0-9]*");
      return pattern.matcher(str).matches();
	} else {
	  return false;	
	}
  }
	
  public static double toDbl(String str){
	if(str.trim().equals("")){
	  return 0;
	}else{
	  return Double.parseDouble(str.trim());
	}
  }
	
  public static boolean isEmpty(String str){
	if (str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("null"))
	  return true;
	else
	  return false;
  }
  
  public static String getSystemTime(String timeFormat) {
	return getTime(timeFormat, 0);
  }  
  

  public static String getTime(String timeFormat, long mSeconds) {
		Date nowTime = new Date(System.currentTimeMillis() + mSeconds); //300000);
		SimpleDateFormat sdFormatter = new SimpleDateFormat(timeFormat);
		String formattedNowTime = sdFormatter.format(nowTime);
		return formattedNowTime;
  }
  
  public static String getFixedLengthStr(String oriStr, int length, String repStr){
	StringBuffer tmpB = new StringBuffer(oriStr);
	if (oriStr.length()>=length){
		return oriStr.substring(0, length);
	} else {
		for (int i=0; i<length-oriStr.length(); i++)
			tmpB.append(repStr);
		return tmpB.toString();
	}	  
  }
  
  public static String getFixedLengthStr(String oriStr, int length){
	return getFixedLengthStr(oriStr, length, " "); 
  } 
  
  public static String mockPageStr(int curPage, int total, int numPerPage){
	int pages = -1;
	if (0 == total % numPerPage){
		pages = total/numPerPage;
	} else {
		pages = total/numPerPage + 1;
	}
	return (new StringBuffer(String.valueOf(curPage))).append("/").append(pages).toString();
  }
  
	public static boolean isNumeric(String str){
	  boolean retVal = false;
		if (str != null) {
		  try {  
		    double d = Double.parseDouble(str);
		    retVal=true;
		  } catch(Exception e){  
		  }  
		}
	  return retVal;  
	}
	
	public static boolean isGreaterThan(String str, double baseNum){
		boolean retVal = false;
		if (str != null) {
		  try {  
		    double tmp = Double.parseDouble(str);
		    if (tmp > baseNum)
		    	retVal = true;
		  } catch(Exception e){  
		  }
		}
	  return retVal;  
	}
	
	public static boolean isGreaterOrEqual(String str, double baseNum){
		boolean retVal = false;
		if (str != null) {
		  try {  
		    double tmp = Double.parseDouble(str);
		    if (tmp >= baseNum)
		    	retVal = true;
		  } catch(Exception e){  
		  }
		}
	  return retVal;  
	}
	
	public static boolean isSmallerThan(String str, double baseNum){
		boolean retVal = false;
	  try {  
	    double tmp = Double.parseDouble(str);
	    if (tmp < baseNum)
	    	retVal = true;
	  } catch(Exception e){  
	  }  
	  return retVal;  
	}
	
	public static boolean isSmallerOrEqual(String str, double baseNum){
		boolean retVal = false;
	  try {  
	    double tmp = Double.parseDouble(str);
	    if (tmp <= baseNum)
	    	retVal = true;
	  } catch(Exception e){  
	  }  
	  return retVal;  
	}
	
	// Valid DAT data starts from 20081010 to the present
	public static boolean isValidYear(String str){
		boolean retVal = false;
	  try {  
	    double tmp = Integer.parseInt(str);
	    if ( tmp > 2007 ) {
	    	Calendar cal = Calendar.getInstance();
	      int year = cal.get(Calendar.YEAR);	    	
	    	if (tmp <= year) {
	    		retVal = true;
	    	}
	    }
	  } catch(Exception e){  
	  }  
	  return retVal;  
	}
  
	public static java.sql.Date convertStringtoSqlDate(String strDate){
		try{
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date date = sdf1.parse(strDate);
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
			return sqlStartDate;
		}catch(Exception e){
			return null;
		}
	}
}
