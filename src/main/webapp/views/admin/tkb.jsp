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
                                    <h2 class="text-gray-600 font-semibold">Classes</h2>
                                </div>
                            </div>
                            <!-- grades -->
                            <c:forEach items="${grades}" var="grade">
                                <div class="class_grade">
                                    <strong>
                                        Grade ${grade}
                                    </strong>
                                    <div class="class__list">
                                        <c:forEach items="${classes}" var="c">
                                            <c:choose>
                                                <c:when test="${c.getKhoi() == grade}">
                                                    <a href="/admin/tkb-detail?classid=${c.getIdLop()}&idnamhoc=${c.getIdNamHoc()}"
                                                        class="class__item">
                                                        <div class="class__name">${c.getTenLop()}</div>
                                                        <div class="class__hk">${c.getTenNamHoc()}</div>
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </main>
                    <jsp:include page="../partials/footer.jsp" />
                </div>
            </div>
            <style>
                .class_grade {
                    border: 2px solid rgba(3, 150, 255, 0.1);
                    margin-top: 25px;
                    padding: 16px;
                    border-radius: 8px;
                }

                .class_grade:nth-child(1) {
                    border-top: 2px solid transparent;
                }

                .class__list {
                    display: grid;
                    grid-template-columns: repeat(6, 1fr);
                    grid-gap: 8px;
                    margin-top: 12px;
                }

                .class__item {
                    border: 2px solid rgb(226, 226, 226);
                    padding: 5px;
                    overflow: hidden;
                    width: 150px;
                    height: 150px;
                    border-radius: 6px;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    flex-direction: column;
                    transition: all 0.2s linear;
                }

                .class__item:hover {
                    background-color: rgba(3, 150, 255, 0.1);
                    border-color: #0396ff;
                }

                .class__name {
                    font-size: 25px;
                    font-weight: bold;
                }

                .class__name,
                .class__hk {
                    max-width: 100%;
                }

                .class__hk {
                    font-size: 12px;
                }
            </style>
        </div>
    </body>

    </html>