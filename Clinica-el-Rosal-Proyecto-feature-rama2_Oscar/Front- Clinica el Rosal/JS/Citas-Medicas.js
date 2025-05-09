async function guardarExamen() {
    const idTipoExamen = document.getElementById('idTipoExamen').value;
    const fechaExamen = document.getElementById('fechaExamen').value;
    const archivoExamen = document.getElementById('archivoExamen').value;
    const idPaciente = document.getElementById('idPaciente').value;
    const idAuxiliar = document.getElementById('idAuxiliar').value;

    const examen = {
        idTipoExamen: idTipoExamen,
        fechaExamen: fechaExamen,
        archivoExamen: archivoExamen,
        idPaciente: idPaciente,
        idAuxiliar: idAuxiliar
    };

    try {
        const response = await fetch('http://localhost:8080/api/examenes', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(examen)
        });

        if (response.ok) {
            alert('Examen guardado exitosamente');
        } else {
            alert('Error al guardar el examen');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Error al conectar con el servidor');
    }
}