/**
 * 
 */
package com.doctortech.fhq.utils;

import java.sql.Timestamp;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.doctortech.framework.consts.Const;

/**
 * @author Gong
 *
 *         2014-8-4
 */
public class DateUtil extends org.apache.commons.lang3.time.DateUtils {

	private static String time_pattern="yyyy-MM-dd HH:mm:ss";
	
	public static String PATTERN_YEAR="yyyy";
	public static String PATTERN_MONTH="yyyy-MM";
	public static String PATTERN_DATE="yyyy-MM-dd";
	
	
	/**
	 * 获取前一天的日期
	 * @return
	 */
	public static Date getLastDayStr(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1); //得到前一天
		Date date = calendar.getTime();
		return date;
	}
	
	/**
	 * 计算短信发送时间是否过期，true表示没过期，false表示已过期
	 * @param date
	 * @param between 分钟
	 * @return
	 */
	public static boolean calculationDate(Date date,int between){
		long time= System.currentTimeMillis()- date.getTime();
		if(time>=between*60*1000){
			return false;
		}else{
			return true;
		}
	}
	
	
	/**
     * 连续登录天数
     * @param smdate
     * @param bdate
     * @return 返回值为0表示两个时间是同一天，返回值为1表示两个时间是连续的一天，返回值大于1表示两个时间不是连续登陆
     * @throws ParseException
     */
    public static int loginDays(Date smdate,Date bdate)    
    {    
    	try {
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
			smdate=sdf.parse(sdf.format(smdate));  
			bdate=sdf.parse(sdf.format(bdate));  
			Calendar cal = Calendar.getInstance();    
			cal.setTime(smdate);    
			long time1 = cal.getTimeInMillis();                 
			cal.setTime(bdate);    
			long time2 = cal.getTimeInMillis();         
			long between_days=(time2-time1)/(1000*3600*24);  
			    
			return Integer.parseInt(String.valueOf(between_days));
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return 0;
    }  
	
	
	
	
	/**
	 * 判断锁定时间是否过期,true表示锁定时间还没到，false表示锁定时间已经到了
	 */
	public static boolean lockedTimeExpire(Date date){
		if(date==null){
			return false;
		}else{
			return System.currentTimeMillis()-date.getTime()<=0?true:false;
		}
	}
	
	
	/**
	 * 获取账号锁定截止日期
	 */
	public static Date getAccountLockedTime(){
		
		
		Calendar cal=Calendar.getInstance();
		Date date;
		
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
		
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR)+Const.LOGIN_ACCOUNT_LOCKED_HOURS);
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, 0);
		date=cal.getTime();
		System.out.println(formatDate(date, "yyyy-MM-dd HH:mm:ss"));
		return date;
	}
	
	/**
	 * 格式化日期
	 */
	public static String formatDateByLock(Date date){
		return formatDate(date,"yyyy年MM月dd日 HH时mm分");
	}
	
	/**
	 * 判断当前日期和最后一次输入密码输入错误时间是不是同一天，
	 * return true表示为同一天，false表示不是同一天
	 */
	public static boolean sameDay(Date lastEnterPwdDate){
	  if(lastEnterPwdDate==null){
		  return true;
	   }
	   Calendar cal1 = Calendar.getInstance();
       cal1.setTime(new Date());

       Calendar cal2 = Calendar.getInstance();
       cal2.setTime(lastEnterPwdDate);

       boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
               .get(Calendar.YEAR);
       boolean isSameMonth = isSameYear
               && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
       boolean isSameDate = isSameMonth
               && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                       .get(Calendar.DAY_OF_MONTH);

       return isSameDate;
		
	}

	/**
	 * 格式化日期
	 */
	public static String formatDate(Date date, String pattern) {
		if(date==null){
			return "";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateStr = simpleDateFormat.format(date);
		return dateStr;
	}
	
	public static String formatDate(Date date) {
		return formatDate(date, time_pattern);
	}
	
	public static Date getDate(String datestr){
		Date date = null;
		try {
			if(!StringUtils.isBlank(datestr)){
				date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datestr);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	
	/**
	 * 计算短信发送时间是否过期，true表示没过期，false表示已过期
	 * @param date
	 * @param between 分钟
	 * @return
	 */
	public static boolean calculationTime(Long date,int between){
		long time= System.currentTimeMillis()- date;
		if(time>=between*60*1000){
			return false;
		}else{
			return true;
		}
	}
	
	
	/**
	 * 截止时间
	 * @param days 天数
	 */
	public static Date getDateDay(Integer days){
		
		if(days==null){
			return null;
		}
		Calendar cal=Calendar.getInstance();
		Date date;
		
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+days);
		
		cal.set(Calendar.HOUR, cal.get(Calendar.HOUR));
		cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, 0);
		date=cal.getTime();
		System.out.println(formatDate(date, "yyyy-MM-dd HH:mm:ss"));
		return date;
	}
	
	public static String getDateTime(Date date){
	    if(date==null) return null ;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(time_pattern);
		return simpleDateFormat.format(date);
	}
	
	/**
	 * 格式化日期
	 * 
	 * @param dateString
	 * @param simpleDateFormat
	 * @return
	 */
	public static Date getDate(String dateString, SimpleDateFormat simpleDateFormat) {

		Date date = null;

		try {
			if(!StringUtils.isBlank(dateString)){
			    date = simpleDateFormat.parse(dateString);
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.print(DateUtil.class.getName());
		}

		return date;
	}

	/**
	 * 去掉时分秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDate(Date date) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date returnDate = null;

		try {
			returnDate = simpleDateFormat.parse(simpleDateFormat.format(date));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.print(DateUtil.class.getName());

		}

		return returnDate;

	}

	/**
	 * 获取指定日期的一天的开始时间
	 * 
	 * @param paramDate
	 * @return
	 */
	public static Date getDayStartTime(Date paramDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(paramDate);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}

	public static String getDayStartTimeString(Date paramDate) {

		return formatDate(getDayStartTime(paramDate), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取指定日期的一天的结束时间
	 * 
	 * @param paramDate
	 * @return
	 */
	public static Date getDayEndTime(Date paramDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(paramDate);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);

		return c.getTime();
	}

	public static String getDayEndTimeString(Date paramDate) {

		return formatDate(getDayEndTime(paramDate), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 相差的天数 date2-date1
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);

		int year1 = calendar.get(Calendar.YEAR);
		int day1 = calendar.get(Calendar.DAY_OF_YEAR);

		calendar.setTime(date2);

		int year2 = calendar.get(Calendar.YEAR);
		int day2 = calendar.get(Calendar.DAY_OF_YEAR);

		// 同年精确计算
		if (year1 == year2) {

			return day2 - day1;
		} else {

			// 非同年，不精确计算
			Calendar cal = Calendar.getInstance();

			cal.setTime(date1);

			long time1 = cal.getTimeInMillis();

			cal.setTime(date2);

			long time2 = cal.getTimeInMillis();

			long between_days = (time2 - time1) / (1000 * 3600 * 24);

			return Integer.parseInt(String.valueOf(between_days));

		}

	}

	/**
	 * 剩余天数
	 * 
	 * @param date
	 * @return
	 */
	public static int daysLeft(Date date) {
		if (date == null)
			return 0;

		// now
		Calendar cal = Calendar.getInstance();

		long time1 = cal.getTimeInMillis();

		// the coming day
		Calendar cal2 = Calendar.getInstance();

		cal2.setTime(date);

		long time2 = cal2.getTimeInMillis();

		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		if (time2 - time1 < 0)
			return 0;

		return Integer.parseInt(String.valueOf(between_days));

	}

	/**
	 * 获取ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 时间天数加减运算
	 * @param date
	 * @param operationDateDay
	 * @return
	 */
	public static String operationDateDay(String date, int operationDateDay) {
		String reStr=null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = sdf.parse(date);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			rightNow.add(Calendar.DAY_OF_YEAR, operationDateDay);// 日期加operationDateDay天
			Date dt1 = rightNow.getTime();
			reStr = sdf.format(dt1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reStr;
	}
	
	/**
	 * 时间月份加减运算
	 * @param date
	 * @param operationDateDay
	 * @return
	 */
	public static String operationDateMonth(String date, int operationDateMonth) {
		String reStr=null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = sdf.parse(date);
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(dt);
			rightNow.add(Calendar.MONTH,operationDateMonth);//日期加operationDateMonth个月
			Date dt1 = rightNow.getTime();
			reStr = sdf.format(dt1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return reStr;
	}
	
	/**
	 * 时间天数加减运算
	 * @param date
	 * @param operationDateDay
	 * @return
	 */
	public static Date operationDateByDay(Date date, int operationDateDay) {
		Date resultDate=date;
		try {
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(date);
			rightNow.add(Calendar.DAY_OF_YEAR, operationDateDay);// 日期加operationDateDay天
			resultDate = rightNow.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultDate;
	}
	
	/**
	 * 时间月份加减运算
	 * @param date
	 * @param operationDateDay
	 * @return
	 */
	public static Date operationDateByMonth(Date date, int operationDateMonth) {
		Date resultDate=date;
		try {
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(date);
			rightNow.add(Calendar.MONTH,operationDateMonth);//日期加operationDateMonth个月
			resultDate = rightNow.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultDate;
	}
	
	
public static String changeDate(String date){
		
		if(StringUtils.isEmpty(date)){
			return "";
		}
		long nowTimes=System.currentTimeMillis();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d=null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			try {
				d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		long createTimes=d.getTime();
		long times=nowTimes-createTimes;
		SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
		String msg="";
		
		if(times<60*1000){ //秒
			msg="刚刚";
		}else if(times<60*60*1000){//分
		    int minute=(int) (times/(60*1000));
			msg=minute+"分钟前";
			
		}else if(times<24*60*60*1000){//小时
			  int hour=(int) (times/(60*60*1000));
				msg=hour+"小时前";
		}else if(times<=7*24*60*60*1000){//天
			  int hour=(int) (times/(24*60*60*1000));
				msg=hour+"天前";
		}else{ //日期
			msg=sm.format(d);
			
		}
		return msg;
	}

public static String changeDate(String date,String format){
	
	if(StringUtils.isEmpty(date)){
		return "";
	}
	long nowTimes=System.currentTimeMillis();
	SimpleDateFormat sdf=new SimpleDateFormat(format);
	Date d=null;
	try {
		d = sdf.parse(date);
	} catch (ParseException e) {
		
		e.printStackTrace();
	}
	long createTimes=d.getTime();
	long times=nowTimes-createTimes;
	SimpleDateFormat sm=new SimpleDateFormat("yyyy-MM-dd");
	String msg="";
	
	if(times<60*1000){ //秒
		msg="刚刚";
	}else if(times<60*60*1000){//分
	    int minute=(int) (times/(60*1000));
		msg=minute+"分钟前";
		
	}else if(times<24*60*60*1000){//小时
		  int hour=(int) (times/(60*60*1000));
			msg=hour+"小时前";
	}else if(times<=7*24*60*60*1000){//天
		  int hour=(int) (times/(24*60*60*1000));
			msg=hour+"天前";
	}else{ //日期
		msg=sm.format(d);
		
	}
	return msg;
}

/**
 * 计算两个日期相差天数
 * @param startDate 开始日期
 * @param endDate 结束日期
 * @return
 */
public static int betweenTwoDate(Date startDate,Date endDate){
	
	if(null==startDate || null==endDate){
		return -2;
	}
	long sTime=startDate.getTime();
	long eTime=endDate.getTime();
	long times=eTime-sTime;
	if(times<=0L){ //时间已经结束
		return -1;
	}else{ 
		 int day=(int) (times/(24*60*60*1000));
		 return day;
	}
}

/**
 * 计算百分比
 * @param startDate
 * @param endDate
 * @param allNum
 * @return
 */
public static int getPerByTwoDay(Date startDate,Date endDate,int allNum){
	Date nowDate=new Date();
	if(null==startDate || null ==endDate){
		return -2;
	}
	long sTime=startDate.getTime();
	long nowTime=nowDate.getTime();
	long endTime=endDate.getTime();
	long current=nowTime-sTime;
	long times=endTime-nowTime;
	if(times<=0L){ //时间已经结束
		return allNum;
	}else{ 
		  int day=(int) (current/(24*60*60*1000));
	      NumberFormat numberFormat = NumberFormat.getInstance();  
		  numberFormat.setMaximumFractionDigits(0);  
		  String result = numberFormat.format((float) day / (float) allNum);  
		  if(StringUtils.isEmpty(result)){
		         return 0;
		     }else{
		         return Integer.parseInt(result);
		    }
		
	}

}

	/** 
	 * 两个时间相差距离多少天多少小时多少分多少秒 
	 * @return String 返回值为：xx天xx小时xx分xx秒 
	 */  
	public static String getDistanceTime(Date one, Date two) {  
	    long day = 0;  
	    long hour = 0;  
	    long min = 0;  
	    long sec = 0;  
	    try { 
	        long time1 = one.getTime();  
	        long time2 = two.getTime();  
	        long diff ;  
	        if(time1<time2) {  
	            diff = time2 - time1;  
	        } else {  
	            diff = time1 - time2;  
	        }  
	        day = diff / (24 * 60 * 60 * 1000);  
	        hour = (diff / (60 * 60 * 1000) - day * 24);  
	        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
	        sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    return day + "天" + hour + "小时" + min + "分" + sec + "秒";  
	}  
	
	/** 
	 * 两个时间相差距离少小时多少分
	 * @return String 返回值为：xx小时xx分
	 */ 
	public static String getDistanceHourMin(Date one, Date two) {  
	    long hour = 0;  
	    long min = 0; 
	    try { 
	        long time1 = one.getTime();  
	        long time2 = two.getTime();  
	        long diff ;  
	        if(time1<time2) {  
	            diff = time2 - time1;  
	        } else {  
	            diff = time1 - time2;  
	        }  
	        hour = (diff / (60 * 60 * 1000));  
	        min = ((diff / (60 * 1000)) - hour * 60);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    return hour + "小时" + min + "分";  
	} 
	
	/** 
     * 得到几天前的时间 
     *  
     * @param d 
     * @param day 
     * @return 
     */  
    public static Date getDateBefore(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);  
        return now.getTime();  
    }   
    
    /**
     * 得到六个月前的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateSixMonthAgo() {  
        Calendar now = Calendar.getInstance();  
        now.set(Calendar.MONTH, -6);  
        return now.getTime();  
    } 
    
    /**
     * 得到一年前的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateOneYearAgo() {  
        Calendar now = Calendar.getInstance();  
        now.set(Calendar.YEAR, -1);  
        return now.getTime();  
    }  
    
    /** 
     * 得到几天后的时间 
     *  
     * @param d 
     * @param day 
     * @return 
     */  
    public static Date getDateAfter(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
        return now.getTime();  
    } 	
	
	    /**
	     * d0>d1
	     * @param d0 日期
	     * @param d1 日期
	     * @param mi 相差分钟
	     * @return
	     */
	   public static boolean compa(Date d0 , Date  d1 ,int mi){
	        
	        Calendar calendar0=Calendar.getInstance();
	        calendar0.setTime(d0);
	        
	        
	        Calendar calendar1=Calendar.getInstance();
	        calendar1.setTime(d1);
	        
	        //同年
	        if(calendar0.get(Calendar.YEAR)!=calendar1.get(Calendar.YEAR)){
	            
	            return false ;
	            
	        }
	        
	        //同日
	        if(calendar0.get(Calendar.DAY_OF_YEAR)!=calendar1.get(Calendar.DAY_OF_YEAR)){
	            
	            return false ;
	            
	        }
	        
	        //同时
	        if(calendar0.get(Calendar.HOUR_OF_DAY)!=calendar1.get(Calendar.HOUR_OF_DAY)){
	            
	            return false ;
	            
	        }
	        
	        //相差时间大于10小于20
	        //10分钟取决任务调度时间间隔
	        int a=calendar0.get(Calendar.MINUTE)-calendar1.get(Calendar.MINUTE);
	        if( a>0 &&a<mi*2){
	            
	            return true ;
	            
	        }
	        
	        return false ;
	        
	        
	    }
	   
	   /**
	    * 日期long类型转化为Date类型
	    * @param time
	    * @return
	    */
	   public static Date ChangeLongToDate(long time){
		   
		   SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
           java.util.Date date = new Date(time);  
           String str = sdf.format(date);  
           System.out.println(str);
           return date;
	   }
	   
	   /**
	    * 日期long类型转化为Date类型
	    * @param time
	    * @return
	    */
	   public static Long ChangeDateToLong(Date time){
		   if(time==null){
			   return null;
		   }
		  return time.getTime();
	   }
	   
	   
	   public static Date stringToDate(String dateString) {
			try {
				String[] splitString = null;
				if (StringUtils.contains(dateString, ".")) {
					splitString = StringUtils.split(dateString, ".");
				} else if (StringUtils.contains(dateString, "-")) {
					splitString = StringUtils.split(dateString, "-");
				}else if (StringUtils.contains(dateString, "/")) {
					splitString = StringUtils.split(dateString, "/");
				} else {
						splitString = new String[] { dateString };
				}
				
				Calendar cal = Calendar.getInstance();
				int year = 1;
				int month = 0;
				int date = 1;
				if (splitString.length > 0) {
					year = Integer.parseInt(splitString[0]);
				}
				if (splitString.length > 1) {
					String mString=splitString[1];
					month = Integer.parseInt(splitString[1]);
					if (month != 0) {
						month--;
					}
				}
				if (splitString.length > 2) {
					date = Integer.parseInt(splitString[2]);
				}
				cal.set(year, month, date);
				return cal.getTime();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	   
	   /**
		 * 判断锁定时间是否过期,true表示锁定时间还没到，false表示锁定时间已经到了
		 */
		public static boolean tipDate(Date date){
			if(date==null){
				return true;
			}else{
				return System.currentTimeMillis()-date.getTime()<=0?false:true;
			}
		}
		
		/**
		 * 字符串转整型的时间
		 * @param dateStr
		 * @return
		 */
	   public static Integer stringToInteger(String dateStr){
		   Integer dateInt = 0;
		   try
			{
				if(StringUtils.isNotBlank(dateStr)){
					dateStr = StringUtils.remove(dateStr, "-");
				   	dateInt = Integer.parseInt(dateStr);
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		   
		   return dateInt;
	   }
	   
	   /**
		 * 整型转字符串时间
		 * @param dateStr
		 * @return
		 */
	   public static String integerToString(Integer dateInput, String pattern){
		   String dateStr = "";
		   try
			{
				if(dateInput!=null && dateInput>0){
					if(StringUtils.equals(pattern, PATTERN_YEAR)){
						dateStr = StringUtils.left(dateInput.toString(), 4);
					}else if(StringUtils.equals(pattern, PATTERN_MONTH)){
						String monthInt = StringUtils.left(dateInput.toString(), 6);
						StringBuilder sb=new StringBuilder(monthInt.toString());
						dateStr = sb.insert(4,"-").toString();
					}else if(StringUtils.equals(pattern, PATTERN_DATE)){
						String dateInt = StringUtils.left(dateInput.toString(), 8);
						StringBuilder sb=new StringBuilder(dateInt.toString());
						dateStr = sb.insert(4,"-").insert(7, "-").toString();
					}
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		   
		   return dateStr;
	   }
	   
	   
	   /**
		 * 整型转字符串时间
		 * @param dateStr
		 * @return
		 */
	   public static String getDateByintegerToString(Integer dateInput, String pattern){
		   String dateStr = "";
		   try
			{
				if(dateInput!=null && dateInput>0){
					if(StringUtils.equals(pattern, "yyyy")){
						dateStr = StringUtils.left(dateInput.toString(), 4);
						
					}else if(StringUtils.equals(pattern, "yyyy.MM")){
						String monthInt = StringUtils.left(dateInput.toString(), 6);
						StringBuilder sb=new StringBuilder(monthInt.toString());
						dateStr = sb.insert(4,".").toString();
					}else if(StringUtils.equals(pattern, "yyyy.MM.dd")){
						String dateInt = StringUtils.left(dateInput.toString(), 8);
						StringBuilder sb=new StringBuilder(dateInt.toString());
						dateStr = sb.insert(4,".").insert(7, ".").toString();
					}
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		   
		   return dateStr;
	   }
	   
	   
	   /**
			 * 整型转字符串时间
			 * @param dateStr
			 * @return
			 */
		   public static String integerToStringByDate(Integer dateInput){
			   String dateStr = "";
			   try
				{
				   if(dateInput==null) return dateStr;
					if(dateInput!=null && dateInput>0){
						String s=String.valueOf(dateInput);
						if(s!=null&&s.length()==4){
							dateStr = StringUtils.left(dateInput.toString(), 4);	
						}else if(s!=null&&s.length()==6){
							String monthInt = StringUtils.left(dateInput.toString(), 6);
							StringBuilder sb=new StringBuilder(monthInt.toString());
							dateStr = sb.insert(4,"-").toString();
						}else if(s!=null&&s.length()==8){
							String dateInt = StringUtils.left(dateInput.toString(), 8);
							StringBuilder sb=new StringBuilder(dateInt.toString());
							dateStr = sb.insert(4,"-").insert(7, "-").toString();
						}
					
					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			   
			   return dateStr;
		   }
		   
		
		   /**
		    * 获取年龄
		    * @param beginNum
		    * @return
		    */
		   public static int getRandAge(int beginNum){
			   Format f = new SimpleDateFormat("yyyy");
		        Calendar c = Calendar.getInstance();
		        c.add(Calendar.YEAR, beginNum);
		        String year=f.format(c.getTime());
		        System.out.println("之前:" + year);
		        year=year+"0000";
		        int r=Integer.parseInt(year);
		        System.out.println("之hou:" + r);

		        return r;
		   }
		
		   
		   /**
		    * 计算当前时间是否超过该周期的缴费时间
		    * @param endDate 缴费结束日期
		    * @param nowDate  当前时间
		    * @return boolean true表示已过期，false表示未过期
		 * @throws ParseException 
		    */
		   public static    boolean calculateWaitPayTime(Integer endDate,String nowDate){
		   	try {
				if(null==endDate||null==nowDate){
					return false;
				}
				String s= DateUtil.getDateByintegerToString(endDate,"yyyy.MM.dd");
				SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");

				Date e=DateUtil.getDate(s,df);
				Date now = df.parse(nowDate);
				long nowTime=now.getTime();
				long end=e.getTime();
				long times=nowTime-end;
				if(times>0l){ //已到期
					return true;
				}
					return false;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		   }
		   
		   /**
		    * 更改待缴费专利缴费日期
		    * @param endDate
		    * @param year
		    * @return
		    */
		   public static Integer addWaitPayTime(Integer endDate,Integer year){			 
				String s=  DateUtil.integerToStringByDate(endDate);
				Date e=DateUtil.getDate(s,new SimpleDateFormat("yyyy-MM-dd"));
		        Calendar temp=Calendar.getInstance();
                temp.setTime(e);
                temp.add(Calendar.YEAR, 1);
                Date  d= temp.getTime();
		        String ss=DateUtil.formatDate(d,"yyyy-MM-dd");
		    //    System.out.println("ss="+ss);
		        return stringToInteger(ss);
		   }
		   
		   public static Date getNextYearDate(Date startDate,Integer year){			 
		        Calendar temp=Calendar.getInstance();
               temp.setTime(startDate);
               temp.add(Calendar.YEAR, year);
               Date  d= temp.getTime();
		       return d;
		   }
		   
		   
		   /**
		    * 根据专利申请日期和当前时间，计算专利缴费提醒开始日期及专利缴费提醒结束日期
		    * @param endDate 缴费结束日期
		    * @param nowDate  当前时间
		    * @return Integer[0] 专利缴费提醒开始日期；Integer[1]专利缴费提醒结束日期；
		    */
		   public static Integer[] getStartAndEndTime(Integer applicationTime){
				Integer[] result=new Integer[2];
			   	if(applicationTime==null){
			   	//	System.out.println(result[0]+","+result[1]);
			   		return result;
			   	}
			   	Date now=new Date();
				String startTimes=  DateUtil.integerToStringByDate(applicationTime);
				Date startDate=DateUtil.getDate(startTimes,new SimpleDateFormat("yyyy-MM-dd"));
			   	Calendar calStart = Calendar.getInstance(); 
			    calStart.setTime(startDate);
			    Calendar calNow = Calendar.getInstance();  
			    calNow.setTime(now);
		   		boolean flag=true;
		   		Date start=calStart.getTime();
		   		Date lastEndDate=getLastDate(start,now);//获取离当前时间最近的一个缴费结束时间
		   		while(flag) {
			   		if(compareTwoDate(lastEndDate,now)){//当前时间未超过上一年缴费结束时间
			   			Integer unPayEnd=DateUtil.stringToInteger(DateUtil.formatDate(lastEndDate,"yyyyMMdd"));	
			   			Date unPayStartDate=getNextYearDate(lastEndDate,-1);
			   			Integer unPayStart=DateUtil.stringToInteger(DateUtil.formatDate(unPayStartDate,"yyyyMMdd"));
			   			result[0]=unPayStart;
			   			result[1]=unPayEnd;
			   			flag=false;
			   		}else {
			   			lastEndDate=getNextYearDate(lastEndDate,1);//获取下一个缴费结束时间
			   		}
		   		}	
		   //	System.out.println("result[0]="+result[0]+",result[1]="+result[1]);
		   	return result;
		  }
		   
		   
		   public static  Date getLastDate(Date startDate,Date now) {
			 	Calendar calStart = Calendar.getInstance(); 
			    calStart.setTime(startDate);
			    Calendar calNow = Calendar.getInstance();  
			    calNow.setTime(now);
			    Calendar temp=calNow;
			     temp.add(Calendar.YEAR, -1);
			     Calendar t= Calendar.getInstance();
			     t.set(Calendar.YEAR, temp.get(Calendar.YEAR));
			     t.set(Calendar.MONTH, calStart.get(Calendar.MONTH));
			     t.set(Calendar.DAY_OF_MONTH, calStart.get(Calendar.DAY_OF_MONTH));
			     return t.getTime();			     
		   }
		   
		   public static boolean compareTwoDate(Date last,Date now) {
			   boolean result=false;
			   long l=last.getTime();
			   long n=now.getTime();
			   if(l-n>0) {//当前时间超过上一次缴费结束时间
				   result=true;
			   }
			   return result;
		   }
		   
		   /**
			 * 园区专利缴费期限是否到达缴费提醒阶段
			 * @param date
			 * @param operationDateDay
			 * @return
			 */
			public static boolean patentReminderDate(Integer endDate,Date d) {
				boolean reStr=false;
				try {
					if(null==endDate){
						return false;
					}
					
					String s= DateUtil.getDateByintegerToString(endDate,"yyyy.MM.dd");
					SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");

					Date e=DateUtil.getDate(s,df);
					
				//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//	Date dt = sdf.parse(date);
					Calendar rightNow = Calendar.getInstance();
					rightNow.setTime(e);
					rightNow.add(Calendar.DAY_OF_YEAR, -Const.PATENT_REMINDER_EXPIRE_DAY);
					Date dt1 = rightNow.getTime();
					System.out.println(df.format(dt1));
					long nowTime=d.getTime();
					long end=dt1.getTime();
					long times=nowTime-end;
					if(times>0l){ //已到期
						reStr= true;
					}else{
						reStr=false;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return reStr;
			}
		   
		  
		 
		   
		   
		 //获取某年某月的第一天日期
		   public static Date getStartMonthDate(int year, int month) {
		          Calendar calendar = Calendar.getInstance();
		          calendar.set(year, month - 1, 1);
		          return calendar.getTime();
		      }


		  //获取某年某月的最后一天日期
		     public static Date getEndMonthDate(int year, int month) {
		          Calendar calendar = Calendar.getInstance();
		          calendar.set(year, month - 1, 1);
		          int day = calendar.getActualMaximum(5);
		          calendar.set(year, month - 1, day);
		          return calendar.getTime();
		      }

		   //获取当天的开始时间
		     public static java.util.Date getDayBegin() {
		         Calendar cal = new GregorianCalendar();
		         cal.set(Calendar.HOUR_OF_DAY, 0);
		         cal.set(Calendar.MINUTE, 0);
		         cal.set(Calendar.SECOND, 0);
		         cal.set(Calendar.MILLISECOND, 0);
		         return cal.getTime();
		     }
		     //获取当天的结束时间
		     public static java.util.Date getDayEnd() {
		         Calendar cal = new GregorianCalendar();
		         cal.set(Calendar.HOUR_OF_DAY, 23);
		         cal.set(Calendar.MINUTE, 59);
		         cal.set(Calendar.SECOND, 59);
		         return cal.getTime();
		     }
		     //获取昨天的开始时间
		     public static Date getBeginDayOfYesterday() {
		         Calendar cal = new GregorianCalendar();
		         cal.setTime(getDayBegin());
		         cal.add(Calendar.DAY_OF_MONTH, -1);
		         return cal.getTime();
		     }
		     //获取昨天的结束时间
		     public static Date getEndDayOfYesterDay() {
		         Calendar cal = new GregorianCalendar();
		         cal.setTime(getDayEnd());
		         cal.add(Calendar.DAY_OF_MONTH, -1);
		         return cal.getTime();
		     }
		     //获取明天的开始时间
		     public static Date getBeginDayOfTomorrow() {
		         Calendar cal = new GregorianCalendar();
		         cal.setTime(getDayBegin());
		         cal.add(Calendar.DAY_OF_MONTH, 1);


		         return cal.getTime();
		     }
		     //获取明天的结束时间
		     public static Date getEndDayOfTomorrow() {
		         Calendar cal = new GregorianCalendar();
		         cal.setTime(getDayEnd());
		         cal.add(Calendar.DAY_OF_MONTH, 1);
		         return cal.getTime();
		     }
		     //获取本周的开始时间
		     public static Date getBeginDayOfWeek() {
		         Date date = new Date();
		         if (date == null) {
		             return null;
		         }
		         Calendar cal = Calendar.getInstance();
		         cal.setTime(date);
		         int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		         if (dayofweek == 1) {
		             dayofweek += 7;
		         }
		         cal.add(Calendar.DATE, 2 - dayofweek);
		         return getDayStartTime(cal.getTime());
		     }
		     //获取本周的结束时间
		     public static Date getEndDayOfWeek(){
		         Calendar cal = Calendar.getInstance();
		         cal.setTime(getBeginDayOfWeek());  
		         cal.add(Calendar.DAY_OF_WEEK, 6); 
		         Date weekEndSta = cal.getTime();
		         return getDayEndTime(weekEndSta);
		     }
		     //获取本月的开始时间
		      public static Date getBeginDayOfMonth() {
		             Calendar calendar = Calendar.getInstance();
		             calendar.set(getNowYear(), getNowMonth() - 1, 1);
		             return getDayStartTime(calendar.getTime());
		         }
		     //获取本月的结束时间
		      public static Date getEndDayOfMonth() {
		             Calendar calendar = Calendar.getInstance();
		             calendar.set(getNowYear(), getNowMonth() - 1, 1);
		             int day = calendar.getActualMaximum(5);
		             calendar.set(getNowYear(), getNowMonth() - 1, day);
		             return getDayEndTime(calendar.getTime());
		         }
		      //获取本年的开始时间
		      public static java.util.Date getBeginDayOfYear() {
		             Calendar cal = Calendar.getInstance();
		             cal.set(Calendar.YEAR, getNowYear());
		             // cal.set
		             cal.set(Calendar.MONTH, Calendar.JANUARY);
		             cal.set(Calendar.DATE, 1);


		             return getDayStartTime(cal.getTime());
		         }
		      //获取本年的结束时间
		      public static java.util.Date getEndDayOfYear() {
		             Calendar cal = Calendar.getInstance();
		             cal.set(Calendar.YEAR, getNowYear());
		             cal.set(Calendar.MONTH, Calendar.DECEMBER);
		             cal.set(Calendar.DATE, 31);
		             return getDayEndTime(cal.getTime());
		         }
		     //获取某个日期的开始时间
		     public static Timestamp getDayStartTime2(Date d) {
		         Calendar calendar = Calendar.getInstance();
		         if(null != d) calendar.setTime(d);
		         calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		         calendar.set(Calendar.MILLISECOND, 0);
		         return new Timestamp(calendar.getTimeInMillis());
		     }
		     //获取某个日期的结束时间
		     public static Timestamp getDayEndTime2(Date d) {
		         Calendar calendar = Calendar.getInstance();
		         if(null != d) calendar.setTime(d);
		         calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),    calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		         calendar.set(Calendar.MILLISECOND, 999);
		         return new Timestamp(calendar.getTimeInMillis());
		     }
		     //获取今年是哪一年
		      public static Integer getNowYear() {
		              Date date = new Date();
		             GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		             gc.setTime(date);
		             return Integer.valueOf(gc.get(1));
		         }
		      //获取本月是哪一月
		      public static int getNowMonth() {
		              Date date = new Date();
		             GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		             gc.setTime(date);
		             return gc.get(2) + 1;
		         }
		      //获取某年是哪一年
		      public static Integer getSomeYear(Date date) {
		             GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		             gc.setTime(date);
		             return Integer.valueOf(gc.get(1));
		         }
		      //获取某月是哪一月
		      public static int getSomeMonth(Date date) {
		             GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		             gc.setTime(date);
		             return gc.get(2) + 1;
		         }
		      //两个日期相减得到的天数
		      public static int getDiffDays(Date beginDate, Date endDate) {


		             if (beginDate == null || endDate == null) {
		                 throw new IllegalArgumentException("getDiffDays param is null!");
		             }


		             long diff = (endDate.getTime() - beginDate.getTime())
		                     / (1000 * 60 * 60 * 24);


		             int days = new Long(diff).intValue();


		             return days;
		         }
		     //两个日期相减得到的毫秒数
		      public static long dateDiff(Date beginDate, Date endDate) {
		             long date1ms = beginDate.getTime();
		             long date2ms = endDate.getTime();
		             return date2ms - date1ms;
		         }
		      //获取两个日期中的最大日期
		      public static Date max(Date beginDate, Date endDate) {
		             if (beginDate == null) {
		                 return endDate;
		             }
		             if (endDate == null) {
		                 return beginDate;
		             }
		             if (beginDate.after(endDate)) {
		                 return beginDate;
		             }
		             return endDate;
		         }
		      //获取两个日期中的最小日期
		      public static Date min(Date beginDate, Date endDate) {
		             if (beginDate == null) {
		                 return endDate;
		             }
		             if (endDate == null) {
		                 return beginDate;
		             }
		             if (beginDate.after(endDate)) {
		                 return endDate;
		             }
		             return beginDate;
		         }
		      //返回某月该季度的第一个月
		      public static Date getFirstSeasonDate(Date date) {
		              final int[] SEASON = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
		             Calendar cal = Calendar.getInstance();
		             cal.setTime(date);
		             int sean = SEASON[cal.get(Calendar.MONTH)];
		             cal.set(Calendar.MONTH, sean * 3 - 3);
		             return cal.getTime();
		         }
		      //返回某个日期下几天的日期
		      public static Date getNextDay(Date date, int i) {
		             Calendar cal = new GregorianCalendar();
		             cal.setTime(date);
		             cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
		             return cal.getTime();
		         }
		      //返回某个日期前几天的日期
		      public static Date getFrontDay(Date date, int i) {
		             Calendar cal = new GregorianCalendar();
		             cal.setTime(date);
		             cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
		             return cal.getTime();
		         }
		      //获取某年某月到某年某月按天的切片日期集合（间隔天数的日期集合）
		      public static List getTimeList(int beginYear, int beginMonth, int endYear,
		                 int endMonth, int k) {
		             List list = new ArrayList();
		             if (beginYear == endYear) {
		                 for (int j = beginMonth; j <= endMonth; j++) {
		                     list.add(getTimeList(beginYear, j, k));


		                 }
		             } else {
		                 {
		                     for (int j = beginMonth; j < 12; j++) {
		                         list.add(getTimeList(beginYear, j, k));
		                     }


		                     for (int i = beginYear + 1; i < endYear; i++) {
		                         for (int j = 0; j < 12; j++) {
		                             list.add(getTimeList(i, j, k));
		                         }
		                     }
		                     for (int j = 0; j <= endMonth; j++) {
		                         list.add(getTimeList(endYear, j, k));
		                     }
		                 }
		             }
		             return list;
		         }
		      //获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
		      public static List getTimeList(int beginYear, int beginMonth, int k) {
		             List list = new ArrayList();
		             Calendar begincal = new GregorianCalendar(beginYear, beginMonth, 1);
		             int max = begincal.getActualMaximum(Calendar.DATE);
		             for (int i = 1; i < max; i = i + k) {
		                 list.add(begincal.getTime());
		                 begincal.add(Calendar.DATE, k);
		             }
		             begincal = new GregorianCalendar(beginYear, beginMonth, max);
		             list.add(begincal.getTime());
		             return list;
		         }
		      
		      //获取上个月的开始时间
		      public static Date getBeginDayOfLastMonth() {
		             Calendar calendar = Calendar.getInstance();
		             int month= getNowMonth() - 2;
		             if(month>=0){
		            	 calendar.set(getNowYear(), month, 1);
		             }else{
		            	 calendar.set(getNowYear()-1, 11, 1);//上一年12月份
		             }
		             
		             return getDayStartTime(calendar.getTime());
		         }
		    //获取上个月的结束时间
		      public static Date getEndDayOfLastMonth() {
		             Calendar calendar = Calendar.getInstance();
		             int month=getNowMonth() - 2;
		             int year=getNowYear();
		             if(month<0){
		            	 month=11;
		            	 year=year-1;
		             }
		             calendar.set(year, month, 1);
		             int day = calendar.getActualMaximum(5);
		             calendar.set(year, month, day);
		             return getDayEndTime(calendar.getTime());
		         }
		      //获取某月的开始时间
		      public static Date getBeginDayOfSomeMonth(Date date) {
		             Calendar calendar = Calendar.getInstance();
		             calendar.setTime(date);
		             calendar.set(getSomeYear(date), getSomeMonth(date) - 1, 1);
		             return getDayStartTime(calendar.getTime());
		         }
		     //获取某月的结束时间
		      public static Date getEndDayOfSomeMonth(Date date) {
		             Calendar calendar = Calendar.getInstance();
		             calendar.set(getSomeYear(date), getSomeMonth(date) - 1, 1);
		             int day = calendar.getActualMaximum(5);
		             calendar.set(getSomeYear(date), getSomeMonth(date) - 1, day);
		             return getDayEndTime(calendar.getTime());
		         }
		      
		      public static void main(String[] args) {
					
				   //System.out.println(DateUtil.patentReminderDate(20180609,new Date()));
		    	  String d="2018-03";
		    	  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		    	  try {
					Date date=sdf.parse(d);
//					Date start=DateUtil.getBeginDayOfLastMonth();
//					Date end=DateUtil.getEndDayOfLastMonth();
					Date start=DateUtil.getBeginDayOfSomeMonth(date);
					Date end=DateUtil.getEndDayOfSomeMonth(date);
					System.out.println(start.toString());
					System.out.println(end.toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	  
				}  
}
