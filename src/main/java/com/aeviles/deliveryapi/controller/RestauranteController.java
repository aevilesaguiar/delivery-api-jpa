package com.aeviles.deliveryapi.controller;


import com.aeviles.deliveryapi.domain.exception.EntidadeEmUsoException;
import com.aeviles.deliveryapi.domain.exception.EntidadeNaoEncontradaException;
import com.aeviles.deliveryapi.domain.model.Restaurante;
import com.aeviles.deliveryapi.domain.repository.RestauranteRepository;
import com.aeviles.deliveryapi.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController //essa anotação é um controlador e possui @Response body
@RequestMapping(value = "/restaurantes")//todas as requisições /restaurantes vão cair nessa requisição
public class RestauranteController {


        @Autowired
        private RestauranteRepository restauranteRepository;

        @Autowired
        private RestauranteService restauranteService;

        @GetMapping
        public List<Restaurante> findAll() {
                return restauranteRepository.findAll();
        }


        @GetMapping(value = "/{restauranteId}")
        public ResponseEntity<Restaurante> findById(@PathVariable Long restauranteId) {

                Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);

                if (restaurante.isPresent()) { //se a cozinha não está nulo, ou seja existe

                        return ResponseEntity.ok(restaurante.get());   //retorne a representação da cozinha
                }

                return ResponseEntity.notFound().build();  //caso contrário retorne NOTfOUND sem corpo algum

        }

        //usaremos responseEntity porque iremos customizar as respostas de status http

        @PostMapping //se eu colocar o coringa "?" aí eu posso dar um Response intety com um corpo de qualquer tipo
        public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
                try {
                        restaurante = restauranteService.salvar(restaurante);

                        return ResponseEntity.status(HttpStatus.CREATED)
                                .body(restaurante);
                } catch (EntidadeNaoEncontradaException e) {
                        return ResponseEntity.badRequest()
                                .body(e.getMessage());
                }
        }

        @PutMapping("/{restauranteId}")
        public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {

                try {
                        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
                        //Existe alguma coisa dentro desse Optional?
                        if (restauranteAtual.isPresent()) {
                                BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");

                                Restaurante restauranteSalvo = restauranteService.salvar(restauranteAtual.get());
                                return ResponseEntity.ok(restauranteAtual);
                        }


                        return ResponseEntity.notFound().build(); //400

                } catch (EntidadeNaoEncontradaException e) {
                        return ResponseEntity.badRequest().body(e.getMessage());//404
                }


        }

        //referencia apenas - achei dificil
        @PatchMapping("/{restauranteId}")
        public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                                  @RequestBody Map<String, Object> campos) {

                Restaurante restauranteAtual = restauranteRepository.findById(restauranteId).orElse(null);

                if (restauranteAtual==null) {
                        return ResponseEntity.notFound().build();
                }

                merge(campos, restauranteAtual);

                return atualizar(restauranteId, restauranteAtual);
        }

        private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

                //do jackson que faz a conversão de objetos para json, ou se json para objetos (responsavel por converter/serializar
                ObjectMapper objectMapper = new ObjectMapper();

                Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);


                //eu preciso de dados origem  apenas para saber o que o consumidor da api quer atualizar
                dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                        //Field classe utilitária do spring, a declaração é do java.langs
                        //field = campo
                        Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                        //eu quero tornar a vaiável acessivel, permitimos o acesso e o acessamos
                        field.setAccessible(true);

                        //buscamos o valor da propriedade field
                        Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);


                        ReflectionUtils.setField(field, restauranteDestino, novoValor);
                });
        }
}