package com.lingnan.USMsystem.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lingnan.USMsystem.common.exception.DaoException;

/**
 * 数据库工具类
 * @author Administrator
 *
 */

public class DBUtils {
	private static String username="scott";
	private static String password="123456";
	private static String driver="oracle.jdbc.OracleDriver";
	private static String url="jdbc:oracle:thin:@localhost:1521:orcl";
	// 创建一个数据库连接
	private static Connection conn = null;
	// 创建statement语句对象
	private static Statement stat = null;
	// 创建一个预编译语句对象
	private static PreparedStatement pstm = null;
	
	// 创建一个结果集对象
	private static ResultSet rs = null;
	/**
	 * 获取数据库连接
	 * @return conn 返回获取的数据库连接
	 */
	public static Connection getConnection() {
		try {
			Class.forName(driver);	// 注册驱动程序
			conn = (Connection) DriverManager.getConnection	// 获取数据库连接
						(url, username, password);
			
		} catch (ClassNotFoundException e) {
			//System.out.println("数据库连接获取失败......");
			//e.printStackTrace();
			throw new DaoException("数据库连接获取失败......");
		} catch (SQLException e) {
			 // System.out.println("SQL语句运行错误......");
			  //e.printStackTrace();
			throw new DaoException("SQL语句运行异常......");
		} 
		System.out.println("数据库连接获取成功......");
		return conn;
	}
	
	/**
	 * 开启事务
	 * @param conn 要开启事务的数据库的连接
	 */
	public static void beginTransaction(Connection conn) {
		try {
			// 将事务的自动提交模式设为假
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			//System.out.println("事务开启失败......");
			//e.printStackTrace();
			throw new DaoException("事务开启失败......");
		}
	}
	
	/**
	 * 提交事务
	 * @param conn 要提交事务的数据库的连接
	 */
	public static void commit(Connection conn) {
		try {
			// 提交事务
			conn.commit();
			// 设置事务的自动提交模式为真
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			//System.out.println("事务提交失败......");
			//e.printStackTrace();
			throw new DaoException("事务提交失败......");
		}
	}
	
	/**
	 * 回滚事务
	 * @param conn 要回滚事务的数据库的连接
	 */
	public static void rollback(Connection conn) {
		try {
			// 回滚事务
			conn.rollback();
			// 将事务的自动提交模式设为真
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DaoException("事务回滚失败......");
		}
	}
	
	/**
	 * 关闭statement对象
	 * @param conn 要关闭statement对象的数据库的连接
	 */
	public static void closeStatement(Connection conn) {
			try {
				// 若Statement对象不为空，则关闭该对象
				if(stat != null) {
					stat.close();
					stat = null;
				}
			} catch (SQLException e) {
				throw new DaoException("statement对象关闭失败......");
			}
	}
	
	/**
	 * 关闭PreparedStatement对象
	 * @param conn 要关闭PreparedStatement对象的数据库的连接
	 */
	public static void closePreparedStatement(Connection conn) {
			try {
				// 若预编译对象不为空，则关闭该对象
				if(pstm != null) {
					pstm.close();
					pstm = null;
				}
			} catch (SQLException e) {
				throw new DaoException("PreparedStatement对象关闭失败......");
			}
	}
	/**
	 * 关闭结果集
	 * @param conn 要关闭结果集的数据库的连接
	 */
	public static void closeResultSet(Connection conn) {
		try {
			// 若结果集不为空，则关闭该结果集
			if(rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			throw new DaoException("结果集关闭失败......");
		}
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn 要关闭的数据库连接对象
	 */
	
	public static void closeconn(Connection conn) {
		try {
			// 若数据库连接对象不为空，则关闭该对象
			if(conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			throw new DaoException("数据库连接对象关闭失败......");
		}
	}
}