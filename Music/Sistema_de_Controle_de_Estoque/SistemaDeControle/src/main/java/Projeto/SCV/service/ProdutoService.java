package Projeto.SCV.service;

import Projeto.SCV.model.Produto;
import Projeto.SCV.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired // Anotação que faz a injeção automática da dependência do ProdutoRepository
    private ProdutoRepository produtoRepository; // Instância do repositório para interagir com o banco de dados de produtos

    // Método para cadastrar um novo produto no banco de dados
    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto); // Salva o produto e retorna o produto salvo (com ID gerado, por exemplo)
    }

    // Método para listar todos os produtos do banco de dados
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll(); // Retorna uma lista de todos os produtos armazenados
    }

    // Método para buscar um produto por seu ID
    public Optional<Produto> buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id); // Retorna um Optional contendo o produto encontrado ou vazio se não encontrado
    }

    // Método para editar um produto, dado seu ID e os novos dados
    public Produto editarProduto(Long id, Produto produtoAtualizado) {
        // Verifica se o produto com o ID existe no banco de dados
        if (produtoRepository.existsById(id)) {
            produtoAtualizado.setId(id); // Define o ID do produto atualizado
            return produtoRepository.save(produtoAtualizado); // Salva o produto atualizado e retorna o produto salvo
        }
        return null; // Se o produto não existir, retorna null
    }

    // Método para excluir um produto dado seu ID
    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id); // Deleta o produto com o ID especificado do banco de dados
    }
}



