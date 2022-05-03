const avatarHeader = document.querySelector(".header__avatar");
const dropdownHeader = document.querySelector(".header__dropdown-content");
const dropdownHeaderMain = document.querySelector(".header__dropdown");
avatarHeader.addEventListener("click", () => handleToggleDropdown());
const handleToggleDropdown = (status = null) => {
    dropdownHeader.classList.toggle("active");
};
// window.addEventListener("click", (e) => {
//     if (e.target.closets(dropdownHeaderMain)) {
//         alert("run")
//     }
// })
const body = document.querySelector("body");
const switchThemeButton = document.querySelector("#switch-theme");

(function darkTheme() {
    let theme = localStorage.getItem("theme");
    if (theme === "dark") {
        body.classList.add("dark");
        switchThemeButton.checked = true;
    } else if (theme === "light") {
        body.classList.remove("dark");
        switchThemeButton.checked = false;
    }
})();
switchThemeButton.addEventListener("change", (e) => {
    if (switchThemeButton.checked) {
        body.classList.add("dark");
        switchThemeButton.checked = true;
        localStorage.setItem("theme", "dark");
    } else {
        body.classList.remove("dark");
        switchThemeButton.checked = false;
        localStorage.setItem("theme", "light");
    }
});

// Modal change pass
const btnChangePass = document.querySelector(".change__pass-btn");
const overlay = document.getElementById("overlay");
const modal = document.getElementById("modal");

btnChangePass.addEventListener("click", function () {
    overlay.classList.add("is-visible");
    modal.classList.add("is-visible");
});

document.getElementById("close-btn").addEventListener("click", function () {
    overlay.classList.remove("is-visible");
    modal.classList.remove("is-visible");
});
overlay.addEventListener("click", function () {
    overlay.classList.remove("is-visible");
    modal.classList.remove("is-visible");
});
