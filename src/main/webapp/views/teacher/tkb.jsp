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
            <main class="tkb">
                <form class="tbk__form">
                    <span>Choose Semester</span>
                    <select name="idhocky" class="tkb__select" id="">
                        <option value="">--Choose semester--</option>

                        <c:forEach items="${hocky}" var="h">
                            <option value="${h.getIdHocKy()}">
                                ${h.getTenHocKy()} (${h.getTenNamHoc()})
                            </option>
                        </c:forEach>
                    </select>
                    <button class="tkb__btn">Search</button>
                </form>
                <div class="tkb__main">
                    <c:choose>
                        <c:when test="${idhocky > 0}">
                            <table class="tkb__content">
                                <c:forEach items="${buoi}" var="b">
                                    <tr>
                                        <th class="tkb__session" rowspan="6">${b.getTenBuoi()}</th>
                                        <th>Shift</th>
                                        <c:forEach items="${thu}" var="th">
                                            <th class="tkb__day">${th.getTenThu()}</th>
                                        </c:forEach>
                                    </tr>
                                    <c:forEach items="${tiet}" var="t">
                                        <c:choose>
                                            <c:when test="${t.getIdBuoi()==b.getIdBuoi() }">
                                                <tr>
                                                    <td class="tkb__shift">${t.getTenTiet()}</td>
                                                    <c:forEach items="${thu}" var="th">
                                                        <td class="tbk__subject edit" data-thu="${th.getIdThu()}"
                                                            data-tiet="${t.getIdTiet()}" data-buoi="${b.getIdBuoi()}">
                                                            <span>
                                                                <c:forEach items="${thoikhoabieu}" var="tkb">
                                                                    <c:choose>
                                                                        <c:when test="${tkb.getIdTiet() == t.getIdTiet()
                                                                                            && tkb.getIdThu() == th.getIdThu()
                                                                                        }">
                                                                            ${tkb.getTenMon()} (${tkb.getTenLop()})
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
                                                            </span>
                                                            <span>

                                                            </span>
                                                        </td>
                                                    </c:forEach>
                                                </tr>
                                            </c:when>
                                            <c:otherwise>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <tr class="tkb__space"></tr>
                                </c:forEach>
                            </table>
                        </c:when>
                        <c:otherwise>
                            Please select Semester
                        </c:otherwise>
                    </c:choose>
                </div>

            </main>
        </div>
        <script src="./js/main.js"></script>
    </body>

    </html>
    <script>
        document.querySelectorAll(".header__menu-item")[1].classList.add("active")
    </script>