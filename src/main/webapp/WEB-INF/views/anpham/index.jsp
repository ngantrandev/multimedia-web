<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath }/">
<meta charset="utf-8" />
<title>Ấn phẩm công khai</title>

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


	<div class="container-lg mt-3">
		<h2 class="text-center">Ấn phẩm đang rao bán</h2>
		<div class="container-fluid d-flex justify-content-between">
			<a href="anpham/my_product.htm" class="btn btn-warning">Ấn phẩm của tôi</a>
			<a data-bs-toggle="offcanvas" data-bs-target="#offcanvasRight"
				aria-controls="offcanvasRight"> <span
				class="container position-relative"> <img
					src="image/ic_cart.png" alt="" srcset=""
					style="width: 30px; height: auto" /> <span id="cartSize"
					class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-secondary">${orderSize}
				</span>
			</span>
			</a>
		</div>

		<div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasRight"
			aria-labelledby="offcanvasRightLabel">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasRightLabel">Giỏ hàng</h5>
				<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
					aria-label="Close"></button>
			</div>
			<div class="offcanvas-body position-relative">
				<div class="container mb-5">
					<c:forEach var="u" items="${listOrder}">
						<div class="row">
							<div class="col-4">
								<img
									src="${u.anPham.imgUrl}"
									alt="hình" srcset="" style="width: 100px; height: auto" />
							</div>
							<div class="col-4">
								<p>${u.anPham.tenAnPham}</p>
								<p>${u.anPham.gia}vnđ</p>
							</div>
							<div class="col-4 d-flex justify-content-around">
								<span><a href="cart/remove/${u.anPham.maAnPham}.htm"> <img
										src="image/ic_minus.png" style="width: 20px; height: auto;"
										alt="" srcset="">
								</a></span> <span>${u.soLuong}</span> <span><a
									href="cart/addtocart/${u.anPham.maAnPham}.htm"> <img
										src="image/ic_plus.png" style="width: 20px; height: auto;"
										alt="" srcset="">
								</a></span>
							</div>
						</div>
					</c:forEach>
					

				</div>

				<div class="position-absolute bottom-0 start-50" style="transform:translate(-50%, -50%);">
					<a href="cart/dat_hang.htm" class="btn btn-warning">Đặt hàng</a>
				</div>

			</div>
		</div>
	</div>

	<div class="container-lg mt-5">

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

							<a href="cart/addtocart/${u.maAnPham}.htm"
								class="btn btn-warning">Add to cart</a>

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