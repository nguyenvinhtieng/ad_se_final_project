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
            <main class="profile">
                <div class="profile__top">
                    <div class="profile__left">
                        <div class="profile__img">
                            <img src="/images/${student.getLinkAvatar()}" alt="">
                        </div>
                    </div>
                    <div class="profile__content">
                        <div class="profile__item">
                            <div class="profile__title">
                                Name:
                            </div>
                            <div class="profile__text">
                                ${student.getTenHocSinh()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                ID:
                            </div>
                            <div class="profile__text">
                                ${student.getIdHocSinh()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Date of Birth:
                            </div>
                            <div class="profile__text">
                                ${student.getNgaySinh()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Gender:
                            </div>
                            <div class="profile__text">
                                ${student.getGioiTinh()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Original place:
                            </div>
                            <div class="profile__text">
                                ${student.getQueQuan()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Nation:
                            </div>
                            <div class="profile__text">
                                ${student.getDanToc()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Gender:
                            </div>
                            <div class="profile__text">
                                ${student.getHoKhau()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Householder:
                            </div>
                            <div class="profile__text">
                                ${student.getGioiTinh()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Phone:
                            </div>
                            <div class="profile__text">
                                ${student.getSdtPhuHuynh()}
                            </div>
                        </div>

                    </div>
                </div>
            </main>
        </div>
        <script src="./js/main.js"></script>
    </body>

    </html>