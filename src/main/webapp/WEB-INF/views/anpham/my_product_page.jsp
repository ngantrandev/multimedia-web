<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath }/">
<meta charset="utf-8" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Ấn phẩm của tôi</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<style>
.card-body .btn {
	opacity: 0;
}

.card-body:hover .btn {
	opacity: 1
}

.card_info_hover {
	width: 300px;
	background-color: #f18c10;
	color: white;
	opacity: 0;
	z-index: 2;
	pointer-events: none;
}

.card:hover .card_info_hover {
	transition: opacity 1s;
	opacity: 1;
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
	  <a href="/multimedia_web/event/show.htm">Sự kiện</a>
	  <a class="active" href="/multimedia_web/anpham/show_list.htm">Cửa hàng</a>
	  <a href="/multimedia_web/user/login.htm" style="right:24px;position:fixed;">Đăng xuất</a>
	</div>
	<h2 class="text-center mt-3">Ấn phẩm của tôi</h2>


	<div class="container-lg mt-3">
		<div class="container-fluid">
			<a href="anpham/my_product/create.htm" class="btn btn-warning">Đăng
				ấn phẩm</a>

		</div>

	</div>



	<div class="container-lg mt-3">

		

		<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3">

			<c:forEach var="u" items="${listAnPham}">
				<div class="col mb-5">
					<div class="card  rounded-4" style="width: 18rem;">
						<img src="${u.imgUrl}" style="height: 250px"
							class="card-image-top object-fit-cover position-relative"
							alt="anh san pham">
						<div class="bottom-50 end-0 card_info_hover position-absolute p-3"
							style="">
							<h3 class="text-center">Mô tả</h3>
							<p>${u.moTa}</p>
						</div>
						<div class="card-body position-relative">
							<h5 class="card-title">${u.tenAnPham}</h5>
							<p class="card-text text-danger mb-5">${u.gia}vnđ</p>

							<div class="position-absolute bottom-0 start-0">
								<a href="anpham/my_product/edit/${u.maAnPham}.htm"
									class="btn btn-warning">Chỉnh sửa</a>
							</div>
							<div class="position-absolute bottom-0 end-0">
								<a href="anpham/my_product/delete/${u.maAnPham}.htm"
									class="btn btn-warning">Xóa</a>
							</div>

						</div>
					</div>

				</div>
			</c:forEach>
		</div>


	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>