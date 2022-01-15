<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>main</title>
<%--url주소이다. css에 있는것을 다 다운받아서 쓰는것  --%>
<link href="/mysite/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite/assets/css/main.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<!-- header와 nav 있는 자리를 공통으로 묶은 것 / 자바 문법이다  -->
		<%-- <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include> --%>

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		
		<div id="container" class="clearfix">
			<!-- aside 없음 -->
			<div id="full-content">
			
				<!-- content-head 없음 -->
				<div id="index"> 
				
					<img id="profile-img" src="/mysite/assets/images/profile.jpg">
					
					<div id="greetings">
						<p class="text-xlarge">
							<span class="bold">안녕하세요!!<br>
							장서윤의 MySite에 오신 것을 환영합니다.<br>
							<br>
							이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.<br>
							</span>
							<br>
							사이트 소개, 회원가입, 방명록, 게시판으로 구성되어 있으며<br>
							jsp&serlvet(모델2) 방식으로 제작되었습니다.<br>
							<br>
							자바 수업 + 데이터베이스 수업 + 웹프로그래밍 수업<br>
							배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.<br>
							<br>
							(자유롭게 꾸며보세요!!)<br>
							<br><br>
							<a class="" href="">[방명록에 글 남기기]</a>
						</p>	
					</div>
					<!-- //greetings -->
					
					<div class="clear"></div>
					
				</div>
				<!-- //index -->
				
			</div>
			<!-- //full-content -->
			

		</div>
		<!-- //container -->
		

		<!-- //footer include로 공통으로 뺌 -->
		<%-- <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include> --%>
		<c:import url = "/WEB-INF/views/include/footer.jsp"></c:import>
		
	</div>
	<!-- //wrap -->

</body>

</html>