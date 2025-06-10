let citaEditandoId = null;

document.addEventListener("DOMContentLoaded", function () {
    const userData = JSON.parse(localStorage.getItem("data-user"));
    if (userData) {
        document.getElementById("usuario").textContent = `${userData.nombre} ${userData.apellido}`;
        document.getElementById("identificacion").textContent = userData.tipoIdentificacion;
        document.getElementById("username").textContent = userData.username;
        document.getElementById("rol").textContent = userData.rol;
    } else {
        alert("Datos del usuario no encontrados. Inicie sesión.");
    }
});

document.addEventListener("DOMContentLoaded", () => {
    cargarEspecialidades();
});

document.getElementById("especialidad").addEventListener("change", function () {
    const especialidadId = this.value;
    cargarMedicosPorEspecialidad(especialidadId);
});

function logout() {
    localStorage.removeItem("data-user");
    window.location.href = "index.html";
}

function mostrarSeccion(id) {
    const secciones = ["containerGestiondeAgendamiento", "containerAfiliacion", "containerGestionExamenes"];
    secciones.forEach(sec => {
        const seccion = document.getElementById(sec);
        if (seccion) {
            seccion.classList.add("container-hidden");
        }
    });

    const activa = document.getElementById(id);
    if (activa) {
        activa.classList.remove("container-hidden");
    } else {
        console.warn("No se encontró la sección con id:", id);
    }
}

function buscarPacientePorIdentificacion() {
    const identificacion = document.getElementById("buscarIdentificacion").value;
    if (!identificacion) {
        alert("Por favor ingrese una identificación válida.");
        return;
    }

    fetch(`http://localhost:8080/paciente/identificacion/${identificacion}`)
        .then(res => {
            if (!res.ok) throw new Error("Paciente no encontrado");
            return res.json();
        })
        .then(data => {
            pacienteSeleccionado = data; // Guardamos el paciente encontrado
            document.getElementById("pacienteNombre").textContent = `${data.nombrePaci} ${data.apellidoPaci}`;
            cargarCitasDelPaciente(data.id); // Trae citas del paciente
        })
        .catch(err => {
            console.error(err);
            alert("Paciente no encontrado.");
            document.getElementById("pacienteNombre").textContent = "";
            pacienteSeleccionado = null;
        });
}

function agendarCita() {
    const form = document.getElementById("formCita");

    if (!pacienteSeleccionado || !pacienteSeleccionado.id) {
        alert("Debe buscar un paciente antes de agendar la cita.");
        return;
    }

    const cita = {
        idPaciente: pacienteSeleccionado.id,
        idMedico: parseInt(document.getElementById("idMedico").value),
        idEspecialidad: parseInt(document.getElementById("especialidad").value),
        fecha: document.getElementById("fecha").value,
        hora: document.querySelector('input[name="hora"]:checked')?.value,
        estado: document.querySelector('input[name="accion"]:checked').value.toUpperCase()
    };

    if (!cita.hora) {
        alert("Debe seleccionar una hora.");
        return;
    }

    fetch("http://localhost:8080/cita", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cita)
    })
        .then(res => {
            if (!res.ok) throw new Error("Error al agendar la cita");
            return res.json();
        })
        .then(() => {
            alert("¡Cita agendada exitosamente!");
            form.reset();
            generarBotonesDeHoras(); // limpia la disponibilidad
            cargarCitasDelPaciente(pacienteSeleccionado.id);
        })
        .catch(err => {
            console.error("Error al enviar cita:", err);
            alert("Error al conectar con el servidor.");
        });
}

function obtenerCitasPorPaciente(idPaciente) {
    fetch(`http://localhost:8080/cita/paciente/${idPaciente}`)
        .then(res => res.json())
        .then(citas => {
            const tbody = citas.map(cita => `
                <tr>
                    <td>${cita.fecha}</td>
                    <td>${cita.hora}</td>
                    <td>${cita.estado}</td>
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="cargarCitaParaReprogramar(${cita.id})">Editar</button>
                        <button class="btn btn-danger btn-sm" onclick="cancelarCita(${cita.id})">Cancelar</button>
                    </td>
                </tr>`).join("");

            document.getElementById("tabla-citas").innerHTML = `
                <table class="table table-bordered">
                    <thead><tr><th>Fecha</th><th>Hora</th><th>Estado</th><th>Acciones</th></tr></thead>
                    <tbody>${tbody}</tbody>
                </table>`;
        });
}

function editarCita(idCita) {
    fetch(`http://localhost:8080/cita/${idCita}`)
        .then(res => res.json())
        .then(cita => {
            citaEditandoId = cita.id;
            document.getElementById("fecha").value = cita.fecha;
            document.querySelector(`input[name="hora"][value="${cita.hora}"]`)?.click();
            document.getElementById("especialidad").value = cita.idEspecialidad;
            cargarMedicosPorEspecialidad(cita.idEspecialidad); // Para asegurar que el médico se cargue
            setTimeout(() => {
                document.getElementById("idMedico").value = cita.idMedico;
            }, 300); // Espera a que se carguen los médicos

            document.querySelector(`input[name="accion"][value="REPROGRAMAR"]`)?.click();

            // Cambiar botón a modo "Actualizar"
            const btn = document.querySelector("button[onclick='agendarCita()']");
            btn.textContent = "Actualizar";
            btn.onclick = actualizarCita;
        })
        .catch(err => {
            console.error("Error cargando cita:", err);
            alert("No se pudo cargar la cita para edición.");
        });
}

function actualizarCita() {
    const form = document.getElementById("formCita");

    if (!pacienteSeleccionado || !pacienteSeleccionado.id) {
        alert("Debe buscar un paciente antes.");
        return;
    }

    const cita = {
        id: citaEditandoId,
        idPaciente: pacienteSeleccionado.id,
        idMedico: parseInt(document.getElementById("idMedico").value),
        idEspecialidad: parseInt(document.getElementById("especialidad").value),
        fecha: document.getElementById("fecha").value,
        hora: document.querySelector('input[name="hora"]:checked')?.value,
        estado: document.querySelector('input[name="accion"]:checked').value.toUpperCase()
    };

    fetch(`http://localhost:8080/cita/${cita.id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cita)
    })
        .then(res => {
            if (!res.ok) throw new Error("Error actualizando la cita");
            return res.json();
        })
        .then(() => {
            alert("¡Cita actualizada exitosamente!");
            form.reset();
            generarBotonesDeHoras();
            cargarCitasDelPaciente(pacienteSeleccionado.id);
            citaEditandoId = null;

            // Restaurar botón
            const btn = document.querySelector("button[onclick='actualizarCita()']");
            btn.textContent = "Aceptar";
            btn.setAttribute("onclick", "agendarCita()");
        })
        .catch(err => {
            console.error("Error al actualizar cita:", err);
            alert("Error al conectar con el servidor.");
        });
}

function cancelarCita(idCita) {
    if (confirm("¿Está seguro de cancelar esta cita?")) {
        fetch(`http://localhost:8080/cita/${idCita}`, {
            method: "DELETE"
        })
            .then(() => {
                alert("Cita cancelada correctamente.");
                cargarCitasDelPaciente(pacienteSeleccionado.id);
            })
            .catch(err => {
                console.error("Error cancelando cita:", err);
                alert("No se pudo cancelar la cita.");
            });
    }
}

function cargarCitasDelPaciente(idPaciente) {
    fetch(`http://localhost:8080/cita/paciente/${idPaciente}`)
        .then(response => response.json())
        .then(data => {
            const tabla = document.getElementById("tabla-citas");
            if (!data.length) {
                tabla.innerHTML = "<p class='text-muted'>No hay citas registradas.</p>";
                return;
            }

            let html = `
                <table class="table table-bordered mt-2">
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Hora</th>
                            <th>Especialidad</th>
                            <th>Médico</th>
                            <th>Consultorio</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
            `;

            data.forEach(cita => {
                html += `
                    <tr>
                        <td>${cita.fecha}</td>
                        <td>${cita.hora}</td>
                        <td>${cita.nombreEspecialidad}</td>
                        <td>${cita.nombreMedico}</td>
                        <td>${cita.consultorio} - ${cita.ubicacionConsultorio}</td>
                        <td>
                            <button class="btn btn-warning btn-sm" onclick="editarCita(${cita.id})">Reprogramar</button>
                            <button class="btn btn-danger btn-sm" onclick="cancelarCita(${cita.id})">Cancelar</button>
                        </td>
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

function generarBotonesDeHoras(horasOcupadas = []) {
    const container = document.getElementById("horas-container");
    container.innerHTML = "";

    const inicio = 6; // 6 AM
    const fin = 20; // 8 PM
    const intervalos = 30; // minutos

    for (let hora = inicio; hora <= fin; hora++) {
        for (let min = 0; min < 60; min += intervalos) {
            const horaFormateada = `${hora.toString().padStart(2, "0")}:${min === 0 ? "00" : "30"}`;

            const estaOcupada = horasOcupadas.includes(horaFormateada);

            const col = document.createElement("div");
            col.className = "col-4"; // 3 columnas

            const idInput = `hora-${hora}-${min}`;
            const radio = `
                <input type="radio" class="btn-check" name="hora" id="${idInput}" value="${horaFormateada}" ${estaOcupada ? "disabled" : ""} required>
                <label class="btn btn-outline-primary w-100" for="${idInput}">${horaFormateada}</label>
            `;
            col.innerHTML = radio;
            container.appendChild(col);
        }
    }
}

function cargarEspecialidades() {
    fetch("http://localhost:8080/especialidad")
        .then(res => res.json())
        .then(data => {
            const select = document.getElementById("especialidad");
            select.innerHTML = '<option disabled selected>Seleccione</option>';
            data.forEach(esp => {
                const option = document.createElement("option");
                option.value = esp.id;
                option.textContent = esp.nombreEspecialidad;
                select.appendChild(option);
            });
        });
}

function cargarMedicosPorEspecialidad(especialidadId) {
    fetch(`http://localhost:8080/medico/especialidad/${especialidadId}`)
        .then(res => res.json())
        .then(medicos => {
            const selectMedico = document.getElementById("idMedico");
            selectMedico.innerHTML = "<option value=''>Seleccione</option>";
            medicos.forEach(medico => {
                const option = document.createElement("option");
                option.value = medico.id;
                option.textContent = medico.nombreMedico + " " + medico.apellidosMedicos;
                selectMedico.appendChild(option);
            });
        })
        .catch(err => {
            console.error("Error cargando médicos:", err);
        });
}