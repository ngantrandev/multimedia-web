<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <base href="${pageContext.servletContext.contextPath}"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  	<title>Spring MVC</title>
  	<style>
  		.itemNotification {
  			margin: 24px;
  			padding:24px;
  			border-style: solid;
  			border-color: grey;
  			border-radius: 12px;
  		}
  		
  		#name-poster {
  			display:inline;
  			font-weight: bold;
  			font-size: 20px;
  		}
  		
  		#time-send-notification {
  			color:grey;
  		}
  		
  		#title-notification {
  			color:black;
  			margin-top:12px;
  			font-weight:bold;
  			font-size:25px;
  		}
  		
  		#content-notification {
  			color:black;
  			font-size:20px;
  		}
  		
  		#logo {
  			width:20px;
  			height:20px;
  		}
  		
  		#item-file-container {
  			margin-top: 12px;
  			padding: 12px;
  			border-style: solid;
  			border-color: #AFAFAF;
  			border-radius: 12px;
  		}
  		
  		#item-file {
  			margin-top: 8px;
  		}
  		
  		#ic-file {
  			width:40px;
  			height:40px;
  			display:inline;
  		}
  		
  		#ic-download {
  			width:40px;
  			height:40px;
  			display:inline;
  		}
  		
  		#btn-download {
  			display:inline;
  			margin-left:24px;
  		}
  		
  		#name-file {
  			display:inline;
  			font-size: 20px;
  		}
  		
  		#label-dowbload {
  			display:inline;
  			font-size: 20px;
  		}
  		
  		#ic-plus {
  			vertical-align: center;
  			display:inline;
  			width: 36px;
  			height: 36px;
  			margin-left:8px;
  		}
  		
  		#label-header{
  			vertical-align: center;
  			display:inline;
			padding-top: 24px;
			align-self: center;
  		}
  		
  		#ic-edit {
  			display:inline;
  			width: 30px;
  			height:30px;
  			-webkit-text-stroke-width: 2px;
  		}
  		
  		.form-edit {
  			display:inline;
  			margin-left:12px;
  		}
  		
  		.btn-edit{
  			background-color: transparent;
  			border: transparent;
  		}
  		
  		.btn-rm{
  			background-color: transparent;
  			border: transparent;
  		}
  		
  		#ic-rm {
  			display:inline;
  			width: 30px;
  			height:30px;
  		}
  		
  		body {
		  margin: 0;
		  font-family: Arial, Helvetica, sans-serif;
		}

	.topnav {
	  overflow: hidden;
	  border-style:solid;
	  border-color: #F6911D;
	  margin-bottom:24px;
	  background-color: white;
	}
	
	.topnav a {
	  float: left;
		color:#F6911D;
	  text-align: center;
	  padding: 14px 16px;
	  text-decoration: none;
	  font-size: 17px;
	}
	
	.topnav a.active {
	  background-color: #F6911D;
	  color: white;
	}
  	</style>
</head>
<body>
	<div class="topnav">
	  <a href="/multimedia_web/home/index.htm">Thời khoá biểu</a>
	  <a href="/multimedia_web/xemdiem.htm">Điểm số</a>
	  <a class="active" href="/multimedia_web/notification/show.htm">Thông báo</a>
	  <a href="/multimedia_web/event/show.htm">Sự kiện</a>
	  <a href="/multimedia_web/anpham/show_list.htm">Cửa hàng</a>
	  <a href="/multimedia_web/user/login.htm" style="right:24px;position:fixed;">Đăng xuất</a>
	</div>
	<div id="container-header">
		<h2 style="margin-left:24px" id="label-header">Thông báo</h2>
		<c:choose>
		    <c:when test= "${isBcs==true}">
		        <a href="/multimedia_web/notification/show_form_create.htm">
					<img id="ic-plus" src="/multimedia_web/image/plus.png"/>
				</a>
		    </c:when>
		</c:choose>
	</div>
	
	<br/>
	<c:choose>
		    <c:when test= "${notifications.size()>0}">
		        <c:forEach var="i" begin="0" end="${notifications.size()-1}">
					<div class="itemNotification">
						<div id="name-poster">${notifications.get(i).getPoster().getFullName()}</div>
						<c:choose>
						    <c:when test= "${isBcs==true}">
						       <form action="/multimedia_web/notification/show_form_update.htm" method="POST" class="form-edit">
									<input hidden name="notificationCode" value="${notifications.get(i).getNotifyCode()}"/>
									<input hidden name="title" value="${notifications.get(i).getTitle()}"/>
									<input hidden name="content" value="${notifications.get(i).getContent()}"/>
									<input hidden name="time" value="${notifications.get(i).getTimeSent()}"/>
									<button type="submit" class="btn-edit"><img id="ic-edit" src="/multimedia_web/image/edit.png"/></button>
								</form>
								
								<form action="/multimedia_web/notification/delete.htm" method="POST" class="form-edit">
									<input hidden name="notificationCode" value="${notifications.get(i).getNotifyCode()}"/>
									<button type="submit" class="btn-rm"><img id="ic-rm" src="/multimedia_web/image/rm.png"/></button>
								</form>
						    </c:when>
						</c:choose>
						<div id="time-send-notification">${notifications.get(i).getTimeSent()}</div>
						<div id="title-notification">${notifications.get(i).getTitle()}</div>
						<div id="content-notification">${notifications.get(i).getContent()}</div>
						<c:choose>
							<c:when test="${notifications.get(i).getFileArr().size()>0}">
								<div id="item-file-container">
									<c:forEach var="j" begin="0" end="${notifications.get(i).getFileArr().size()-1}">
										<div id="item-file">
											<img id="ic-file" src="/multimedia_web/image/file.png"/>
											<div id="name-file">${notifications.get(i).getFileArr().get(j)}</div>
											<div id="btn-download">
												<img id="ic-download" src="/multimedia_web/image/download.png">
													<a href="/multimedia_web/uploaded_file/${notifications.get(i).getFileArr().get(j)}" id="label-dowbload">Tải xuống</a>
												</img>
											</div>
										</div>
									</c:forEach>
								</div>
							</c:when>
						</c:choose>
					</div>
				</c:forEach>
		    </c:when>
		</c:choose>
</body>
</html>