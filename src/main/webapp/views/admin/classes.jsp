<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
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
                                    <h2 class="text-gray-600 font-semibold">Notification</h2>
                                </div>
                                <div class="flex items-center justify-between">
                                    <div class="lg:ml-40 ml-10 space-x-8">
                                        <button id="btn-add"
                                            class="bg-indigo-600 px-4 py-2 rounded-md text-white font-semibold tracking-wide cursor-pointer">Add
                                            +</button>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <div class="-mx-4 sm:-mx-8 px-4 sm:px-8 py-4 overflow-x-auto">
                                    <div class="inline-block min-w-full shadow rounded-lg overflow-hidden">
                                        <table class="min-w-full leading-normal">
                                            <thead>
                                                <tr>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        Id
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        Name
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        Grade
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        Room
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        Year
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        Action
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${classes}" var="c">
                                                    <tr>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <div class="flex items-center">
                                                                <div class="ml-3">
                                                                    <p class="text-gray-900 whitespace-no-wrap">
                                                                        ${c.getIdLop()}
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <p class="text-gray-900 whitespace-no-wrap">
                                                                ${c.getTenLop()}
                                                            </p>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <p class="text-gray-900 whitespace-no-wrap">
                                                                ${c.getKhoi()}
                                                            </p>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <span class="px-2 py-1 font-semibold leading-tight rounded-sm
                                                                <c:choose>
                                                                    <c:when test="${c.getIdPhongHoc()==0 }">
                                                                text-red-700 bg-red-100
                                                                ">
                                                                NO ROOM
                                                            </span>
                                                            <br />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:choose>
                                                                    <c:when test="${c.getIdPhongHoc()==-1 }">
                                                                        text-red-700 bg-red-100
                                                                        ">
                                                                        GRADUATED
                                                                        </span>
                                                                        <br />
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        text-blue-700 bg-blue-100">
                                                                        ${c.getTenPhongHoc()}
                                                                        </span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <p class="text-gray-900 whitespace-no-wrap">
                                                                ${c.getNamVaoTruong()}
                                                            </p>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <p data-id="${c.getIdLop()}"
                                                                data-tenphong="${c.getTenPhongHoc()}"
                                                                data-idphong="${c.getIdPhongHoc()}"
                                                                data-tenlop="${c.getTenLop()}"
                                                                data-khoi="${c.getKhoi()}"
                                                                data-namvaotruong="${c.getNamVaoTruong()}"
                                                                class="inline-block cursor-pointer edit text-gray-400 hover:text-gray-100 ml-2">
                                                                <i class="material-icons-round text-base">edit</i>
                                                                </a>
                                                            </p>
                                                            <p data-id="${c.getIdLop()}"
                                                                class="inline-block cursor-pointer delete text-gray-400 hover:text-gray-100 ml-2">
                                                                <i
                                                                    class="material-icons-round text-base">delete_outline</i>
                                                                </a>
                                                            </p>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div
                                            class="px-5 py-5 bg-white border-t flex flex-col xs:flex-row items-center xs:justify-between          ">
                                            <span class="text-xs xs:text-sm text-gray-900">
                                                Showing 1 to 4 of 50 Entries
                                            </span>
                                            <div class="inline-flex mt-2 xs:mt-0">
                                                <button
                                                    class="text-sm text-indigo-50 transition duration-150 hover:bg-indigo-500 bg-indigo-600 font-semibold py-2 px-4 rounded-l">
                                                    Prev
                                                </button>
                                                &nbsp; &nbsp;
                                                <button
                                                    class="text-sm text-indigo-50 transition duration-150 hover:bg-indigo-500 bg-indigo-600 font-semibold py-2 px-4 rounded-r">
                                                    Next
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </main>
                    <jsp:include page="../partials/footer.jsp" />
                    <jsp:include page="../partials/modals/delete_class.jsp" />
                    <jsp:include page="../partials/modals/create_class.jsp" />
                    <jsp:include page="../partials/modals/edit_class.jsp" />
                </div>
            </div>
        </div>
        <script>
            const modalCreate = document.querySelector('.main-modal-create');
            const modalDelete = document.querySelector('.main-modal-delete');
            const modalEdit = document.querySelector('.main-modal-edit');

            const closeButtonModalCreate = document.querySelectorAll('.modal-close-create');
            const closeButtonModalDelete = document.querySelectorAll('.modal-close-delete');
            const closeButtonModalEdit = document.querySelectorAll('.modal-close-edit');

            const deletes = document.querySelectorAll(".delete")
            const edits = document.querySelectorAll(".edit")

            const btnAdd = document.getElementById('btn-add')

            const btnDelete = document.querySelector('.btn-delete')

            initModal([
                { modal: modalCreate, closeButtons: closeButtonModalCreate },
                { modal: modalDelete, closeButtons: closeButtonModalDelete },
                { modal: modalEdit, closeButtons: closeButtonModalEdit }
            ])

            btnAdd.addEventListener("click", (e) => {
                openModal(modalCreate)
            })

            deletes.forEach(item => {
                item.addEventListener("click", e => {
                    openModal(modalDelete)
                    setDataToDeleteModal(item)
                })
            })
            edits.forEach(item => {
                item.addEventListener("click", e => {
                    openModal(modalEdit)
                    setDataToEditModal(item)
                })
            })
            function setDataToDeleteModal(item) {
                const id = item.getAttribute("data-id")
                btnDelete.href = "/admin/classes/delete?id=" + id
            }

            const nameEdit = document.querySelector(".name-edit")
            const idEdit = document.querySelector(".id-edit")
            const gradeEdit = document.querySelector(".grade-edit")
            const roomEdit = document.querySelector(".room-edit")
            const namvaotruongEdit = document.querySelector(".namvaotruong-edit")
            const idEditView = document.querySelector(".id-edit-view")
            function setDataToEditModal(item) {
                idEdit.value = item.getAttribute("data-id")
                idEditView.value = item.getAttribute("data-id")
                roomEdit.innerHTML = item.getAttribute("data-tenphong")
                roomEdit.value = item.getAttribute("data-idphong")
                gradeEdit.innerHTML = item.getAttribute("data-khoi")
                gradeEdit.value = item.getAttribute("data-khoi")
                nameEdit.value = item.getAttribute("data-tenlop")
                namvaotruongEdit.value = item.getAttribute("data-namvaotruong")
            }
        </script>
    </body>

    </html>