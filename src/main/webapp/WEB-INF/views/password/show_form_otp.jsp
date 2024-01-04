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
    <title>Multimedia</title>
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
            width:200px;
            background-color: #F6911D;
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
	    <form id="formlogin" class="d-flex flex-column" action="/multimedia_web/password/authOtp.htm" method="post">
	        <div id="bodycontainer_body" class="p-1 p-lg-4">
	            <div class="inputgroup d-flex flex-column">
	               <label for="lgMSSV" class="p-2">Otp</label>
	               <input id="lgMSSV" name="otp" class="col-10 p-2" placeholder="Nhập otp" style="border-radius:10px;border-color:#F6911D;"/>
	            </div>
	            <div style="color:red">${message}</div>
	    	</div>
		    <div id="bodycontainer_footer" class="d-flex p-3" >
		        <button class="col-4 p-2 btn" id="btn_submit"  type="submit">Xác nhận</button>
		    </div>
	    </form>
   </div>
</body>
</html>