<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath }/">
<meta charset="utf-8" />
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
</style>
</head>
<body>

	<nav
		class="container-lg navbar navbar-expand-sm bg-warning rounded-pill">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Multimedia </a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
				aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse " id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Trang chủ</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Thông
							tin</a></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> N20DCPT044 </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">Thông tin cá nhân</a></li>
							<li><a class="dropdown-item" href="#">Đơn hàng</a></li>
							<li><a class="dropdown-item" href="#">Đăng xuất</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	
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