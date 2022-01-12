package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserController extends HttpServlet {
	//private static final long serialVersionUID = 1L;
       
	//기본 생성자 삭제
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if("joinForm".equals(action)) {
			
			System.out.println("action=joinForm");
			
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");
			
		} else if("join".equals(action)) {
			
			System.out.println("action=join");
			
			//회원가입 파라미터 호출
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			// vo로 객체화
			UserVo userVo = new UserVo(id,password,name,gender);
			//System.out.println(userVo);
			
			//dao 생성 후 insert로 데이터 저장
			UserDao userDao = new UserDao();
			//-->dao에서 count 활용, 성공 시 count 숫자 들어감
			int count = userDao.insert(userVo);
			
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinOk.jsp");
	
		} else if("loginForm".equals(action)) {
			
			System.out.println("action=loginForm");
			
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginForm.jsp");
			
		} else if("login".equals(action)) {
		
			System.out.println("action=login");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			UserDao userDao = new UserDao();
			UserVo authVo = userDao.getUser(id,password);
			
			if(authVo == null) {//로그인 실패 
				System.out.println("로그인 실패");
				
				WebUtil.redirect(request, response, "/mysite/user?action=loginForm&result=fail");
			} else {
				System.out.println("로그인 성공");
				
				//★세션 영역의 어트리뷰트에 데이터 넣기
				//세션 주소 불러오기
				 HttpSession session = request.getSession();
				 //세션 영역에 객체 넣기
				 session.setAttribute("authUser", authVo);
				
				 WebUtil.redirect(request, response, "/mysite/main");
			}
 
		} else if("logout".equals(action)) {
			
			System.out.println("action=logout");
			
			HttpSession session = request.getSession();
			//세션 값 지우기 
			session.removeAttribute("authUser");
			session.invalidate();
			
			//리다이렉트
			WebUtil.redirect(request, response, "/mysite/main");
			
		} else if ("modifyForm".equals(action)){
				
			System.out.println("action=modifyForm");
			
			//포워드
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyForm.jsp");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
