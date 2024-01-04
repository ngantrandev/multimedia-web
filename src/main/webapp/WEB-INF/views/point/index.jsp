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
    <style>
    	#container_tableScore{
    		margin:auto;
    		width:fit-content;
    		max-width:100%;
       		border-radius: 10px;
       		  box-shadow:  0px 0px 0.4rem -5px;
    	}
		table {

		 border-collapse:none;
		 border: 1px solid #FFC685;
		 border-radius: 10px;
		 overflow:hidden;
		}
    	thead th{
    	text-align:center;
    	    border-color:#FFFF;
    		border-width:1px;}
		.namhoc_tile{
		font-weight:700;
		background-color:#FFC685;
		}
    	#table_header_title th{
    		padding:2rem 1rem;
    		background-color:#F6911D;
    	}
    	.table_detail_point td,td{
    	text-align:center;
    	padding:10px;
    	border: 1px solid #FFC685;
    	}
    	#space_table{
    		
    		padding-top:2rem;
    	}
    	.btn_chucnangphu{
  				justify-content: strech;
    			border-radius:30px;
    			border: 1px solid #F6911D; 
    			text-align: center;
    			word-spacing: 1rem;
    	}
		
    </style>
</head>
<body>
	<div class="container-lg mt-5 d-flex flex-column" >
	<div class="m-auto">
		<div id="containerButton_header" class=" d-flex justify-content-end my-2" >
			<button id="btn_print" class="btn btn_chucnangphu mx-4 p-2"><i class="bi bi-printer-fill" style="color:#F6911D"></i> In</button>
			<button id="btn_export_excel" class="btn btn_chucnangphu p-2" ><i class="bi bi-file-earmark-excel-fill" style="color:#F6911D"></i>Excel</button>
		</div>
		<div id="container_tableScore" class="col-10 mb-5">
			<table class="m-auto">
			<thead>
				 <tr id="table_header_title">
				    <th>STT</th>
			  	    <th>Mã MH</th>
					<th>Tên môn học</th>
			  	    <th>Số tín chỉ</th>
					<th>Điểm thi</th>
					<th>Điểm cc</th>
			  	    <th>Điểm kt</th>
					<th>Điểm th</th>
					<th>Điểm se</th>
			  	    <th>Kết quả</th>
				 </tr>
			</thead>
			<tbody>
		<c:if test="${listpoint.size()>0}">
				<tr class="namhoc_tile">
				<td class=" text-center" colspan="100%">Năm học ${listpoint.get(0).nh} - ${listpoint.get(0).nh+1}</td>
				</tr>
		<c:forEach var="point" items="${listpoint}" varStatus = "i">
			<c:if test="${i.index>0&&i.index<listpoint.size()}">
				<c:if test="${point.nh!=listpoint.get(i.index-1).nh}">
					<tr >
						<td id="space_table" colspan="100%" ></td>
					</tr>
					<tr class="namhoc_tile" >
					<td class="text-center" colspan="100%" >Năm học ${point.nh} - ${point.nh+1}</td>
					</tr>
				</c:if>
			</c:if>
				 <tr class="table_detail_point">
				    <td>${i.index+1}</td>
				    <td>${point.subject.id}</td>
				    <td>${point.subject.name}</td>
				    <td>${point.subject.soTin}</td>
				    <td>${point.thi}</td>
				    <td>${point.cc}</td>
				  	<td>${point.kt}</td>
				    <td>${point.th}</td>
				    <td>${point.se}</td>
				    <td>
				    <c:choose>
				    	<c:when test="${point.thi>4 && point.cc>4}"><i class="bi bi-check" style="color:#F6911D;font-size: 2rem"></i></c:when>
				    	<c:otherwise><i class="bi bi-x" style="color:#C70000;font-size: 2rem"></i></c:otherwise>
				    </c:choose>
				  	</td>
				  </tr>
		</c:forEach>
		</c:if>
		
		</table>
		</div>
		</div>
	</div>
</body>
</html>