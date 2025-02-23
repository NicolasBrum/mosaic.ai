// Seleciona a área e o botão
const areaBloqueada = document.getElementById("areaBloqueada");
const botaoDesbloquear = document.getElementById("botaoDesbloquear");

// Bloqueia a área inicialmente
areaBloqueada.classList.add("bloqueada");

// Desbloqueia a área quando o botão for clicado
botaoDesbloquear.addEventListener("click", function() {
    areaBloqueada.classList.remove("bloqueada");
    alert("Área desbloqueada! Agora você pode interagir com ela.");
});