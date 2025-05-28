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

    var url = "http://localhost:8080/auth";
    var method = "POST";
    var ifSuccessLogin = function(apiResponse){
        console.log("Login::response" + JSON.stringify(apiResponse));
        if(apiResponse.data.active){
            addAlert("usuario logeado con exito", "success", 3);
        } else{
            addAlert("usuario no encontrado", "warning", 8);
        }
        closeLoader();
    };

    const ifErrorLogin = function(data){
        addAlert("Se genero un error en el servidor", "danger", 8);
        closeLoader();
    };
    
    openLoader();
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

   isPresent = validMethods.find(function(item){
    return item === method; 
   });

   if(isPresent ==="") {
        alert("Metodo" + method + "No permitodo"); 
        return;
   }

    var jsonData = "";
    if(method === "POST" || method === "PUT") {
        jsonData = JSON.stringify(data);
    }


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

function openLoader() {
    $("#loader-mask").addClass("show");
}

function closeLoader() {
    $("#loader-mask").removeClass("show");
}

function addAlert(msg, type, time = null){

    var id = "alert_" + getRandomInt(1000, 99999);

    var html = '<div id="'+id+'" class="alert alert-'+type+'" role="alert" style="display:none">';
    html += msg;
    html += "</div>"

    

    $("#Alerts").prepend($(html));
    $("#"+id).show('fast');

    time = time===null ? 2000 : time * 1000;


    window.setTimeout(function(){
        $("#"+id).hide('fast');
    }, time);
}

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max-min+1)) + min;
}