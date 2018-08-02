package com.lingnan.USMsystem.USM.business.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lingnan.USMsystem.USM.domain.UserVO;
import com.lingnan.USMsystem.business.dao.UserDao;
import com.lingnan.USMsystem.business.dao.UserDaoImpl;
import com.lingnan.USMsystem.common.constant.EnumType;
import com.lingnan.USMsystem.common.dao.DaoFactory;
import com.lingnan.USMsystem.common.exception.DaoException;
import com.lingnan.USMsystem.common.exception.ServiceException;
import com.lingnan.USMsystem.common.util.DBUtils;

/**
 * 
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService{
	/**
	 * 用户service类实例，在类的内部创建唯一的实例
	 */
	private static UserService userService = new UserServiceImpl();
	/**
	 * 构造方法私有化
	 */
	private UserServiceImpl() {
		
	}
	/**
	 * 获取用户service实例
	 * @return 实例对象
	 */
	public static UserService getInstance() {
		return userService;
	}
	/**
	 * 用户登录
	 * @param name 用户名
	 * @param pass 密码
	 * @return 用户信息
	 */
	public UserVO login(String name, String pass) {
		// 声明数据库连接对象，用于保存数据库连接对象
		Connection conn = null;
		UserVO user = null;
		try{
			// 调用数据库工具类的getConnection方法，取得数据库连接对象，并赋值给数据库连接对象变量
			conn = DBUtils.getConnection();
			// 调用dao工厂类的getDao方法，取得指定类型的dao接口的实现类，并赋值给dao接口变量
			UserDao uDao = (UserDao) DaoFactory.getDao(conn, EnumType.USER_DAO);
			// 调用dao中的login方法，进行登录操作，将结果赋给登录结果变量
			user = uDao.login(name, pass);
		} catch (DaoException e) {
			// 将自定义的异常抛出
			throw e;
		} catch (Exception e) {
			throw new ServiceException("用户登录错误......",e);
		} finally {
			DBUtils.closeconn(conn);
		}
		// 返回用户登录结果
		return user;
	}
	
	/**
	 * 用户注册/添加用户
	 * @param user 要添加的用户信息
	 * @return 成功返回true，失败返回false
	 * @throws ServiceException 自定义的异常
	 */
	public boolean addUser(UserVO user) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstm = null;
		conn = DBUtils.getConnection();
		UserDaoImpl userDaoImpl  = new UserDaoImpl(conn);
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
