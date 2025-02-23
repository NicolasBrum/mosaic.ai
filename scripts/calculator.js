const acessToCalculatorButton = document.getElementById("acess_to_calculator");

const calculator = document.getElementById("calculator");
const calculatorInputs = document.getElementsByClassName("turnover__form__input");
const elementosArray = Array.from(calculatorInputs);


acessToCalculatorButton.addEventListener("click",function() {
    elementosArray.forEach(function(elemento) {
        elemento.classList.remove("turnover__form__input");
        elemento.removeAttribute("disabled");
    });
})