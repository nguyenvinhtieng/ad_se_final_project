<header class="header">
    <div class="header__logo">
        CD_CNPM
    </div>
    <ul class="header__menu">
        <li class="header__menu-item">
            <a href="/student/home" class="header__menu-link">Home</a>
        </li>
        <li class="header__menu-item">
            <a href="/student/tkb" class="header__menu-link">TKB</a>
        </li>
        <li class="header__menu-item">
            <a href="/student/notification" class="header__menu-link">Notify</a>
        </li>
        <li class="header__menu-item">
            <a href="/student/score" class="header__menu-link">Score</a>
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
                <a href="/student/profile">Your profile</a>
            </li>
            <li class="header__dropdown-item">
                <a class="change__pass-btn" href="#!">Change password</a>
            </li>
            <li class="header__dropdown-item">
                <a href="#!">Logout</a>
            </li>
        </ul>
    </div>
</header>
<!-- Modal doi mat khau -->
<div class="overlay" id="overlay"></div>
<div class="modal" id="modal">
    <button class="modal-close-btn" id="close-btn"> &times</button>
    <h3>Change your password</h3>
    <form action="/changepass" class="modal__content" method="POST">
        <div class="text-field">
            <label for="username3">Current password</label>
            <input name="currp" autocomplete="off" type="text" id="username3" placeholder="Current password" />
        </div>
        <div class="text-field">
            <label for="username3">New password</label>
            <input name="newp" autocomplete="off" type="text" id="username3" placeholder="New password" />
        </div>
        <div class="text-field">
            <label for="username3">Repeat</label>
            <input name="repeatp" autocomplete="off" type="text" id="username3" placeholder="Repeat" />
        </div>
        <button class="btn__save">Save</button>
    </form>
</div>

<!-- Ket Thuc Modal  -->