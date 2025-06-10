document.addEventListener("DOMContentLoaded", function () {
    const userData = JSON.parse(localStorage.getItem("data-user"));
    console.log("Usuario localStorage:", userData);

    if (!userData || !userData.id) {
        alert("No se pudo cargar la afiliación del usuario.");
        return;
    }

    const nombreCompleto = `${userData.nombre} ${userData.apellido}`;
    document.getElementById("nombreUsuario").textContent = nombreCompleto;
    document.getElementById("tipoIdentificacion").textContent = userData.tipoIdentificacion;
    document.getElementById("identificacion").textContent = userData.identificacion;

    fetch(`http://localhost:8080/estado_afiliacion/paciente/${userData.id}`)
        .then(response => {
            if (!response.ok) throw new Error("Error consultando afiliación");
            return response.json();
        })
        .then(data => {
            console.log("Afiliación obtenida:", data);

            if (!data || !data.estadoAfiliacion) {
                alert("No se encontró afiliación registrada para este usuario.");
                return;
            }

            document.getElementById("estadoAfiliacion").textContent = data.estadoAfiliacion;
            document.getElementById("fechaActivacion").textContent = formatearFecha(data.fechaActivacion);
            document.getElementById("fechaCertificado").textContent = formatearFecha(data.fechaCertificado);
            document.getElementById("observaciones").textContent = data.observaciones || "";
        })
        .catch(error => {
            console.error("Error al cargar afiliación:", error);
        });
});

function formatearFecha(fecha) {
    if (!fecha) return "";
    const f = new Date(fecha);
    const dia = f.getDate().toString().padStart(2, "0");
    const mes = f.toLocaleString('default', { month: 'long' });
    const año = f.getFullYear();
    return `${dia} días del mes ${mes} del ${año}`;
}
