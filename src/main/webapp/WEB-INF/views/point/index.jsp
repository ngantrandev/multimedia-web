<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xem Điểm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
    <base href="${pageContext.servletContext.contextPath}/">
</head>
<body>
	<div class="container-lg mt-5 d-flex flex-column">
		<div id="containerButton_header">
			<button id="btn_print">In<i></i></button>
			<button id="btn_export_excel">In<i></i></button>
		</div>
		<div id="container_tableScore">
			<table>
				 <tr>
				    <th>STT</th>
			  	    <th>Mã MH</th>
					<th>Tên môn học</th>
			  	    <th>Số tín chỉ</th>
					<th>Điểm thi</th>
					<th>Điểm cc</th>
			  	    <th>Điểm kt</th>
					<th>Điểm th</th>
					<th>Điểm se</th>
			  	    <th>Điểm TK</th>
			  	    <th>Kết quả</th>
			  	    <th>Chi tiết</th>
				 </tr>
		<c:if test="${listpoint.size()>0}">
				<tr class="namhoc_tile">
				<th class="namhoc_tile text-center" style="" colspan="100">Năm học ${listpoint.get(0).nh} - ${listpoint.get(0).nh+1}<th>
				</tr>
		<c:forEach var="point" items="${listpoint}" varStatus = "i">
		<c:if test="${i.index>0&&i.index<listpoint.size()}">
			<c:if test="${point.nh!=listpoint.get(i.index-1).nh}">
				<tr class="namhoc_tile "  >
				<th class="text-center"colspan="100">Năm học ${point.nh} - ${point.nh+1}<th>
				</tr>
			</c:if>
		</c:if>
				 <tr>
				    <td>${i.index+1}</td>
				    <td>${point.subject.id}</td>
				    <td>${point.subject.name}</td>
				    <td>${point.subject.soTin}</td>
				    <td>${point.thi}</td>
				    <td>${point.cc}</td>
				  	<td>${point.kt}</td>
				    <td>${point.th}</td>
				    <td>${point.se}</td>
				  </tr>
		</c:forEach>
		</c:if>
		</table>
		</div>
	</div>
</body>
</html>