<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath }/" />
<meta charset="utf-8" />
<title>Thời khóa biểu</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />

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
	border-color: #afafaf;
	border-radius: 12px;
}

#item-file {
	margin-top: 8px;
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
</style>
</head>
<body>
	<div class="container pt-5">
		<h1 class="text-center">Thời khóa biểu tuần ${tuan}</h1>
		<table class="table table-bordered" style="border-color: #f6911d">
			<thead>
				<tr>
					<th scope="col" style="width: 10%;" class=""><a
						href="home/tkb/${tuan- 1}.htm"
						class="btn d-flex align-items-center text-center"
						style="background-color: #f79e38; color: white"> Tuần trước </a></th>
					<th scope="col"
						style="background-color: #f6911d; width: 12.5%; color: white;border-color: white"
						class="text-center">Thứ 2</th>
					<th scope="col"
						style="background-color: #f6911d; width: 12.5%; color: white;border-color: white"
						class="text-center">Thứ 3</th>
					<th scope="col"
						style="background-color: #f6911d; width: 12.5%; color: white;border-color: white"
						class="text-center">Thứ 4</th>
					<th scope="col"
						style="background-color: #f6911d; width: 12.5%; color: white;border-color: white"
						class="text-center">Thứ 5</th>
					<th scope="col"
						style="background-color: #f6911d; width: 12.5%; color: white;border-color: white"
						class="text-center">Thứ 6</th>
					<th scope="col"
						style="background-color: #f6911d; width: 12.5%; color: white;border-color: white"
						class="text-center">Thứ 7</th>
					<th scope="col" style="width: 10%"><a
						href="home/tkb/${tuan + 1}.htm"
						class="btn d-flex align-items-center text-center"
						style="background-color: #f79e38; color: white"> Tuần sau </a></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row" style="height: 200px;">Sáng</th>

					<c:forEach var="thu" begin="2" end="8">
						<td><c:forEach var="scheduleDay" items="${listTkbTuan}">
								<c:if
									test="${scheduleDay.thu eq thu and scheduleDay.buoi eq '0'}">
									<c:out value="${scheduleDay.monhoc.tenmh}" />
									<br>
									<c:out value="Giảng viên: ${scheduleDay.tengv}" />
									<br>
									<c:out value="Thời gian: ${scheduleDay.thoigian}" />
									<div
										style="width: 100%; height: 1px; background-color: #f6911d;"></div>
								</c:if>
							</c:forEach></td>
					</c:forEach>
				</tr>
				<tr>
					<th scope="row" style="height: 200px">Chiều</th>
					<c:forEach var="thu" begin="2" end="8">
						<td><c:forEach var="scheduleDay" items="${listTkbTuan}">
								<c:if
									test="${scheduleDay.thu eq thu and scheduleDay.buoi eq '1'}">
									<c:out value="${scheduleDay.monhoc.tenmh}" />
									<br />
								</c:if>
							</c:forEach></td>
					</c:forEach>
				</tr>


			</tbody>
		</table>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>
