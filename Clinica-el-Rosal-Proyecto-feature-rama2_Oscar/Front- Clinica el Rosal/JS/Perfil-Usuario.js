document.addEventListener("DOMContentLoaded", () => {
    const dataUser = JSON.parse(localStorage.getItem("data-user"));

    if (!dataUser) {
        alert("No hay sesión activa. Redirigiendo al login...");
        window.location.href = "Login.html";
        return;
    }

    document.getElementById("usuario").innerText = dataUser.nombre + "  " + dataUser.apellido;
    document.getElementById("identificacion").innerText = dataUser.tipoIdentificacion + " - " + dataUser.identificacion;
    document.getElementById("username").innerText = dataUser.username;
    document.getElementById("rol").innerText = dataUser.rol;
    document.getElementById("genero").innerText = dataUser.genero;
    document.getElementById("fechaNacimiento").innerText = dataUser.fechaNacimiento;
    document.getElementById("telefono").innerText = dataUser.telefono;
    document.getElementById("grupoSanguineo").innerText = dataUser.grupoSanguineo;
    document.getElementById("tipoDeAlergia").innerText = dataUser.tipoDeAlergia;

});

function logout() {
    if (confirm("¿Estás seguro de que deseas cerrar sesión?")) {
        localStorage.clear();
        window.location.href = "index.html";
    }
}
    /*document.addEventListener("DOMContentLoaded", function() {
                
    const citasBtn = document.querySelector('a[href="#"]:nth-child(1)');
    const diagnosticoBtn = document.querySelector('a[href="#"]:nth-child(2)');
    const medicamentosBtn = document.querySelector('a[href="#"]:nth-child(3)');
    const afiliacionBtn = document.querySelector('a[href="#"]:nth-child(4)');

   
    citasBtn.addEventListener("click", function(event) {
        event.preventDefault(); 
        window.location.href = "citas-medicas.html"; 
    });

    diagnosticoBtn.addEventListener("click", function(event) {
        event.preventDefault();
        window.location.href = "diagnostico-resultados.html"; 
    });

    afiliacionBtn.addEventListener("click", function(event) {
        event.preventDefault();
        window.location.href = "estado-afiliacion.html";
    });
});*/


