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
            <main class="score">
                <div class="score__filter">
                    <select class="score__select" name="" id="">
                        <option value="">--Choose Semester</option>
                        <option value="">asd</option>
                    </select>
                    <button class="score__btn">Search</button>
                </div>
                <table class="score__content">
                    <tr class="">
                        <th></th>
                        <th class="score__type">
                            Ktra Mieng
                        </th>
                        <th class="score__type">
                            15 min
                        </th>
                        <th class="score__type">
                            60 min
                        </th>
                        <th class="score__type">
                            60 min
                        </th>
                    </tr>
                    <tr>
                        <th>Math</th>
                        <td class="score__score">10,10,10</td>
                        <td class="score__score">10,10,10</td>
                        <td class="score__score">10,10,10</td>
                        <td class="score__score">10,10,10</td>
                    </tr>
                    <tr>
                        <th>Math</th>
                        <td class="score__score">10,10,10</td>
                        <td class="score__score">10,10,10</td>
                        <td class="score__score">10,10,10</td>
                        <td class="score__score">10,10,10</td>
                    </tr>
                    <tr>
                        <th>Math</th>
                        <td class="score__score">10,10,10</td>
                        <td class="score__score">10,10,10</td>
                        <td class="score__score">10,10,10</td>
                        <td class="score__score">10,10,10</td>
                    </tr>
                    <tr>
                        <th>Math</th>
                        <td class="score__score">10,10,10</td>
                        <td class="score__score">10,10,10</td>
                        <td class="score__score">10,10,10</td>
                        <td class="score__score">10,10,10</td>
                    </tr>
                </table>
            </main>
        </div>
        <script src="./js/main.js"></script>
    </body>

    </html>
    <script>
        document.querySelectorAll(".header__menu-item")[3].classList.add("active")
    </script>