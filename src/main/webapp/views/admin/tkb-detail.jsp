<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <html lang="en">

    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Home Admin</title>
        <jsp:include page="../partials/file_and_lib.jsp" />
    </head>

    <body>
        <div>
            <jsp:include page="../partials/nav.jsp" />
            <div class="flex overflow-hidden bg-white pt-16">
                <jsp:include page="../partials/sidebar_admin.jsp" />
                <div id="main-content" class="h-full w-full bg-gray-50 relative overflow-y-auto lg:ml-64">
                    <main class="bg-white shadow rounded-lg p-4 md:p-6 xl:p-8 my-6
                    mx-4">
                        <div class="bg-white p-8 rounded-md w-full">
                            <div class=" flex items-center justify-between pb-2">
                                <div>
                                    <h2 class="text-gray-600 font-semibold">
                                        ${title_header}
                                    </h2>
                                </div>
                            </div>
                            <!-- grades -->
                            <form class="tkb__search">
                                <input type="hidden" value="${classid}" name="classid">
                                <input type="hidden" value="${idnamhoc}" name="idnamhoc">
                                <select name="idhocky" id="" class="tkb__select">
                                    <option value="">--Choose semester--</option>
                                    <c:forEach items="${hocky}" var="h">
                                        <option value="${h.getIdHocKy()}">
                                            ${h.getTenHocKy()}
                                        </option>
                                    </c:forEach>
                                </select>
                                <button class="tkb__btn">Search</button>
                            </form>
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
                                                                <td class="tbk__subject edit"
                                                                    data-thu="${th.getIdThu()}"
                                                                    data-tiet="${t.getIdTiet()}"
                                                                    data-buoi="${b.getIdBuoi()}">
                                                                    <span>
                                                                        <c:forEach items="${thoikhoabieu}" var="tkb">
                                                                            <c:forEach items="${monhoc}" var="mh">
                                                                                <c:choose>
                                                                                    <c:when test="${tkb.getIdMonHoc()== mh.getIdMonHoc() 
                                                                                            && tkb.getIdTiet() == t.getIdTiet()
                                                                                            && tkb.getIdThu() == th.getIdThu()
                                                                                        }">
                                                                                        ${mh.getTenMonHoc()}
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </c:forEach>
                                                                        </c:forEach>
                                                                    </span>
                                                                    <span>
                                                                        <ion-icon name="create"></ion-icon>
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
                    <jsp:include page="../partials/modals/edit_tkb.jsp" />
                    <jsp:include page="../partials/footer.jsp" />
                </div>
            </div>
            <script>
                const edits = document.querySelectorAll(".edit")
                const modalEdit = document.querySelector(".main-modal-edit")
                const closeButtonEdit = document.querySelectorAll(".modal-close-edit")
                initModal([
                    { modal: modalEdit, closeButtons: closeButtonEdit },
                ]);
                edits.forEach(item => {
                    item.addEventListener("click", e => {
                        openModal(modalEdit)
                        setDataToEditModal(item)
                    })
                })

                const thuEdit = document.querySelector('.thu-edit')
                const tietEdit = document.querySelector('.tiet-edit')
                const buoiEdit = document.querySelector('.buoi-edit')

                function setDataToEditModal(item) {
                    thuEdit.value = item.getAttribute("data-thu")
                    tietEdit.value = item.getAttribute("data-tiet")
                    buoiEdit.value = item.getAttribute("data-buoi")
                }
            </script>
            <style>
                .tkb__search {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    gap: 4px;
                }

                .tkb__select {
                    padding: 10px 20px;
                    border: 1px solid rgb(222, 222, 222);
                    border-radius: 8px;
                }

                .tkb__btn {
                    padding: 10px 20px;
                    border: none;
                    outline: none;
                    border-radius: 8px;
                    background-color: #7367F0;
                    color: white;
                }

                .tkb__content {
                    margin-top: 25px;
                    width: 100%;
                }

                .tkb__session {
                    /* transform: rotate(-90deg); */
                    background-color: #0396ff;
                    color: white;
                    width: 30px;
                    height: 30px;
                }

                .tkb__session-text {
                    transform: rotate(-90deg);
                }

                .tkb__day {
                    background-color: #7367f0;
                    color: white;
                    padding: 6px 0;
                }

                .tkb__shift {
                    text-align: center;
                    font-weight: bold;
                }

                .tbk__subject {
                    text-align: center;
                    padding: 6px 0;
                    cursor: pointer;
                    transition: all 0.2s linear;
                }

                .tbk__subject:hover {
                    background-color: #0396ff;
                }

                .tkb__space {
                    height: 50px;
                }
            </style>
        </div>
    </body>

    </html>