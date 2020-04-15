package com.ibm.cloud.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Date Utility Class converts a java.util.Date to a String
 * and a String to a java.util.Date. More functions can be added as required 
 * 
 * @author: Swati Singh
 * @Created by: Swati Singh for CR19-xxxxxx - OSCWI eEnablement Cloud Application
 * @version 1.0
 */
public class DateUtil {

	private final static java.lang.String ClassName = "[DateUtil] ";
	
	public final static java.lang.String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public final static java.lang.String DocumentLog_DateTime_Format = "MM/dd/yyyy HH:mm:ss a z";
	
	public final static java.lang.String DocumentLogEditInfo_DateTime_Format = "MM/dd/yyyy HH:mm:ss a";
	
	public final static java.lang.String Report_DateTime_Format = "MM/dd/yyyy HH:mm:ss a";
	
	public final static java.lang.String Request_DateTime_Format = "MM/dd/yyyy";
	
	public final static java.lang.String PageHeader_Date_Format = "MM/dd/yyyy";
	
	public final static java.lang.String ReportFile_DateTime_Format = "MMddyyyy_HHmmss";
	
	public final static java.lang.String DEFAULT_YEAR_FORMAT = "yyyy";
	
	public final static java.lang.String timeZone_GMT = "GMT";
	
	/*
	Common Date and Time Patterns
	=======================================================================================================================
    Letter 	Date or Time Component 								Presentation 			Examples
    =======================================================================================================================
	G 		Era designator 										Text 					AD
	y 		Year 												Year 					1996; 96
	Y 		Week year 											Year 					2009; 09
	M 		Month in year (context sensitive) 					Month 					July; Jul; 07
	L 		Month in year (standalone form) 					Month 					July; Jul; 07
	w 		Week in year 										Number 					27
	W 		Week in month 										Number 					2
	D 		Day in year 										Number 					189
	d 		Day in month 										Number 					10
	F 		Day of week in month 								Number 					2
	E 		Day name in week 									Text 					Tuesday; Tue
	u 		Day number of week (1 = Monday, ..., 7 = Sunday) 	Number 					1
	a 		Am/pm marker 										Text 					PM
	H 		Hour in day (0-23) 									Number 					0
	k 		Hour in day (1-24) 									Number 					24
	K 		Hour in am/pm (0-11) 								Number 					0
	h 		Hour in am/pm (1-12) 								Number 					12
	m 		Minute in hour 										Number 					30
	s 		Second in minute 									Number 					55
	S 		Millisecond 										Number 					978
	z 		Time zone 											General time zone 		Pacific Standard Time; PST; GMT-08:00
	Z 		Time zone 											RFC 822 time zone 		-0800
	X 		Time zone 											ISO 8601 time zone 		-08; -0800; -08:00
	*/
	

	public static void main(String[] args) {
		String strDate = "2019-01-14 14:01:00";
		
		System.out.println("getDate_yyyy_MM_dd_hh_mm_ss => "+getDefaultDateFormat_yyyy_MM_dd_hh_mm_ss());
		
		System.out.println("getPageHeaderDate => "+getPageHeaderDate(strDate));
		
		System.out.println("getRequestDate => "+getRequestDate(strDate));
		
		System.out.println("getReportDate => "+getReportDate(strDate));
		
		System.out.println("getDocumentLogDate => "+getDocumentLogDate(strDate));
		
		System.out.println("getDocumentLogEditInfoDate => "+getDocumentLogEditInfoDate(strDate));
		
	}
	
	/**
	 * Gets the current system date in the Page Header date Format: <strong>MM/dd/yyyy</strong>
	 * @return the current system date
	 */
	public synchronized final static String getPageHeaderDate(String strDate){
		String methodName = "[getDocumentLogDate] - ";
		try {
			if(strDate != null){
				SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
				Date date = df.parse(strDate);
				df = new SimpleDateFormat(PageHeader_Date_Format);
				return df.format(date);
			}else{
				return null;
			}
		} catch (Exception e) {
			System.out.println(ClassName + methodName +"Unable to format date.  " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Gets the current system date in the request date Format: <strong>MM/dd/yyyy</strong>
	 * @return the current system date
	 */
	public synchronized final static String getRequestDate(String strDate){
		String methodName = "[getReportDate] - ";
		try {
			if(strDate != null){
				SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
				Date date = df.parse(strDate);
				df = new SimpleDateFormat(Request_DateTime_Format);
				return df.format(date);
			}else{
				return null;
			}
		} catch (Exception e) {
			System.out.println(ClassName + methodName +"Unable to format date.  " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Gets the current system date in the report date Format: <strong>MM/dd/yyyy HH:mm:ss a</strong>
	 * @return the current system date
	 */
	public synchronized final static String getReportDate(String strDate){
		String methodName = "[getReportDate] - ";
		try {
			if(strDate != null){
				SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
				Date date = df.parse(strDate);
				df = new SimpleDateFormat(Report_DateTime_Format);
				return df.format(date);
			}else{
				return null;
			}
		} catch (Exception e) {
			System.out.println(ClassName + methodName +"Unable to format date.  " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Gets the current system date in the documentlog date Format in <strong>GMT</strong>: <strong>MM/dd/yyyy HH:mm:ss a</strong>
	 * @return the current system date
	 */
	public synchronized final static String getDocumentLogDate(String strDate){
		String methodName = "[getDocumentLogDate] - ";
		try {
			if(strDate != null){
				SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
				Date date = df.parse(strDate);
				
				DateFormat gmtFormat = new SimpleDateFormat(DocumentLog_DateTime_Format);
				TimeZone gmtTime = TimeZone.getTimeZone(timeZone_GMT);
				gmtFormat.setTimeZone(gmtTime);
				return gmtFormat.format(date);
			}else{
				return null;
			}
		} catch (Exception e) {
			System.out.println(ClassName + methodName +"Unable to format date.  " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Gets the current system date in the documentlog date Format: <strong>MM/dd/yyyy HH:mm:ss a</strong>
	 * @return the current system date
	 */
	public synchronized final static String getDocumentLogEditInfoDate(String strDate){
		String methodName = "[getDocumentLogEditInfoDate] - ";
		try {
			if(strDate != null){
				SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
				Date date = df.parse(strDate);
				df = new SimpleDateFormat(DocumentLogEditInfo_DateTime_Format);
				return df.format(date);
			}else{
				return null;
			}
		} catch (Exception e) {
			System.out.println(ClassName + methodName +"Unable to format date.  " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Gets the current system date in the dafult date Format: <strong>yyyy-MM-dd hh:mm:ss</strong>
	 * @return the current system date
	 */
	public synchronized final static String getDefaultDateFormat_yyyy_MM_dd_hh_mm_ss(){
		String methodName = "[getDefaultDateFormat_yyyy_MM_dd_hh_mm_ss] - ";
		try {
			SimpleDateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			java.util.Date now = new java.util.Date();
			return df.format(now);
		} catch (Exception e) {
			System.out.println(ClassName + methodName +"Unable to format date.  " + e.getMessage());
			return null;
		}
	}

	/**
	 * Gets the current system date in the report date Format: <strong>MMddyyyy_HHmmss</strong>
	 * @return the current system date
	 */
	public synchronized final static String getRequestReportDateForFile(){
		String methodName = "[getRequestReportDateForFile] - ";
		try {
			SimpleDateFormat df = new SimpleDateFormat(ReportFile_DateTime_Format);
			java.util.Date now = new java.util.Date();
			return df.format(now);
		} catch (Exception e) {
			System.out.println(ClassName + methodName +"Unable to format date.  " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * Gets the current system date in the default year Format: <strong>yyyy</strong>
	 * @return the current system date
	 */
	public synchronized final static String getDefaultYearFormat_yyyy(){
		String methodName = "[getDefaultYearFormat_yyyy] - ";
		try {
			SimpleDateFormat df = new SimpleDateFormat(DEFAULT_YEAR_FORMAT);
			java.util.Date now = new java.util.Date();
			return df.format(now);
		} catch (Exception e) {
			System.out.println(ClassName + methodName +"Unable to format date.  " + e.getMessage());
			return null;
		}
	}

}






