<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="nav.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
    <base href="${pageContext.servletContext.contextPath}/">
<title>Insert title here</title>
<style>
*{
    box-sizing: border-box;
    transition: all 0.25s cubic-bezier(0.455, 0.03, 0.515, 0.955);
}
nav{
    box-sizing: border-box;
    position: fixed;
    width: 100%;
    max-height: 200px;
    height: 4vw;
    min-height: 80px;
    padding: 10px;
}
#nav_container{
    width: 100%;
    border-radius: 10px;
    background-color: #F6911D;
    /* overflow: hidden; */
}
#nav_container ul:first-child{
    padding: 0;
    height: 100%;
    align-content: stretch;
}
#nav_container_left li,#nav_container_left li a{
    display: flex;
    color: #fff;
    text-decoration: none;
    list-style: none;
    height: 100%;
    align-items: center;
    justify-self: center;
    border-color: #ffffff00;
    border-style: solid;
    border-width: 0 1px 0 1px;
}
#nav_container li{
    padding: 0;
}
#nav_container_left li:first-child{
    border-width: 0 1px 0 0px;
    padding: 0;
}
#nav_container_left li:first-child,#nav_container_left li a:first-child{
    border-radius: 30px 0 0 30px;
    padding: 0;
    padding-right: 10px;
}

#nav_container_left li:first-child:hover {
    box-shadow: inset 0 0 10px 1px #ffc17b;
    border-color: #fff;
    border-style: solid;
    border-width: 0 1px 0 0px;
}
#nav_container_left li:hover {
    box-shadow: inset 0 0 10px 1px #ffc17b;
    border-color: #fff;
    border-style: solid;
    border-width: 0 1px 0 1px;
}
#nav_container_logo{
    border-radius: 100%;
    height: 100%;
}

#nav_container_profile{
    color: #fff;
    border: none;
    background-color: #f6911e;
    height: 100%;
    align-items: center;
    border-radius: 10px;
}
#nav_container_profile:hover{
    background: rgba(246, 145, 30, 0.17);
border-radius: 16px;
box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
backdrop-filter: blur(14.2px);
-webkit-backdrop-filter: blur(14.2px);
/* border:1px solid #ffc17b; */
}
#nav_container_profile-thumb{
    width: 100%;
    height: 100%;
    color: #fff;
    border: none;
    background-color: #f6911e;
}
#nav_profile_avtar{
    height: 100%;
    border-radius: 100%;
}
#nav_profile_dialog {
    height: fit-content;
    width: 100%;
    display: none;
    height: fit-content;
    padding: 0;
    margin: 0;
}

#nav_profile_dialog li{
    margin: 5px 0;
    display: flex;
    list-style: none;
    border-radius: none;
    width: 100%;
    height: fit-content;
        cursor: default;
}
#nav_profile_dialog a{
    justify-content: space-evenly;
    display: flex;
    width: 100%;
    height: 100%;
    padding: 20px;
    border-radius: 0 !important;
    border-width: 0 0px 0 0px !important;
}

#nav_profile_dialog a:hover{
    background-color: #F6911D;
    cursor: pointer;
}

#nav_container_profile:hover #nav_profile_dialog{
    display: flex;
    width: 100%;
    background: rgba(246, 145, 30, 0.5);
    border-radius: 16px;
    box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
    backdrop-filter: blur(14.2px);
    -webkit-backdrop-filter: blur(14.2px);
    height: 1000px;
}
</style>
</head>
<body>
<jsp:include page="WEB-INF/views/navbar.jsp" />
<div>Hello world from Eclipse Java Web SpringMVC</div>
</body>
</html>