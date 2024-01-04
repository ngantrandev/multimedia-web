<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

    <nav class="w-100 d-flex">
        <div id="nav_container" class=".container-fluid d-flex justify-content-between">
            <ul id="nav_container_left" class="d-flex align-content-stretch justify-content-start">
                <li><a href=""><img id="nav_container_logo" src="image/logo.png">Trang chủ</a></li>
                <li><a href="" class="p-3">thông tin</a></li>
            </ul>
            <div id="nav_container_profile" class="d-flex flex-column">
                <button id="nav_container_profile-thumb" class="d-flex align-content-center">
                    <div id="nav_profile_avtar" class="overflow-hidden">
                        <img style="height: 100%;" src="https://cdn.sforum.vn/sforum/wp-content/uploads/2023/10/avatar-trang-4.jpg"/>
                    </div>
                    <h5 class="p-3 m-0">N20DCPT021</h5>
                    <i class="bi bi-caret-down-fill m-auto" ></i>
                </button>
                <ul id="nav_profile_dialog" class="flex-column">
                    <li>
                        <a>
                        <i class="bi bi-info-circle-fill"></i> Thông tin cá nhân<i class="bi bi-chevron-right"></i>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class="bi bi-archive-fill"></i>Xem điểm<i class="bi bi-chevron-right"></i>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class="bi bi-basket-fill"></i>Đơn hàng<i class="bi bi-chevron-right"></i>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class="bi bi-door-open-fill"></i>Đăng xuất<i class="bi bi-chevron-right"></i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</body>
</html>