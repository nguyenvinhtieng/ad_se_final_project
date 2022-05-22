<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home Teacher</title>
        <jsp:include page="../partials/file_and_lib_user.jsp" />
    </head>

    <body>
        <div class="main-container">
            <jsp:include page="../partials/header_teacher.jsp" />
            <main class="classes">
                <div class="classes__block">
                    <h2 class="classes__title">GVCN</h2>
                    <div class="classes__list">
                        <!-- lopchunhiem -->
                        <c:forEach items="${lopchunhiem}" var="lop">
                            <a href="/teacher/headteacher?idlop=${lop.getIdLop()}" class="classes__item">
                                <span class="classes__name">${lop.getTenLop()}</span>
                                <span class="classes__semester">${lop.getTenNamHoc()}</span>
                            </a>
                        </c:forEach>
                    </div>
                </div>
                <div class="classes__block">
                    <h2 class="classes__title">Day Mon</h2>
                    <div class="classes__list">
                        <c:forEach items="${lopbomon}" var="lop">
                            <a href="#!" class="classes__item">
                                <span class="classes__subject">${lop.getTenMonHoc()}</span>
                                <span class="classes__name">${lop.getTenLop()}</span>
                                <span class="classes__semester">${lop.getTenNamHoc()}</span>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </main>
        </div>
        <script src="./js/main.js"></script>
    </body>

    </html>
    <script>
        document.querySelectorAll(".header__menu-item")[2].classList.add("active")
    </script>