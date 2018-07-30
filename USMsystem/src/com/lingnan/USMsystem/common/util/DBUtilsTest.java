package com.lingnan.USMsystem.common.util;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

public class DBUtilsTest {

	@Test	// 注解
	public void testGetConnection() {
		Connection conn = DBUtils.getConnection();
		System.out.println("这是获取数据库连接的方法"+conn);
	}

	@Test
	public void testBeginTransaction() {
		System.out.println("这是开启事务的方法");
	}

	@Test
	public void testCommit() {
		System.out.println("这是提交事务的方法");
	}

	@Test
	public void testRollback() {
		System.out.println("这是回滚事务的方法");
	}

	@Test
	public void testCloseStatement() {
		System.out.println("这是关闭statement对象的方法");
	}

	@Test
	public void testCloseResultSet() {
		System.out.println("这是关闭结果集的方法");
	}

	@Test
	public void testCloseconn() {
		System.out.println("这是关闭数据库连接的方法");
	}

}
