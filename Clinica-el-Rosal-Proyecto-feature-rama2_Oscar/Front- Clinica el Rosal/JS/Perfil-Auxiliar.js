let citaEditandoId = null;
let pacienteSeleccionado = null;
document.getElementById("fecha").addEventListener("change", actualizarHorasDisponibles);
document.getElementById("idMedico").addEventListener("change", actualizarHorasDisponibles);

function actualizarHorasDisponibles() {
    const fecha = document.getElementById("fecha").value;
    const idMedico = document.getElementById("idMedico").value;
    if (!fecha || !idMedico) return;

    fetch(`http://localhost:8080/cita/medico/${idMedico}/fecha/${fecha}`)
        .then(res => res.json())
        .then(data => {
            const horasOcupadas = data.map(c => c.hora);
            generarBotonesDeHoras(horasOcupadas);
        })
        .catch(err => console.error("Error al obtener horas ocupadas:", err));
}
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

    const boton = document.getElementById("btn-agendar");
    boton.addEventListener("click", agendarCita);
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
    const horaSeleccionada = document.querySelector('input[name="hora"]:checked');
    const accionSeleccionada = document.querySelector('input[name="accion"]:checked');

    if (!pacienteSeleccionado || !pacienteSeleccionado.id) {
        alert("Debe buscar un paciente primero.");
        return;
    }

    if (!horaSeleccionada) {
        alert("Debe seleccionar una hora disponible.");
        return;
    }

    if (!accionSeleccionada) {
        alert("Debe seleccionar una acción.");
        return;
    }

    const cita = {
        idPaciente: pacienteSeleccionado.id,
        idMedico: parseInt(document.getElementById("idMedico").value),
        idEspecialidad: parseInt(document.getElementById("especialidad").value),
        fecha: document.getElementById("fecha").value,
        hora: horaSeleccionada.value,
        estado: accionSeleccionada.value.toUpperCase()
    };

    fetch("http://localhost:8080/cita", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(cita)
    })
        .then(res => {
            if (!res.ok) throw new Error("Error al guardar la cita.");
            return res.json();
        })
        .then(() => {
            alert("Cita agendada correctamente.");
            form.reset();
            generarBotonesDeHoras(); // limpia visualmente
            cargarCitasDelPaciente(pacienteSeleccionado.id);
        })
        .catch(err => {
            console.error("Error:", err);
            alert("No se pudo agendar la cita.");
        });
}



function editarCita(idCita) {
    fetch(`http://localhost:8080/cita/${idCita}`)
        .then(res => res.json())
        .then(data => {
            citaEditandoId = data.id;
            document.getElementById("fecha").value = data.fecha;

            document.getElementById("especialidad").value = data.idEspecialidad;
            cargarMedicosPorEspecialidad(data.idEspecialidad);

            setTimeout(() => {
                document.getElementById("idMedico").value = data.idMedico;
                generarBotonesDeHoras(); // Para generar los botones si no estaban visibles
                const radioHora = document.querySelector(`input[name="hora"][value="${data.hora}"]`);
                if (radioHora) radioHora.checked = true;
            }, 300);

            const radioAccion = document.querySelector('input[name="accion"][value="REPROGRAMAR"]');
            if (radioAccion) radioAccion.checked = true;

            const btn = document.getElementById("btn-agendar");
            btn.textContent = "Actualizar";

            const nuevoBoton = btn.cloneNode(true);
            btn.parentNode.replaceChild(nuevoBoton, btn);
            nuevoBoton.addEventListener("click", actualizarCita);
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

    const horaSeleccionada = document.querySelector('input[name="hora"]:checked');
    if (!horaSeleccionada) {
        alert("Debe seleccionar una hora.");
        return;
    }

    const accionRadio = document.querySelector('input[name="accion"]:checked');
    if (!accionRadio) {
        alert("Debe seleccionar una acción.");
        return;
    }

    const cita = {
        id: citaEditandoId,
        idPaciente: pacienteSeleccionado.id,
        idMedico: parseInt(document.getElementById("idMedico").value),
        idEspecialidad: parseInt(document.getElementById("especialidad").value),
        fecha: document.getElementById("fecha").value,
        hora: horaSeleccionada.value,
        estado: accionRadio.value.toUpperCase()
    };

    console.log("Datos a enviar al backend (PUT):", cita);  // 🔍 AQUÍ LO VES

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

document.getElementById("fecha").addEventListener("change", function () {
    const fecha = this.value;
    const idMedico = document.getElementById("idMedico").value;

    if (!fecha || !idMedico) return;

    fetch(`http://localhost:8080/cita/medico/${idMedico}/fecha/${fecha}`)
        .then(res => res.json())
        .then(data => {
            const horasOcupadas = data.map(c => c.hora);
            generarBotonesDeHoras(horasOcupadas);
        })
        .catch(err => console.error("Error al obtener horas ocupadas:", err));
});

function generarBotonesDeHoras(horasOcupadas = []) {
    const container = document.getElementById("horas-container");
    container.innerHTML = "";

    const inicio = 6;
    const fin = 20;

    for (let h = inicio; h <= fin; h++) {
        for (let m = 0; m < 60; m += 30) {
            const horaFormateada = `${h.toString().padStart(2, '0')}:${m === 0 ? '00' : '30'}`;
            const estaOcupada = horasOcupadas.includes(horaFormateada);

            const idInput = `hora-${h}-${m}`;
            const col = document.createElement("div");
            col.className = "col-4";

            col.innerHTML = `
                <input type="radio" class="btn-check" name="hora" id="${idInput}" value="${horaFormateada}" ${estaOcupada ? 'disabled' : ''} required>
                <label class="btn btn-outline-primary w-100" for="${idInput}">${horaFormateada}</label>
            `;
            container.appendChild(col);
        }
    }
}

function cargarEspecialidades() {
    fetch("http://localhost:8080/especialidad")
        .then(res => res.json())
        .then(cita => {
            const select = document.getElementById("especialidad");
            select.innerHTML = '<option disabled selected>Seleccione</option>';
            cita.forEach(esp => {
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

document.getElementById("formAfiliacion").addEventListener("submit", function (e) {
    e.preventDefault();

    const afiliacion = {
        nombre: document.getElementById("nombres").value,
        apellido: document.getElementById("apellidos").value,
        tipoIdentificacion: document.getElementById("tipoIdentificacion").value,
        identificacion: document.getElementById("Identificacion").value,
        fechaNacimiento: document.getElementById("fechaNacimiento").value,
        telefono: document.getElementById("telefono").value,
        correo: document.getElementById("correo").value,
        direccion: document.getElementById("direccion").value,
        municipio: document.getElementById("Municipio").value,
        tipoAfiliacion: document.getElementById("tipoAfiliacion").value,
        seguro: document.getElementById("Seguro").value,
        rol: document.getElementById("rol").value
    };

    fetch("http://localhost:8080/afiliacion", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(afiliacion)
    })
        .then(response => {
            if (!response.ok) throw new Error("No se pudo guardar la afiliación.");
            return response.json();
        })
        .then(data => {
            alert("Afiliación registrada con éxito.");
            document.getElementById("formAfiliacion").reset();
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Error al registrar afiliación.");
        });
});



// Acción del botón enviar del formulario de afiliación
const formAfiliacion = document.getElementById("formAfiliacion");

formAfiliacion.addEventListener("submit", function (e) {
    e.preventDefault();

    const afiliacion = {
        nombre: document.getElementById("nombres").value,
        apellido: document.getElementById("apellidos").value,
        tipoIdentificacion: document.getElementById("tipoIdentificacion").value,
        identificacion: document.getElementById("Identificacion").value,
        fechaNacimiento: document.getElementById("fechaNacimiento").value,
        telefono: document.getElementById("telefono").value,
        correo: document.getElementById("correo").value,
        direccion: document.getElementById("direccion").value,
        municipio: document.getElementById("Municipio").value,
        tipoAfiliacion: document.getElementById("tipoAfiliacion").value,
        seguro: document.getElementById("Seguro").value,
        rol: document.getElementById("rol").value
    };

    // Primero crear afiliación
    fetch("http://localhost:8080/afiliacion", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(afiliacion)
    })
        .then(response => {
            if (!response.ok) throw new Error("No se pudo guardar la afiliación.");
            return response.json();
        })
        .then(dataAfiliacion => {
            alert("Afiliación registrada con éxito.");

            ///////////// seccion afiliacion /////////////
            let usuarioEditandoId = null;

            function crearUsuarioDesdeAfiliacion() {
                const usuario = construirObjetoUsuario();

                if (!usuario) return;

                fetch("http://localhost:8080/usuario", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(usuario)
                })
                    .then(res => {
                        if (!res.ok) throw new Error("No se pudo crear el usuario.");
                        return res.json();
                    })
                    .then(() => {
                        alert("Usuario creado exitosamente.");
                        document.getElementById("formAfiliacion").reset();
                    })
                    .catch(error => {
                        console.error("Error creando usuario:", error);
                        alert("Error al crear usuario.");
                    });
            }

            function buscarUsuarioPorCedula() {
                const identificacion = document.getElementById("buscarUsuarioCedula").value;

                if (!identificacion) {
                    alert("Por favor ingrese una cédula para buscar.");
                    return;
                }

                fetch(`http://localhost:8080/usuario/identificacion/${identificacion}`)
                    .then(res => {
                        if (!res.ok) throw new Error("Usuario no encontrado.");
                        return res.json();
                    })
                    .then(data => {
                        usuarioEditandoId = data.id;
                        llenarFormularioAfiliacion(data);
                        alert("Usuario cargado para edición.");
                    })
                    .catch(error => {
                        console.error("Error buscando usuario:", error);
                        alert("Usuario no encontrado.");
                    });
            }

            function editarUsuarioExistente() {
                if (!usuarioEditandoId) {
                    alert("Debe buscar un usuario antes de editar.");
                    return;
                }

                const usuario = construirObjetoUsuario();
                if (!usuario) return;

                usuario.id = usuarioEditandoId;

                fetch(`http://localhost:8080/usuario/${usuarioEditandoId}`, {
                    method: "PUT",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(usuario)
                })
                    .then(res => {
                        if (!res.ok) throw new Error("Error al actualizar el usuario.");
                        return res.json();
                    })
                    .then(() => {
                        alert("Usuario actualizado correctamente.");
                        document.getElementById("formAfiliacion").reset();
                        usuarioEditandoId = null;
                    })
                    .catch(error => {
                        console.error("Error actualizando usuario:", error);
                        alert("Error al actualizar.");
                    });
            }

            function construirObjetoUsuario() {
                const nombre = document.getElementById("nombres").value.trim();
                const apellido = document.getElementById("apellidos").value.trim();
                const tipoIdentificacion = document.getElementById("tipoIdentificacion").value;
                const identificacion = document.getElementById("Identificacion").value.trim();
                const fechaNacimiento = document.getElementById("fechaNacimiento").value;
                const rol = document.getElementById("rol").value;
                const telefono = document.getElementById("telefono").value.trim();
                const correo = document.getElementById("correo").value.trim();
                const direccion = document.getElementById("direccion").value.trim();
                const municipio = document.getElementById("Municipio").value.trim();
                const tipoAfiliacion = document.getElementById("tipoAfiliacion").value;
                const seguro = document.getElementById("Seguro").value;

                if (!nombre || !apellido || !identificacion || !correo) {
                    alert("Por favor complete todos los campos obligatorios.");
                    return null;
                }

                return {
                    nombre: nombre,
                    apellido: apellido,
                    tipoIdentificacion: tipoIdentificacion,
                    identificacion: identificacion,
                    fechaNacimiento: fechaNacimiento,
                    telefono: telefono,
                    correo: correo,
                    direccion: direccion,
                    municipio: municipio,
                    tipoAfiliacion: tipoAfiliacion,
                    seguro: seguro,
                    rol: rol,
                    login: correo,
                    password: identificacion
                };
            }

            function llenarFormularioAfiliacion(usuario) {
                document.getElementById("nombres").value = usuario.nombre || "";
                document.getElementById("apellidos").value = usuario.apellido || "";
                document.getElementById("tipoIdentificacion").value = usuario.tipoIdentificacion || "";
                document.getElementById("Identificacion").value = usuario.identificacion || "";
                document.getElementById("fechaNacimiento").value = usuario.fechaNacimiento || "";
                document.getElementById("telefono").value = usuario.telefono || "";
                document.getElementById("correo").value = usuario.correo || "";
                document.getElementById("direccion").value = usuario.direccion || "";
                document.getElementById("Municipio").value = usuario.municipio || "";
                document.getElementById("tipoAfiliacion").value = usuario.tipoAfiliacion || "";
                document.getElementById("Seguro").value = usuario.seguro || "";
                document.getElementById("rol").value = usuario.rol || "";
            }

            const usuario = {
                login: afiliacion.correo,
                password: afiliacion.identificacion,
                idRol: rolToId(afiliacion.rol),
                idPaciente: afiliacion.rol === "PACIENTE" ? dataAfiliacion.id : null,
                idAuxiliar: afiliacion.rol === "AUXILIAR" ? dataAfiliacion.id : null,
                idMedico: afiliacion.rol === "MEDICO" ? dataAfiliacion.id : null,
                idFarmaceutico: afiliacion.rol === "FARMACEUTICO" ? dataAfiliacion.id : null
            };

            return fetch("http://localhost:8080/usuario", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(usuario)
            });
        })
        .then(response => {
            if (!response.ok) throw new Error("No se pudo crear el usuario.");
            alert("Usuario creado exitosamente.");
            formAfiliacion.reset();
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Error en el proceso de afiliación y creación de usuario.");
        });
});

function rolToId(rol) {
    switch (rol) {
        case "PACIENTE": return 1;
        case "AUXILIAR": return 2;
        case "MEDICO": return 3;
        case "FARMACEUTICO": return 4;
        default: return 0;
    }
}