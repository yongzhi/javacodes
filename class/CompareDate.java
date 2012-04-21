package com;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class CompareDate {

	/**
	 * 计算两个时间相差的年月日
	 * 
	 * @param Date
	 *            src
	 * @param Date
	 *            dst
	 * @throws ParseException
	 */
	public static String compareYMD(Date src, Date dst) {
		Calendar srcCal = Calendar.getInstance();
		srcCal.setTime(src);
		Calendar dstCal = Calendar.getInstance();
		dstCal.setTime(dst);

		// 比较年月日
		int year = dstCal.get(Calendar.YEAR) - srcCal.get(Calendar.YEAR);
		int month = dstCal.get(Calendar.MONTH) - srcCal.get(Calendar.MONTH);
		int day = dstCal.get(Calendar.DAY_OF_MONTH)
				- srcCal.get(Calendar.DAY_OF_MONTH);
		// 实际年份差：
		year = year
				- ((month > 0) ? 0 : ((month < 0) ? 1 : ((day >= 0 ? 0 : 1))));
		// 实际月份差：
		month = (month <= 0) ? (day > 0 ? 12 + month : 12 + month - 1)
				: (day >= 0 ? month : month - 1);
		// 去除年月之后的剩余的实际天数差：
		dstCal.add(Calendar.MONTH, -1);
		day = (day <= 0) ? (perMonthDays(dstCal)) + day : day;
		String ages = year + "年" + month + "个月零" + day + "天";
		return ages;
	}

	/**
	 * 判断一个时间所在月有多少天
	 * 
	 * @param Calendar
	 *            具体时间的日历对象
	 * @throws ParseException
	 */
	public static int perMonthDays(Calendar cal) {
		int maxDays = 0;
		int month = cal.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.MARCH:
		case Calendar.MAY:
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.OCTOBER:
		case Calendar.DECEMBER:
			maxDays = 31;
			break;
		case Calendar.APRIL:
		case Calendar.JUNE:
		case Calendar.SEPTEMBER:
		case Calendar.NOVEMBER:
			maxDays = 30;
			break;
		case Calendar.FEBRUARY:
			if (isLeap(cal.get(Calendar.YEAR))) {
				maxDays = 29;
			} else {
				maxDays = 28;
			}
			break;
		}
		return maxDays;
	}

	/**
	 * 判断某年是否是闰年
	 * 
	 * @param year
	 *            年份
	 * @throws ParseException
	 */
	public static boolean isLeap(int year) {
		boolean leap = false;
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			leap = true;
		}
		return leap;
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		// 当前时间
		Date now = new Date();
		// 设置一个时间2012-2-18 8:14  （Java 日期中年份是0表示1900年，月份0表示一月）
		Date birthdy = new Date(2012 - 1900, 2 - 1, 18, 8, 14);
		// 计算日期差值
		String ages = compareYMD(birthdy, now);
		System.out.println(ages);
	}

}
