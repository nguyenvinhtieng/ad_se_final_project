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
                                    <h2 class="text-gray-600 font-semibold">Semester</h2>
                                </div>
                                <div class="flex items-center justify-between">
                                    <div class="lg:ml-40 ml-10 space-x-8">
                                        <button id="btn-add-sem"
                                            class="bg-indigo-600 px-4 py-2 rounded-md text-white font-semibold tracking-wide cursor-pointer">New
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
                                                        ID
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        Name
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        School year
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        Start day
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        End day
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        Status
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        Action
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${hocky}" var="hk">
                                                    <tr>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <div class="flex items-center">
                                                                <div class="ml-3">
                                                                    <p class="text-gray-900 whitespace-no-wrap">
                                                                        ${hk.getIdHocKy()}
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <div class="flex items-center">
                                                                <div class="ml-3">
                                                                    <p class="text-gray-900 whitespace-no-wrap">
                                                                        ${hk.getTenHocKy()}
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <div class="flex items-center">
                                                                <div class="ml-3">
                                                                    <p class="text-gray-900 whitespace-no-wrap">
                                                                        ${hk.getTenNamHoc()}
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <div class="flex items-center">
                                                                <div class="ml-3">
                                                                    <p class="text-gray-900 whitespace-no-wrap">
                                                                        ${hk.getNgayBatDau()}
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <div class="flex items-center">
                                                                <div class="ml-3">
                                                                    <p class="text-gray-900 whitespace-no-wrap">
                                                                        ${hk.getNgayKetThuc()}
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <span class="px-2 py-1 font-semibold leading-tight rounded-sm
                                                                <c:choose>
                                                                    <c:when 
                                                                    test=" ${hk.getTrangThai()=='ACTIVE' }">
                                                                text-blue-700 bg-blue-100
                                                                <br />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    text-red-700 bg-red-100
                                                                    <br />
                                                                </c:otherwise>
                                                                </c:choose>
                                                                ">
                                                                ${hk.getTrangThai()}
                                                            </span>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <p data-id=" ${hk.getIdHocKy()}"
                                                                data-name="${hk.getTenHocKy()}"
                                                                data-startday="${hk.getNgayBatDau()}"
                                                                data-endday="${hk.getNgayKetThuc()}"
                                                                data-status="${hk.getTrangThai()}"
                                                                data-idnamhoc="${hk.getIdNamHoc()}"
                                                                data-tennamhoc="${hk.getTenNamHoc()}"
                                                                class="edit cursor-pointer inline-block text-gray-400 hover:text-gray-100 mx-2">
                                                                <i class="material-icons-outlined text-base">edit</i>
                                                            </p>
                                                            <p data-id=" ${hk.getIdHocKy()}"
                                                                class="inline-block cursor-pointer delete text-gray-400 hover:text-gray-100 ml-2">
                                                                <i
                                                                    class="material-icons-round text-base">delete_outline</i>
                                                                </a>
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
                    <jsp:include page="../partials/modals/create_semester.jsp" />
                    <jsp:include page="../partials/modals/delete_semester.jsp" />
                    <jsp:include page="../partials/modals/edit_semester.jsp" />
                </div>
            </div>
        </div>
        <script>

            const btnAddSemester = document.getElementById('btn-add-sem')

            const modalCreate = document.querySelector(".main-modal-create")
            const modalDelete = document.querySelector(".main-modal-delete")
            const modalEdit = document.querySelector(".main-modal-edit")
            const closeButtonEdit = document.querySelectorAll(".modal-close-edit")
            const closeButtonCreate = document.querySelectorAll(".modal-close-create")
            const closeButtonDelete = document.querySelectorAll(".modal-close-delete")

            const deletes = document.querySelectorAll(".delete")
            const edits = document.querySelectorAll(".edit")
            const btnDelete = document.querySelector(".btn-delete")

            const idEdit = document.querySelector('.id-edit')
            const nameEdit = document.querySelector('.name-edit')
            const startdayEdit = document.querySelector('.startday-edit')
            const enddayEdit = document.querySelector('.endday-edit')
            const statusEdit = document.querySelector('.status-edit')
            const schoolyearEdit = document.querySelector('.schoolyear-edit')

            deletes.forEach(item => {
                item.addEventListener("click", (e) => {
                    openModal(modalDelete)
                    setContentDeleteModal(item)
                })
            })
            edits.forEach(item => {
                item.addEventListener("click", (e) => {
                    openModal(modalEdit)
                    setContentEditModal(item)
                })
            })
            function setContentEditModal(item) {
                idEdit.value = item.getAttribute("data-id")
                nameEdit.value = item.getAttribute("data-name")
                startdayEdit.value = item.getAttribute("data-startday")
                enddayEdit.value = item.getAttribute("data-endday")
                statusEdit.innerHTML = item.getAttribute("data-status")
                statusEdit.value = item.getAttribute("data-status")
                schoolyearEdit.innerHTML = item.getAttribute("data-tennamhoc")
                schoolyearEdit.value = item.getAttribute("data-idnamhoc")
            }
            function setContentDeleteModal(item) {
                let id = item.getAttribute("data-id")
                btnDelete.href = "/admin/semester/delete?id=" + id
            }
            btnAddSemester.addEventListener("click", (e) => openModal(modalCreate))

            initModal([
                { modal: modalCreate, closeButtons: closeButtonCreate },
                { modal: modalDelete, closeButtons: closeButtonDelete },
                { modal: modalEdit, closeButtons: closeButtonEdit }
            ]);
        </script>
    </body>

    </html>