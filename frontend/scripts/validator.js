const inputFields = document.querySelectorAll('input[type="number"]');

    inputFields.forEach(input => {
        input.addEventListener('input', function(event) {
            // Remove o hífen (-) da entrada
            this.value = this.value.replace(/-/g, '');
        });
    });