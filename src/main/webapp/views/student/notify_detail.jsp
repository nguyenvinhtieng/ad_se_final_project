<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home student</title>
        <jsp:include page="../partials/file_and_lib_user.jsp" />
    </head>

    <body>
        <div class="main-container">
            <jsp:include page="../partials/header_student.jsp" />
            <main class="notify">
                <div class="notify__redirect">
                    <a href="/student/home">Home ></a>
                    <a href="/student/notification">Notify list ></a>
                    <span>Notify detail</span>
                </div>
                <div class="notify__detail-content">
                    <div class="notity__title">
                        ${notifies_detail.getTieuDe()}
                    </div>
                    <div class="notify__date">
                        ${notifies_detail.getNgayDang()}
                    </div>
                    <div class="notify__text">
                        ${notifies_detail.getNoiDung()}
                    </div>
                </div>
            </main>
        </div>
    </body>

    </html>
    <script>
        document.querySelectorAll(".header__menu-item")[2].classList.add("active")
    </script>