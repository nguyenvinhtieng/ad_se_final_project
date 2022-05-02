<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Home Admin</title>
    <jsp:include page="../partials/file_and_lib.jsp" />
</head>

<body>
    <div>
        <jsp:include page="../partials/nav.jsp" />

        <div class="flex overflow-hidden bg-white pt-16">
            <jsp:include page="../partials/sidebar_student.jsp" />
			<div id="main-content" class="h-full w-full bg-gray-50 relative overflow-y-auto lg:ml-64">
                <main class="bg-white shadow rounded-lg p-4 md:p-6 xl:p-2 my-6
                    mx-4">
                    <div class="bg-white p-8 rounded-md w-full">
                        <div class="">
                        <c:forEach items="${notifies_detail}" var="thongbao">
                            <h1 class="text-center font-semibold text-blue-500" style="font-size: 30px;">${thongbao.getTieuDe()}</h1>
                            <div class="font-semibold text-blue-500 text-right mt-4 mb-4 underline" style="text-align: right; font-style: italic; text-decoration: underline;">Ngày đăng: ${thongbao.getNgayDang()}</div>
                            <div class="text-justify" style="text-align: justify;">
                            	 ${thongbao.getNoiDung()}
                            </div>
                         </c:forEach>
                        </div>
                    </div>
                </main>
            
                <jsp:include page="../partials/modals/create_noti.jsp" />
                <jsp:include page="../partials/modals/delete_noti.jsp" />
                <jsp:include page="../partials/footer.jsp" />
             </div>
        </div>
    </div>
    <script>
        const modalCreate = document.querySelector('.main-modal-create');
        const modalDelete = document.querySelector('.main-modal-delete');

        const closeButtonModalCreate = document.querySelectorAll('.modal-close-create');
        const closeButtonModalDelete = document.querySelectorAll('.modal-close-delete');

        const deletes = document.querySelectorAll(".btn-delete")
        const btnAddNoti = document.getElementById('btn-add-noti')

        const btnDeleteNoti = document.querySelector('.btn-delete-noti')

        initModal([
            { modal: modalCreate, closeButtons: closeButtonModalCreate },
            { modal: modalDelete, closeButtons: closeButtonModalDelete },
        ])

        btnAddNoti.addEventListener("click", (e) => {
            openModal(modalCreate)
        })

        deletes.forEach(item => {
            item.addEventListener("click", e => {
                openModal(modalDelete)
                setDataToDeleteModal(item)
            })
        })

        function setDataToDeleteModal(item) {
            console.log(item);
            const id = item.getAttribute("data-id")
            btnDeleteNoti.href = "/admin/delete?id=" + id
        }
    </script>
</body>

</html>