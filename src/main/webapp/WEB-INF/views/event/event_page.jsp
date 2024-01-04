<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="UTF-8">
<base href="${pageContext.servletContext.contextPath}" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Multimedia</title>
<style>
.itemNotification {
	margin: 24px;
	padding: 24px;
	border-style: solid;
	border-color: grey;
	border-radius: 12px;
}

#name-poster {
	display: inline;
	font-weight: bold;
	font-size: 20px;
}

#time-send-notification {
	color: grey;
}

#title-notification {
	color: black;
	margin-top: 12px;
	font-weight: bold;
	font-size: 25px;
}

#content-notification {
	color: black;
	font-size: 20px;
}

#logo {
	width: 20px;
	height: 20px;
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
	width:100%;
	height:100%;
	padding:12px;
}

#ic-file {
	width: 40px;
	height: 40px;
	display: inline;
}

#ic-download {
	width: 40px;
	height: 40px;
	display: inline;
}

#btn-download {
	display: inline;
	margin-left: 24px;
}

#name-file {
	display: inline;
	font-size: 20px;
}

#label-dowbload {
	display: inline;
	font-size: 20px;
}

#ic-plus {
	vertical-align: center;
	display: inline;
	width: 36px;
	height: 36px;
	margin-left: 8px;
}

#label-header {
	vertical-align: center;
	display: inline;
	padding-top: 24px;
	align-self: center;
}

#ic-edit {
	display: inline;
	width: 30px;
	height: 30px;
	-webkit-text-stroke-width: 2px;
}

.form-edit {
	display: inline;
	margin-left: 12px;
}

.btn-edit {
	background-color: transparent;
	border: transparent;
}

.btn-rm {
	background-color: transparent;
	border: transparent;
}

#ic-rm {
	display: inline;
	width: 30px;
	height: 30px;
}

#thumb-event {
	border-radius:40px;
	width:100%;
	height:50%;
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
	  <a href="/multimedia_web/notification/show.htm">Thông báo</a>
	  <a class="active" href="/multimedia_web/event/show.htm">Sự kiện</a>
	  <a href="/multimedia_web/anpham/show_list.htm">Cửa hàng</a>
	  <a href="/multimedia_web/user/login.htm" style="right:24px;position:fixed;">Đăng xuất</a>
	</div>
	<div id="container-header">
		<h2 style="margin-left: 24px" id="label-header">Sự kiện</h2>
		<c:choose>
			<c:when test="${isBch==true}">
				<a href="/multimedia_web/event/show_form_create.htm"> <img
					id="ic-plus" src="/multimedia_web/image/plus.png" />
				</a>
			</c:when>
		</c:choose>
	</div>

	<br />
	<c:choose>
		<c:when test="${events.size()>0}">
			<c:forEach var="i" begin="0" end="${events.size()-1}">
				<div class="itemNotification">
					<div id="name-poster">${events.get(i).getPoster().getFullName()}</div>
					<c:choose>
						<c:when test="${isBch==true}">
							<form action="/multimedia_web/event/show_form_update.htm"
								method="POST" class="form-edit">
								<input hidden name="eventCode"
									value="${events.get(i).getEventCode()}" /> <input hidden
									name="name" value="${events.get(i).getName()}" /> <input hidden
									name="content" value="${events.get(i).getContent()}" /> <input
									hidden name="time" value="${events.get(i).getTime()}" />
								<button type="submit" class="btn-edit">
									<img id="ic-edit" src="/multimedia_web/image/edit.png" />
								</button>
							</form>

							<form action="/multimedia_web/event/delete.htm" method="POST"
								class="form-edit">
								<input hidden name="eventCode"
									value="${events.get(i).getEventCode()}" />
								<button type="submit" class="btn-rm">
									<img id="ic-rm" src="/multimedia_web/image/rm.png" />
								</button>
							</form>
						</c:when>
					</c:choose>
					<div id="time-send-notification">${events.get(i).getTime()}</div>
					<div id="title-notification">${events.get(i).getName()}</div>
					<div id="content-notification">${events.get(i).getContent()}</div>
					<div id="item-file">
						<img id="thumb-event" src="/multimedia_web/uploaded_file/${events.get(i).getNameThumbImg()}"/>
					</div>
				</div>
			</c:forEach>
		</c:when>
	</c:choose>
</body>
</html>