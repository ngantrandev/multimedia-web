<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix ="form" %>
<%@ taglib uri ="http://java.sun.com/jstl/core_rt" prefix = "c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ddan</title>
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

        *{
            font-family: 'Roboto', sans-serif;
            box-sizing: border-box;
            padding: 0;
            margin: 0;
            font-size: 100%;
            transition: all 1s;
        }
        #body{
            margin: auto;
            max-width: 1440px;
            width: 100%;
        }
        #bodycontainer{
            overflow: hidden;
            border-radius: 20px;
            box-shadow:  0px 0px 1rem -5px;
        }
        .btnTitle{
            font-size: 180%;
            color: #F6911D;
            border: 0;
            background-color: #FFFF;
            margin: 0;
            padding: 4%;
            font-weight: 700;
			text-decoration: none;
        }
        .btnTitle[data-value="0"]:hover{
			cursor:pointer ;
            color: #FFFF;
            background-color: #F6911D;
            box-shadow: inset 10px 10px 20px 0px #00000075;
        }
        .btnLg[data-value="0"]{
            box-shadow: inset -10px -10px 20px -10px #00000075;
            color: #FFFF;
            background-color: #F6911D;
            border-radius: 0px 10px 10px 0px;
        }
        .btnReg[data-value="0"]{
            
            box-shadow: inset 10px -10px 20px -10px #00000075;
            color: #FFFF;
            background-color: #F6911D;
            border-radius: 10px 0px 0px 10px;
        }
        #btn_submit{
            color: #FFFF;
            background-color: #F6911D;
            margin-left: auto;
        }
        .group-input_pass{
            border-style:inset;
            border-width: 2px;
            border-radius: 10px;
            border-color: #F6911D;
        }
        .group-input_pass input:focus{
            outline: none;
        }
/* 		.group-input_pass input:focus .group-input_pass{
          outline: #0000;
           border-color: #FFFF;
        } */
        .group-input_pass:focus-within {
            outline: #0000;
             border-color: #000000;
		}
		input{
		           border-width: 2px;
		}
        #imglogo{
        	border-radius:100%;
            margin: 10px auto;
        }
        *[id$=errors]{
        color: #FF0000; margin-left:10%;padding:5px 0px}
        .messerr{
        color: #FF0000;padding:5px 0px; text-align: center;width:100%;
        }
    </style>
</head>
<body style="background-color:#FFFF" class="d-flex flex-column">
    <div id="body" class=".container d-flex flex-column mb-5" style="height: fit-content;" >
    <img id="imglogo" src="image/logo.png" alt="LOGODPT" width="100px" height="100px">
        <!-- form đăng nhập -->
   <div id="bodycontainer" class="col-11 col-md-6 col-lg-6 col-xxl-4" style="background-color: #FFFF; height:fit-content;margin:0 auto">
        <div id="bodycontainer_header"class="d-flex">
        	<c:if test="${action=='login'}">
	            <a href="user/login.htm" id="btnLg" class="btnTitle btnLg col-6" data-value="1">Đăng Nhập</a>
	            <a href="user/register.htm" id="btnReg" class=" btnTitle btnReg col-6" data-value="0">Đăng kí</a>
        	</c:if>
        	<c:if test="${action=='register'}">
	            <a href="user/login.htm" id="btnLg" class="btnTitle btnLg col-6" data-value="0">Đăng Nhập</a>
	            <a href="user/register.htm" id="btnReg" class="btnTitle btnReg col-6" data-value="1">Đăng kí</a>
        	</c:if>
        </div>
     <c:if test="${action=='login'}">
    <form:form id="formlogin" class="d-flex flex-column" action="" method="post" modelAttribute="user">
        <div id="bodycontainer_body" class="p-1 p-lg-4">
            <div class="inputgroup d-flex flex-column">
               <label for="lgMSSV" class="p-2">Mã sinh viên</label>
               <form:input path = "studentCode" id="lgMSSV" name="studentCode" class="col-10 p-2" placeholder="Nhập mã sinh viên" style="margin:auto;border-radius:10px;border-color:#F6911D;"/>
            </div>
            <form:errors path="studentCode"/>
            <div class="inputgroup d-flex flex-column">
                <label for="lgPassword" class="p-2">Mật khẩu</label>
                <div class="d-flex m-auto col-10 group-input_pass ">
                    <form:input path = "password" id="lgPassword" name="lgPassword" class="col-10 p-2" type="password" placeholder="Nhập mật khẩu" style="margin:auto;border:none;"/>
                    <span class="col-1 p-1 d-flex m-auto togglePassword" style="cursor: pointer">
                        <i class="bi bi-eye-fill m-auto " id="togglePassword" ></i>
                   </span>
                </div> 
                <form:errors path="password"/>
            </div>
    </div>
    <p class="messerr">${message}</p>
    <div id="bodycontainer_footer" class="d-flex p-3" >
        <a href="" class="col-4 p-2">Quên mật khẩu</a>
        <button class="col-4 p-2 btn" id="btn_submit"  type="submit">Đăng nhập</button>
    </div>
    </form:form >
   	</c:if>
   	 <c:if test="${action=='register'}">
    <form:form id="formregister" class="d-flex flex-column" action="" method="post" modelAttribute="user">
        <div id="bodycontainer_body" class="p-1 p-lg-4">
			<div class="inputgroup d-flex flex-column">
                <label for="rgfullname" class="p-2">Họ và tên</label>
                <form:input path="fullName" id="rgfullname" name="rgfullname" class="col-10 p-2"  placeholder="Nhập tên" style="margin:auto;border-radius:10px;border-color:#F6911D;"/>
                <form:errors path="fullName"/>
            </div>
            <div class="inputgroup d-flex flex-column">
                <label for="rgMSSV" class="p-2">Mã sinh viên</label>
                <form:input path="studentCode" id="rgMSSV" name="rgMSSV" class="col-10 p-2" placeholder="Nhập mã sinh viên" style="margin:auto;border-radius:10px;border-color:#F6911D;"/>
                <form:errors path="studentCode"/>
            </div>
            <div class="inputgroup d-flex flex-column">
                <label for="rgclassCode" class="p-2">Lớp</label>
                <form:input path="classCode" id="rgclassCode" name="rgclassCode" class="col-10 p-2"  placeholder="Nhập tên lớp" style="margin:auto;border-radius:10px;border-color:#F6911D;"/>
                <form:errors path="classCode"/>
            </div>
            <div class="inputgroup d-flex flex-column">
                <label for="rgPassword" class="p-2">Mật khẩu</label>
                    <div class="d-flex m-auto col-10 group-input_pass ">
        			        <form:input path="password" id="rgPassword" name="rgPassword" class="col-10 p-2" type="password" placeholder="Nhập mật khẩu" style="margin:auto;border:none;"/>
                                <span class="col-1 p-1 d-flex m-auto togglePassword" style="cursor: pointer">
                    			   <i class="bi bi-eye-fill m-auto " id="togglePassword" ></i>
		                	   </span>		
					</div>
                <form:errors path="password"/>
            </div>
           <!--  <div class="inputgroup d-flex flex-column">
                <label for="rgPassword_again" class="p-2">Nhập lại mật khẩu</label>
                <div class="d-flex m-auto col-10 group-input_pass ">
                <input id="rgPassword_again"name="rgPassword_again" class="col-10 p-2" placeholder="Nhập mật khẩu"  type="password"  style="margin:auto;border:none;"/>
                   <span class="col-1 p-1 d-flex m-auto togglePassword" style="cursor: pointer">
                       <i class="bi bi-eye-fill m-auto " id="togglePassword" ></i>
                   </span>
                </div>
                <p style="color: #FF0000; margin-left:10%;padding:5px 0px">Đây là thông báo lỗi </p>
            </div> -->
            <div id="group_birth_gender" class="col-10 m-auto p-2 inputgroup d-flex flex-row">
			<div class=" inputgroup d-flex flex-column col-6 m-auto">
                <label for="rgBirth" class="p-2">Ngày sinh</label>
                <form:input path="birthday" id="rgBirth" name="rgBirth" pattern="dd/MM/yyyy" class="col-12 p-2" placeholder="Nhập ngày sinh" type="date" style="margin:auto;border-radius:10px;border-color:#F6911D;"/>
                <form:errors path="birthday"/>
            </div>
            <div class="choose_group d-flex flex-column col-6 m-auto">
            	<label>Giới tính</label>
            	<div class="d-flex flex-row p-2 col-12 ">
            		<div class="form-check m-auto">
					  <form:radiobutton  path="gender" class="form-check-input" name="gender" id="gender" value='0' />
					  <label class="form-check-label" for="gender">Nam</label>
					</div>
					<div class="form-check m-auto">
					  <form:radiobutton path="gender" class="form-check-input" name="gender" id="gender_1" value='1' />
					  <label class="form-check-label" for="gender_1">Nữ</label>
					</div>
            	</div>
            	<form:errors path="gender"/>
            </div>
            </div>
            <div class="inputgroup d-flex flex-column">
                <label for="rgPhone" class="p-2">Số Điện Thoại</label>
                    <form:input path="phone" id="rgPhone" name="rgPhone" type="tel" class="col-10 p-2"  placeholder="Nhập số điện thoại" style="margin:auto;border-radius:10px;border-color:#F6911D;"/>
                   <form:errors path="phone"/>
            </div>
        </div>
        <div id="bodycontainer_footer" class="d-flex p-3" >
            <button class="col-4 p-2 btn" id="btn_submit"  type="submit">Đăng kí</button>
        </div>
    </form:form>
   	   	</c:if>
   </div>
   </div>
</body>
<script>
    var loginform = true;
    document.querySelector(".btnLg").addEventListener("click", changeformLG);
    document.querySelector(".btnReg").addEventListener("click", changeformReg);
    function changeformLG(){
        loginform=true;
        if(loginform){
        document.getElementById("btnLg").dataset.value = "1";
        document.getElementById("btnReg").dataset.value = "0";
        document.getElementById("formregister").classList.add("d-none");
        document.getElementById("formregister").classList.remove("d-flex");
        document.getElementById("formlogin").classList.remove("d-none");
        }
    }
    function changeformReg(){
        loginform=false;
        if(!loginform){
        document.getElementById("btnLg").dataset.value = "0";
        document.getElementById("btnReg").dataset.value = "1";
        document.getElementById("formlogin").classList.add("d-none");
        document.getElementById("formlogin").classList.remove("d-flex");
        document.getElementById("formregister").classList.remove("d-none");
        }
    }
    $(document).ready(function(){
        $(".togglePassword").on('click',function(e){
            e.preventDefault();
            if($(this).parent().children('input').attr("type") == "text"){
                $(this).parent().children('input').attr('type', 'password');
                $(this).children('i').removeClass("bi-eye-slash-fill");
                $(this).children('i').addClass("bi-eye-fill");
            }
            else
            if($(this).parent().children('input').attr("type") == "password"){
                $(this).parent().children('input').attr('type', 'text');
                $(this).children('i').addClass("bi-eye-slash-fill");
                $(this).children('i').removeClass("bi-eye-fill");
            };
        })
    })
</script>
</html>