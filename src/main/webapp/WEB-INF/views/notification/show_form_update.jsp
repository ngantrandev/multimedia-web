<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <base href="${pageContext.servletContext.contextPath}"/>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  	<title>Multimedia</title>
  	<style>
  		#container-header{
  			font-size: 40px;
			align-self: center;
			text-align:center;
  		}
  		
  		#label-header{
  			font-size: 50px;
  		}
  		
  		#logo {
  			width:20px;
  			height:20px;
  		}
  		
  		.web-form-container {
  			padding-left: 56px;
  			padding-right: 56px;
  		}
  		
  		.web-input-title {
  			height:50px
  		}
  		
  		.web-input-content {
  			height:300px
  		}
  		
  		.label-input {
  			font-size:25px;
  		}
  		
  		.web-btn-send {
  			background-color: #F6911D;
  			border-color: #F6911D;
  			margin-top: 24px;
  			font-size: 18px;
  			color:white;
  			width: 100px;
  			height: 50px;
  		}
  		
  		.web-btn-send:hover {
  			background-color: #F6911D;
  			border-color: #F6911D;
  			margin-top: 24px;
  			font-size: 18px;
  			width: 100px;
  			height: 50px;
  			color:white;
  		}
  		
  		.web-btn-back {
  			background-color: white;
  			border-color: #F6911D;
  			margin-top: 24px;
  			color: #F6911D;
  			text-align:center;
  			vertical-align:center;
  			align:center;
  			font-size: 18px;
  			width: 100px;
  			height: 50px;
  		}
  		
  		.web-btn-back:hover {
  			background-color: white;
  			border-color: #F6911D;
  			margin-top: 24px;
  			color: #F6911D;
  			text-align:center;
  			font-size: 18px;
  			width: 100px;
  			height: 50px;
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
		<h2 style="margin-left:24px" id="label-header">Sửa thông báo</h2>
	</div>
	<form:form class="web-form-container" action="/multimedia_web/notification/update.htm"
		method="POST" enctype="multipart/form-data" modelAttribute="notification">
		<form:input path="time" type="hidden" />
		<form:input path="notifiCode" type="hidden"/>
	  <div class="form-group">
	    <label class="label-input">Tiêu đề</label>
	    <form:input path="title" type="text" class="form-control web-input-title" placeholder="Nhập tiêu đề"/>
	    <form:errors path="title" style="color:red"/>
	  </div>
	  <div class="form-group">
	    <label class="label-input">Nội dung</label>
	    <form:textarea rows="10" path="content" class="form-control web-input-content" placeholder="Nhập nội dung"/>
	  </div>
	  <div>
		  <label class="label-input">Đính kèm file</label>
		  <input multiple class="form-control form-control-lg" type="file" name="files">
	  </div>
	  <a href="/multimedia_web/notification/show.htm" class="btn web-btn-back">Quay lại</a>
	  <button type="submit" class="btn web-btn-send">Sửa</button>
	</form:form>
</body>
</html>