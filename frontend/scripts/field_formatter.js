document.querySelectorAll('.turnover__form__input:not(.turnover__form__percentage)').forEach(input => {
    input.addEventListener('input', (e) => {
        let value = e.target.value.replace(/\D/g, '');  // Remover qualquer coisa que não seja número
        if (value) {
            // Aplica a formatação para moeda
            value = (parseInt(value) / 100).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
        }
        e.target.value = value;
    });

    input.addEventListener('blur', (e) => {
        if (!e.target.value) {
            e.target.value = 'R$ 0,00'; // Se o campo estiver vazio, coloca o valor inicial
        }
    });

    input.value = '';  // Inicializar com valor vazio
});
