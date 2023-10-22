package com.curso_spring.api_restful.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso_spring.api_restful.model.Produto;
import com.curso_spring.api_restful.model.exceptions.ResourceNotFoundException;
import com.curso_spring.api_restful.repositories.ProdutoRepository;
import com.curso_spring.api_restful.shared.ProdutoDTO;

@Service
public class ProdutoServico {

    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoDTO> obterTodos() {
        List<Produto> produtos = repository.findAll();
        return produtos.stream()
            .map(produto -> new ModelMapper()
            .map(produto, ProdutoDTO.class))
            .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> obterPorId(Integer id) {
        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        ProdutoDTO produtoDto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        return Optional.of(produtoDto);
    }

    public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
        produtoDto.setId(null);

        ModelMapper mapper = new ModelMapper();
        Produto produto = mapper.map(produtoDto, Produto.class);

        produto = repository.save(produto);

        produtoDto.setId(produto.getId());

        return produtoDto;
    }

    public Void deletar(Integer id) {
        Optional<Produto> produto = repository.findById(id);

        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado na base de dados.");
        }

        repository.deleteById(id);
        return null;
    }

    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {
        produtoDto.setId(id);

        ModelMapper mapper = new ModelMapper();

        Produto produto = mapper.map(produtoDto, Produto.class);

        repository.save(produto);

        return produtoDto;
    }
}
