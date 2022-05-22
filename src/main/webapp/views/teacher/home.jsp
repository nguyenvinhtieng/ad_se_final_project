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
        <main class="home">
            <h2>
                Teacher portal
            </h2>
            <div class="home__content">

                <div class="home__left">
                    <h1>Lorem ipsum dolor sit amet consectetur adipisicing.</h1>
                    <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Blanditiis iusto asperiores, beatae
                        vitae labore ratione inventore tenetur quam dolorum velit!</p>
                </div>
                <div class="home__right">
                    <img src="./images/school.png" alt="">
                </div>
            </div>
        </main>
    </div>
    <script src="./js/main.js"></script>
</body>

</html>
<script>
    document.querySelectorAll(".header__menu-item")[0].classList.add("active")
</script>