<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
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
  			font-weight: bold;
  			font-size: 20px;
  		}
  		
  		#time-send-notification {
  			color:grey;
  		}
  		
  		#title-notification {
  			color:black;
  			font-weight:bold;
  			font-size:25px;
  		}
  		
  		#content-notification {
  			color:black;
  			font-size:20px;
  		}
  	</style>
</head>
<body>
	<nav class="navbar navbar-default" style="background-color:#F6911D">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="#">
	      	<img src="${pageContext.request.contextPath}/WEB-INF/icon/logo.png">
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
	<h2 style="margin-left:24px">Thông báo</h2>
	<br/>
	<c:forEach var="i" begin="0" end="${notifications.size()-1}">
		<div class="itemNotification">
			<div id="name-poster">${notifications.get(i).getPoster().getFullName()}</div>
			<div id="time-send-notification">${notifications.get(i).getTimeSent()}</div>
			<div id="title-notification">${notifications.get(i).getTitle()}</div>
			<div id="content-notification">${notifications.get(i).getContent()}</div>
		</div>
	</c:forEach>
</body>
</html>