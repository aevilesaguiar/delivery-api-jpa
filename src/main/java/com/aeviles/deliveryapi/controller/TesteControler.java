package com.aeviles.deliveryapi.controller;

import com.aeviles.deliveryapi.domain.model.Cozinha;
import com.aeviles.deliveryapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teste") //a requisição será realizada/teste/cozinhas/por-nome
public class TesteControler {

        @Autowired
        private CozinhaRepository cozinhaRepository;
/*
        @GetMapping("/cozinhas/por-nome")
        public List<Cozinha> cozinhaPorNome(@RequestParam("nome") String nome){//OU SEJA PARAMETRO DA REQUISIÇÃO, ou seja o nome do parametro
                                                                                //ou seja estou fazendo o biding para a variavel que estou recebendo como argumento do método
            return cozinhaRepository.consultarPorNome(nome);
        }*/

}
