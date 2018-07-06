/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//  RIES

document.getElementById("formulario").onsubmit = function (event) {
    event.preventDefault();
    console.log("Salvar Animal");
    SalvaAnimal();
    limparCamposFormulario();//Adicionar no final da requisição do POST          
    buscarAnimal(); //Adicionar no final da requisição do POST
};

var elementoBody = document.querySelector("body");
elementoBody.onload = buscarAnimal;



function buscarAnimal() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let main = document.querySelector("main");

            let listaAnimais = JSON.parse(this.responseText);
            console.log(listaAnimais);
            montarHTML(listaAnimais);
        }
    };

    xhttp.open("GET", "http://localhost:8080/VeterinariaAPI/api/animal/", true);
    xhttp.send();
}

function montarHTML(listaAnimais) {
    document.querySelector("table").innerHTML = `<tr>
                <th>nome</th>                
                <th>idade</th>
                <th>porte</th> 
                <th>Vacinas</th>
             </tr> `;

    for (let ind in listaAnimais) {
        let tr = document.createElement("tr");
        let linha =
            `       <td>${listaAnimais[ind].idade} Anos</td>
                    <td>${listaAnimais[ind].nome}</td>
                    <td>${listaAnimais[ind].porte}</td>
                    <td>${listaAnimais[ind].vacinas}</td>
                `;
        tr.innerHTML = linha;
        document.querySelector("table").appendChild(tr);

    }
}

function enviaAnimal(animal) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            limparCamposFormulario();
            buscarAnimal();
        }
    }

    xhttp.open("POST", "http://localhost:8080/VeterinariaAPI/api/animal", true);
    xhttp.setRequestHeader("content-type", "application/json");
    xhttp.send(JSON.stringify(animal));
}

function SalvaAnimal() {
    let nome = document.getElementById("nome").value;    
    let idade = document.getElementById("idade").value;
    let porte = document.getElementById("porte").value;
    let vacinas = document.getElementById("vacinas").value;

    let animal = {};
    animal.nome = nome;
    animal.idade = idade;
    animal.porte = porte;
    animal.vacinas = vacinas;

    enviaAnimal(animal);
}
function limparCamposFormulario() {
    document.getElementById("nome").value = "";
    document.getElementById("idade").value = "";
    document.getElementById("porte").value = "";
    document.getElementById("vacinas").value = "";
}