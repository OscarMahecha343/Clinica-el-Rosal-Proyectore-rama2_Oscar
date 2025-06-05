document.addEventListener("DOMContentLoaded", () => {

  const dataUser = JSON.parse(localStorage.getItem("data-user"));
  if (!dataUser) {
    alert("No hay sesión activa. Redirigiendo al login…");
    return window.location.href = "Login.html";
  }

  
  document.getElementById("usuario").innerText         = dataUser.nombre + " " + dataUser.apellido;
  document.getElementById("identificacion").innerText  = dataUser.tipoIdentificacion + " - " + dataUser.identificacion;
  document.getElementById("username").innerText        = dataUser.username;
  document.getElementById("rol").innerText             = dataUser.rol;
  document.getElementById("genero").innerText          = dataUser.genero;
  document.getElementById("fechaNacimiento").innerText = dataUser.fechaNacimiento;
  document.getElementById("telefono").innerText        = dataUser.telefono;
  document.getElementById("grupoSanguineo").innerText  = dataUser.grupoSanguineo;
  document.getElementById("tipoDeAlergia").innerText   = dataUser.tipoDeAlergia;

 
  const navMap = {
    "btn-citas":                "citas-medicas.html",
    "btn-diagnostico":          "Examenes.html",
    "btn-afiliacion":           "Afiliacion.html",         
    "btn-estado_afiliacion":    "Estado_Afiliacion.html"
  };

  Object.entries(navMap).forEach(([btnId, targetUrl]) => {
    const btn = document.getElementById(btnId);
    if (!btn) return;
    btn.addEventListener("click", e => {
      e.preventDefault();
      
      localStorage.setItem("data-user", JSON.stringify(dataUser));
      
      window.location.href = targetUrl;
    });
  });
});

// Función de logout
function logout() {
  if (confirm("¿Estás seguro de que deseas cerrar sesión?")) {
    localStorage.clear();
    window.location.href = "Login.html";
  }
}
