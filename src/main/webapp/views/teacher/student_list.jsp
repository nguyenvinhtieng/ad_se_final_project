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
            <main class="student">
                <div class="student__title">
                    Student class 10A1
                    <button class="student__btn btn__show_modal_add_student">
                        Add new Student
                    </button>
                </div>
                <table class="student__table">
                    <tr>
                        <th>Id Student</th>
                        <th>Full Name</th>
                        <th>Date of Birth</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach items="${hocsinhlist}" var="hocsinh">
                        <tr>
                            <td>${hocsinh.getIdHocSinh()}</td>
                            <td>${hocsinh.getTenHocSinh()}</td>
                            <td>${hocsinh.getNgaySinh()}</td>
                            <td>
                                <span data-id="${hocsinh.getIdHocSinh()}" class="delete__student">
                                    <ion-icon name="trash"></ion-icon>
                                </span>
                                <span>
                                    <ion-icon name="eye"></ion-icon>
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </main>
        </div>

        <script src="./js/main.js"></script>

        <div class="overlay" id="overlay_add"></div>
        <div class="modal" id="modal_add">
            <button class="modal-close-btn" id="close-btn"> &times</button>
            <h3>Add Student</h3>
            <form action="" class="modal__content" method="POST">
                <div class="text-field">
                    <label for="username3">Student Id</label>
                    <input name="id" type="text" id="username3" placeholder="Student Id" />
                </div>
                <button class="btn__save">Save</button>
            </form>
        </div>
        <script>
            let deletes = document.querySelectorAll(".delete__student")
            deletes.forEach(item => {
                item.onclick = (e) => {
                    let id = item.getAttribute("data-id")
                    showModalDelete(id)
                }
            })
            function showModalDelete(idHs) {
                Swal.fire({
                    title: 'Do you want delete this student?',
                    showDenyButton: true,
                    showCancelButton: true,
                    showConfirmButton: false,
                    denyButtonText: `Delete`,
                }).then((result) => {
                    if (result.isDenied) {
                        window.location.href = "/teacher/delete-student?idlop=2&idhs=" + idHs
                    }
                })
            }
        </script>

    </body>

    </html>
    <script>
        document.querySelectorAll(".header__menu-item")[2].classList.add("active")
    </script>