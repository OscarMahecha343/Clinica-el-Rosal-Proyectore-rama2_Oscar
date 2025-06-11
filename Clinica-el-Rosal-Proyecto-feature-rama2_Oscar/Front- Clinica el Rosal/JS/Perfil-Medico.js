document.addEventListener("DOMContentLoaded", function () {
    // Botones
    const btnAgendamiento = document.getElementById("btnAgendamiento");
    const btnHistoria = document.getElementById("btnHistoriaClinica");
    const btnExamenes = document.getElementById("btnExamenes");

    // Secciones
    const secciones = {
        containerAgendamiento: document.getElementById("containerAgendamiento"),
        containerHistoriaClinica: document.getElementById("containerHistoriaClinica"),
        containerExamenes: document.getElementById("containerExamenes")
    };

    // Ocultar todas las secciones
    function ocultarTodo() {
        Object.values(secciones).forEach(sec => sec.classList.add("d-none"));
    }

    // Mostrar la sección deseada
    function mostrar(id) {
        ocultarTodo();
        const section = secciones[id];
        if (section) {
            section.classList.remove("d-none");
            section.classList.add("d-block");
        }
    }

    // Listeners
    btnAgendamiento.addEventListener("click", () => mostrar("containerAgendamiento"));
    btnHistoria.addEventListener("click", () => mostrar("containerHistoriaClinica"));
    btnExamenes.addEventListener("click", () => mostrar("containerExamenes"));
});

function logout() {
    localStorage.removeItem("data-user");
    window.location.href = "index.html";
}