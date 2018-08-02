package com.USMsystem.USM.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Vector;

import com.lingnan.USMsystem.USM.domain.UserVO;
import com.lingnan.USMsystem.business.dao.UserDaoImpl;
import com.lingnan.USMsystem.common.util.DBUtils;
import com.lingnan.USMsystem.common.util.TypeUtils;

/**
 *定义管理员操作界面
 * @author Administrator
 *
 */
public class AdminFrame extends NormalFrame{
	/**
	 * 带参的构造器，用于初始化user属性
	 * @param user 用户信息
	 */
	public AdminFrame(UserVO user) {
		super(user);
	}
	public void loginSuccShow() {
		System.out.println("欢迎录主窗体......");
		System.out.println("您好，"+user.getName()+"      您的权限是："+user.getPower());
	// 判断用户权限，若为管理员则进入管理员操作界面，若为普通用户，则进入普通用户界面
		if(user.getPower().equals("管理员"))
			this.show();
		else {
			new NormalFrame(user).show();
		}
	}
	/**
	 * 管理员操作界面
	 */
	public void show() {
		// 声明缓冲处理流对象，用于接收控制台输入的数据
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		
		// 循环操作
		while(true) {
			// 管理员操作界面
			System.out.println("欢迎来到管理员操作界面");
			System.out.println("************************************");
			System.out.println("添加用户------------------------------1");
			System.out.println("删除用户------------------------------2");
			System.out.println("修改用户------------------------------3");
			System.out.println("查询用户------------------------------4");
			System.out.println("退出程序------------------------------0");
			int i = -1;
			// 读取用户控制台输入，如果输入值正确则中断循环，否则提示错误信息，重新输入
			while(true) {
				try {
					// 读取用户输入操作选项的数字并转换成int类型
					i = Integer.parseInt(buf.readLine());
					// 中断该循环，并进入下一步操作：i值判断
					break;
				} catch (Exception e) {
					// 出现异常时，提示错误信息，并要求重新输入
					System.out.println("输入错误，只能输入0~4之间的数字，请重新输入：");
				} 
			}
			/*
			 * 判断用户输入的值，
			 * 若输入值为1，则进行添加用户操作；输入值为2，进行删除用户操作；
			 * 输入值为3，进行修改用户操作；输入值为4，进行查询用户操作；
			 * 输入值为0，退出系统。
			 */
			IndexFrame index = new IndexFrame();
			switch(i) {
			case 1:
				this.addShow(); // 添加用户
				break;
			case 2:
				this.delShow();	// 删除用户
				break; // 中断switch
			case 3:
				this.updateShow();	//修改用户
				break;
			case 4:
				this.searchShow();	//查询界面
				break;
			case 0:
				index.show();
				break;
			default: // 输入0~4之外的数字
				System.out.println("输入操作有误，请重新输入！");
			}
		}
	}

	
	/**
	 * 修改用户信息界面
	 */
	public void updateShow() {
		Connection conn = DBUtils.getConnection();
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		UserDaoImpl ud4  = new UserDaoImpl(conn);
		try {
			System.out.println("请输入您要修改的用户的id:");
			int id = Integer.parseInt(buf.readLine());
			UserVO uv3 = ud4.findUserByID(id);
			if(uv3 != null) {
				System.out.println("查询成功，结果如下：");
				System.out.println(uv3.getId()+" "+uv3.getUserid()+" "+uv3.getName()+" "+uv3.getPass()+" "
						+uv3.getMail()+" "+uv3.getPower()+" "+uv3.getBirth()+" "+uv3.getStatus());
				System.out.println("请输入新的用户名：");
				String newName = buf.readLine();
				uv3.setName(newName);
				System.out.println("请输入新的密码：");
				String newPass = buf.readLine();
				uv3.setPass(newPass);
				while(true) {
					System.out.println("请输入新邮箱：");
					String newMail = buf.readLine();
					if(TypeUtils.checkEmail(newMail)) {
						uv3.setMail(newMail);
						break;
					}
				}
				System.out.println("请输入要修改的用户权限（管理员/普通用户)：");
				String newPower = buf.readLine();
				// 调用数据库工具类的beginTransaction方法，开启事务
				DBUtils.beginTransaction(conn);
				boolean flag = false;
				uv3.setPower(newPower);
				// 调用dao中的updateUser方法，进行数据修改操作
				flag = ud4.updateUser(uv3);
				// 调用数据库工具类的commit方法，提交事务
				DBUtils.commit(conn);
				if(flag)
					System.out.println("修改成功......");
				else
					System.out.println("修改失败......");
			} else {
				System.out.println("抱歉没有找到id为"+id+"的记录......");
			}
		} catch (IOException e) {
			System.out.println("输入错误......");
			// 出现异常，回滚事务
			DBUtils.rollback(conn);
		}
	}
	
	/**
	 * 用户查询界面
	 */
	public void searchShow() {
		Connection conn = DBUtils.getConnection();
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		// 循环操作
		while(true) {
			// 用户查询界面
			System.out.println("欢迎来到用户查询界面");
			System.out.println("******************************");
			System.out.println("查询全部用户---------------------1");
			System.out.println("根据ID查询----------------------2");
			System.out.println("根据用户名查询--------------------3");
			System.out.println("退出程序------------------------0");
			System.out.println("请输入要做的操作：");
			int i = -1;
			// 读取用户控制台输入，如果输入值正确则中断循环，否则提示错误信息，重新输入
			while(true) {
				try {
					// 读取用户输入操作选项的数字并转换成int类型
					i = Integer.parseInt(buf.readLine());
					// 中断该循环，并进入下一步操作：i值判断
					break;
				} catch (Exception e) {
					// 出现异常时，提示错误信息，并要求重新输入
					System.out.println("输入错误，只能输入0~3之间的数字，请重新输入：");
				} 
			}
			
			/*
			 * 判断用户输入的值，若输入值为1，则查询全部用户；
			 * 输入值为2，进行根据ID查询；输入值为3，根据用户名查询。
			 */
			switch(i) {
			case 1:
				System.out.println("请输入页面大小：");
				try {
					UserDaoImpl ud1  = new UserDaoImpl(conn);
					//Vector<UserVO> u = ud1.findAll();
					int pageSize = Integer.parseInt(buf.readLine());
					System.out.println("请输入要查询的页码");
					int pageNo = Integer.parseInt(buf.readLine());
					Vector<UserVO> u = ud1.findUsers(pageNo, pageSize);
					if(u != null) {
						System.out.println("查询成功，结果如下：");
						this.selectUserShow(u,pageNo,pageSize);
					}
				} catch (NumberFormatException | IOException e2) {
					System.out.println("输入有误，请重新输入......");
				}
//				System.out.println("查询成功，结果如下：");
//				for(UserVO v: u) {
//					System.out.println(v.getId()+"  "+v.getUserid()+"  "+v.getName()+"  "+v.getPass()+"  "
//							+v.getMail()+"  "+v.getPower()+"  "+v.getBirth()+"  "+v.getStatus());
//				}
				break;
			case 2:
				int id = 0;
				UserDaoImpl ud2  = new UserDaoImpl(conn);
				try {
					System.out.println("请输入您要查找的id：");
					id = Integer.parseInt(buf.readLine());
					UserVO uv1 = ud2.findUserByID(id);
					if(uv1 != null) {
						System.out.println("查询成功，结果如下：");
						System.out.println(uv1.getId()+" "+uv1.getUserid()+" "+uv1.getName()+" "+uv1.getPass()+" "
						+uv1.getMail()+" "+uv1.getPower()+" "+uv1.getBirth()+" "+uv1.getStatus());
					} else {
						System.out.println("没有找到id为"+id+"的记录......");
					}
				} catch (Exception e1) {
					System.out.println("出现异常......");
				}
				break; // 中断switch
			case 3:
				String name = null;
				UserDaoImpl ud3  = new UserDaoImpl(conn);
				try {
					System.out.println("请输入您要查找的用户名：");
					name = buf.readLine();
					UserVO uv2 = ud3.findUserByName(name);
					if(uv2 != null) {
						System.out.println("查询成功，结果如下：");
						System.out.println(uv2.getId()+" "+uv2.getUserid()+" "+uv2.getName()+" "+uv2.getPass()+" "
						+uv2.getMail()+" "+uv2.getPower()+" "+uv2.getBirth()+" "+uv2.getStatus());
					} else {
						System.out.println("抱歉，没有找到用户名为"+name+"的记录......");
					}
				} catch (IOException e) {
					System.out.println("输入输出异常......");
				}
				break;
			case 0:
				// 退出当前界面
				this.show();
				break;
			default: // 输入0~3之外的数字
				System.out.println("输入操作有误，请重新输入！");
			}
		}
	}
	/**
	 * 分页查询的实现
	 * @param u Vector<UserVO>对象
	 * @param pageNo 页码
	 * @param pageSize 页面大小，即每页显示的记录数
	 */
	public void selectUserShow(Vector<UserVO> u, int pageNo, int pageSize) {
			Connection conn = DBUtils.getConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			java.util.Iterator<UserVO> it = u.iterator();
			UserVO user = null;
			while(it.hasNext()) {
				user = it.next();
				System.out.println("---------------------------------------------------------------------------------------------------|");
				System.out.println("ID:"+user.getId()+"        用户名:"+user.getName()+"           密码:"+user.getPass()+"          邮箱:"+user.getMail()+"           权限:"+user.getPower());
				System.out.println("---------------------------------------------------------------------------------------------------|");
			}
			System.out.println("第"+pageNo+"页");
			System.out.println("上一页---------------1");
			System.out.println("下一页---------------2");
			System.out.println("退出查询-------------3");
			int i = -1;
			while(true) {
				try {
					i = Integer.parseInt(br.readLine());
					break;
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					System.out.println("你的输入有误，只能输入1~3的数字");
					System.out.println("请重新输入");
				}
			}
			switch(i) {
			case 1:
				try {
					UserDaoImpl ud  = new UserDaoImpl(conn);
					Vector<UserVO> u15 = ud.findUsers(pageNo-1, pageSize);
					if(u15 != null)
						this.selectUserShow(u15, pageNo-1,pageSize);
				} catch (NumberFormatException e) {
					System.out.println("出现异常......");
				}
				break;
			case 2:
				try {
					UserDaoImpl ud  = new UserDaoImpl(conn);
					Vector<UserVO> u16 = ud.findUsers(pageNo+1, pageSize);
					if(u16 != null)
						this.selectUserShow(u16, pageNo+1,pageSize);
				} catch (NumberFormatException e) {
					System.out.println("出现异常......");
				}
				break;
			case 3:
				this.show();
				break;
			default:
				System.out.println("您的输入操作不正确，请重新输入");
			}
	}
	
	/**
	 * 删除用户界面
	 */
	public void delShow() {
		Connection conn = DBUtils.getConnection();
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		UserDaoImpl ud5  = new UserDaoImpl(conn);
		try {
			System.out.println("请输入您要删除的用户的id:");
			int id = Integer.parseInt(buf.readLine());
			UserVO uv5 = ud5.findUserByID(id);
			if(uv5 != null) {
				System.out.println("查询成功，结果如下：");
				System.out.println(uv5.getId()+" "+uv5.getUserid()+" "+uv5.getName()+" "+uv5.getPass()+" "
						+uv5.getMail()+" "+uv5.getPower()+" "+uv5.getBirth()+" "+uv5.getStatus());
				System.out.println("确定删除该用户？(yes/no)");
				String choose = buf.readLine();
				if(choose.equals("yes")) {
					boolean flag = false;
					flag = ud5.deleteUser(id);
					if(flag)
						System.out.println("删除成功......");
				} else {
					System.out.println("抱歉，没有找到id为"+id+"的记录......");
				}
			}
		} catch (IOException e) {
			System.out.println("输入错误......");
		}
	}
}
