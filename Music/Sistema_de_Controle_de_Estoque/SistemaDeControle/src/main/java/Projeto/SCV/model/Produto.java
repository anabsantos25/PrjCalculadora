package Projeto.SCV.model;

import jakarta.persistence.*; // Importa as anotações do JPA para mapeamento de banco de dados

@Entity // Define que essa classe é uma entidade que será mapeada para uma tabela no banco de dados
public class Produto {

    @Id // Define o campo 'id' como chave primária da tabela no banco de dados
    private Long id;

    @Column(unique = true) // Define que o campo 'nome' será único na tabela (não pode haver produtos com o mesmo nome)
    private String nome;

    private String descricao; // Descrição do produto
    private double preco; // Preço do produto
    private String fornecedor; // Nome do fornecedor do produto
    private int quantidade; // Quantidade disponível do produto

    // Getters e Setters são utilizados para acessar e modificar os campos da classe
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}



