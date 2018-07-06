/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//  RIES

var path = "http://localhost:8080/VeterinariaAPI/api/agenda";

document.getElementById("formulario").onsubmit = function (event) {
    event.preventDefault();
    console.log("Salvar Agenda");
    SalvaAgenda();
    limparCamposFormulario();//Adicionar no final da requisição do POST          
    buscaAgenda(); //Adicionar no final da requisição do POST
};

var elementoBody = document.querySelector("body");
elementoBody.onload = buscaAgenda();



function buscaAgenda() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let main = document.querySelector("main");

            let listaAgenda = JSON.parse(this.responseText);
            console.log(listaAgenda);
            montarHTML(listaAgenda);
        }
    };

    xhttp.open("GET", path, true);
    xhttp.send();
}

function montarHTML(listaAgenda) {
    document.querySelector("table").innerHTML = `
                <th>data</th>
                <th>descricao</th>
                <th>status</th>
              `;

    for (let ind in listaAgenda) {
        let tr = document.createElement("tr");
        let linha =`<td>${listaAgenda[ind].data}</td>
                    <td>${listaAgenda[ind].descricao}</td>
                    <td>${listaAgenda[ind].status}</td>
                `;
        tr.innerHTML = linha;
        document.querySelector("table").appendChild(tr);

    }
}

function enviaAgenda(agenda) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            limparCamposFormulario();
            buscaAgenda();
        }
    }

    xhttp.open("POST", path, true);
    xhttp.setRequestHeader("content-type", "application/json");
    xhttp.send(JSON.stringify(agenda));
}

function SalvaAgenda() {
    let data = document.getElementById("data").value;
    let hora = document.getElementById("hora").value;
    let data_hora = data + " " + hora;
    let descricao = document.getElementById("descricao").value;
    let status = document.getElementById("status").value;

    let agenda = {};
    agenda.data = data_hora;
    agenda.descricao = descricao;
    agenda.status = status;

    enviaAgenda(agenda);
}
function limparCamposFormulario() {
    document.getElementById("data").value = "";
    document.getElementById("hora").value = "";
    document.getElementById("descricao").value = "";
    document.getElementById("status").value = "";
}