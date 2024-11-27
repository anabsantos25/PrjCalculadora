package Projeto.SCV.controller;

import Projeto.SCV.model.Produto;
import Projeto.SCV.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos") // Define o caminho base para os endpoints deste controlador
@CrossOrigin(origins = "http://localhost:63342") // Permite requisições do frontend na URL especificada
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService; // Injeção de dependência da camada de serviço

    // Endpoint para cadastrar um novo produto
    @PostMapping // Mapeia requisição POST para o endpoint "/produtos"
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        // Verifica se o produto já existe (baseado no ID)
        if (produto.getId() != null) {
            Optional<Produto> produtoExistente = produtoService.buscarProdutoPorId(produto.getId());
            // Se o produto já existir, retorna erro 400 (Bad Request)
            if (produtoExistente.isPresent()) {
                return ResponseEntity.badRequest().body(null); // Produto já existente
            }
        }
        // Cadastra o novo produto
        Produto novoProduto = produtoService.cadastrarProduto(produto);
        // Retorna a resposta com o novo produto cadastrado (status 200 OK)
        return ResponseEntity.ok(novoProduto);
    }

    // Endpoint para listar todos os produtos
    @GetMapping // Mapeia requisição GET para o endpoint "/produtos"
    public ResponseEntity<List<Produto>> listarProdutos() {
        // Chama o serviço para obter todos os produtos
        List<Produto> produtos = produtoService.listarProdutos();
        // Retorna a lista de produtos com status 200 OK
        return ResponseEntity.ok(produtos);
    }

    // Endpoint para buscar um produto pelo ID
    @GetMapping("/{id}") // Mapeia requisição GET para "/produtos/{id}"
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Long id) {
        // Busca o produto pelo ID no serviço
        Optional<Produto> produto = produtoService.buscarProdutoPorId(id);
        // Se o produto for encontrado, retorna com status 200 OK
        return produto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); // Se não encontrado, retorna 404 Not Found
    }

    // Endpoint para editar um produto
    @PutMapping("/{id}") // Mapeia requisição PUT para "/produtos/{id}"
    public ResponseEntity<?> editarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        // Verifica se o produto existe antes de tentar editar
        Optional<Produto> produtoExistente = produtoService.buscarProdutoPorId(id);
        if (produtoExistente.isPresent()) {
            // Se o produto existe, realiza a edição
            Produto produtoEditado = produtoService.editarProduto(id, produtoAtualizado);
            // Retorna o produto editado com status 200 OK
            return ResponseEntity.ok(produtoEditado);
        }
        // Se o produto não for encontrado, retorna erro 404 (Not Found) com mensagem
        return ResponseEntity.status(404).body("Produto com ID " + id + " não encontrado.");
    }

    // Endpoint para excluir um produto
    @DeleteMapping("/{id}") // Mapeia requisição DELETE para "/produtos/{id}"
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        // Verifica se o produto existe antes de tentar excluir
        if (produtoService.buscarProdutoPorId(id).isPresent()) {
            // Exclui o produto e retorna status 204 (No Content)
            produtoService.excluirProduto(id);
            return ResponseEntity.noContent().build();
        }
        // Se o produto não for encontrado, retorna erro 404 (Not Found)
        return ResponseEntity.notFound().build(); // Produto não encontrado
    }
}