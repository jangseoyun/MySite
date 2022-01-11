package com.javaex.dao;

import com.javaex.vo.UserVo;

public class TestDao {

	public static void main(String[] args) {
		
		UserVo userVo = new UserVo("ddd","1234","강호동","male");
		UserDao userDao = new UserDao();
		
		userDao.insert(userVo);
		System.out.println();

	}

}
