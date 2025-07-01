// ================================
// PERFIL-MEDICO.JS
// ================================

// 1. SECCIONES
const secciones = {
  containerAgendamiento: document.getElementById("containerAgendamiento"),
  containerHistoriaClinica: document.getElementById("containerHistoriaClinica"),
  containerExamenes: document.getElementById("containerExamenes"),
};

// variable global para identificar historia clínica activa
window.idHistoriaSeleccionada = null;

// 2. OCULTAR TODAS LAS SECCIONES
function ocultarTodo() {
  Object.values(secciones).forEach((sec) => sec.classList.add("d-none"));
}

// 3. MOSTRAR UNA SECCIÓN
function mostrar(id) {
  ocultarTodo();
  const section = secciones[id];
  if (section) {
    section.classList.remove("d-none");
    section.classList.add("d-block");
  }
}

// 4. INICIO
document.addEventListener("DOMContentLoaded", async () => {
  const userData = JSON.parse(localStorage.getItem("data-user"));
  console.log("🧠 Datos del usuario logueado:", userData);

  if (!userData || !userData.nombre) {
    alert("Usuario no autenticado. Redirigiendo al login...");
    window.location.href = "index.html";
    return;
  }

  document.getElementById("usuario").textContent = userData.nombre;
  document.getElementById("tipoIdentificacion").textContent = userData.tipoIdentificacion;
  document.getElementById("username").textContent = userData.username;
  document.getElementById("rol").textContent = userData.rol;

  document.getElementById("calendario").addEventListener("change", cargarCitasMedico);
  document.getElementById("btnAgendamiento").addEventListener("click", () => mostrar("containerAgendamiento"));
  document.getElementById("btnHistoriaClinica").addEventListener("click", () => mostrar("containerHistoriaClinica"));
  document.getElementById("btnExamenes").addEventListener("click", () => mostrar("containerExamenes"));

  cargarCitasMedico();
});

// 5. LOGOUT
function logout() {
  localStorage.removeItem("data-user");
  window.location.href = "index.html";
}

// 6. CITAS
async function cargarCitasMedico() {
  try {
    const userData = JSON.parse(localStorage.getItem("data-user"));
    if (!userData?.id) throw new Error("Usuario no autenticado");

    const idMedico = await obtenerIdMedicoDesdeUsuario(userData.id);
    const fecha = document.getElementById("calendario").value;
    if (!fecha) {
      alert("Seleccione una fecha para ver las citas.");
      return;
    }

    const response = await fetch(`http://localhost:8080/cita/medico/${idMedico}/fecha/${fecha}`);
    if (!response.ok) throw new Error("Error al cargar citas");
    const citas = await response.json();

    const tbody = document.querySelector("table tbody");
    tbody.innerHTML = "";
    citas.forEach((cita) => {
      tbody.insertAdjacentHTML("beforeend", `
        <tr>
          <td>${convertirHoraAMPM(cita.hora)}</td>
          <td>${cita.nombrePaciente || "-"}</td>
          <td>${cita.nombreEspecialidad || "-"}</td>
          <td>Principal</td>
          <td>
            <div class="form-check form-check-inline">
              <input type="radio" class="form-check-input" name="asistencia_${cita.id}" value="si"
              onclick="mostrarHistoriaClinicaDesdeCita(${cita.id})"> <label class="form-check-label">Sí</label>
            </div>
            <div class="form-check form-check-inline">
              <input type="radio" class="form-check-input" name="asistencia_${cita.id}" value="no">
              <label class="form-check-label">No</label>
            </div>
          </td>
        </tr>
      `);
    });
  } catch (error) {
    console.error("❌ Error al cargar citas:", error);
  }
}

function convertirHoraAMPM(hora) {
  const [h, m] = hora.split(":");
  const h12 = h % 12 || 12;
  const suf = h >= 12 ? "P.M" : "A.M";
  return `${h12}:${m} ${suf}`;
}

// 7. DATOS MÉDICO
async function obtenerIdMedicoDesdeUsuario(idUsuario) {
  const response = await fetch(`http://localhost:8080/medico/usuario/${idUsuario}`);
  if (!response.ok) throw new Error("❌ Error obteniendo médico");
  const medico = await response.json();
  return medico.id;
}

// 8. MOSTRAR HISTORIA
async function mostrarHistoriaClinicaDesdeCita(idCita) {
  try {
    mostrar("containerHistoriaClinica");

    const resCita = await fetch(`http://localhost:8080/cita/${idCita}`);
    if (!resCita.ok) throw new Error("❌ Error obteniendo cita");

    const cita = await resCita.json();
    window.idHistoriaSeleccionada = cita.idHistoria;

    document.getElementById("paciente").textContent = cita.nombrePaciente ?? "-";
    document.getElementById("fechaConsulta").textContent = cita.fecha ?? "-";
    document.getElementById("medicoTratante").textContent = cita.nombreMedico ?? "-";

    const resPaciente = await fetch(`http://localhost:8080/paciente/${cita.idPaciente}`);
    if (!resPaciente.ok) throw new Error("❌ Error obteniendo paciente");

    const paciente = await resPaciente.json();
    document.getElementById("telefono").textContent = paciente.telefono ?? "-";
    document.getElementById("correo").textContent = paciente.correo ?? "-";
    document.getElementById("direccion").textContent = paciente.direccion ?? "-";
    document.getElementById("edad").textContent = calcularEdad(paciente.fechaNacimiento);
    document.getElementById("identificacion").textContent = paciente.identificacion ?? "-";
    document.getElementById("genero").textContent = paciente.genero ?? "-";
    document.getElementById("afiliacion").textContent = paciente.idSeguro?.nombreSeguro ?? "-";

    window.idPacienteSeleccionado = paciente.id;

    const btnVerHistorial = document.getElementById("btnVerHistorial");
    btnVerHistorial.onclick = () => abrirModalHistoriales(paciente.id);

    await cargarPrescripcionesGuardadas(window.idHistoriaSeleccionada);

  } catch (error) {
    console.error("❌ Error mostrando historia clínica:", error);
    alert(error.message);
  }
}

// 9. CALCULAR EDAD
function calcularEdad(fechaNacimiento) {
  if (!fechaNacimiento) return "";
  const nac = new Date(fechaNacimiento);
  const hoy = new Date();
  let edad = hoy.getFullYear() - nac.getFullYear();
  if (hoy.getMonth() < nac.getMonth() || (hoy.getMonth() === nac.getMonth() && hoy.getDate() < nac.getDate())) {
    edad--;
  }
  return `${edad} años`;
}

// 10. MODAL HISTORIAL
async function abrirModalHistoriales(idPaciente) {
  try {
    const modal = new bootstrap.Modal(document.getElementById("modalHistoriales"));
    modal.show();

    const res = await fetch(`http://localhost:8080/historia/paciente/${idPaciente}`);
    if (!res.ok) throw new Error("Error cargando historial");
    const historiales = await res.json();

    const tabla = document.getElementById("tablaHistoriales");
    tabla.innerHTML = "";
    historiales.forEach((h) => {
      tabla.insertAdjacentHTML("beforeend", `
        <tr>
          <td>${h.fechaConsulta ?? "-"}</td>
          <td>${h.motivoConsulta ?? "-"}</td>
          <td>${h.diagnostico ?? "-"}</td>
          <td>${h.tratamiento ?? "-"}</td>
          <td>${h.alergias ?? "-"}</td>
          <td>${h.antecedentes ?? "-"}</td>
          <td>${h.signosVitales ?? "-"}</td>
          <td>${h.examenesSolicitados ?? "-"}</td>
          <td>${h.prescripcionMedica ?? "-"}</td>
        </tr>
      `);
    });
  } catch (error) {
    console.error("❌ Error cargando historial:", error);
    alert(error.message);
  }
}

// 11. GUARDAR HISTORIA
async function guardarHistoriaClinica() {
  try {
    const idPaciente = window.idPacienteSeleccionado;
    if (!idPaciente) throw new Error("No hay paciente seleccionado");

    const userData = JSON.parse(localStorage.getItem("data-user"));
    const idMedico = await obtenerIdMedicoDesdeUsuario(userData.id);

    const data = {
      idPaciente,
      idMedico,
      fechaConsulta: new Date().toISOString().split("T")[0],
      motivoConsulta: document.getElementById("motivoConsulta").value.trim(),
      diagnostico: document.getElementById("diagnostico").value.trim(),
      tratamiento: document.getElementById("tratamiento").value.trim(),
      alergias: document.getElementById("alergias").value.trim(),
      antecedentes: document.getElementById("antecedentes").value.trim(),
      signosVitales: document.getElementById("signosVitales").value.trim(),
      examenesSolicitados: document.getElementById("examenesSolicitados").value.trim(),
      historialClinico: ""
    };

    const res = await fetch(`http://localhost:8080/historia`, {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(data),
    });
    if (!res.ok) throw new Error("Error guardando historia clínica");

    alert("✅ Historia clínica guardada correctamente");
    document.getElementById("formHistoriaClinica").reset();

  } catch (error) {
    console.error("❌ Error guardando historia clínica:", error);
    alert(error.message);
  }
}

// 12. PRESCRIPCIÓN
function agregarPrescripcionFila() {
  const medicamento = document.getElementById("medicamento").value.trim();
  const cantidad = document.getElementById("cantidad").value.trim();
  const presentacion = document.getElementById("presentacion").value;
  const indicaciones = document.getElementById("indicaciones").value.trim();

  if (!medicamento || !cantidad || !presentacion || !indicaciones) {
    alert("⚠️ Todos los campos son obligatorios.");
    return;
  }

  document.getElementById("tablaPrescripcionNueva").insertAdjacentHTML("beforeend", `
    <tr>
      <td>${medicamento}</td>
      <td>${cantidad}</td>
      <td>${presentacion}</td>
      <td>${indicaciones}</td>
      <td><button class="btn btn-danger btn-sm" onclick="this.closest('tr').remove()">🗑️</button></td>
    </tr>
  `);

  document.getElementById("medicamento").value = "";
  document.getElementById("cantidad").value = "";
  document.getElementById("presentacion").selectedIndex = 0;
  document.getElementById("indicaciones").value = "";
}

async function guardarPrescripcion() {
  try {
    const idHistoria = window.idHistoriaSeleccionada;
    if (!idHistoria) throw new Error("No hay historia clínica seleccionada");

    const filas = document.querySelectorAll("#tablaPrescripcionNueva tr");
    if (filas.length === 0) {
      alert("Debe agregar al menos un medicamento.");
      return;
    }

    const lista = [];
    filas.forEach(tr => {
      const celdas = tr.querySelectorAll("td");
      lista.push({
        idHistoria,
        idMedicamentos: 0, // ajusta según tu modelo
        cantidadTotal: parseInt(celdas[1].innerText),
        presentacion: celdas[2].innerText,
        indicaciones: celdas[3].innerText
      });
    });

    const res = await fetch(`http://localhost:8080/prescripcion`, {
      method: "POST",
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(lista),
    });
    if (!res.ok) throw new Error("Error guardando prescripción");

    alert("✅ Prescripción guardada correctamente");
    document.getElementById("tablaPrescripcionNueva").innerHTML = "";
    await cargarPrescripcionesGuardadas(idHistoria);

  } catch (error) {
    console.error("❌ Error guardando prescripción:", error);
    alert(error.message);
  }
}

async function cargarPrescripcionesGuardadas(idHistoria) {
  try {
    console.log("🩺 Buscando prescripciones con idHistoria:", idHistoria);

    const res = await fetch(`http://localhost:8080/prescripcion/historia/${idHistoria}`);
    if (!res.ok) throw new Error("Error consultando prescripciones");
    const prescripciones = await res.json();

    const tabla = document.getElementById("tablaPrescripcionesGuardadas");
    tabla.innerHTML = "";
    prescripciones.forEach(p => {
      tabla.insertAdjacentHTML("beforeend", `
        <tr>
          <td>${p.medicamento || "-"}</td>
          <td>${p.cantidadTotal || "-"}</td>
          <td>${p.presentacion || "-"}</td>
          <td>${p.indicaciones || "-"}</td>
        </tr>
      `);
    });
  } catch (error) {
    console.error("❌ Error cargando prescripciones:", error);
    alert(error.message);
  }
}

// ================================
// FIN
// ================================
