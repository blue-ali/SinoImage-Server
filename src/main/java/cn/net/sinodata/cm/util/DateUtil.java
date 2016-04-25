/**
 * 
 */
package cn.net.sinodata.cm.util;

import java.util.Date;

/**
 * @author Administrator
 *
 */
public class DateUtil {
	
	public static Date addTime(final Date date , int hour, int minute, int second){
		int time = (hour * 60 * 60 + minute * 60 + second) * 1000;
		date.setTime(date.getTime() + time);
		return date;
	}
	
	public static void main(String[] args) {
	}

}
