package com.lingnan.USMsystem.USM.business.service;

import com.lingnan.USMsystem.USM.domain.UserVO;

public interface UserService {

	public UserVO login(String name, String pass);

}
