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
