package com.lingnan.USMsystem.business.dao;

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
	
}
