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
            const data = `nombre=${nombre}&apellido=${apellido}&documento=${documento}&edad=${edad}&genero=${genero}&direccion=${direccion}&telefono=${telefono}&correo=${correo}&idDisciplina=${idDisciplina}&stat=${"1"}`;

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
        document.getElementById("disciplinas-table").style.display = "none";
        document.getElementById("crearDisciplina").style.display = "none"
    }
});


document.addEventListener("DOMContentLoaded", function () {
    document.querySelector("#afiliados-link").addEventListener("click", () => {
        document.getElementById("editForm").style.display = "none"
        const xhr = new XMLHttpRequest();

        xhr.open("GET", "http://localhost:8080/demo1_war_exploded/afiliado-servlet", true);

        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                const response = xhr.responseText;
                if (response !== 'null') {
                    const afiliados = JSON.parse(response);

                    afiliados.forEach(afiliado => {
                        res = document.querySelector("#res")
                        res.innerHTML = '';
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


document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("afiliados-table").addEventListener("click", function (event) {
        if (event.target && event.target.id === "formEdit") {
            var filaAfiliado = event.target.closest("tr");
            var idAfiliado = filaAfiliado.querySelector("td:first-child").innerText;
            var nombreAfiliado = filaAfiliado.querySelector("td:nth-child(2)").innerText;
            var apellidoAfiliado = filaAfiliado.querySelector("td:nth-child(3)").innerText;
            var documentoAfiliado = filaAfiliado.querySelector("td:nth-child(4)").innerText;
            var correoAfiliado = filaAfiliado.querySelector("td:nth-child(5)").innerText;
            var edadAfiliado = filaAfiliado.querySelector("td:nth-child(6)").innerText;
            var telefonoAfiliado = filaAfiliado.querySelector("td:nth-child(7)").innerText;
            var generoAfiliado = filaAfiliado.querySelector("td:nth-child(8)").innerText;
            var direccionAfiliado = filaAfiliado.querySelector("td:nth-child(9)").innerText;
            var disciplinaAfiliado = filaAfiliado.querySelector("td:nth-child(10)").innerText;

            document.getElementById("editForm").style.display = "block";
            document.getElementById("afiliados-table").style.display = "none";
            document.getElementById("crearAfiliado").style.display = "none";

            document.getElementById("editId").value = idAfiliado;
            document.getElementById("editId").readOnly = true;
            document.getElementById("editNombre").value = nombreAfiliado;
            document.getElementById("editApellido").value = apellidoAfiliado;
            document.getElementById("editDocumento").value = documentoAfiliado;
            document.getElementById("editCorreo").value = correoAfiliado;
            document.getElementById("editEdad").value = edadAfiliado;
            document.getElementById("editTelefono").value = telefonoAfiliado;
            document.getElementById("editGenero").value = generoAfiliado;
            document.getElementById("editDireccion").value = direccionAfiliado;
            //document.getElementById("editIdDisciplina").value = disciplinaAfiliado;

            const xhr = new XMLHttpRequest()
            xhr.open("GET", "http://localhost:8080/demo1_war_exploded/disciplina-servlet", true)

            xhr.onreadystatechange = () => {

                if (xhr.readyState === 4 && xhr.status === 200) {
                    if (xhr.responseText != 'null') {
                        const book = JSON.parse(xhr.responseText)

                        const selectElement = document.getElementById("editIdDisciplina");

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
            event.preventDefault();
        }
    });
});

document.querySelector("#actualizar").addEventListener("click", () => {
    const xhr = new XMLHttpRequest()

    idAfiliado = document.getElementById("editId").value
    nombre = document.getElementById("editNombre").value
    apellido = document.getElementById("editApellido").value
    documento = document.getElementById("editDocumento").value
    correo = document.getElementById("editCorreo").value
    edad = document.getElementById("editEdad").value
    telefono = document.getElementById("editTelefono").value
    genero = document.getElementById("editGenero").value
    direccion = document.getElementById("editDireccion").value
    idDisciplina = document.getElementById("editIdDisciplina").value

    xhr.open("POST", "http://localhost:8080/demo1_war_exploded/afiliado-servlet", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    const data = `id=${idAfiliado}&nombre=${nombre}&apellido=${apellido}&documento=${documento}&edad=${edad}&genero=${genero}&direccion=${direccion}&telefono=${telefono}&correo=${correo}&idDisciplina=${idDisciplina}&stat=${"2"}`;

    xhr.send(data);
});

document.querySelector("#eliminar").addEventListener("click", () => {
    if (confirm("¿Estás seguro de que deseas eliminar este afiliado?")) {
        const xhr = new XMLHttpRequest()

        idAfiliado = document.getElementById("editId").value
        nombre = document.getElementById("editNombre").value
        apellido = document.getElementById("editApellido").value
        documento = document.getElementById("editDocumento").value
        correo = document.getElementById("editCorreo").value
        edad = document.getElementById("editEdad").value
        telefono = document.getElementById("editTelefono").value
        genero = document.getElementById("editGenero").value
        direccion = document.getElementById("editDireccion").value
        idDisciplina = document.getElementById("editIdDisciplina").value

        xhr.open("POST", "http://localhost:8080/demo1_war_exploded/afiliado-servlet", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        alert(idAfiliado)
        const data = `id=${idAfiliado}&nombre=${nombre}&apellido=${apellido}&documento=${documento}&edad=${edad}&genero=${genero}&direccion=${direccion}&telefono=${telefono}&correo=${correo}&idDisciplina=${idDisciplina}&stat=${"3"}`;
        xhr.send(data);
    }
});

document.querySelector("#disciplinas-link").addEventListener("click", function (event) {
    event.preventDefault();

    var mainContent = document.getElementById("main-content");
    var disciplinastable = document.getElementById("disciplinas-table");
    var crearDisciplina = document.getElementById("crearDisciplina");
    var form = document.getElementById("formAfi");

    if (disciplinastable.style.display === "none") {
        disciplinastable.style.display = "table";
        crearDisciplina.style.display = "block";
        mainContent.style.display = "none";
        form.style.display = "none";
    }
});

//Tabla de Disciplinas
document.addEventListener("DOMContentLoaded", function () {

    document.querySelector("#disciplinas-link").addEventListener("click", function (event) {

        const xhr = new XMLHttpRequest();

        xhr.open("GET", "http://localhost:8080/demo1_war_exploded/disciplina-servlet", true);

        xhr.onreadystatechange = () => {
            if (xhr.readyState === 4 && xhr.status === 200) {
                const response = xhr.responseText;
                if (response !== 'null') {
                    const disciplinas = JSON.parse(response);

                    disciplinas.forEach(disciplina => {
                        res = document.querySelector("#dis")
                        dis.innerHTML = '';
                        const objetoJSON = JSON.parse(disciplina);

                        const arraymiem = objetoJSON.miembros

                        traermiembros(arraymiem)
                            .then((miembros) => {
                                res.innerHTML += `<tr>
                            <td>${objetoJSON.id}</td>
                            <td>${objetoJSON.disciplina}</td>
                            <td>${miembros}</td>
                            <td>${objetoJSON.miembros}</td>
                            <td><button type="button" class="btn btn-outline-primary" id="formEdit">Editar</button></td>
                        </tr>`;
                            })

                    });
                }
            }
        };

        xhr.send(null);
    });
});

function traermiembros(arraymiem) {
    let promesas = []; // Array para almacenar todas las promesas de las solicitudes AJAX
    let miem = ''; // Variable para almacenar el resultado final

    arraymiem.forEach((objeto) => {
        const data = `objectId=${objeto.$oid}`;
        const promesa = new Promise((resolve, reject) => {
            realizarSolicitudAjaxDisciplinas(data, (error, response) => {
                if (error) {
                    reject(error);
                } else {
                    const objetoJSON1 = JSON.parse(response);
                    resolve(`${objetoJSON1.nombre} ${objetoJSON1.apellido}`);
                }
            });
        });
        promesas.push(promesa);
    });

    // Esperar a que todas las promesas se resuelvan
    return Promise.all(promesas)
        .then((resultados) => {
            miem = resultados.join('\n'); // Unir los resultados con saltos de línea
            return miem; // Devolver el resultado final
        })
        .catch((error) => {
            console.error('Error en una o más solicitudes AJAX:', error);
        });
}

function realizarSolicitudAjaxDisciplinas(data, callback) {
    const xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/demo1_war_exploded/afiliado2-servlet", true);
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


//agregar disciplina emergente
var botonAgregarDisciplina = document.getElementById('crearDisciplina');
var modal = document.getElementById('modal');

botonAgregarDisciplina.addEventListener('click', function () {
    modal.style.display = "block";
    botonGuardar.addEventListener('click', function (event) {
        event.preventDefault();
        document.getElementById("disciplinas-link").click();

        // Aquí puedes agregar el código para guardar la disciplina
        var modal = document.getElementById('modal');
        modal.style.display = 'none';
    });
});


window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

var botonGuardar = document.getElementById('agregarDisciplina');

