document.addEventListener("DOMContentLoaded", function () {
    // Mostrar datos de usuario
    const userData = JSON.parse(localStorage.getItem("data-user"));
    if (userData) {
        document.getElementById("usuario").textContent = `${userData.nombre} ${userData.apellido}`;
        document.getElementById("identificacion").textContent = userData.tipoIdentificacion;
        document.getElementById("username").textContent = userData.username;
        document.getElementById("rol").textContent = userData.rol;
        console.log("🟢 Usuario cargado:", userData);
    } else {
        alert("Datos del usuario no encontrados. Inicie sesión.");
        console.error("❌ userData no encontrado en localStorage");
    }

    // Logout
    document.getElementById("btnCerrarSesion").addEventListener("click", function () {
        console.log("🔴 Cerrando sesión");
        localStorage.removeItem("data-user");
        window.location.href = "index.html";
    });

    // Gestión de inventario
    document.getElementById("btnGestiondeInventario").addEventListener("click", function () {
        showContainer("containerGestiondeInventario");
        console.log("🟢 Mostrando contenedor inventario");
        cargarMedicamentos();
    });

    function showContainer(containerId) {
        document.getElementById(containerId).classList.remove("container-hidden");
    }

    const form = document.getElementById("form-medicamento");
    const btnGuardar = document.getElementById("guardarMedicamento");
    const btnLimpiar = document.getElementById("limpiarFormulario");

    btnGuardar.addEventListener("click", function (event) {
        event.preventDefault();

        const idMedicamento = form.dataset.idMedicamento;
        const medicamento = {
            nombre: document.getElementById("nombre").value,
            cantidad: parseInt(document.getElementById("cantidad").value),
            descripcion: document.getElementById("descripcion").value,
            categoria: document.getElementById("categoria").value,
            unidadMedida: document.getElementById("unidad").value,
            precioUnitario: parseFloat(document.getElementById("precio").value),
            fechaVencimiento: document.getElementById("vencimiento").value,
            proveedor: document.getElementById("proveedor").value,
            fechaActualizacion: document.getElementById("actualizacion").value,
            estado: document.getElementById("estado").value
        };

        console.log("🟢 Enviando medicamento:", medicamento);
        if (!medicamento.nombre || medicamento.cantidad === null || medicamento.cantidad === undefined || medicamento.cantidad <= 0) {
            alert("Por favor complete nombre y una cantidad mayor a 0.");
            console.error("❌ Validación fallida:", medicamento);
            return;
        }

        let url = "http://localhost:8080/inventario_medicamentos";
        let method = "POST";

        if (idMedicamento) {
            url += `/${idMedicamento}`;
            method = "PUT";
            medicamento.id = parseInt(idMedicamento);
            console.log("🟡 Editando medicamento id:", medicamento.id);
        }

        fetch(url, {
            method: method,
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(medicamento)
        })
            .then(response => {
                console.log(`✅ Respuesta ${method}:`, response.status);
                if (response.ok) {
                    alert("Guardado correctamente");
                    form.reset();
                    delete form.dataset.idMedicamento;
                    cargarMedicamentos();
                } else {
                    alert("Error al guardar el medicamento.");
                    console.error("❌ Error guardando:", response);
                }
            })
            .catch(error => {
                console.error("❌ Error en fetch:", error);
                alert("Error de conexión con el servidor.");
            });
    });

    btnLimpiar.addEventListener("click", function () {
        console.log("🟡 Reseteando formulario");
        form.reset();
        delete form.dataset.idMedicamento;
    });

    function cargarMedicamentos() {
        console.log("🔵 Cargando medicamentos...");
        fetch("http://localhost:8080/inventario_medicamentos")
            .then(response => {
                console.log("🔵 status getAll:", response.status);
                return response.json();
            })
            .then(data => {
                console.log("🔵 Medicamentos recibidos:", data);
                pintarTabla(data);
            })
            .catch(error => {
                console.error("❌ Error cargando medicamentos:", error);
                alert("Error cargando medicamentos.");
            });
    }

    function pintarTabla(data) {
        if (!Array.isArray(data)) {
            console.error("❌ Data recibida no es array:", data);
            alert("Error procesando datos del servidor");
            return;
        }

        const tabla = document.getElementById("tablaInventario").querySelector("tbody");
        tabla.innerHTML = "";

        data.forEach(med => {
            const fila = tabla.insertRow();
            fila.insertCell().textContent = med.id;
            fila.insertCell().textContent = med.nombre;
            fila.insertCell().textContent = med.cantidad;
            fila.insertCell().textContent = med.descripcion || "";
            fila.insertCell().textContent = med.categoria || "";
            fila.insertCell().textContent = med.unidadMedida || "";
            fila.insertCell().textContent = med.precioUnitario || "";
            fila.insertCell().textContent = med.fechaVencimiento || "";
            fila.insertCell().textContent = med.proveedor || "";
            fila.insertCell().textContent = med.fechaActualizacion || "";
            fila.insertCell().textContent = med.estado || "";

            const celdaAccion = fila.insertCell();

            const btnEditar = document.createElement("button");
            btnEditar.textContent = "Editar";
            btnEditar.classList.add("btn", "btn-warning", "btn-sm", "me-2");
            btnEditar.addEventListener("click", function () {
                console.log("🟡 Editando medicamento id:", med.id);
                cargarFormulario(med);
            });
            celdaAccion.appendChild(btnEditar);

            const btnInactivar = document.createElement("button");
            btnInactivar.textContent = "Inactivar";
            btnInactivar.classList.add("btn", "btn-danger", "btn-sm");
            btnInactivar.addEventListener("click", function () {
                console.log("🟡 Inactivando medicamento id:", med.id);
                inactivarMedicamento(med.id);
            });
            celdaAccion.appendChild(btnInactivar);
        });
    }

    function cargarFormulario(med) {
        console.log("🟡 Cargando formulario con:", med);
        form.dataset.idMedicamento = med.id;
        document.getElementById("nombre").value = med.nombre;
        document.getElementById("cantidad").value = med.cantidad;
        document.getElementById("descripcion").value = med.descripcion || "";
        document.getElementById("categoria").value = med.categoria || "";
        document.getElementById("unidad").value = med.unidadMedida || "";
        document.getElementById("precio").value = med.precioUnitario || "";
        document.getElementById("vencimiento").value = med.fechaVencimiento || "";
        document.getElementById("proveedor").value = med.proveedor || "";
        document.getElementById("actualizacion").value = med.fechaActualizacion || "";
        document.getElementById("estado").value = med.estado || "";
    }

    function inactivarMedicamento(id) {
        if (!confirm("¿Está seguro de inactivar este medicamento?")) return;

        console.log("🔴 Enviando PUT inactivar:", id);

        fetch(`http://localhost:8080/inventario_medicamentos/${id}/inactivar`, {
            method: "PUT"
        })
            .then(response => {
                console.log("🔴 status inactivar:", response.status);
                if (response.ok) {
                    alert("Medicamento inactivado correctamente.");
                    cargarMedicamentos();
                } else {
                    alert("Error al inactivar el medicamento.");
                    console.error("❌ Error inactivar:", response);
                }
            })
            .catch(error => {
                console.error("❌ Error inactivar:", error);
                alert("Error en la conexión al servidor.");
            });
    }

    // búsqueda
    const btnBuscar = document.getElementById("btnBuscar");
    const btnMostrarTodo = document.getElementById("btnMostrarTodo");

    btnBuscar.addEventListener("click", function () {
        const filtro = document.getElementById("busquedaMedicamento").value.toLowerCase();
        console.log("🔍 Buscando:", filtro);

        fetch("http://localhost:8080/inventario_medicamentos")
            .then(response => response.json())
            .then(data => {
                const filtrados = data.filter(med => med.nombre && med.nombre.toLowerCase().includes(filtro))
                console.log("🔍 Filtrados:", filtrados);
                pintarTabla(filtrados);
            })
            .catch(error => {
                console.error("❌ Error búsqueda:", error);
            });
    });

    btnMostrarTodo.addEventListener("click", function () {
        console.log("🟢 Mostrar todos");
        cargarMedicamentos();
        document.getElementById("busquedaMedicamento").value = "";
    });
});
