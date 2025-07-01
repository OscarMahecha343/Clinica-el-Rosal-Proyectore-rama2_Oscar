// 1. VARIABLES GLOBALES
const secciones = {
  containerAgendamiento: document.getElementById("containerAgendamiento"),
  containerHistoriaClinica: document.getElementById("containerHistoriaClinica"),
  containerExamenes: document.getElementById("containerExamenes"),
};

// 2. FUNCIONES GLOBALES
function ocultarTodo() {
  Object.values(secciones).forEach((sec) => sec.classList.add("d-none"));
}

function mostrar(id) {
  ocultarTodo();
  const section = secciones[id];
  if (section) {
    section.classList.remove("d-none");
    section.classList.add("d-block");
  }
}

// 3. AL CARGAR
document.addEventListener("DOMContentLoaded", function () {
  const btnAgendamiento = document.getElementById("btnAgendamiento");
  const btnHistoria = document.getElementById("btnHistoriaClinica");
  const btnExamenes = document.getElementById("btnExamenes");
  const userData = JSON.parse(localStorage.getItem("data-user"));

  console.log("🧠 Datos del usuario logueado:", userData);

  if (!userData || !userData.nombre) {
    alert("Usuario no autenticado. Redirigiendo al login...");
    window.location.href = "index.html";
    return;
  }

  document.getElementById("usuario").textContent = userData.nombre ?? "";
  document.getElementById("tipoIdentificacion").textContent =
    userData.tipoIdentificacion ?? "";
  document.getElementById("username").textContent = userData.username ?? "";
  document.getElementById("rol").textContent = userData.rol ?? "";
  document.getElementById("calendario").addEventListener("change", cargarCitasMedico);

  // listeners
  btnAgendamiento.addEventListener("click", () => mostrar("containerAgendamiento"));
  btnHistoria.addEventListener("click", () => mostrar("containerHistoriaClinica"));
  btnExamenes.addEventListener("click", () => mostrar("containerExamenes"));

  cargarCitasMedico();
});

function logout() {
  localStorage.removeItem("data-user");
  window.location.href = "index.html";
}

// 4. CARGAR CITAS
async function cargarCitasMedico() {
  const userData = JSON.parse(localStorage.getItem("data-user"));
  if (!userData || !userData.id) {
    alert("Usuario no autenticado.");
    return;
  }

  const idMedico = await obtenerIdMedicoDesdeUsuario(userData.id);
  const fecha = document.getElementById("calendario").value;

  if (!fecha) {
    alert("Por favor seleccione una fecha para ver las citas.");
    return;
  }

  try {
    const response = await fetch(
      `http://localhost:8080/cita/medico/${idMedico}/fecha/${fecha}`
    );
    if (!response.ok) throw new Error("Error al cargar citas");

    const citas = await response.json();
    const tbody = document.querySelector("table tbody");
    tbody.innerHTML = ""; // limpiar tabla

    citas.forEach((cita) => {
      const row = document.createElement("tr");
      const horaAMPM = convertirHoraAMPM(cita.hora);

      row.innerHTML = `
  <td>${horaAMPM}</td>
  <td>${cita.nombrePaciente || "Paciente desconocido"}</td>
  <td>${cita.nombreEspecialidad || "No asignada"}</td>
  <td>Principal</td>
  <td>
    <div class="form-check form-check-inline">
      <input type="radio" class="form-check-input" name="asistencia_${cita.id}" value="si" onclick="mostrarHistoriaClinicaDesdeCita(${cita.id})">
      <label class="form-check-label">Sí</label>
    </div>
    <div class="form-check form-check-inline">
      <input type="radio" class="form-check-input" name="asistencia_${cita.id}" value="no">
      <label class="form-check-label">No</label>
    </div>
  </td>
`;
      tbody.appendChild(row);
    });
  } catch (error) {
    console.error("❌ Error al cargar citas:", error);
  }
}

function convertirHoraAMPM(hora) {
  const [hora24, minutos] = hora.split(":");
  const horaNum = parseInt(hora24, 10);
  const periodo = horaNum >= 12 ? "P.M" : "A.M";
  const hora12 = horaNum % 12 || 12;
  return `${hora12}:${minutos} ${periodo}`;
}

async function obtenerIdMedicoDesdeUsuario(idUsuario) {
  const response = await fetch(`http://localhost:8080/medico/usuario/${idUsuario}`);
  if (!response.ok) throw new Error("❌ No se pudo obtener el médico desde el usuario");

  const medico = await response.json();
  console.log("🩺 Datos del médico obtenidos desde el usuario:", medico);
  return medico.id;
}

async function mostrarHistoriaClinicaDesdeCita(idCita) {
  try {
    mostrar("containerHistoriaClinica");

    const response = await fetch(`http://localhost:8080/cita/${idCita}`);
    if (!response.ok) throw new Error("❌ No se pudo obtener la información de la cita");

    const cita = await response.json();

    document.getElementById("paciente").textContent = cita.nombrePaciente ?? "N/A";
    document.getElementById("fechaConsulta").textContent = cita.fecha ?? "";
    document.getElementById("medicoTratante").textContent = cita.nombreMedico ?? "";

    // obtener datos completos del paciente
    const pacienteResponse = await fetch(`http://localhost:8080/paciente/${cita.idPaciente}`);
    if (!pacienteResponse.ok) throw new Error("❌ No se pudo obtener los datos completos del paciente");

    const paciente = await pacienteResponse.json();

    document.getElementById("telefono").textContent = paciente.telefono ?? "";
    document.getElementById("correo").textContent = paciente.correo ?? "";
    document.getElementById("direccion").textContent = paciente.direccion ?? "";
    document.getElementById("edad").textContent = calcularEdad(paciente.fechaNacimiento);
    document.getElementById("identificacion").textContent = paciente.identificacion ?? "";
    document.getElementById("genero").textContent = paciente.genero ?? "";
    document.getElementById("afiliacion").textContent = paciente.idSeguro?.nombreSeguro ?? "";

    // asignar idPaciente global
    window.idPacienteSeleccionado = paciente.id;

    // habilitar botón modal
    const btnVerHistorial = document.getElementById("btnVerHistorial");
    if (btnVerHistorial) {
      btnVerHistorial.onclick = () => abrirModalHistoriales(paciente.id);
    }
  } catch (error) {
    console.error("❌ Error al mostrar historia clínica:", error);
    alert("No se pudo mostrar la historia clínica.");
  }
}

function calcularEdad(fechaNacimiento) {
  if (!fechaNacimiento) return "";
  const nacimiento = new Date(fechaNacimiento);
  const hoy = new Date();
  let edad = hoy.getFullYear() - nacimiento.getFullYear();
  const mes = hoy.getMonth() - nacimiento.getMonth();
  if (mes < 0 || (mes === 0 && hoy.getDate() < nacimiento.getDate())) {
    edad--;
  }
  return edad + " años";
}


// historia modal//

async function abrirModalHistoriales(idPaciente) {
  try {
    console.log("🩺 Cargando historial para paciente", idPaciente);

    // abrir modal
    const modal = new bootstrap.Modal(document.getElementById("modalHistoriales"));
    modal.show();

    const response = await fetch(`http://localhost:8080/historia/paciente/${idPaciente}`);
    if (!response.ok) throw new Error("Error obteniendo historial clínico");

    const historiales = await response.json();

    const tabla = document.getElementById("tablaHistoriales");
    tabla.innerHTML = "";

    historiales.forEach((historia) => {
      const fila = `
        <tr>
          <td>${historia.fechaConsulta ?? "-"}</td>
          <td>${historia.motivoConsulta ?? "-"}</td>
          <td>${historia.diagnostico ?? "-"}</td>
          <td>${historia.tratamiento ?? "-"}</td>
          <td>${historia.alergias ?? "-"}</td>
          <td>${historia.antecedentes ?? "-"}</td>
          <td>${historia.signosVitales ?? "-"}</td>
          <td>${historia.examenesSolicitados ?? "-"}</td>
          <td>${historia.prescripcionMedica ?? "-"}</td>
        </tr>
      `;
      tabla.insertAdjacentHTML("beforeend", fila);
    });
  } catch (error) {
    console.error("❌ Error al cargar historial:", error);
    alert("No se pudo cargar el historial clínico.");
  }
}

async function guardarHistoriaClinica() {
  const idPaciente = window.idPacienteSeleccionado;
  if (!idPaciente) {
    alert("⚠️ No hay paciente seleccionado.");
    return;
  }

  const data = {
    idPaciente: idPaciente,
    motivoConsulta: document.getElementById("motivoConsulta").value.trim(),
    diagnostico: document.getElementById("diagnostico").value.trim(),
    tratamiento: document.getElementById("tratamiento").value.trim(),
    alergias: document.getElementById("alergias").value.trim(),
    antecedentes: document.getElementById("antecedentes").value.trim(),
    signosVitales: document.getElementById("signosVitales").value.trim(),
    examenesSolicitados: document.getElementById("examenesSolicitados").value.trim(),
    prescripcionMedica: null // a futuro
  };

  try {
    const response = await fetch(`http://localhost:8080/historia`, {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error("Error al guardar historia clínica");

    alert("✅ Historia clínica guardada correctamente");
    document.getElementById("formHistoriaClinica").reset();

    // recarga tabla historial
    abrirModalHistoriales(idPaciente);
  } catch (error) {
    console.error("❌ Error al guardar historia clínica:", error);
    alert("No se pudo guardar la historia clínica.");
  }
}
