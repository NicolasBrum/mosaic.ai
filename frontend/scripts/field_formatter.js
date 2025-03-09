document.querySelectorAll('.turnover__form__input:not(.turnover__form__percentage)').forEach(input => {
    input.addEventListener('input', (e) => {
        let value = e.target.value.replace(/\D/g, '');
        if (value) {
            //  formatação da moeda
            value = (parseInt(value) / 100).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
        }
        e.target.value = value;
    });

    input.addEventListener('blur', (e) => {
        if (!e.target.value) {
            e.target.value = 'R$ 0,00';
        }
    });

    input.value = '';
});
