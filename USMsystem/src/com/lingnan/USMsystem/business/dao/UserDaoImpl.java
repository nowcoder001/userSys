package com.lingnan.USMsystem.business.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.lingnan.USMsystem.USM.domain.UserVO;
import com.lingnan.USMsystem.common.exception.DaoException;
import com.lingnan.USMsystem.common.exception.ServiceException;
import com.lingnan.USMsystem.common.util.DBUtils;

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
	
	/**
	 * 用户登录
	 * @param name 用户名
	 * @param pass 密码
	 * @return 用户信息
	 */
	public UserVO login(String name, String pass) {
		// 创建预编译对象
		PreparedStatement pstm = null;
		// 创建结果集
		ResultSet rs = null;
		// 创建一个user对象
		UserVO user= null;
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
		} finally {
			DBUtils.closePreparedStatement(conn);
			DBUtils.closeResultSet(conn);
		}
		return user;
	}

	/**
	 * 查找最大的id值
	 * @return 返回最大的id值
	 */
	public int findMaxId() {
		int maxid;
		// 创建预编译对象
		PreparedStatement pstm = null;
		// 创建结果集
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("select MAX(id) from users");
			rs = pstm.executeQuery();
			rs.next();
			maxid = rs.getInt(1);
		} catch (SQLException e) {
			throw new DaoException("SQL语句运行错误......");
		} finally {
			DBUtils.closePreparedStatement(conn);
			DBUtils.closeResultSet(conn);
		}
		return maxid;
	}

	
	/**
	 * 注册用户/添加用户
	 * @return 注册/添加成功返回true，否则返回false
	 */
	public boolean addUser(UserVO user) {
		boolean flag = false;
		// 创建预编译对象
		PreparedStatement pstm = null;
		UserDaoImpl userDaoImpl  = new UserDaoImpl(conn);
		int id = userDaoImpl.findMaxId()+1;
		String userid = user.getUserid();
		String name = user.getName();
		String pass = user.getPass();
		String mail = user.getMail();
		// 注册时默认是普通用户
		String power = "普通用户";
		// 将获取的java.util.Date格式的日期转换成java.sql.Date格式
		Date birth = new java.sql.Date(user.getBirth().getTime());
		// 注册时状态status默认为1，代表有效
		String status = "1";
		try {
			pstm = conn.prepareStatement("insert into users values(?,?,?,?,?,?,?,?)");
			pstm.setInt(1, id);
			pstm.setString(2, userid);
			pstm.setString(3, name);
			pstm.setString(4, pass);
			pstm.setString(5, mail);
			pstm.setString(6, power);
			pstm.setDate(7, birth);
			pstm.setString(8, status);
			int rows = pstm.executeUpdate();
			if(rows != 0)
				flag = true;
			
		} catch (SQLException e) {
			throw new ServiceException("用户注册失败......");
		} finally {
			DBUtils.closePreparedStatement(conn);
		}
		return flag;
	}

	/**
	 * @param id 要查找的id
	 * @return 与id相应的用户信息
	 */
	public UserVO findUserByID(int id) {
		// 创建预编译对象
		PreparedStatement pstm = null;
		// 创建结果集
		ResultSet rs = null;
		// 创建一个user对象
		UserVO user= null;
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
		} finally {
			DBUtils.closePreparedStatement(conn);
			DBUtils.closeResultSet(conn);
		}
		return user;
	}
	
	
	/**
	 * 根据用户名查询用户
	 * @param name 用户名
	 * @return 用户信息
	 */
	public UserVO findUserByName(String name) {
		// 创建预编译对象
		PreparedStatement pstm = null;
		// 创建结果集
		ResultSet rs = null;
		// 创建一个user对象
		UserVO user= null;
		try {
			pstm = conn.prepareStatement("select * from users where name like '%"+name+"%'");
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
			System.out.println("SQL语句运行错误......");
		} finally {
			DBUtils.closePreparedStatement(conn);
			DBUtils.closeResultSet(conn);
		}
		return user;
	}
	
	
	/**
	 * 查询所有用户
	 */
	public Vector<UserVO> findAll() {
		Vector<UserVO> v = new Vector<UserVO>();
		UserVO user = null;
		// 创建预编译对象
		PreparedStatement pstm = null;
		// 创建结果集
		ResultSet rs = null;
		try {
			pstm = conn.prepareStatement("select * from users order by id");
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
				v.add(user);
			}
		} catch (SQLException e) {
			throw new ServiceException("SQL语句运行错误......");
		} finally {
			DBUtils.closePreparedStatement(conn);
			DBUtils.closeResultSet(conn);
		}
		return v;
	}

	/**
	 * 修改/更新用户信息
	 */
	public boolean updateUser(UserVO user) {
		boolean flag = false;
		// 创建预编译对象
		PreparedStatement pstm = null;
		int id = user.getId();
		String name = user.getName();
		String pass = user.getPass();
		String mail = user.getMail();
		try {
			pstm = conn.prepareStatement("update users set name = ?,pass = ?,mail = ? where id = ?");
			pstm.setString(1, name);
			pstm.setString(2, pass);
			pstm.setString(3, mail);
			pstm.setInt(4, id);
			int rows = pstm.executeUpdate();
			if(rows != 0)
				flag = true;
		} catch (SQLException e) {
			throw new ServiceException("SQL语句运行错误......");
		} finally {
			DBUtils.closePreparedStatement(conn);
		}
		return flag;
	}

	/**
	 * 删除用户
	 * @param id 用户的id
	 */
	public boolean deleteUser(int id) {
		boolean flag = false;
		// 创建预编译对象
		PreparedStatement pstm = null;
		try {
			pstm = conn.prepareStatement("delete from users where id = '"+id+"'");
			pstm.executeUpdate();
			flag = true;
		} catch (SQLException e) {
			throw new ServiceException("SQL语句运行错误......");
		} finally {
			DBUtils.closePreparedStatement(conn);
		}
		return flag;
	}

	/**
	 * 查询所有有效用户
	 */
	public Vector<UserVO> findAllValid() {
		// 创建预编译对象
		PreparedStatement pstm = null;
		// 创建结果集对象
		ResultSet rs = null;
		// 创建Vector<UserVO>对象
		Vector<UserVO> v = new Vector<UserVO>();
		// 创建user对象
		UserVO user = null;
		try {
			pstm = conn.prepareStatement("select * from users where status = '1'");
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
				v.add(user);
			}
		} catch (SQLException e) {
			throw new ServiceException("SQL语句运行错误......");
		} finally {
			DBUtils.closePreparedStatement(conn);
			DBUtils.closeResultSet(conn);
		}
		return v;
	}

	/**
	 * 获取指定页的用户信息
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 用户信息
	 */
	public Vector<UserVO> findUsers(int pageNo, int pageSize) {
		// 声明预编译对象变量
		PreparedStatement pstm = null;
		// 创建结果集对象
		ResultSet rs = null;
		// 声明用户对象变量
		Vector<UserVO> v = new Vector<UserVO>();
		// 声明user对象
		UserVO user = null;
		try {
			pstm = conn.prepareStatement("select * from(select t2.*,rownum rn from (select t1.* from users t1 order by id) t2) " +
					"where rn>? and rn<=?");
			pstm.setInt(1, (pageNo-1)*pageSize);
			pstm.setInt(2, pageNo*pageSize);
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
				v.add(user);
			}
		} catch (SQLException e) {
			System.out.println("SQL语句运行错误......");
		} finally {
			DBUtils.closeResultSet(conn);
			DBUtils.closePreparedStatement(conn);
		}
		return v;
	}

	/**
	 * 获取记录数量
	 */
	public int getRecordCount() {
		// 创建预编译对象
		PreparedStatement pstm = null;
		// 创建结果集对象
		ResultSet rs = null;
		int count = 0;
		try {
			pstm = conn.prepareStatement("select count(*) from users");
			rs = pstm.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			throw new ServiceException("SQL语句运行错误......");
		}
		return count;
	}
	
}
