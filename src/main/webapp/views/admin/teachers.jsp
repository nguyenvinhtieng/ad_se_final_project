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
                                    <h2 class="text-gray-600 font-semibold">Teacher</h2>
                                </div>
                                <div class="flex items-center justify-between">
                                    <div class="lg:ml-40 ml-10 space-x-8">
                                        <button id="btn-add-teacher"
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
                                                        Name
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        Phone
                                                    </th>
                                                    <th
                                                        class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">
                                                        Email
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
                                                <c:forEach items="${teachers}" var="teacher">
                                                    <tr>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <div class="flex items-center">
                                                                <div class="ml-3">
                                                                    <p class="text-gray-900 whitespace-no-wrap">
                                                                        ${teacher.getName()}
                                                                    </p>
                                                                </div>
                                                            </div>
                                                        </td>

                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <p class="text-gray-900 whitespace-no-wrap">
                                                                ${teacher.getPhone()}
                                                            </p>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <p class="text-gray-900 whitespace-no-wrap">
                                                                ${teacher.getEmail()}
                                                            </p>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <span class="px-2 py-1 font-semibold leading-tight rounded-sm
                                                                <c:choose>
                                                                    <c:when test=" ${teacher.getStatus()=='ACTIVE' }">
                                                                text-blue-700 bg-blue-100
                                                                <br />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    text-red-700 bg-red-100
                                                                    <br />
                                                                </c:otherwise>
                                                                </c:choose>
                                                                ">
                                                                ${teacher.getStatus()}
                                                            </span>
                                                        </td>
                                                        <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                                            <p data-id="${teacher.getIdGiaoVien()}"
                                                                data-name="${teacher.getName()}"
                                                                data-identity="${teacher.getIdentity()}"
                                                                data-date="${teacher.getDate()}"
                                                                data-sex="${teacher.getSex()}"
                                                                data-originalplace="${teacher.getOriginalplace()}"
                                                                data-status="${teacher.getStatus()}"
                                                                data-phone="${teacher.getPhone()}"
                                                                data-email="${teacher.getEmail()}"
                                                                data-avatar="${teacher.getAvatar()}"
                                                                data-nation="${teacher.getNation()}"
                                                                data-household="${teacher.getHousehold()}"
                                                                class="inline-block cursor-pointer view text-gray-400 hover:text-gray-100 ml-2">
                                                                <i class="material-icons-round text-base">visibility</i>
                                                                </a>
                                                            <p data-id="${teacher.getIdGiaoVien()}"
                                                                data-name="${teacher.getName()}"
                                                                data-identity="${teacher.getIdentity()}"
                                                                data-date="${teacher.getDate()}"
                                                                data-sex="${teacher.getSex()}"
                                                                data-originalplace="${teacher.getOriginalplace()}"
                                                                data-status="${teacher.getStatus()}"
                                                                data-phone="${teacher.getPhone()}"
                                                                data-email="${teacher.getEmail()}"
                                                                data-avatar="${teacher.getAvatar()}"
                                                                data-nation="${teacher.getNation()}"
                                                                data-household="${teacher.getHousehold()}"
                                                                class="edit cursor-pointer inline-block text-gray-400 hover:text-gray-100 mx-2">
                                                                <i class="material-icons-outlined text-base">edit</i>
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
                    <jsp:include page="../partials/modals/add_teacher.jsp" />
                    <jsp:include page="../partials/modals/edit_teacher.jsp" />
                    <jsp:include page="../partials/modals/view_teacher.jsp" />
                </div>
            </div>
        </div>
        <script>
            const btnAdd = document.getElementById('btn-add-teacher')

            const modalCreate = document.querySelector(".main-modal-create")
            const closeButtonCreate = document.querySelectorAll(".modal-close-create")
            const modalEdit = document.querySelector(".main-modal-edit")
            const closeButtonEdit = document.querySelectorAll(".modal-close-edit")
            const modalView = document.querySelector(".main-modal-view")
            const closeButtonView = document.querySelectorAll(".modal-close-view")

            const edits = document.querySelectorAll(".edit")
            const views = document.querySelectorAll(".view")

            const idEdit = document.querySelector('.id-edit')
            const nameEdit = document.querySelector('.name-edit')
            const identityEdit = document.querySelector('.identity-edit')
            const dateEdit = document.querySelector('.date-edit')
            const sexEdit = document.querySelector('.sex-edit')
            const statusEdit = document.querySelector('.status-edit')
            const originalplaceEdit = document.querySelector('.originalplace-edit')
            const nationEdit = document.querySelector('.nation-edit')
            const householdEdit = document.querySelector('.household-edit')
            const phoneEdit = document.querySelector('.phone-edit')
            const emailEdit = document.querySelector('.email-edit')
            const avatarEdit = document.querySelector('.avatar-edit')

            initModal([
                { modal: modalCreate, closeButtons: closeButtonCreate },
                { modal: modalEdit, closeButtons: closeButtonEdit },
                { modal: modalView, closeButtons: closeButtonView }
            ]);
            edits.forEach(item => {
                item.addEventListener("click", (e) => {
                    openModal(modalEdit)
                    setContentEditModal(item)
                })
            })
            views.forEach(item => {
                item.addEventListener("click", (e) => {
                    openModal(modalView)
                    setContentViewModal(item)
                })
            })

            function setContentEditModal(item) {
                idEdit.value = item.getAttribute("data-id")
                nameEdit.value = item.getAttribute("data-name")
                identityEdit.value = item.getAttribute("data-identity")
                dateEdit.value = item.getAttribute("data-date")
                // statusEdit.innerHTML = item.getAttribute("data-status")
                sexEdit.value = item.getAttribute("data-sex")
                sexEdit.innerHTML = item.getAttribute("data-sex")
                originalplaceEdit.value = item.getAttribute("data-originalplace")
                nationEdit.value = item.getAttribute("data-nation")
                householdEdit.value = item.getAttribute("data-household")
                phoneEdit.value = item.getAttribute("data-phone")
                emailEdit.value = item.getAttribute("data-email")
                // avatarEdit.value = item.getAttribute("data-avatar")
            }

            btnAdd.addEventListener("click", (e) => openModal(modalCreate))
            const viewContent = document.querySelector(".view-content")
            function setContentViewModal(item) {
                let imgSource = "/images/" + item.getAttribute("data-avatar")

                viewContent.innerHTML = `<div class="flex flex-col items-center justify-center bg-white p-4 shadow rounded-lg">
                    <div class="avatar inline-flex shadow-lg border border-gray-200 rounded-full overflow-hidden max-h-20 max-w-20">
                        <img src="`+ imgSource + `"
                            alt="" class="object-cover h-full w-full ">
                    </div>

                    <h2 class="mt-4 font-bold text-xl">`+ item.getAttribute("data-name") + `</h2>
                    <h6 class="mt-2 text-sm font-medium">Teacher</h6>

                    <p class="text-xs text-gray-500 text-center mt-3 w-full">
                        =============================================</br>
                        <div class="line__data">
                            <strong>Name: </strong>`+ item.getAttribute("data-name") + `</br></div>
                        <div class="line__data">
                            <strong>Identity: </strong>`+ item.getAttribute("data-identity") + `</br></div>
                        <div class="line__data">
                            <strong>Date of birth: </strong>`+ item.getAttribute("data-date") + `</br></div>
                        <div class="line__data">
                            <strong>Gender: </strong>`+ item.getAttribute("data-sex") + `</br></div>
                        <div class="line__data">
                            <strong>Original place: </strong>`+ item.getAttribute("data-originalplace") + `</br></div>
                        <div class="line__data">
                            <strong>Nation: </strong>`+ item.getAttribute("data-nation") + `</br></div>
                        <div class="line__data">
                            <strong>Householder: </strong>`+ item.getAttribute("data-household") + `</br></div>
                        <div class="line__data">
                            <strong>Phone number: </strong>`+ item.getAttribute("data-phone") + `</br></div>
                        <div class="line__data">
                            <strong>Email: </strong>`+ item.getAttribute("data-email") + `</br></div>
                    </p>
                </div>`
            }

        </script>
        <style>
            .avatar {
                max-width: 100px;
                width: 100px;
                max-height: 100px;
                height: 100px;
            }

            .avatar img {
                object-fit: cover;
            }

            .line__data {
                padding: 2px 4px;
                text-align: left;
                justify-content: flex-start;
                font-size: 16px;
                width: 100%;
            }
        </style>
    </body>

    </html>