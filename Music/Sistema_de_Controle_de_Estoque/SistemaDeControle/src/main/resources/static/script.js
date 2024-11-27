let modoEdicao = false;

function adicionarProduto() {
    document.getElementById('form-adicionar').style.display = 'block'; // Exibe o formulário de adicionar
    document.getElementById('form-adicionar').querySelector('h2').innerText = 'Adicionar Produto'; // Garantir que o título esteja correto
}

function salvarProduto(event) {
    event.preventDefault(); // Evita o envio padrão do formulário

    const produto = {
        id: document.getElementById('id').value,
        nome: document.getElementById('nome').value,
        descricao: document.getElementById('descricao').value,
        quantidade: document.getElementById('quantidade').value,
        preco: document.getElementById('preco').value,
        fornecedor: document.getElementById('fornecedor').value,
    };

    if (modoEdicao) {
        // Atualiza o produto existente
        fetch(`http://localhost:8080/produtos/${produto.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(produto)
        })
            .then(response => {
                if (!response.ok) {
                    if (response.status === 400) {
                        throw new Error("Erro: O ID informado já existe ou o produto é inválido.");
                    }
                    throw new Error("Erro ao atualizar o produto.");
                }
                return response.json();
            })
            .then(data => {
                console.log('Produto alterado:', data);
                alert('Produto alterado com sucesso!');
                document.getElementById('form-adicionar').style.display = 'none'; // Fecha o formulário
                document.getElementById('id').disabled = false; // Reativa o campo ID
                modoEdicao = false; // Reseta o modo
                carregarEstoque(); // Recarrega a tabela
            })
            .catch(error => {
                console.error('Erro ao alterar produto:', error);
                alert(error.message);
            });
    } else {
        // Adiciona um novo produto
        fetch('http://localhost:8080/produtos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(produto)
        })
            .then(response => {
                if (!response.ok) {
                    if (response.status === 400) {
                        throw new Error("Erro: Produto com o mesmo nome já existe ou os dados são inválidos.");
                    }
                    throw new Error("Erro ao adicionar o produto.");
                }
                return response.json();
            })
            .then(data => {
                console.log('Produto adicionado:', data);
                alert('Produto adicionado com sucesso!');
                document.getElementById('form-adicionar').style.display = 'none'; // Fecha o formulário
                carregarEstoque(); // Recarrega a tabela
            })
            .catch(error => {
                console.error('Erro ao adicionar produto:', error);
                alert(error.message);
            });
    }
}

function alterarProduto() {
    const id = prompt("Digite o ID do produto a ser alterado:");
    if (!id) return;

    // Busca o produto no banco de dados
    fetch(`http://localhost:8080/produtos/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Produto não encontrado.");
            }
            return response.json();
        })
        .then(produto => {
            // Preenche o formulário com os dados do produto
            document.getElementById('id').value = produto.id;
            document.getElementById('id').disabled = true; // Bloqueia edição do ID
            document.getElementById('nome').value = produto.nome;
            document.getElementById('descricao').value = produto.descricao;
            document.getElementById('quantidade').value = produto.quantidade;
            document.getElementById('preco').value = produto.preco;
            document.getElementById('fornecedor').value = produto.fornecedor;

            // Exibe o formulário e altera o título para 'Alterar Produto'
            document.getElementById('form-adicionar').style.display = 'block';
            document.getElementById('form-adicionar').querySelector('h2').innerText = 'Alterar Produto';
            modoEdicao = true; // Ativa o modo de edição
        })
        .catch(error => {
            console.error('Erro ao buscar produto:', error);
            alert(error.message);
        });
}

function excluirProduto() {
    const id = prompt("Digite o ID do produto a ser excluído:");
    if (!id) return;

    // Envia a requisição para o backend para excluir o produto
    fetch(`http://localhost:8080/produtos/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao excluir o produto: ID não encontrado ou erro no servidor.');
            }
            alert('Produto excluído com sucesso!');
            carregarEstoque(); // Atualiza a tabela com os dados
        })
        .catch(error => {
            console.error('Erro ao excluir produto:', error);
            alert(error.message);
        });
}

function carregarEstoque() {
    fetch('http://localhost:8080/produtos')
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao carregar os produtos.");
            }
            return response.json();
        })
        .then(produtos => {
            const tabela = document.getElementById('estoque-table').getElementsByTagName('tbody')[0];
            tabela.innerHTML = ''; // Limpa a tabela antes de preencher

            produtos.forEach(produto => {
                const row = tabela.insertRow();
                row.innerHTML = `
                    <td>${produto.id}</td>
                    <td>${produto.nome}</td>
                    <td>${produto.descricao}</td>
                    <td>${produto.quantidade}</td>
                    <td>${produto.preco}</td>
                    <td>${produto.fornecedor}</td>
                `;
            });
        })
        .catch(error => {
            console.error('Erro ao carregar o estoque:', error);
            alert(error.message);
        });
}

document.getElementById('form-adicionar').addEventListener('reset', () => {
    modoEdicao = false;
    document.getElementById('id').disabled = false; // Reativa o campo ID
    document.getElementById('form-adicionar').querySelector('h2').innerText = 'Adicionar Produto'; // Resetando o título
});

// Carregar os dados assim que a página for carregada
document.addEventListener('DOMContentLoaded', carregarEstoque);

