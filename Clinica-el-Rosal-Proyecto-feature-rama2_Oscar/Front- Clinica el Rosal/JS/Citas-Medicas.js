document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("formCita");

    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const userData = JSON.parse(localStorage.getItem("data-user"));

        if (!userData || !userData.id) {
            alert("Usuario no encontrado. Inicia sesión nuevamente.");
            return;
        }

        const cita = {
            idPaciente: userData.id,
            idMedico: parseInt(document.getElementById("medico").value),
            fecha: document.getElementById("fecha").value,
            hora: document.getElementById("hora").value,
            estado: "pendiente", 
            idEspecialidad: document.getElementById("especialidad").selectedOptions[0].textContent

        };

        console.log("Cita enviada al backend:", cita); // Depuración

        fetch("http://localhost:8080/cita", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(cita)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("No se pudo agendar la cita.");
                }
                return response.json();
            })
            .then(data => {
                alert("¡Cita agendada exitosamente!");
                form.reset();
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Error al conectar con el servidor.");
            });
    });
});

function cargarCitasDelPaciente() {
    const userData = JSON.parse(localStorage.getItem("data-user"));

    if (!userData || !userData.id) {
        return;
    }

    fetch(`http://localhost:8080/cita/paciente/${userData.id}`)
        .then(response => response.json())
        .then(data => {
            const tabla = document.getElementById("tabla-citas");
            if (data.length === 0) {
                tabla.innerHTML = "<p class='text-muted'>No hay citas registradas.</p>";
                return;
            }

            let html = `
                <table class="table table-bordered mt-2">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Especialidad</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
            `;

            data.forEach(cita => {
                html += `
                    <tr>
                        <td>${cita.id}</td>
                        <td>${cita.fecha}</td>
                        <td>${cita.hora}</td>
                        <td>${cita.idEspecialidad}</td>
                        <td>${cita.estado}</td>
                    </tr>
                `;
            });

            html += `</tbody></table>`;
            tabla.innerHTML = html;
        })
        .catch(error => {
            console.error("Error al cargar citas:", error);
        });
}

document.addEventListener("DOMContentLoaded", function () {
    cargarEspecialidades();

    document.getElementById("especialidad").addEventListener("change", function () {
        const especialidadId = this.value;
        cargarMedicosPorEspecialidad(especialidadId);
    });
});

function cargarEspecialidades() {
    fetch("http://localhost:8080/especialidad")
        .then(res => res.json())
        .then(data => {
            const select = document.getElementById("especialidad");
            select.innerHTML = '<option value="" disabled selected>Seleccione</option>';

            data.forEach(especialidad => {
                const option = document.createElement("option");
                option.value = especialidad.id;
                option.textContent = especialidad.nombreEspecialidad;
                select.appendChild(option);
            });
        });
}

function cargarMedicosPorEspecialidad(especialidadId) {
    fetch(`http://localhost:8080/medico/especialidad/${especialidadId}`)
        .then(res => res.json())
        .then(data => {
            const select = document.getElementById("medico");
            select.innerHTML = '<option value="" disabled selected>Seleccione</option>';

            data.forEach(medico => {
                const option = document.createElement("option");
                option.value = medico.id;
                option.textContent = `${medico.nombre} ${medico.apellido}`;
                select.appendChild(option);
            });
        });
}