<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath }/">
<meta charset="utf-8" />
<title>Kết quả</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<style>
.card-body .btn {
	position: absolute;
	top: 100%;
	left: 50%;
	opacity: 0;
	transform: translateX(-50%);
}

.card-body:hover .btn {
	transform: translateX(-50%) translateY(-100%);
	opacity: 1
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


	<div class="container-lg mt-5">

		<div class="container-fluid">
			<h1>${message}</h1>
			<a href="anpham/my_product.htm"
								class="btn btn-warning">Quay lại</a>
		</div>

	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>