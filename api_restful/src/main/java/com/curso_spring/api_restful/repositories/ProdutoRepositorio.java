package com.curso_spring.api_restful.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.curso_spring.api_restful.model.Produto;
import com.curso_spring.api_restful.model.exceptions.ResourceNotFoundException;

@Repository
public class ProdutoRepositorio {

    private List<Produto> produtos = new ArrayList<>();
    private Integer ultimoId = 0;

    /**
     * Método que retorna uma lista com todos os produtos cadastrados
     * @return produtos
     */
    public List<Produto> obterTodos() {
        return produtos;
    }

    /**
     * Método que retorna um produto com base no seu id
     * @param id do produto
     * @return Produto da base de dados
     */
    public /* Optional<Produto> */ Produto obterPorId(Integer id) {
        // minha solução
        Produto produtoValidacao = new Produto();
        produtoValidacao.setId(id);
        return produtos.get(produtos.indexOf(produtoValidacao));

        // solução da aula
        // return produtos.stream().filter((Produto p) -> p.getId() == id).findFirst().get();
    }

    /**
     * Recebe um produto e cadastra na lista da base de dados
     * @param produto que será adicionado
     * @return produto adicionado
     */
    public Produto adicionar(Produto produto) {
        produto.setId(++ultimoId);
        produtos.add(produto);
        return produto;
    }

    /**
     * Atualiza um determinado produto de acordo com as novas informações fornecidas pelo usuário
     * @param produto
     * @return
     */
    public Produto atualizar(Produto produto) {
        int indice = produtos.indexOf(produto);
        if (indice == -1) throw new ResourceNotFoundException("Produto não encontrado");
        produtos.set(indice, produto);
        return produto;
        // método do professor -> encontrar produto por id, deletar e depois salvar novamente com o mesmo id mas com
        // as novas informações
    }

    /**
     * Método para deletar um produto baseado no id informado
     * @param id informado
     * @return void
     */
    public Void deletar(Integer id) {
        // minha solução
        Produto produtoValidacao = new Produto();
        produtoValidacao.setId(id);
        produtos.remove(produtoValidacao);
        return null;

        // solução professor
        // produtos.removeIf(x -> x.getId() == id);
        // return null;
    }
}
