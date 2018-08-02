package com.lingnan.USMsystem.business.dao;

import java.util.List;
import java.util.Vector;

import com.lingnan.USMsystem.USM.domain.UserVO;
import com.lingnan.USMsystem.common.dao.BaseDao;

/**
 * 用户dao接口
 * 需继承BaseDao接口
 * @author Administrator
 *
 */
public interface UserDao extends BaseDao{
	/**
	 * 注册用户/添加用户
	 * 
	 * @param user 用户信息
	 * @return true/false
	 */
	public boolean addUser(UserVO user);
	
	/**
	 * 用户登录
	 * @param name 输入用户名
	 * @param pass 输入密码
	 * @return 用户信息
	 */
	public UserVO login(String name, String pass);
	/**
	 * 查找当前最大的id，注册的新用户的id为当前最大的id的值加1
	 * @return 最大的id的值
	 */
	public int findMaxId();
	/**
	 * 根据id查找用户
	 * 
	 * @param id 用户的id
	 * @return 用户信息
	 */
	public UserVO findUserByID(int id);
	/**
	 * 根据用户名查找用户
	 * @param name 用户名
	 * @return 用户信息
	 */
	public UserVO  findUserByName(String name);
	/**
	 * 查询所有用户
	 * @return 用户信息
	 */
	public Vector<UserVO> findAll();
	/**
	 * 更新用户
	 * @param user 用户信息
	 * @return true/false
	 */
	public boolean updateUser(UserVO user);
	/**
	 * 删除用户
	 * @param id 用户的id
	 * @return true/false
	 */
	public boolean deleteUser(int id);
	/**
	 * 查询所有有效用户
	 * @return 用户信息集合
	 */
	public Vector<UserVO> findAllValid();
	/**
	 * 获取指定页的用户信息
	 * @param pageNo 页码
	 * @param pageSize 页面大小
	 * @return 用户信息集合
	 */
	public Vector<UserVO> findUsers(int pageNo, int pageSize);
	/**
	 * 获取记录数量
	 * @return 记录数量
	 */
	public int getRecordCount();
}
