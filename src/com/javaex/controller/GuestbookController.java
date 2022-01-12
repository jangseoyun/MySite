package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;


@WebServlet("/guest")
public class GuestbookController extends HttpServlet {
	//private static final long serialVersionUID = 1L;
       
	//기본 생성자 삭제
	
	//dao vo guestbook꺼 가져다쓰기
	//첫화면은 guestbook 화면 가져다 쓰기
	//데이터 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		GuestBookDao guestbookDao = new GuestBookDao();
		GuestbookVo guestbookVo;
		
		if("gbadd".equals(action)) {
			
			System.out.println("action=gbadd");
			
			String name = request.getParameter("name");
			String password = request.getParameter("pass");
			String content = request.getParameter("content");
			
			guestbookVo = new GuestbookVo(name,password,content); 
			guestbookDao.guestbookInsert(guestbookVo);
			
			//리다이렉트
			WebUtil.redirect(request, response, "/mysite/guest");
		
		} else {
			
			System.out.println("guest>addList");
			
			List<GuestbookVo> gbList = guestbookDao.getList();
			
			request.setAttribute("gbList", gbList);
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
