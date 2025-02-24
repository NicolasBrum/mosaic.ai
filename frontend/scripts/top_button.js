const topButton = document.getElementById("btnTopo");

window.onscroll = function() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        btnTopo.style.display = "block"; // Mostra o botão
    } else {
        btnTopo.style.display = "none"; // Esconde o botão
    }
};

btnTopo.onclick = function() {
    document.body.scrollTop = 0; // Para navegadores Safari
    document.documentElement.scrollTop = 0; // Para outros navegadores
};

btnTopo.onclick = function() {
    window.scrollTo({
        top: 0,
        behavior: "smooth" // Rola suavemente
    });
};