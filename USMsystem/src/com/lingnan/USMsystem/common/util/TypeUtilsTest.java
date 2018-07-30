package com.lingnan.USMsystem.common.util;

import static org.junit.Assert.*;

import java.beans.Statement;
import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import com.lingnan.USMsystem.common.exception.DateException;

public class TypeUtilsTest {

	@Test
	public void testDateToStr() {
		Date date = new Date();
		String str = TypeUtils.dateToStr(date);
		System.out.println(str);
	}

	@Test
	public void testStrToDate() {
		String str = "2018-07-28";
		Date date = null;
		try {
			date = TypeUtils.strToDate(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new DateException("字符串转日期失败......");
		}
		System.out.println(date);
	}
	
	@Test
	public void testIsMail() {
		String str = "";
		boolean flag = false;
		flag = TypeUtils.checkEmail(str);
		System.out.println(flag);
	}

}
