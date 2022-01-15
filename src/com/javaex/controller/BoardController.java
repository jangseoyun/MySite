package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;


@WebServlet("/board")
public class BoardController extends HttpServlet {
	//private static final long serialVersionUID = 1L;
       
    //기본 생성자 삭제

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String action = request.getParameter("action");

		BoardDao boardDao = new BoardDao();
		BoardVo boardVo = null;
		
		if("boardList".equals(action)) {
			
			System.out.println("action=boardList");

			List<BoardVo> bList = boardDao.boardList();
			
			request.setAttribute("bList",bList);
			//System.out.println("controller: "+bList);

			//forward
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		
		} else if("writeForm".equals(action)) {
			
			System.out.println("action=writeForm");

			//forward 
			WebUtil.forward(request, response, "/WEB-INF/views/board/writeForm.jsp");
			
		} else if("boardAdd".equals(action)) {
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int uNo = Integer.parseInt(request.getParameter("uNo"));
			
			boardDao.boardInsert(title,content,uNo);

			//redirect
			WebUtil.redirect(request, response, "/mysite/board?action=boardList");
			
		} else if ("boardRead".equals(action)) {
			
			System.out.println("action=boardRead");
			
			int bNo = Integer.parseInt(request.getParameter("bNo"));
			
			//views count
			boardDao.boardViews(bNo); 
			
			boardVo = boardDao.getBoard(bNo);
			//System.out.println(boardVo);
			
			//Attribute
			request.setAttribute("getBoard", boardVo);
			//forward
			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");
		
		} else if ("boardDelete".equals(action)) {
			
			System.out.println("action=boardDelete");

			boardDao.baordDelete(Integer.parseInt(request.getParameter("bNo")));
			
			//redirect
			WebUtil.redirect(request, response, "/mysite/board?action=boardList");
			
		} else if ("modifyForm".equals(action)) {
			
			System.out.println("action=modiftForm");

			boardVo = boardDao.getBoard(Integer.parseInt(request.getParameter("bNo")));
			
			//Attribute
			request.setAttribute("getBoard", boardVo);
			//forward
			WebUtil.forward(request, response, "/WEB-INF/views/board/modifyForm.jsp");
			
		} else if ("modify".equals(action)) {
			
			System.out.println("action=modify");
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int bNo = Integer.parseInt(request.getParameter("bNo"));
			
			boardVo = new BoardVo();
			boardVo.setTitle(title);
			boardVo.setContent(content);
			boardVo.setbNo(bNo);

			boardDao.boardUpdate(boardVo);
			//System.out.println("controller:"+boardVo);
			
			//redirect
			WebUtil.redirect(request, response, "/mysite/board?action=boardList");
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
