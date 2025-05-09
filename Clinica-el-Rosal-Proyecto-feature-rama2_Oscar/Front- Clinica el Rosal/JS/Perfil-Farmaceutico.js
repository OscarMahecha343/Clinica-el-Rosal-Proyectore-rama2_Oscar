document.addEventListener("DOMContentLoaded", function () {

    function hideAllContainers() {
        document.getElementById("containerGestiondeInventario").classList.add("container-hidden");
    }

    function showContainer(containerId) {
        hideAllContainers();
        document.getElementById(containerId).classList.remove("container-hidden");
    }

    document.getElementById("btnGestiondeInventario").addEventListener("click", function () {
        showContainer("containerGestiondeInventario");
        cargarMedicamentos(); // Al mostrar el contenedor, cargar los medicamentos
    });

    let btnGuardar = document.getElementById("guardarMedicamento");
    let btnLimpiar = document.getElementById("limpiarFormulario");

    btnGuardar.addEventListener("click", function (event) {
        event.preventDefault(); // Evita que la página se recargue

        let nombre = document.getElementById("nombre").value;
        let cantidad = document.getElementById("cantidad").value;
        let descripcion = document.getElementById("descripcion").value;
        let categoria = document.getElementById("categoria").value;
        let unidadMedida = document.getElementById("unidad").value; 
        let precioUnitario = parseFloat(document.getElementById("precio").value); 
        let fechaVencimiento = document.getElementById("vencimiento").value; 
        let proveedor = document.getElementById("proveedor").value;
        let fechaActualizacion = document.getElementById("actualizacion").value; 
        let estado = document.getElementById("estado").value;

        if (!nombre || !cantidad) {
            alert("Por favor, completa el nombre y cantidad.");
            return;
        }

        let medicamento = {
            nombreMedicamento: nombre,
            cantidad: parseInt(cantidad),
            descripcion: descripcion,
            categoria: categoria,
            unidadMedida: unidadMedida,
            precioUnitario: precioUnitario,
            fechaVencimiento: fechaVencimiento,
            proveedor: proveedor,
            fechaActualizacion: fechaActualizacion,
            estado: estado
        };

        fetch('http://localhost:8080/inventario_medicamentos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(medicamento)
        })
        .then(response => {
            if (response.ok) {
                alert("Medicamento guardado exitosamente.");
                document.getElementById("form-medicamento").reset();
                cargarMedicamentos();
            } else {
                alert("Error al guardar el medicamento.");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Error en la conexión con el servidor.");
        });
    });

    btnLimpiar.addEventListener("click", function (event) {
        event.preventDefault();
        document.getElementById("form-medicamento").reset();
    });

    function cargarMedicamentos() {
        fetch('http://localhost:8080/inventario_medicamentos')
        .then(response => response.json())
        .then(data => {
            let tabla = document.getElementById("tablaInventario").querySelector("tbody");
            tabla.innerHTML = ""; // Limpiar la tabla antes de cargar

            data.forEach(medicamento => {
                let fila = tabla.insertRow();

                fila.insertCell().textContent = medicamento.id;
                fila.insertCell().textContent = medicamento.nombre;
                fila.insertCell().textContent = medicamento.cantidad;
                fila.insertCell().textContent = medicamento.descripcion; // Descripción (no la tienes en DTO aún)
                fila.insertCell().textContent = medicamento.categoria; // Categoría
                fila.insertCell().textContent = medicamento.unidadMedida; // Unidad
                fila.insertCell().textContent = medicamento.precioUnitario; // Precio
                fila.insertCell().textContent = medicamento.fechaVencimiento; // Fecha de vencimiento
                fila.insertCell().textContent = medicamento.proveedor; // Proveedor
                fila.insertCell().textContent = medicamento.fechaActualizacion;// Fecha de actualización
                fila.insertCell().textContent = medicamento.estado; // Estado

                let celdaAccion = fila.insertCell();
                let btnEliminar = document.createElement("button");
                btnEliminar.textContent = "Eliminar";
                btnEliminar.classList.add("btn", "btn-danger", "btn-sm");

                btnEliminar.addEventListener("click", function () {
                    eliminarMedicamento(medicamento.id);
                });

                celdaAccion.appendChild(btnEliminar);
            });
        })
        .catch(error => {
            console.error("Error al cargar medicamentos:", error);
        });
    }

    function eliminarMedicamento(id) {
        if (!confirm("¿Está seguro de eliminar este medicamento?")) {
            return;
        }

        fetch(`http://localhost:8080/inventario_medicamentos/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                alert("Medicamento eliminado exitosamente.");
                cargarMedicamentos();
            } else {
                alert("Error al eliminar el medicamento.");
            }
        })
        .catch(error => {
            console.error("Error al eliminar medicamento:", error);
            alert("Error en la conexión con el servidor.");
        });
    }

});