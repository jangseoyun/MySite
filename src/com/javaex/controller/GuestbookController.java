package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/guest")
public class GuestbookController extends HttpServlet {
	//private static final long serialVersionUID = 1L;
       
	//기본 생성자 삭제
	
	//dao vo guestbook꺼 가져다쓰기
	//첫화면은 guestbook 화면 가져다 쓰기
	//데이터 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
