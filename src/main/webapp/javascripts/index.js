function openModal(modal) {
    modal.classList.remove("fadeOut");
    modal.classList.add("fadeIn");
    modal.style.display = "flex";
}

function closeModal(modal) {
    modal.classList.remove("fadeIn");
    modal.classList.add("fadeOut");
    setTimeout(() => {
        modal.style.display = "none";
    }, 500);
}

function initModal(data) {
    data.forEach((obj) => {
        const { modal, closeButtons } = obj;
        for (let i = 0; i < closeButtons.length; i++) {
            const elements = closeButtons[i];
            elements.onclick = (e) => {
                closeModal(modal);
            };
        }
        modal.style.display = "none";
        window.onclick = function (event) {
            if (event.target == modal) {
                closeModal(modal);
            }
        };
    });
}
function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(";");
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == " ") {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
function setCookie(cname, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + exdays * 24 * 60 * 60 * 1000);
    let expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
function upperCaseFirstCharacter(str) {
    return str
        .replace(/(?:^\w|[A-Z]|\b\w)/g, function (word, index) {
            return index === 0 ? word.toUpperCase() : word.toUpperCase();
        })
        .replace(/\s+/g, "");
}
function showToast(type, message) {
    let title = upperCaseFirstCharacter(type);
    setTimeout(() => {
        Swal.fire(title, message, type);
    }, 100);
}
function checkToast() {
    let toastObj = getCookie("TOAST-MESSAGE");
    if (!toastObj || toastObj === "") return;
    let [type, message] = toastObj.split("#");
    message = message.split("/").join(" ");
    showToast(type, message);
    setCookie("TOAST-MESSAGE", null, 1);
}

(function run() {
    //checkToast();
})();
