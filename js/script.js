const backurl = "http://localhost:8080";
/*
function loadDeputadoInfo() {
    var id = window.location.hash.substr(1); 

    fetch(backurl + `/deputados/${id}`) // Supondo que sua API siga um padrão RESTful
        .then(response => response.json())
        .then(data => {
            document.getElementById('deputadoName').innerText = `Deputado ${data.name}`;

        })
        .catch(error => {
            console.error('Erro ao carregar informações do deputado:', error);
        });
}
function loadEventos() {
    var id = window.location.hash.substr(1); 

    fetch(backurl + `/deputados/eventos/${id}`) 
        .then(response => response.json())
    const eventos = ['Evento 1', 'Evento 2', 'Evento 3'];

    const eventosList = document.getElementById('eventosList');
    eventosList.innerHTML = ''; 
    eventos.forEach(evento => {
        const li = document.createElement('li');
        li.innerText = evento;
        eventosList.appendChild(li);
    });
}
*/
// deputados
function cadastrarDeputado() {
    const nome = document.getElementById('nome').value;
    const siglaP = document.getElementById('siglaPartido').value;
    const siglaUF = document.getElementById('siglaUF').value;
    const idLegis = document.getElementById('idLegis').value;
    const email =  document.getElementById('email').value;

    const dadosDeputado = {
        nome: nome,
        siglaPartido: siglaP,
        siglaUF: siglaUF,
        idLegis: idLegis,
        email: email

    };

    fetch(backurl + '/deputados/cadastrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dadosDeputado)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Resposta do servidor:', data);
    })
    .catch(error => {
        console.error('Erro ao cadastrar deputado:', error);

    });
}
function carregarListaDeputados() {
    fetch(backurl + '/deputados', {
        method: 'GET',
        mode: 'no-cors',
        headers: {
            'Accept': 'application/json',
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro na solicitação: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Dados da resposta:', data);
            const listaDeputadosElement = document.getElementById('listaDeputados');
            listaDeputadosElement.innerHTML = '';
            data.forEach(deputado => {
                const listItem = document.createElement('li');
                const link = document.createElement('a');
                link.href = `eventsList.html#${deputado.id}`;
                link.textContent = `Deputado ${deputado.name}`;
                listItem.appendChild(link);
                listaDeputadosElement.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Erro ao carregar lista de deputados:', error);
        });
}
// document.addEventListener('DOMContentLoaded', carregarListaDeputados);

function detalharDeputado(id) {
    fetch(backurl + `/deputados/${id}`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        console.log('Detalhes do deputado:', data);
        // Faça algo com os detalhes do deputado, se necessário
    })
    .catch(error => {
        console.error('Erro ao detalhar deputado:', error);
    });
}
function listarEventosPorDeputado(id) {
    fetch(backurl + `/deputados/eventos/${id}`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        console.log('Lista de eventos por deputado:', data);
        // Faça algo com a lista de eventos, se necessário
    })
    .catch(error => {
        console.error('Erro ao listar eventos por deputado:', error);
    });
}
function vincularEvento(id, dados) {
    fetch(backurl + `deputados/eventos/${id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Resposta do vínculo de evento:', data);
        // Faça algo com a resposta, se necessário
    })
    .catch(error => {
        console.error('Erro ao vincular evento:', error);
    });
}

// eventos
function cadastrarEvento(dados) {
    fetch(backurl + '/eventos/cadastrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Resposta do cadastro de evento:', data);
        // Faça algo com a resposta, se necessário
    })
    .catch(error => {
        console.error('Erro ao cadastrar evento:', error);
    });
}
function listarEventos() {
    fetch(backurl + '/eventos', {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        console.log('Lista de eventos:', data);
        // Faça algo com a lista de eventos, se necessário
    })
    .catch(error => {
        console.error('Erro ao listar eventos:', error);
    });
}
function editarEvento(id, dados) {
    fetch(backurl + `/eventos/editar/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dados),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Resposta da edição de evento:', data);
        // Faça algo com a resposta, se necessário
    })
    .catch(error => {
        console.error('Erro ao editar evento:', error);
    });
}
function deletarEvento(id) {
    fetch(backurl + `/eventos/deletar/${id}`, {
        method: 'DELETE',
    })
    .then(response => response.json())
    .then(data => {
        console.log('Resposta da exclusão de evento:', data);
        // Faça algo com a resposta, se necessário
    })
    .catch(error => {
        console.error('Erro ao deletar evento:', error);
    });
}





