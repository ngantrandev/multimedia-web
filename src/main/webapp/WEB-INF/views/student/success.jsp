<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <base href="${pageContext.servletContext.contextPath}/">
<title>Spring MVC</title>
</head>
<body>

<h1>THÔNG TIN SINH VIÊN PTITHCM</h1>
<ul>
<li> HỌ VÀ TÊN: <span th:text="${name}"></span></li>
<li> ĐIỂM TB:  <span th:text="${mark}"></span></li>
<li> CHUYÊN NGÀNH: <span th:text="${major}"></span></li>
</ul>

</body>
</html>