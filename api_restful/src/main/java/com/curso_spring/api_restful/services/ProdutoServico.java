package com.curso_spring.api_restful.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso_spring.api_restful.model.Produto;
import com.curso_spring.api_restful.repositories.ProdutoRepositorio;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepositorio repositorio;

    public List<Produto> obterTodos() {
        return repositorio.obterTodos();
    }

    public /* Optional<Produto> */ Produto obterPorId(Integer id) {
        return repositorio.obterPorId(id);
        // solução professor
        // return repositorio.obterPorId(id);
    }

    public Produto adicionar(Produto produto) {
        return repositorio.adicionar(produto);
    }

    public Void deletar(Integer id) {
        return repositorio.deletar(id);
    }

    public Produto atualizar(Integer id, Produto produto) {
        produto.setId(id);
        return repositorio.atualizar(produto);
    }
}
