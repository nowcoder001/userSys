package com.lingnan.USMsystem.USM.domain;

import java.util.Date;

/**
 * 用户信息类
 * @author Administrator
 *
 */
public class UserVO {
	
	private int id;					// 编号，主键
	private String userid; 			// 用户编号
	private String name;			// 用户名
	private String pass;			// 密码
	private String mail;			// 邮箱
	private String power;			// 用户类型
	private Date birth;				// 生日
	private String status;			// 状态
	
	/**
	 * 获取编号
	 * @return 用户的id
	 */
	public int getId() {
		return id;
	}
	/**
	 * 设置编号
	 * @param id 编号
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 获取用户id
	 * @return 用户id
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * 设置用户id
	 * @param userid 用户id
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * 获取用户名
	 * @return 用户名name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置用户名name
	 * @param name 用户名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取密码
	 * @return 密码
	 */
	public String getPass() {
		return pass;
	}
	/**
	 * 设置密码
	 * @param pass 密码
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	/**
	 * 获取邮箱
	 * @return 邮箱
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * 设置邮箱
	 * @param mail 邮箱
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * 获取用户类型
	 * @return 用户类型
	 */
	public String getPower() {
		return power;
	}
	/**
	 * 设置用户类型
	 * @param power 用户类型
	 */
	public void setPower(String power) {
		this.power = power;
	}
	/**
	 * 获取出生日期
	 * @return 出生日期
	 */
	public Date getBirth() {
		return birth;
	}
	/**
	 * 设置出生日期
	 * @param birth 出生日期
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	/**
	 * 获取用户当前状态
	 * @return 返回用户当前状态，1/0
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置用户当前状态
	 * @param status 用户当前状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}
}
