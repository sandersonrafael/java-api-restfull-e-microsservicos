package com.curso_spring.api_restful.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso_spring.api_restful.model.Produto;
import com.curso_spring.api_restful.services.ProdutoServico;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoServico servico;

    @GetMapping
    public List<Produto> obterTodos() {
        return servico.obterTodos();
    }

    @GetMapping(value = "/{id}")
    public Produto obterPorId(@PathVariable Integer id) {
        return servico.obterPorId(id).get();
    }

    @PostMapping
    public Produto adicionar(@RequestBody Produto produto) {
        return servico.adicionar(produto);
    }

    @PutMapping(value = "/{id}")
    public Produto atualizar(@PathVariable Integer id, @RequestBody Produto produto) {
        return servico.atualizar(id, produto);
    }

    @DeleteMapping(value = "/{id}")
    public String deletar(@PathVariable Integer id) {
        servico.deletar(id);
        return "Produto " + id + " deletado com sucesso";
    }
}
