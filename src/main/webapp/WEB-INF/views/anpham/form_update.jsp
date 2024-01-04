<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath }/">
<meta charset="utf-8" />
<title>Chỉnh sửa ấn phẩm</title>

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

*[id$=errors] {
	color: red;
	font-style: italic;
}

.form-floating>:first-child {
	width: 80%;
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

	<div class="container-lg mt-3 position-relative">

		<h1 class="text-center">Chỉnh sửa ấn phẩm</h1>

		<form:form action="/multimedia_web/anpham/my_product/edit/${currAnPham.maAnPham}.htm"
			method="POST" enctype="multipart/form-data" modelAttribute="anPhamForm">
			<div class="form-floating mb-3">
				<form:input path="ten" type="text" class="form-control"
					id="floatingInput" placeholder="Tên ấn phẩm"></form:input>
				<form:errors path="ten" />

				<label for="floatingInput">Tên ấn phẩm</label>
			</div>
			<div class="form-floating mb-3">
				<form:input path="gia" type="number"
					onkeypress="return isNumberKey(event)" class="form-control"
					id="floatingInput" placeholder="Giá tiền"></form:input>
				<form:errors path="gia" />

				<label for="floatingInput">Giá tiền</label>
			</div>
			<div class="form-floating mb-3">
				<form:input path="soLuongTon" type="number"
					onkeypress="return isNumberKey(event)" class="form-control"
					id="floatingInput" placeholder="Số lượng hiện có"></form:input>
				<form:errors path="soLuongTon" />

				<label for="floatingInput">Số lượng hiện có</label>

			</div>

			<div class="form-floating mb-3">
				<form:textarea path="moTa" class="form-control"
					placeholder="Mô tả ấn phẩm" id="floatingInput"
					style="height: 200px;"></form:textarea>
				<form:errors path="moTa" />

				<label for="floatingInput">Mô tả</label>

			</div>

			<div class="mb-1">
				<label class="label-input">Ảnh ấn phẩm</label> <input multiple
					class="" id="fileInput" type="file" name="file" accept="image/*">
				<form:errors path="file" />
			</div>

			<a href="anpham/my_product.htm" class="btn btn-warning">Quay lại</a>
			<button type="submit" class="btn btn-success">Chỉnh sửa</button>
			
		</form:form>

		<div class="text-center  position-absolute top-0 end-0">
			<img  class="img-fluid" id="imgTag" src="${oldImage}" alt="ảnh ấn phẩm"
				style="width: 200px; height: 200px;">

			<h5 class="mt-2">Ảnh ấn phẩm</h5>
		</div>
		





	</div>




	<script type="text/javascript">
	function isNumberKey(evt) {
		  var charCode = (evt.which) ? evt.which : evt.keyCode
		  if (charCode > 31 && (charCode < 48 || charCode > 57))
		    return false;
		  return true;
		}
	
	
    document.addEventListener('DOMContentLoaded', function () {
        const fileInput = document.getElementById('fileInput');
        const img = document.getElementById('imgTag');

        handleFileSelect();

        function handleFileSelect() {
            fileInput.addEventListener('change', function (event) {
                loadImgToImgTag(event, img);
            });

            // Trigger file input change event
            fileInput.click();
        }

        function loadImgToImgTag(event, img) {
            const files = event.target.files;

            for (const file of files) {
                const reader = new FileReader();

                reader.onload = function (e) {
                    img.src = e.target.result;
                };

                reader.readAsDataURL(file);
            }
        }
    });
</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>