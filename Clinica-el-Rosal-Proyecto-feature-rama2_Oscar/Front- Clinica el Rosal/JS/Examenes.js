document.addEventListener("DOMContentLoaded", function () {
    const userData = JSON.parse(localStorage.getItem("data-user"));
    if (!userData || !userData.id) {
        alert("Usuario no identificado. Por favor inicie sesión.");
        return;
    }

    document.getElementById("nombreUsuario").textContent = userData.nombre || "N/A";
    document.getElementById("correoUsuario").textContent = userData.username || "N/A";

    fetch(`http://localhost:8080/estado_afiliacion/paciente/${userData.id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("No se pudieron cargar los exámenes.");
            }
            return response.json();
        })
        .then(data => {
            const tbody = document.getElementById("examTableBody");
            tbody.innerHTML = "";

            if (data.length === 0) {
                tbody.innerHTML = `<tr><td colspan="3">No hay exámenes disponibles.</td></tr>`;
                return;
            }

            data.forEach(examen => {
                const row = document.createElement("tr");

                row.innerHTML = `
        <td>${examen.nombreExamen}</td>
        <td>${examen.fechaExamen}</td>
        <td>${examen.archivoExamen || "Sin archivo"}</td>
        <td>
            ${examen.archivoExamen
                        ? ` <a href="http://localhost:8080/archivos/${examen.archivoExamen}" 
     download class="btn btn-sm btn-success">
     <i class="bi bi-download"></i> Descargar
  </a>`
                        : "No disponible"}
        </td>
    `;

                tbody.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Error al cargar los exámenes:", error);
        });
});
