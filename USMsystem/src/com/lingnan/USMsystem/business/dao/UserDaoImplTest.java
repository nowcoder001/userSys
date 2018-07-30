package com.lingnan.USMsystem.business.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.text.ParseException;

import org.junit.Test;

import com.lingnan.USMsystem.USM.domain.UserVO;
import com.lingnan.USMsystem.common.exception.DateException;
import com.lingnan.USMsystem.common.util.DBUtils;
import com.lingnan.USMsystem.common.util.TypeUtils;

public class UserDaoImplTest {

	@Test
	public void testLogin() {
	Connection conn = DBUtils.getConnection();
	UserDaoImpl userDaoImpl  = new UserDaoImpl(conn);
	UserVO u = userDaoImpl.login("Mike", "123456");
	System.out.println(u.getId()+" "+u.getUserid()+" "+u.getName()+" "+u.getPass()+" "
	+u.getMail()+" "+u.getPower()+" "+u.getBirth()+" "+u.getStatus());
	}
	
	@Test
	public void testFindMaxId() {
		Connection conn = DBUtils.getConnection();
		UserDaoImpl userDaoImpl  = new UserDaoImpl(conn);
		int id = userDaoImpl.findMaxId();
		System.out.println(id);
	}

	@Test
	public void testFindUserByID() {
		Connection conn = DBUtils.getConnection();
		UserDaoImpl userDaoImpl  = new UserDaoImpl(conn);
		UserVO u = userDaoImpl.findUserByID(2);
		System.out.println(u.getId()+" "+u.getUserid()+" "+u.getName()+" "+u.getPass()+" "
		+u.getMail()+" "+u.getPower()+" "+u.getBirth()+" "+u.getStatus());
	}

	@Test
	public void testAddUser() {
		Connection conn = DBUtils.getConnection();
		UserDaoImpl userDaoImpl  = new UserDaoImpl(conn);
		UserVO u = new UserVO();
		boolean flag = false;
		try {
			u.setId(userDaoImpl.findMaxId()+1);
			u.setUserid("333");
			u.setName("Jack");
			u.setPass("student666");
			u.setMail("www@123.com");
			u.setPower("管理员");
			u.setBirth(TypeUtils.strToDate("2018-07-29"));
			u.setStatus("1");
			flag = userDaoImpl.addUser(u);
		} catch (ParseException e) {
			throw new DateException("日期格式转换失败......");
		}
		System.out.println(flag);
	}

}
