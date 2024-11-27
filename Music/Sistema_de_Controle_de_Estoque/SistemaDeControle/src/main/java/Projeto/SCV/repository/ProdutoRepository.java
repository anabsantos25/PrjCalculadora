package Projeto.SCV.repository;

import Projeto.SCV.model.Produto; // Importa o modelo de Produto para ser utilizado no repositório
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository para realizar operações de CRUD no banco de dados
import org.springframework.stereotype.Repository; // Anotação que define a classe como um repositório Spring

@Repository // Define que essa interface é um repositório do Spring, que pode ser injetado em outros componentes
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // A interface ProdutoRepository herda JpaRepository, o que permite realizar operações básicas de CRUD (Create, Read, Update, Delete)
    // O tipo de entidade (Produto) e o tipo da chave primária (Long) são passados como parâmetros para o JpaRepository
}



