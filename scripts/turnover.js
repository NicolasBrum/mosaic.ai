
const calculateButton = document.getElementById("calculate__button");
const value = document.getElementById("teste");

calculateButton.onclick = function() {
    const salary = parseFloat(document.getElementById("salary").value);
    const benefits = parseFloat(document.getElementById("benefits").value);
    const fines = parseFloat(document.getElementById("fines").value);
    const recruitment = parseFloat(document.getElementById("recruitment").value);
    const training = parseFloat(document.getElementById("training").value);
    const rh = parseFloat(document.getElementById("rh").value);
    const turnover = parseFloat(document.getElementById("turnover").value);

    const sum = salary + benefits + fines + recruitment + training + rh;
    const totalValue = (turnover / 100) * sum;
    value.textContent = `R$ ${totalValue.toFixed(2)}`;
};

function validateFields(value){
    if(value === null || value === undefined || value.trim() === ''){
        alert("campo com erro");
    }
}