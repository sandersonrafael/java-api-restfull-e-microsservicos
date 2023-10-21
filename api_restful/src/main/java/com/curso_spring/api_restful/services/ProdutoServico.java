package com.curso_spring.api_restful.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso_spring.api_restful.model.Produto;
import com.curso_spring.api_restful.repositories.ProdutoRepository;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> obterTodos() {
        return repository.findAll();
    }

    public Optional<Produto> /* Produto */ obterPorId(Integer id) {
        return repository.findById(id);
        // solução professor
        // return repositorio.obterPorId(id);
    }

    public Produto adicionar(Produto produto) {
        return repository.save(produto);
    }

    public Void deletar(Integer id) {
        repository.deleteById(id);
        return null;
    }

    public Produto atualizar(Integer id, Produto produto) {
        produto.setId(id);
        return repository.save(produto);
    }
}
