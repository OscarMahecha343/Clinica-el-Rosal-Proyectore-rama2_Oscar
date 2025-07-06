
let citaEditandoId = null;
let pacienteSeleccionado = null;


document.getElementById("fecha").addEventListener("change", actualizarHorasDisponibles);
document.getElementById("idMedico").addEventListener("change", actualizarHorasDisponibles);


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


document.addEventListener("DOMContentLoaded", () => {
    cargarEspecialidades();

    const boton = document.getElementById("btn-agendar");
    boton.addEventListener("click", agendarCita);
});

document.getElementById("especialidad").addEventListener("change", function () {
    const especialidadId = this.value;
    cargarMedicosPorEspecialidad(especialidadId);
});

document.addEventListener("DOMContentLoaded", () => {
    const campo = document.getElementById("buscarIdentificacionAfiliacion");
    if (!campo) {
        console.error("❌ Campo #buscarIdentificacion no fue encontrado al cargar la página");
    } else {
        console.log("✅ Campo #buscarIdentificacion detectado correctamente al cargar");
    }
});

function limpiarFormularioAfiliacion() {
    const campos = [
        "nombre", "apellido", "tipo_identificacion", "identificacionPaciente", "genero", "fecha_nacimiento",
        "telefono", "correo", "direccion", "id_municipio", "tipo_afiliacion", "id_seguro",
        "grupo_sangineo", "alergias", "tipo_de_alergia"
    ];

    campos.forEach(id => {
        const campo = document.getElementById(id);
        if (campo) {
            if (campo.tagName === "SELECT" || campo.tagName === "INPUT") {
                campo.value = "";
            }
        }
    });

    console.log("🧹 Formulario de afiliación limpiado correctamente");
}

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

        // Si es la sección de afiliación, conecta el botón
        if (id === "containerAfiliacion") {
            const btnCrear = document.getElementById("btnCrearUsuario");
            if (btnCrear) {
                btnCrear.removeEventListener("click", crearAfiliacion);
                btnCrear.addEventListener("click", crearAfiliacion);
            }
        }
    } else {
        console.warn("No se encontró la sección con id:", id);
    }
}



function buscarPacientePorIdentificacion(idCampo) {
    const input = document.getElementById(idCampo);
    const identificacion = input.value.trim();
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
            console.log("🔍 Paciente encontrado:", data);
            pacienteSeleccionado = data;


            const nombreLabel = document.getElementById("nombrePacienteMostrar");
            if (nombreLabel) {
                nombreLabel.textContent = data.nombrePaci + " " + data.apellidoPaci;
            }

            if (idCampo === "buscarIdentificacionAfiliacion") {
                llenarFormularioAfiliacionConDatos(data);
            }

            if (typeof cargarCitasDelPaciente === "function") {
                cargarCitasDelPaciente(data.id);
            }
        })
        .catch(err => {
            console.error("❌ Error en la búsqueda:", err);
            alert("Paciente no encontrado.");

            const nombreLabel = document.getElementById("nombrePacienteMostrar");
            if (nombreLabel) nombreLabel.textContent = "";

            pacienteSeleccionado = null;
        });
}


function llenarFormularioAfiliacionConDatos(paciente) {
    document.getElementById("pacienteId").value = paciente.id || ''

    const mapeo = {
        nombre: paciente.nombrePaci,
        apellido: paciente.apellidoPaci,
        tipo_identificacion: paciente.tipoIdentificacion,
        identificacionPaciente: paciente.identificacion,
        genero: paciente.genero,
        fecha_nacimiento: paciente.fechaNacimiento,
        telefono: paciente.telefono,
        correo: paciente.correo,
        direccion: paciente.direccion,
        id_municipio: paciente.idMunicipio,
        tipo_afiliacion: paciente.tipoAfiliacion || '',
        id_seguro: paciente.idSeguro,
        grupo_sangineo: paciente.grupoSangineo,
        alergias: paciente.alergias,
        tipo_de_alergia: paciente.tipoAlergia
    };

    for (const id in mapeo) {
        const campo = document.getElementById(id);
        if (campo) {
            campo.value = mapeo[id];
            console.log(`✅ Campo ${id} cargado con valor: ${mapeo[id]}`);
        } else {
            console.warn(`⚠️ Campo ${id} no encontrado en el DOM`);
        }
    }

    const hiddenId = document.getElementById("pacienteId");
    if (hiddenId) {
        hiddenId.value = paciente.id;
    } else {
        console.log("ℹ️ Campo oculto 'pacienteId' no está presente, se recomienda agregarlo si usarás actualización.");
    }
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

            const btn = document.getElementById("btn-agendar");
            btn.textContent = "Actualizar";

            btn.replaceWith(btn.cloneNode(true));
            const nuevoBtn = document.getElementById("btn-agendar");
            nuevoBtn.textContent = "Actualizar";
            nuevoBtn.addEventListener("click", actualizarCita);
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

    console.log("Datos a enviar al backend (PUT):", cita);

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

async function cargarHorasOcupadas() {
    const fechaSeleccionada = document.getElementById("fecha").value;
    if (!fechaSeleccionada) return;

    try {
        const response = await fetch(`http://localhost:8080/cita/fecha/${fechaSeleccionada}`);
        if (!response.ok) throw new Error("No se pudieron cargar las citas.");

        const citas = await response.json();
        const horasOcupadas = citas.map(c => c.hora.slice(0, 5));

        generarBotonesDeHoras(horasOcupadas);

    } catch (error) {
        console.error("Error al consultar franjas:", error);
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



///////////// seccion afiliacion /////////////

document.addEventListener("DOMContentLoaded", function () {
    cargarMunicipios();
    cargarSeguros();
});

function safeValue(id) {
    const el = document.getElementById(id);
    if (!el) {
        console.error(`Elemento con id '${id}' no encontrado`);
        return "";
    }
    return el.value.trim();
}

function construirObjetoPaciente() {
    console.log("🩺 Validando campos para paciente...");

    const campos = [
        "pacienteId", "nombre", "apellido", "tipo_identificacion", "identificacionPaciente", "genero", "fecha_nacimiento",
        "telefono", "correo", "direccion", "id_municipio", "tipo_afiliacion", "id_seguro",
        "grupo_sangineo", "alergias", "tipo_de_alergia"
    ];

    for (let id of campos) {
        const el = document.getElementById(id);
        if (!el) {
            console.error(`❌ Campo no encontrado en el DOM: ${id}`);
            throw new Error(`Campo '${id}' no encontrado`);
        }
        const valor = el.value?.trim();
        if (!valor && el.required !== false) {
            console.warn(`⚠️ Campo '${id}' está vacío o solo contiene espacios`);
        } else {
            console.log(`✅ Campo ${id} cargado correctamente`);
        }
    }

    const identificacionValue = document.getElementById("identificacionPaciente").value.trim();
    if (!identificacionValue) {
        console.error("❌ Campo identificacionPaciente está vacío");
        alert("El campo de identificación es obligatorio.");
        throw new Error("Campo identificacionPaciente vacío");
    }

    // Construcción del objeto
    const paciente = {
        id: parseInt(document.getElementById("pacienteId")?.value) || null,
        nombrePaci: document.getElementById("nombre").value.trim(),
        apellidoPaci: document.getElementById("apellido").value.trim(),
        tipoIdentificacion: document.getElementById("tipo_identificacion").value.trim(),
        identificacion: identificacionValue,
        genero: document.getElementById("genero").value.trim(),
        fechaNacimiento: document.getElementById("fecha_nacimiento").value.trim(),
        telefono: document.getElementById("telefono").value.trim(),
        correo: document.getElementById("correo").value.trim(),
        direccion: document.getElementById("direccion").value.trim(),
        idMunicipio: parseInt(document.getElementById("id_municipio").value),
        tipoAfiliacion: document.getElementById("tipo_afiliacion").value.trim(),
        idSeguro: parseInt(document.getElementById("id_seguro").value),
        grupoSangineo: document.getElementById("grupo_sangineo").value?.trim() || null,
        alergias: document.getElementById("alergias").value?.trim() || null,
        tipoAlergia: document.getElementById("tipo_de_alergia").value?.trim() || null
    };

    console.log("📦 Objeto paciente preparado:", paciente);
    return paciente;
}



function crearAfiliacion() {
    console.log("🟢 Botón Crear Usuario presionado");

    let paciente;
    try {
        paciente = construirObjetoPaciente();
    } catch (err) {
        console.error("❌ Error en validación de campos:", err.message);
        alert("⚠️ Verifica los campos antes de continuar.");
        return;
    }

    console.log("📦 Enviando al backend:", paciente);

    fetch("http://localhost:8080/paciente", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(paciente)
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(errorText => {
                    console.group("❌ Error al guardar paciente:");
                    console.error("📍 Código de estado HTTP:", response.status);
                    console.warn("📝 Respuesta del backend:");
                    console.error("❌ Respuesta del backend:", errorText); // <- CORREGIDO
                    console.groupEnd();
                    throw new Error("No se pudo guardar el paciente. Verifica los detalles en consola.");
                });
            }
            return response.json();
        })
        .then(data => {
            if (!data || !data.id) {
                throw new Error("Respuesta inválida del servidor. El ID no fue retornado.");
            }

            alert("✅ Paciente registrado exitosamente con ID: " + data.id);
            document.getElementById("formAfiliacion").reset();
        })
        .catch(error => {
            if (error.message.includes("paciente")) {
                alert("⚠️ Ya existe un paciente con esa identificación.");
            } else {
                console.warn("⚠️ Error capturado en catch:", error.message || error);
                alert("❌ Error al guardar paciente. Revisa la consola o comunícate con soporte.");
            }
        });
}

function actualizarAfiliacion() {
    console.log("🔄 Botón Actualizar presionado");

    let paciente;
    try {
        paciente = construirObjetoPaciente();
    } catch (err) {
        console.error("❌ Error al construir el objeto paciente:", err.message);
        alert("⚠️ Verifica los campos antes de actualizar.");
        return;
    }

    if (!paciente.identificacion) {
        alert("⚠️ No se puede actualizar sin una identificación válida.");
        return;
    }

    console.log("📦 Objeto a actualizar:", paciente);

    fetch(`http://localhost:8080/paciente/identificacion/${paciente.identificacion}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(paciente)
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(errorText => {
                    console.error("❌ Error del backend:", errorText);
                    throw new Error("No se pudo actualizar el paciente.");
                });
            }
            return response.json();
        })
        .then(data => {
            alert("✅ Paciente actualizado exitosamente.");
            limpiarFormularioAfiliacion();
            console.log("🟢 Respuesta del servidor:", data);
        })
        .catch(error => {
            console.warn("⚠️ Error al actualizar paciente:", error.message || error);
            alert("❌ Falló la actualización del paciente.");
        });
}

function cargarMunicipios() {
    fetch("http://localhost:8080/municipio")
        .then(res => res.json())
        .then(data => {
            const select = document.getElementById("id_municipio");
            select.innerHTML = '<option disabled selected>Seleccione un municipio</option>';
            data.forEach(muni => {
                const opt = document.createElement("option");
                opt.value = muni.id;
                opt.textContent = muni.nombreMunicipio;
                select.appendChild(opt);
            });
        })
        .catch(err => console.error("Error al cargar municipios:", err));
}

function cargarSeguros() {
    fetch("http://localhost:8080/seguro")
        .then(res => res.json())
        .then(data => {
            const select = document.getElementById("id_seguro");
            select.innerHTML = '<option disabled selected>Seleccione un seguro</option>';
            data.forEach(seguro => {
                const opt = document.createElement("option");
                opt.value = seguro.id;
                opt.textContent = seguro.nombre;
                select.appendChild(opt);
            });
        })
        .catch(err => console.error("Error al cargar seguros:", err));
}

document.addEventListener("DOMContentLoaded", function () {
    cargarMunicipios();
    cargarSeguros();
    cargarTiposDeExamen();
});

///////examenes//////

document.addEventListener("DOMContentLoaded", () => {
    const user = JSON.parse(localStorage.getItem("data-user"));
    if (user && user.rol === "Perfil Auxiliar") {
        const idAuxiliar = user.idAuxiliar || user.id;
        document.getElementById("id_auxiliar").value = idAuxiliar;
        document.getElementById("nombre_auxiliar").value = `${user.nombre} ${user.apellido}`;
        console.log("👨‍⚕️ Auxiliar activo:", user.nombre + " " + user.apellido);
    } else {
        console.warn("⚠️ No se encontró un auxiliar activo en localStorage.");
    }

    cargarTiposDeExamen();
});

function cargarTiposDeExamen() {
    fetch("http://localhost:8080/detalle_examenes/tipo")
        .then(res => res.json())
        .then(data => {
            const select = document.getElementById("idTipoExamen");
            select.innerHTML = '<option value="" disabled selected>Seleccione tipo</option>';
            data.forEach(e => {
                const option = document.createElement("option");
                option.value = e.id;
                option.textContent = e.nombre;
                select.appendChild(option);
            });
        })
        .catch(err => console.error("❌ Error tipos de examen:", err));
}

function buscarIdentificacionExamen() {
    const ced = document.getElementById("buscarIdentificacionExamen").value.trim();
    if (!ced) return alert("Ingrese identificación válida");
    console.log("🩺 idPaciente en el form:", document.getElementById("id_paciente").value);

    // Paso 1: obtener paciente
    fetch(`http://localhost:8080/paciente/identificacion/${ced}`)
        .then(res => {
            if (!res.ok) throw new Error("Paciente no encontrado");
            return res.json();
        })
        .then(paciente => {
            document.getElementById("id_paciente").value = paciente.id;
            document.getElementById("nombre_paciente").value = `${paciente.nombrePaci} ${paciente.apellidoPaci}`;

            // Paso 2: obtener exámenes por idPaciente
            return fetch(`http://localhost:8080/detalle_examenes/paciente/${paciente.id}`);
        })
        .then(res => {
            if (!res.ok) throw new Error("Error al obtener exámenes");
            return res.json();
        })
        .then(examenes => {
            console.log("✅ Exámenes encontrados:", examenes);
            cargarTablaExamenes(examenes);
        })
        .catch(err => {
            console.error("❌ Error:", err);
            alert(err.message);
            cargarTablaExamenes([]);  // limpia tabla en caso de error
        });
}

function cargarTablaExamenes(examenes) {
    const tbody = document.getElementById("examTableBodyAuxiliar");
    tbody.innerHTML = "";

    if (!examenes || examenes.length === 0) {
        tbody.innerHTML = "<tr><td colspan='7'>No hay exámenes registrados.</td></tr>";
        return;
    }

    examenes.forEach(e => {
        const row = document.createElement("tr");
        row.innerHTML = `
      <td>${e.nombreTipoExamen || "Sin tipo"}</td>
      <td>${e.fechaExamen}</td>
      <td>${e.archivoExamen}</td>
      <td>${e.nombrePaciente || ""}</td>
      <td>${e.nombreAuxiliar || ""}</td>
      <td>${e.createdAt}</td>
      <td>
        <a href="http://localhost:8080/archivo/${e.archivoExamen}
        " download class="btn btn-sm btn-outline-primary">PDF</a>

    </td>`;
        tbody.appendChild(row);
    });
}

document.getElementById("formularioExamenAuxiliar").addEventListener("submit", e => {
    e.preventDefault();
    const form = e.target;
    const formData = new FormData(form);

    const idPaciente = document.getElementById("id_paciente").value;
    console.log("🩺 Validación previa submit idPaciente:", idPaciente);
    if (!idPaciente) {
        alert("⚠️ Por favor primero busque el paciente antes de subir el examen.");
        return;
    }

    fetch("http://localhost:8080/detalle_examenes/upload", {
        method: "POST",
        body: formData
    })
        .then(res => {
            if (!res.ok) throw new Error("Error al subir examen");
            return res.text();
        })
        .then(msg => {
            alert("✅ " + msg);
            buscarIdentificacionExamen();
            form.reset();
        })
        .catch(err => {
            console.error("❌ Error subir examen:", err);
            alert(err.message);
        });
});
