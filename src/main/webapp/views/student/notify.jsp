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
                <form class="notify__filter">
                    <select class="notify__category" name="cate" id="">
                        <option value="">--Choose category--</option>
                        <c:forEach items="${types}" var="t">
                            <option value="${t.getIdLoaiThongBao()}">${t.getTen()}</option>
                        </c:forEach>

                    </select>
                    <input class="notify__title-search" name="title" type="text" placeholder="Title search...">
                    <button class="notify__btn">Search</button>
                </form>
                <ul class="notify__list">
                    <c:forEach items="${notifies}" var="n">
                        <li class="notify__item">
                            <a class="notify__item-link" href="/student/notify/detail?id=${n.getIdThongBao()}">
                                <div class="notify__title">
                                    ${n.getTieuDe()}
                                </div>
                                <div class="notyfy__date">
                                    ${n.getNgayDang()}
                                </div>
                                <div class="notify__content">
                                    ${n.getNoiDung()}
                                </div>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
                <div class="notify__pagination">
                    <a class="notity__btn-pagination" href="/student/notification${prevpage}">
                        Prev
                    </a>
                    <a class="notity__btn-pagination" href="/student/notification${nextpage}">
                        Next
                    </a>
                </div>
            </main>
        </div>
        <style>
            .notify__pagination {
                margin-bottom: 25px;
            }
        </style>
    </body>

    </html>
    <script>
        document.querySelectorAll(".header__menu-item")[2].classList.add("active")
    </script>