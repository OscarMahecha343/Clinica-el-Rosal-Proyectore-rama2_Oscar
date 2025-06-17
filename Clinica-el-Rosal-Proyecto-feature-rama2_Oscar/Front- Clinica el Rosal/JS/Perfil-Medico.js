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

    const nombreMedico = document.getElementById("nombreMedico");
    if (nombreMedico) nombreMedico.textContent = userData.nombre;

    document.getElementById("usuario").textContent = userData.nombre ?? '';
    document.getElementById("tipoIdentificacion").textContent = userData.tipoIdentificacion ?? '';
    document.getElementById("username").textContent = userData.username ?? '';
    document.getElementById("rol").textContent = userData.rol ?? '';
    document.getElementById("calendario").addEventListener("change", cargarCitasMedico);

  
    const secciones = {
        containerAgendamiento: document.getElementById("containerAgendamiento"),
        containerHistoriaClinica: document.getElementById("containerHistoriaClinica"),
        containerExamenes: document.getElementById("containerExamenes")
    };

   
    function ocultarTodo() {
        Object.values(secciones).forEach(sec => sec.classList.add("d-none"));
    }

    function mostrar(id) {
        ocultarTodo();
        const section = secciones[id];
        if (section) {
            section.classList.remove("d-none");
            section.classList.add("d-block");
        }
    }

    // Listeners
    btnAgendamiento.addEventListener("click", () => mostrar("containerAgendamiento"));
    btnHistoria.addEventListener("click", () => mostrar("containerHistoriaClinica"));
    btnExamenes.addEventListener("click", () => mostrar("containerExamenes"));
    cargarCitasMedico(); 
});

function logout() {
    localStorage.removeItem("data-user");
    window.location.href = "index.html";
}


/////]/// container agendamiento /////

async function cargarCitasMedico() {
    const userData = JSON.parse(localStorage.getItem("data-user"));
    if (!userData || !userData.id) {
        alert("Usuario no autenticado.");
        return;
    }

    const idMedico = await obtenerIdMedicoDesdeUsuario(userData.id);
    const fecha = document.getElementById("calendario").value;

    try {
        const response = await fetch(`http://localhost:8080/cita/medico/${idMedico}/fecha/${fecha}`);
        if (!response.ok) throw new Error("Error al cargar citas");

        const citas = await response.json();
        const tbody = document.querySelector("table tbody");
        tbody.innerHTML = ""; // Limpiar tabla

        citas.forEach((cita) => {
            const row = document.createElement("tr");
            const horaAMPM = convertirHoraAMPM(cita.hora);

            row.innerHTML = `
  <td>${horaAMPM}</td>
  <td>${cita.paciente?.nombre || "Paciente desconocido"} ${cita.paciente?.apellido || ""}</td>
  <td>${cita.nombreEspecialidad || 'No asignada'}</td>
  <td>Principal</td>
  <td>
    <div class="form-check form-check-inline">
      <input type="radio" class="form-check-input" name="asistencia_${cita.id}" value="si" onclick="mostrarHistoriaClinicaDesdeCita(${cita.id})">
      <label class="form-check-label" for="asistencia_${cita.id}_si">Sí</label>
    </div>
    <div class="form-check form-check-inline">
      <input class="form-check-input" type="radio" name="asistencia_${cita.id}" value="no" id="asistencia_${cita.id}_no">
      <label class="form-check-label" for="asistencia_${cita.id}_no">No</label>
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

    document.getElementById("containerAgendamiento").classList.add("container-hidden");

    
    document.getElementById("containerHistoriaClinica").classList.remove("container-hidden");

 
    const response = await fetch(`http://localhost:8080/cita/${idCita}`);
    if (!response.ok) throw new Error("❌ No se pudo obtener la información de la cita");

    const cita = await response.json();

 
    const paciente = cita.paciente;

    document.getElementById("paciente").textContent = `${paciente.nombre} ${paciente.apellido}`;
    document.getElementById("telefono").textContent = paciente.telefono ?? '';
    document.getElementById("correo").textContent = paciente.correo ?? '';
    document.getElementById("direccion").textContent = paciente.direccion ?? '';
    document.getElementById("edad").textContent = calcularEdad(paciente.fechaNacimiento);
    document.getElementById("identificacion").textContent = paciente.identificacion ?? '';
    document.getElementById("genero").textContent = paciente.genero ?? '';
    document.getElementById("afiliacion").textContent = paciente.afiliacion ?? '';
    document.getElementById("fechaConsulta").textContent = cita.fecha;
    document.getElementById("medicoTratante").textContent = cita.nombreMedico;

  } catch (error) {
    console.error("❌ Error al mostrar historia clínica:", error);
    alert("No se pudo mostrar la historia clínica.");
  }
}

async function mostrarHistoriaClinicaDesdeCita(idCita) {
  try {
    // Ocultar agendamiento y mostrar historia
    document.getElementById("containerAgendamiento").classList.add("container-hidden");
    document.getElementById("containerHistoriaClinica").classList.remove("container-hidden");

    const response = await fetch(`http://localhost:8080/cita/${idCita}`);
    if (!response.ok) throw new Error("❌ No se pudo obtener la información de la cita");

    const cita = await response.json();
    const paciente = cita.paciente;

    document.getElementById("paciente").textContent = `${paciente.nombre} ${paciente.apellido}`;
    document.getElementById("telefono").textContent = paciente.telefono ?? '';
    document.getElementById("correo").textContent = paciente.correo ?? '';
    document.getElementById("direccion").textContent = paciente.direccion ?? '';
    document.getElementById("edad").textContent = calcularEdad(paciente.fechaNacimiento);
    document.getElementById("identificacion").textContent = paciente.identificacion ?? '';
    document.getElementById("genero").textContent = paciente.genero ?? '';
    document.getElementById("afiliacion").textContent = paciente.afiliacion ?? '';
    document.getElementById("fechaConsulta").textContent = cita.fecha;
    document.getElementById("medicoTratante").textContent = cita.nombreMedico;

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