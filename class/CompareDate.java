package com;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class CompareDate {

	/**
	 * ��������ʱ������������
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

		// �Ƚ�������
		int year = dstCal.get(Calendar.YEAR) - srcCal.get(Calendar.YEAR);
		int month = dstCal.get(Calendar.MONTH) - srcCal.get(Calendar.MONTH);
		int day = dstCal.get(Calendar.DAY_OF_MONTH)
				- srcCal.get(Calendar.DAY_OF_MONTH);
		// ʵ����ݲ
		year = year
				- ((month > 0) ? 0 : ((month < 0) ? 1 : ((day >= 0 ? 0 : 1))));
		// ʵ���·ݲ
		month = (month <= 0) ? (day > 0 ? 12 + month : 12 + month - 1)
				: (day >= 0 ? month : month - 1);
		// ȥ������֮���ʣ���ʵ�������
		dstCal.add(Calendar.MONTH, -1);
		day = (day <= 0) ? (perMonthDays(dstCal)) + day : day;
		String ages = year + "��" + month + "������" + day + "��";
		return ages;
	}

	/**
	 * �ж�һ��ʱ���������ж�����
	 * 
	 * @param Calendar
	 *            ����ʱ�����������
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
	 * �ж�ĳ���Ƿ�������
	 * 
	 * @param year
	 *            ���
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
		// ��ǰʱ��
		Date now = new Date();
		// ����һ��ʱ��2012-2-18 8:14  ��Java �����������0��ʾ1900�꣬�·�0��ʾһ�£�
		Date birthdy = new Date(2012 - 1900, 2 - 1, 18, 8, 14);
		// �������ڲ�ֵ
		String ages = compareYMD(birthdy, now);
		System.out.println(ages);
	}

}
