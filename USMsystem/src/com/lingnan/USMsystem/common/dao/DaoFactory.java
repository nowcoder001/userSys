package com.lingnan.USMsystem.common.dao;

import java.sql.Connection;
import com.lingnan.USMsystem.business.dao.UserDaoImpl;
import com.lingnan.USMsystem.common.exception.ServiceException;

public class DaoFactory {
/**
 * 获取用户dao对象的工厂方法
 * 
 * @param conn 数据库连接对象
 * @param type dao常量
 * @return dao接口
 */
	public static BaseDao getDao(Connection conn, String type) {
		//如果传入的dao类型是用户user，就实例化userDao实现类
		if("user".equals(type)) {
			// 返回实例化的dao对象
			return new UserDaoImpl(conn);
		}
		//else if("order".equals(type)) {
		//	return new OrderDaoImpl(conn);
		//}
		// 如果没有与以上指定类型相匹配的值，就提示错误信息
		else {
			throw new ServiceException("dao工厂方法出错......");
		}
	}
}
