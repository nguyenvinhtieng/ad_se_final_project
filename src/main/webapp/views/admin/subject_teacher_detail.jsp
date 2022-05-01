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
                                    <h2 class="text-gray-600 font-semibold">Class ${lop.getTenLop()}</h2>
                                </div>
                            </div>
                                <div class="class_grade">
                                    <div class="class__list">
                                        <c:forEach items="${monhoc}" var="m">
                                            <a href="#" data-id="${m.getIdMonHoc()}" 
                                            data-class="${lop.getIdLop()}"
                                            data-message="Choose a ${m.getTenMonHoc()} teacher for class ${lop.getTenLop()}" class="class__item edit">
                                                <div class="class__name">${m.getTenMonHoc()}</div> 
                                                <c:forEach items="${giaovienmonhoc}" var="g">
                                                    <c:choose>
                                                        <c:when test="${g.getIdMonHoc() == m.getIdMonHoc()}">
                                                            <div class="class__hk">${g.getTenGv()}</div>
                                                            <div class="class__hk">(${g.getUsernameGv()})</div>
                                                        </c:when>
                                                        <c:otherwise>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </a>
                                        </c:forEach>
                                    </div>
                                </div>
                            
                        </div>
                    </main>
                    <jsp:include page="../partials/footer.jsp" />
                    <jsp:include page="../partials/modals/choose_subject_teacher.jsp" />
                </div>
            </div>
            <style>
                .class_grade{
                    border: 2px solid rgba(3, 150, 255, 0.1);
                    margin-top: 25px;
                    padding: 16px;
                    border-radius: 8px;
                }
                .class_grade:nth-child(1){
                    border-top: 2px solid transparent;
                }
                .class__list{
                    display: grid;
                    grid-template-columns: repeat(6, 1fr);
                    grid-gap: 8px;
                    margin-top: 12px;
                }
                .class__item{
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
                .class__item:hover{
                    background-color: rgba(3, 150, 255,0.1);
                    border-color: #0396ff;
                }
                .class__name{
                    font-size: 25px;
                    font-weight: bold;
                }
                .class__name, .class__hk{
                    max-width: 100%;
                }
                .class__hk{
                    font-size: 12px;
                }
            </style>

        </div>
        <script>
            const modalEdit = document.querySelector('.main-modal-edit');
            const closeButtonModalEdit = document.querySelectorAll('.modal-close-edit');
            
            initModal([
                { modal: modalEdit, closeButtons: closeButtonModalEdit }
            ])
            const edits = document.querySelectorAll(".edit")
            edits.forEach(item => {
                item.addEventListener("click", e => {
                    openModal(modalEdit)
                    setDataToModalEdit(item)
                })
            })
            const messageContent = document.querySelector(".subject-teacher-message")
            const subjectId = document.querySelector(".subject-edit")
            function setDataToModalEdit(item){
                messageContent.innerHTML = item.getAttribute("data-message")
                subjectId.value = item.getAttribute("data-id")
            }
        </script>
    </body>

    </html>