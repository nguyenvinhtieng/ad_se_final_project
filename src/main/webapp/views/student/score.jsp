<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TKB student</title>
    <link rel="stylesheet" href="./css/style.css">
</head>

<body>
    <div class="main-container">
        <header class="header">
            <div class="header__logo">
                CD_CNPM
            </div>
            <ul class="header__menu">
                <li class="header__menu-item">
                    <a href="./home.html" class="header__menu-link">Home</a>
                </li>
                <li class="header__menu-item">
                    <a href="./tkb.html" class="header__menu-link">TKB</a>
                </li>
                <li class="header__menu-item">
                    <a href="./notify.html" class="header__menu-link">Notify</a>
                </li>
                <li class="header__menu-item active">
                    <a href="./score.html" class="header__menu-link">Score</a>
                </li>
            </ul>
            <div class="header__dropdown">
                <input type="checkbox" class="switch-theme" id="switch-theme">
                <label for="switch-theme" class="header__toggle__theme">

                </label>
                <div class="header__avatar">
                    <img src="https://source.unsplash.com/random" alt="">
                </div>
                <ul class="header__dropdown-content">
                    <li class="header__dropdown-item">
                        <a href="./profile.html">Your profile</a>
                    </li>
                    <li class="header__dropdown-item">
                        <a href="#!">Change password</a>
                    </li>
                    <li class="header__dropdown-item">
                        <a href="#!">Logout</a>
                    </li>
                </ul>
            </div>
        </header>
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