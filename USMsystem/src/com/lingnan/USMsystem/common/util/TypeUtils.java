package com.lingnan.USMsystem.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lingnan.USMsystem.common.exception.DateException;
import com.lingnan.USMsystem.common.exception.EmailException;

import oracle.net.aso.p;

/**
 * 字符串与日期类型之间相互转换的方法
 * @author Administrator
 *
 */
public class TypeUtils {
	/**
	 * 日期转换成字符串
	 * @param date 要转换成字符串的日期
	 * @return 按'yyyy-MM-dd'的格式返回字符串格式
	 */
	public static String dateToStr(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String str = null;
		try {
			str = sf.format(date);
		} catch(Exception e) {
			//System.out.println("日期转字符串失败......");
			throw new DateException("日期格式转换失败......");
		}
		return str;
	}
	/**
	 * 字符串转日期
	 * @param str 要转换为日期格式的字符串
	 * @return 返回指定格式的日期类型
	 * @throws ParseException 格式转换异常
	 */
	public static Date strToDate(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			 date = sdf.parse(str);
			
		} catch (Exception e) {
			//System.out.println("字符串转日期失败......");
			throw new DateException("字符串转日期失败......");
		}
		return date;
	}
	
	/**
	 * 判断输入的值是否为空
	 * @param str 输入的值
	 */
	public static void isNull(String str) {
		if(str == null)
			System.out.println("输入的值不能为空......");
	}
	
	/**
	 * 判断输入的邮箱格式是否正确
	 * @param str 输入的邮箱地址
	 * @return 返回邮箱地址是否正确
	 */
	public static boolean checkEmail(String str) {
		boolean flag = false;
			String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern p;
			Matcher m;
		try {
			  p = Pattern.compile(regEx1);
			  m = p.matcher(str);
			  if(m.matches())
				 flag = true;
		} catch (Exception e) {
			throw new EmailException("输入邮箱格式错误......",e);
		}
		return flag;
	}
}
