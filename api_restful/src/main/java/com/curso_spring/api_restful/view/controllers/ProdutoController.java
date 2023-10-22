package com.curso_spring.api_restful.view.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso_spring.api_restful.services.ProdutoServico;
import com.curso_spring.api_restful.shared.ProdutoDTO;
import com.curso_spring.api_restful.view.model.ProdutoRequest;
import com.curso_spring.api_restful.view.model.ProdutoResponse;

@RestController
@RequestMapping(value = "/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoServico servico;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodos() {
        List<ProdutoDTO> produtosDto = servico.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<ProdutoResponse> produtosResponse = produtosDto.stream().map(produtoDto -> {
            return mapper.map(produtoDto, ProdutoResponse.class);
        }).collect(Collectors.toList());

        return new ResponseEntity<>(produtosResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> obterPorId(@PathVariable Integer id) {
        // try {
            Optional<ProdutoDTO> produtoDto = servico.obterPorId(id);

            ModelMapper mapper = new ModelMapper();

            ProdutoResponse produtoResponse = mapper.map(produtoDto.get(), ProdutoResponse.class);

            return new ResponseEntity<>(produtoResponse, HttpStatus.OK);
        // } catch (Exception e) {
        //     new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoRequest) {
        ModelMapper mapper = new ModelMapper();

        ProdutoDTO produtoDTO = mapper.map(produtoRequest, ProdutoDTO.class);

        produtoDTO = servico.adicionar(produtoDTO);

        ProdutoResponse produtoResponse = mapper.map(produtoDTO, ProdutoResponse.class);

        return new ResponseEntity<>(produtoResponse, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Integer id, @RequestBody ProdutoRequest produtoRequest) {
        ModelMapper mapper = new ModelMapper();

        ProdutoDTO produtoDto = mapper.map(produtoRequest, ProdutoDTO.class);

        produtoDto = servico.atualizar(id, produtoDto);

        return new ResponseEntity<>(mapper.map(produtoDto, ProdutoResponse.class), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        servico.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
