document.addEventListener("DOMContentLoaded", function () {
    cargarCitasDelPaciente();
    cargarEspecialidades();

    document.getElementById("especialidad").addEventListener("change", function () {
        const especialidadId = this.value;
        cargarMedicosPorEspecialidad(especialidadId);
    });

    const userData = JSON.parse(localStorage.getItem("data-user"));
    if (userData && userData.rol === "Perfil Usuario") {
        const reprogramar = document.getElementById("reprogramarCita");
        const cancelar = document.getElementById("cancelarCita");

        if (reprogramar) reprogramar.parentElement.classList.add("d-none");
        if (cancelar) cancelar.parentElement.classList.add("d-none");
    }

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
            idMedico: parseInt(document.getElementById("idMedico").value),
            idEspecialidad: parseInt(document.getElementById("especialidad").value),
            fecha: document.getElementById("fecha").value,
            hora: document.getElementById("hora").value,
            estado: document.querySelector('input[name="accion"]:checked').value.toUpperCase()
        };

        console.log("Enviando cita:", cita);

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
                cargarCitasDelPaciente(); // Refrescar tabla
            })
            .catch(err => {
                console.error("Error al enviar cita:", err);
                alert("Error al conectar con el servidor.");
            });
    });
});

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

document.getElementById("fecha").addEventListener("change", function () {
    const fechaSeleccionada = this.value;
    const horaSelect = document.getElementById("hora");
    horaSelect.innerHTML = '<option value="">Cargando...</option>';

    fetch(`http://localhost:8080/cita/fecha/${fechaSeleccionada}`)
        .then(res => res.json())
        .then(citas => {
            const horasOcupadas = citas.map(c => c.hora);
            horaSelect.innerHTML = '<option value="">Seleccione una hora</option>';

            const inicio = 6;
            const fin = 20;
            for (let h = inicio; h <= fin; h++) {
                for (let m of [0, 30]) {
                    const hora = `${h.toString().padStart(2, '0')}:${m.toString().padStart(2, '0')}`;
                    if (!horasOcupadas.includes(hora)) {
                        const option = document.createElement("option");
                        option.value = hora;
                        option.textContent = hora;
                        horaSelect.appendChild(option);
                    }
                }
            }

            if (horaSelect.options.length === 1) {
                horaSelect.innerHTML = '<option value="">No hay horarios disponibles</option>';
            }
        })
        .catch(err => {
            console.error("Error al consultar franjas:", err);
            horaSelect.innerHTML = '<option value="">Error al cargar horarios</option>';
        });
});

function cargarCitasDelPaciente() {
    const userData = JSON.parse(localStorage.getItem("data-user"));
    if (!userData || !userData.id) return;

    fetch(`http://localhost:8080/cita/paciente/${userData.id}`)
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

document.getElementById("fecha").addEventListener("change", function () {
    const fechaSeleccionada = this.value;

    fetch(`http://localhost:8080/cita/fecha/${fechaSeleccionada}`)
        .then(res => res.json())
        .then(data => {
            const horasOcupadas = data.map(cita => cita.hora);
            generarBotonesDeHoras(horasOcupadas);
        })
        .catch(err => {
            console.error("Error al consultar franjas:", err);
        });
});