$("#loginForm").submit(function (event) {
    event.preventDefault();

    let isValidForm = true;

    const idRol = $("#idRol").val();
    const login = $("#login").val().trim();
    const password = $("#password").val().trim();

    // Limpiar errores
    $("#idRol, #login, #password").removeClass("is-invalid");

    if (!idRol || idRol === "") {
        $("#idRol").addClass("is-invalid");
        isValidForm = false;
    }

    if (login === "") {
        $("#login").addClass("is-invalid");
        isValidForm = false;
    }

    if (password === "") {
        $("#password").addClass("is-invalid");
        isValidForm = false;
    }

    // Validar correo electrónico válido
    const regexCorreo = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!regexCorreo.test(login)) {
        $("#login").addClass("is-invalid");
        alert("Ingrese un correo válido.");
        isValidForm = false;
    }

    if (!isValidForm) {
        alert("Por favor, complete todos los campos correctamente.");
        return;
    }

    
    const request = {
        login: login,
        password: password,
        idRol: idRol
    };

    console.log("Login::request" + JSON.stringify(request));

    const url = "http://localhost:8080/auth";
    const method = "POST";

    const ifSuccessLogin = function(data){
        console.log("Login::response" + JSON.stringify(data));
    };

    const ifErrorLogin = function(data){
        alert("Al hacer login se generó un error");
    };

    callApi(url, method, request, ifSuccessLogin, ifErrorLogin);

    // Redirección según el perfil
    //  switch (idRol) {
    //      case "1": window.location.href = "perfil-usuario.html"; break;
     //     case "2": window.location.href = "perfil-medico.html"; break;
     //     case "3": window.location.href = "perfil-auxiliar.html"; break;
     //     case "4": window.location.href = "perfil-farmaceutico.html"; break;
     //     default: alert("Rol no reconocido."); break;
     // }
});

const validMethods = ["GET", "POST", "PUT", "DELETE"];

function callApi(url, method, data, cbSuccess, cbError) {
    console.log("callApi :: " + method + " :: " + url);

    const isPresent = validMethods.includes(method);
    if (!isPresent) {
        alert("Método " + method + " no permitido");
        return;
    }

    const jsonData = (method === "POST" || method === "PUT") ? JSON.stringify(data) : null;

    $.ajax({
        url: url,
        type: method,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: jsonData,
        headers: {
            'Authorization': 'token123'
        },
        success: function (result) {
            try {
                cbSuccess(result);
            } catch (e) {
                console.log("Error en cbSuccess", e);
            }
        },
        error: function (xhr, status, error) {
            try {
                console.log("Error:", error);
                cbError(xhr.status);
            } catch (e) {
                console.log("Error en cbError", e);
                alert("El llamado al servidor falló " + xhr.status);
            }
        }
    });
}