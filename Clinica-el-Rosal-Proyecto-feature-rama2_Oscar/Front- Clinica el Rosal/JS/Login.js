$("#loginForm").submit(function (event) { 
        event.preventDefault(); // Evita el envío del formulario

        let isValidForm = true;

        const idRol = $("#idRol").val();
        const login = $("#login").val().trim();
        const password = $("#password").val().trim();

        // Limpiar clases de errores anteriores
        $("#idRol, #login, #password").removeClass("is-invalid");

        // Validar idRol
        if (!idRol || idRol === "") {
            $("#idRol").addClass("is-invalid");
            isValidForm = false;
        }

        // Validar login
        if (login === "") {
            $("#login").addClass("is-invalid");
            isValidForm = false;
        }

        // Validar password
        if (password === "") {
            $("#password").addClass("is-invalid");
            isValidForm = false;
        }

        if (!isValidForm) {

            var request = {

                login : $("#login").val(),
                password : $("#password").val(),
                idRol :  $("#idRol").val()
            };

            console.log("Login::request" + JSON.stringify(Request));

            alert("Por favor, complete todos los campos correctamente.");
            return;
        } else {
            alert("No envia info")
        }

        // Validar correo electrónico válido
        const regexCorreo = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!regexCorreo.test(login)) {
            $("#login").addClass("is-invalid");
            alert("Ingrese un correo válido.");
            isValidForm = false;
        }

        // Redirección según el perfil seleccionado
        switch (idRol) {
            case "1": // Usuario
                window.location.href = "perfil-usuario.html";
                break;
            case "2": // Médico
                window.location.href = "perfil-medico.html";
                break;
            case "3": // Auxiliar
                window.location.href = "perfil-auxiliar.html";
                break;
            case "4": // Farmacéutico
                window.location.href = "perfil-farmaceutico.html";
                break;
            default:
                alert("Rol no reconocido.");
        }
});