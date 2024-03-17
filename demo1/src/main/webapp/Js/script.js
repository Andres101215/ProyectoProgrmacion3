document.addEventListener("DOMContentLoaded", function () {
    document.querySelector("#crearAfiliado").addEventListener("click", function () {
        var formAfi = document.getElementById("formAfi");
        var afiliadostabla = document.getElementById("afiliadostabla");

        if(formAfi.style.display = "block"){
            afiliadostabla.style.display="none";
        }
        

        const xhr = new XMLHttpRequest()
        xhr.open("GET", "http://localhost:8080/demo1_war_exploded/disciplina-servlet", true)

        xhr.onreadystatechange = () => {

            if (xhr.readyState === 4 && xhr.status === 200) {
                if (xhr.responseText != 'null') {
                    const book = JSON.parse(xhr.responseText)

                    console.log(book)

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
        });
    });
    afiliadostabla.style.display="block";
});

function clearMainContent() {
    var mainContent = document.getElementById("main-content");
    while (mainContent.firstChild) {
        mainContent.removeChild(mainContent.firstChild);
    }
}

document.getElementById("afiliados-link").addEventListener("click", function(event) {
    event.preventDefault();

    var mainContent = document.getElementById("main-content");
    var afiliadosTable = document.getElementById("afiliados-table");
    var crearAfiliado = document.getElementById("crearAfiliado");

    if (afiliadosTable.style.display === "none") {
        afiliadosTable.style.display = "block";
        crearAfiliado.style.display = "block";
        mainContent.style.display = "none";
    }
});
document.querySelector("#btn").addEventListener("click", () => {
    const xhr = new XMLHttpRequest();

    xhr.open("GET", "http://localhost:8080/demo1_war_exploded/afiliado-servlet", true);

    xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
            const response = xhr.responseText;
            if (response !== 'null') {
                const afiliados = JSON.parse(response);
                fillTable(afiliados);
            } else {
                alert("No hay datos disponibles.");
            }
        }
    };

    xhr.send(null);
});

function fillTable(afiliados) {
    const tableBody = document.querySelector("#afiliados-table tbody");
    tableBody.innerHTML = "";

    afiliados.forEach(afiliado => {
        const row = document.createElement("tr");

        const properties = ["id", "nombre", "apellido", "documento", "edad", "genero", "direccion", "telefono", "correo", "disciplina"];
        properties.forEach(property => {
            const cell = document.createElement("td");
            cell.textContent = afiliado[property];
            row.appendChild(cell);
        });

        tableBody.appendChild(row);
    });

    const table = document.querySelector("#afiliados-table");
    table.style.display = "block";
}