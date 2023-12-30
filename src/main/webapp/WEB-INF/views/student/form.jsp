<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
          xmlns:th="http://www.thymeleaf.org"
          xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
          xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
<%-- <base href = "${pageContext.servletContext.contextPath }/"> --%>
</head>
<body>
<h2 SINH PTITHCM></h2>
<form action="/student/save_data" method="post">

<!-- 	<div>Họ và tên</div>
	<input name = name>
	
	<div>Điểm trung bình</div>
	<input name = mark>
	
	<div>Chuyên ngành</div>
	
	<label>
		<input name = "major" type="radio" value = "APP"/>
		Ứng dụng phần mềm
	</label>
	<label>
		<input name = "major" type="radio" value = "WEB"/>
		Thiết kế trang web
	</label> -->

	<hr>
	<button>Lưu</button>

</form>
</body>
</html>