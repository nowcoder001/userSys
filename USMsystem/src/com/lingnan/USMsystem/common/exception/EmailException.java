package com.lingnan.USMsystem.common.exception;

public class EmailException extends ServiceException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 默认的构造方法
	 */
	public EmailException() {
		
	}
	
	/**
	 * 异常信息的构造方法
	 * @param message 异常的详细信息
	 */
	public EmailException(String message) {
		super(message);
	}
	
	/**
	 * 异常原因的构造方法
	 * @param cause 引起异常的原因
	 */
	public EmailException(Throwable cause) {
		super(cause);
	}
	
	/**
	 * 异常信息及引起异常的原因的方法
	 * @param message 异常的详细信息
	 * @param cause 引起异常的原因
	 */
	public EmailException(String message, Throwable cause) {
		super(message, cause);
	}
}
