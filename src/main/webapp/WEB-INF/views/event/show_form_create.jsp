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
  	<title>Spring MVC</title>
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
  		
  		
  	</style>
</head>
<body>
	<nav class="navbar navbar-default" style="background-color:#F6911D">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">
	      	<img id="logo" src="/multimedia_web/image/logo.png">
	      </a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li><a href="#" style="font-weight:bold;color:white">Trang chủ</a></li>
	      <li style="background-color:white"><a href="#" style="font-weight:bold;color:black">Thông báo</a></li>
	      <li><a href="#" style="font-weight:bold;color:white">Sự kiện</a></li>
	      <li><a href="#" style="font-weight:bold;color:white">Cửa hàng</a></li>
	    </ul>
	  </div>
	</nav>
	<div id="container-header">
		<h2 style="margin-left:24px" id="label-header">Thêm thông báo</h2>
	</div>
	<form:form class="web-form-container" action="/multimedia_web/notification/create.htm"
		method="POST" enctype="multipart/form-data" modelAttribute="notification">
	  <div class="form-group">
	    <label class="label-input">Tiêu đề</label>
	    <form:input path="title" type="text" class="form-control web-input-title" placeholder="Nhập tiêu đề"/>
	  </div>
	  <div class="form-group">
	    <label class="label-input">Nội dung</label>
	    <form:input path="content" class="form-control web-input-content" placeholder="Nhập nội dung" rows="10"></form:input>
	  </div>
	  <div>
		  <label class="label-input">Đính kèm file</label>
		  <input multiple class="form-control form-control-lg" type="file" name="files">
	  </div>
	  <a href="/multimedia_web/notification/show.htm" class="btn web-btn-back">Quay lại</a>
	  <button type="submit" class="btn web-btn-send">Gửi</button>
	</form:form>
</body>
</html>