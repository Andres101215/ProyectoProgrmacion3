document.addEventListener("DOMContentLoaded", function () {
    document.querySelector("#crearAfiliado").addEventListener("click", function () {
        var formAfi = document.getElementById("formAfi");
        var btnFlotante = document.getElementById("crearAfiliado");
        var afiliadosTable = document.getElementById("afiliados-table");

        afiliadosTable.style.display = "none";
        btnFlotante.style.display = "none";

        formAfi.style.display = "block";

        const xhr = new XMLHttpRequest()
        xhr.open("GET", "http://localhost:8080/demo1_war_exploded/disciplina-servlet", true)

        xhr.onreadystatechange = () => {

            if (xhr.readyState === 4 && xhr.status === 200) {
                if (xhr.responseText != 'null') {
                    const book = JSON.parse(xhr.responseText)

                    const selectElement = document.getElementById("idDisciplina");

                    book.forEach((objeto) => {
                        const objetoJSON = JSON.parse(objeto);

                        const option = document.createElement("option");

                        option.value = objetoJSON.id;

                        option.textContent = objetoJSON.disciplina;

                        selectElement.appendChild(option);

                    });
                }
            }
        }
        xhr.send(null)

        document.querySelector("#registrar").addEventListener("click", function () {
            event.preventDefault();
            const nombre = document.querySelector("#nombre").value
            const apellido = document.querySelector("#apellido").value
            const documento = document.querySelector("#documento").value
            const edad = document.querySelector("#edad").value
            const genero = document.querySelector("#genero").value
            const direccion = document.querySelector("#direccion").value
            const telefono = document.querySelector("#telefono").value
            const correo = document.querySelector("#correo").value
            const idDisciplina = document.querySelector("#idDisciplina").value

            const xhr = new XMLHttpRequest()

            xhr.open("POST", "http://localhost:8080/demo1_war_exploded/afiliado-servlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            const data = `nombre=${nombre}&apellido=${apellido}&documento=${documento}&edad=${edad}&genero=${genero}&direccion=${direccion}&telefono=${telefono}&correo=${correo}&idDisciplina=${idDisciplina}`;

            xhr.send(data);

            afiliadosTable.style.display = "table";
            formAfi.style.display = "none";
            btnFlotante.style.display = "block";
        });
    });
});

function clearMainContent() {
    var mainContent = document.getElementById("main-content");
    while (mainContent.firstChild) {
        mainContent.removeChild(mainContent.firstChild);
    }
}

document.getElementById("afiliados-link").addEventListener("click", function (event) {
    event.preventDefault();

    var mainContent = document.getElementById("main-content");
    var afiliadosTable = document.getElementById("afiliados-table");
    var crearAfiliado = document.getElementById("crearAfiliado");
    var form = document.getElementById("formAfi");

    if (afiliadosTable.style.display === "none") {
        afiliadosTable.style.display = "table";
        crearAfiliado.style.display = "block";
        mainContent.style.display = "none";
        form.style.display = "none";

    }
});


document.querySelector("#afiliados-link").addEventListener("click", () => {
    const xhr = new XMLHttpRequest();

    xhr.open("GET", "http://localhost:8080/demo1_war_exploded/afiliado-servlet", true);

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const response = xhr.responseText;
            if (response !== 'null') {
                const afiliados = JSON.parse(response);
                res = document.querySelector("#res")
                res.innerHTML = '';

                afiliados.forEach(afiliado => {
                    const objetoJSON = JSON.parse(afiliado);
                    const data1 = `idDisciplina=${objetoJSON.Disciplina.$oid}`;

                    realizarSolicitudAjax(data1, (error, response) => {
                        if (error) {
                            console.error(error);
                            return; // Salir si hay un error
                        }
                        const objetoJSON1 = JSON.parse(response);

                        res.innerHTML += `<tr>
                            <td>${objetoJSON.id}</td>
                            <td>${objetoJSON.nombre}</td>
                            <td>${objetoJSON.apellido}</td>
                            <td>${objetoJSON.documento}</td>
                            <td>${objetoJSON.correo}</td>
                            <td>${objetoJSON.edad}</td>
                            <td>${objetoJSON.telefono}</td>
                            <td>${objetoJSON.genero}</td>
                            <td>${objetoJSON.direccion}</td>
                            <td>${objetoJSON1.disciplina}</td>
                            <td><button type="button" class="btn btn-outline-primary" id="formEdit">Editar</button></td>
                        </tr>`;
                    });
                });
            }
        }
    };

    xhr.send(null);
});
function realizarSolicitudAjax(data, callback) {
    const xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/demo1_war_exploded/disciplina2-servlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onload = function () {
        if (xhr.status >= 200 && xhr.status < 300) {
            const response = JSON.parse(xhr.responseText);
            callback(null, response);
        } else {
            callback('Error al realizar la solicitud: ' + xhr.statusText, null);
        }
    };

    xhr.onerror = function () {
        callback('Error de red al realizar la solicitud.', null);
    };

    xhr.send(data);
}