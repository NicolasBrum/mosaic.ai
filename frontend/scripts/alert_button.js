const alertButton = document.getElementById("btn_to_form");

alertButton.onclick = function() {

    const forms = document.getElementById("form");

    const formPosition = forms.getBoundingClientRect().top + window.scrollY;

    window.scrollTo({
        top: formPosition,
        behavior: "smooth" // Rola suavemente
    });
};