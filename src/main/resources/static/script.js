document.getElementById("submitButton").addEventListener("click", async function () {
    const courseId = document.getElementById("id").value;
    const name = document.getElementById("name").value;
    const description = document.getElementById("description").value;

    const courseData = {
        name: name,
        description: description
    };

    try {
        const response = await fetch(`http://localhost:8080/api/course/${courseId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(courseData)
        });

        const messageDiv = document.getElementById("message");

        if (response.ok) {
            messageDiv.innerHTML = `<div class="alert alert-success">Corso aggiornato con successo!</div>`;
        } else if (response.status === 404) {
            messageDiv.innerHTML = `<div class="alert alert-warning">Corso non trovato!</div>`;
        } else {
            messageDiv.innerHTML = `<div class="alert alert-danger">Errore durante l'aggiornamento del corso.</div>`;
        }
    } catch (error) {
        console.error("Errore nella richiesta:", error);
        document.getElementById("message").innerHTML =
            `<div class="alert alert-danger">Errore imprevisto: ${error.message}</div>`;
    }
});
