document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("afiliacionForm").addEventListener("submit", function (event) {
        event.preventDefault();

        let nombres = document.getElementById("nombres").value.trim();
        let apellidos = document.getElementById("apellidos").value.trim();
        let tipoIdentificacion = document.getElementById("tipoIdentificacion").value;
        let identificacion = document.getElementById("Identificacion").value.trim();
        let fechaNacimiento = document.getElementById("fechaNacimiento").value;
        let telefono = document.getElementById("telefono").value.trim();
        let correo = document.getElementById("correo").value.trim();
        let direccion = document.getElementById("direccion").value.trim();
        let municipio = document.getElementById("Municipio").value.trim();
        let tipoAfiliacion = document.getElementById("tipoAfiliacion").value;
        let seguro = document.getElementById("Seguro").value;

        let errores = [];

        if (nombres === "") errores.push("El campo 'Nombres' es obligatorio.");
        if (apellidos === "") errores.push("El campo 'Apellidos' es obligatorio.");
        if (tipoIdentificacion === "") errores.push("Debe seleccionar un tipo de identificación.");
        if (identificacion === "") errores.push("El campo 'Identificación' es obligatorio.");
        if (fechaNacimiento === "") errores.push("El campo 'Fecha de Nacimiento' es obligatorio.");
        if (telefono === "") errores.push("El campo 'Teléfono' es obligatorio.");
        if (correo === "") errores.push("El campo 'Correo Electrónico' es obligatorio.");
        if (direccion === "") errores.push("El campo 'Dirección' es obligatorio.");
        if (municipio === "") errores.push("El campo 'Municipio' es obligatorio.");
        if (tipoAfiliacion === "Seleccione") errores.push("Debe seleccionar un tipo de afiliación.");
        if (seguro === "") errores.push("Debe seleccionar un seguro.");

        let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(correo)) errores.push("El formato del 'Correo Electrónico' no es válido.");

        let phoneRegex = /^[0-9]+$/;
        if (!phoneRegex.test(telefono)) errores.push("El campo 'Teléfono' solo debe contener números.");

        if (errores.length > 0) {
            alert("Errores en el formulario:\n" + errores.join("\n"));
        } else {
            // Crear objeto para enviar
            const afiliacion = {
                nombres: nombres,
                apellidos: apellidos,
                tipoIdentificacion: tipoIdentificacion,
                identificacion: identificacion,
                fechaNacimiento: fechaNacimiento,
                telefono: telefono,
                correo: correo,
                direccion: direccion,
                municipio: municipio,
                tipoAfiliacion: tipoAfiliacion,
                seguro: seguro
            };

            // Enviar al backend
            fetch('http://localhost:8080/afiliaciones', {   // <- URL de tu endpoint
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(afiliacion)
            })
            .then(response => {
                if (response.ok) {
                    alert('Afiliación guardada correctamente.');
                    document.getElementById("afiliacionForm").reset(); // Limpiar formulario
                } else {
                    alert('Error al guardar la afiliación.');
                }
            })
            .catch(error => {
                console.error('Error en la solicitud:', error);
                alert('Error de conexión con el servidor.');
            });
        }
    });
});