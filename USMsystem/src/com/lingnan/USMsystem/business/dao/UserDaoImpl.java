package com.lingnan.USMsystem.business.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lingnan.USMsystem.USM.domain.UserVO;
import com.lingnan.USMsystem.common.exception.DaoException;
import com.lingnan.USMsystem.common.exception.ServiceException;
import com.lingnan.USMsystem.common.util.DBUtils;
import com.lingnan.USMsystem.common.util.TypeUtils;

/**
 * 用户dao的实现类
 * @author Administrator
 *
 */
public class UserDaoImpl implements UserDao {
	/**
	 * 数据库连接
	 */
	private Connection conn;
	/**
	 * 构造方法
	 * 
	 * @param conn 数据库连接
	 */
	public UserDaoImpl(Connection conn) {
		// 给属性赋初值
		this.conn = conn;
	}
	
	// 创建预编译对象
	PreparedStatement pstm = null;
	// 创建结果集
	ResultSet rs = null;
	// 创建一个user对象
	UserVO user= null;
	/**
	 * 用户登录
	 * @return 返回UserVO对象
	 */
	public UserVO login(String name, String pass) {
		try {
			pstm = conn.prepareStatement("select * from users where name = ? and pass = ?");
			pstm.setString(1, name);
			pstm.setString(2, pass);
			rs = pstm.executeQuery();
			while(rs.next()) {
				user = new UserVO();
				user.setId(rs.getInt("id"));
				user.setUserid(rs.getString("userid"));
				user.setName(rs.getString("name"));
				user.setPass(rs.getString("pass"));
				user.setMail(rs.getString("mail"));
				user.setPower(rs.getString("power"));
				user.setBirth(rs.getDate("birth"));
				user.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			throw new ServiceException("SQL语句运行错误......");
		}
		return user;
	}

	/**
	 * 查找最大的id值
	 * @return 返回最大的id值
	 */
	public int findMaxId() {
		int maxid;
		try {
			pstm = conn.prepareStatement("select MAX(id) from users");
			rs = pstm.executeQuery();
			rs.next();
			maxid = rs.getInt(1);
		} catch (SQLException e) {
			throw new DaoException("SQL语句运行错误......");
		}
		return maxid;
	}

	/**
	 * @param id 要查找的id
	 * @return 与id相应的用户信息
	 */
	public UserVO findUserByID(int id) {
		try {
			pstm = conn.prepareStatement("select * from users where id = '"+id+"'");
			rs = pstm.executeQuery();
			while(rs.next()) {
				user = new UserVO();
				user.setId(rs.getInt("id"));
				user.setUserid(rs.getString("userid"));
				user.setName(rs.getString("name"));
				user.setPass(rs.getString("pass"));
				user.setMail(rs.getString("mail"));
				user.setPower(rs.getString("power"));
				user.setBirth(rs.getDate("birth"));
				user.setStatus(rs.getString("status"));
			}
		} catch (SQLException e) {
			throw new ServiceException("SQL语句运行错误......");
		}
		return user;
	}
	
	/**
	 * 注册用户/添加用户
	 * @return 注册/添加成功返回true，否则返回false
	 */
	public boolean addUser(UserVO user) {
		boolean flag = false;
		UserDaoImpl userDaoImpl  = new UserDaoImpl(conn);
		//int id = user.getId();
		int id = userDaoImpl.findMaxId()+1;
		String userid = user.getUserid();
		String name = user.getName();
		String pass = user.getPass();
		String mail = user.getMail();
		String power = user.getPower();
		Date birth = (Date) user.getBirth();
		String status = user.getStatus();
		String sql = "insert into users values"
				+"('"+id+"','"+userid+"','"+name+"','"+pass+"','"+mail+"','"+power+"','"+birth+"','"+status+"')";
		try {
			pstm = conn.prepareStatement(sql);
			pstm.executeUpdate();
			flag = true;
			
		} catch (SQLException e) {
			throw new ServiceException("用户注册失败......");
		}
		return flag;
	}
	
}
