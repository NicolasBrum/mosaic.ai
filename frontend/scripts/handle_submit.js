async function handleSubmit(event) {
    event.preventDefault();

    const emailInput = document.getElementById("email");
    const email = emailInput.value;

    const name = document.getElementById("name").value;
    const lastname = document.getElementById("lastname").value;
    const cargo = document.getElementById("cargo").value;
    const empresa = document.getElementById("empresa").value;

    try {
        const response = await fetch(`http://localhost:8080/mosaic/validate-domain?email=${email}`, {
            method: "GET",
        });

        if (response.ok) {
            alert("Seu email foi validado com sucesso!"); 

            const formData = {
                name: name,
                surname: lastname,
                position: cargo,
                enterprise: empresa,
                email: email
            };

            await sendFormData(formData);

            await sendEmail(formData);

        } else {
            const errorData = await response.text();
            alert("Erro na validação do email: " + errorData);
        }

    } catch (error) {
        alert("Communication error with the API: " + error.message);
    }
}

async function sendFormData(formData) {
    try {
        const sendResponse = await fetch('http://localhost:8080/lead', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        });

        if (sendResponse.ok) {
            alert("Informações enviadas com sucesso!");
            document.querySelector(".form__container").reset();
        } else {
            const errorData = await sendResponse.text();
            alert("Erro enviando suas informações " + errorData);
        }
    } catch (error) {
        alert("Communication error with the API: " + error.message);
    }
}

async function sendEmail(formData) {
    try {
        const sendResponse = await fetch('http://localhost:8080/mosaic/send-email', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        });

        if (sendResponse.ok) {
            document.querySelector(".form__container").reset();
        } else {
            const errorData = await sendResponse.text();
            alert("Erro enviando seu email: " + errorData);
        }
    } catch (error) {
        alert("Communication error with the API: " + error.message);
    }
}

document.getElementById("form").addEventListener("submit", handleSubmit);
