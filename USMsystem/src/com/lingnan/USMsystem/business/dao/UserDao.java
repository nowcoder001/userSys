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
	 * @param user
	 * @return
	 */
	public boolean addUser(UserVO user);
	
	/**
	 * 用户登录
	 * @param name 输入用户名
	 * @param pass 输入密码
	 * @return
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
	 * @param id 
	 */
	public UserVO findUserByID(int id);
	/**
	 * 根据用户名查找用户
	 * @param name
	 * @return
	 */
	public UserVO  findUserByName(String name);
	/**
	 * 查询所有用户
	 * @return
	 */
	public Vector<UserVO> findAll();
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public boolean updateUser(UserVO user);
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public boolean deleteUser(int id);
	/**
	 * 查询所有有效用户
	 * @return
	 */
	public Vector<UserVO> findAllValid();
	/**
	 * 获取指定页的用户信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Vector<UserVO> findUsers(int pageNo, int pageSize);
	/**
	 * 获取记录数量
	 * @return
	 */
	public int getRecordCount();
}
