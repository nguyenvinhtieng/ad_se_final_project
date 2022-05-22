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
            <main class="profile">
                <div class="profile__top">
                    <div class="profile__left">
                        <div class="profile__img">
                            <img src="/images/${giaovien.getAvatar()}" alt="">
                        </div>
                    </div>
                    <div class="profile__content">
                        <div class="profile__item">
                            <div class="profile__title">
                                Name:
                            </div>
                            <div class="profile__text">
                                ${giaovien.getName()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Identity:
                            </div>
                            <div class="profile__text">
                                ${giaovien.getIdentity()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Date of Birth:
                            </div>
                            <div class="profile__text">
                                ${giaovien.getDate()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Gender:
                            </div>
                            <div class="profile__text">
                                ${giaovien.getSex()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Original place:
                            </div>
                            <div class="profile__text">
                                ${giaovien.getOriginalplace()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Nation:
                            </div>
                            <div class="profile__text">
                                ${giaovien.getNation()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Household:
                            </div>
                            <div class="profile__text">
                                ${giaovien.getHousehold()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Phone number:
                            </div>
                            <div class="profile__text">
                                ${giaovien.getPhone()}
                            </div>
                        </div>
                        <div class="profile__item">
                            <div class="profile__title">
                                Email:
                            </div>
                            <div class="profile__text">
                                ${giaovien.getEmail()}
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <script src="./js/main.js"></script>
    </body>

    </html>