package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;


@WebServlet("/main")
public class MainController extends HttpServlet {
	//private static final long serialVersionUID = 1L;

    //기본 생성자 삭제
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/main");
		
		//포워드
		WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//post형태로 하면 여기로 왔다가 get으로 감 
		doGet(request, response);
	}

}
