package com.aeviles.deliveryapi.controller;


import com.aeviles.deliveryapi.domain.exception.EntidadeEmUsoException;
import com.aeviles.deliveryapi.domain.exception.EntidadeNaoEncontradaException;
import com.aeviles.deliveryapi.domain.model.Cozinha;
import com.aeviles.deliveryapi.domain.repository.CozinhaRepository;
import com.aeviles.deliveryapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController //essa anotação é um controlador e possui @Response body
@RequestMapping("/cozinhas")//todas as requisições /cozinhas vai cair nessa requisição
public class CozinhaController {


        @Autowired
        private CozinhaRepository cozinhaRepository;

        @Autowired
        private CozinhaService cozinhaService;

        @GetMapping
        public List<Cozinha> findAll() {
                return cozinhaRepository.findAll();
        }



        @GetMapping(value = "/{cozinhaId}")// Biding Path variable "cozinhaId" pode ter qualquer outro nome
        public ResponseEntity<Cozinha> findById(@PathVariable Long cozinhaId) { //PathVariable passamos o nome da nossa variavel que queremos fazer um biding

                //optional de cozinha que pode ter ou não uma cozinha evitando o nullPointer Exception
                //essse metodo nunca retorna um null, ou seja a cozinha está presente ou não
                Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);

                if (cozinha.isPresent()) {

                        return ResponseEntity.ok(cozinha.get());
                }

                return ResponseEntity.notFound().build();  //caso contrário retorne NOTfOUND sem corpo algum

        }



        //metodo salvar/adicionar
        @PostMapping //Mapeamento do método post http
        @ResponseStatus(HttpStatus.CREATED) // O recurso foi criado
        public Cozinha salvar(@RequestBody Cozinha cozinha) { //essa anotação @RequestBody diz que o parametro vai receber o corpo da requisição.Pega o corpo do JSON e vincula com a cozinha
                return cozinhaService.salvar(cozinha);
        }

        //Utilizaremos ResponseEntity por que precisaremos tratar a resposta http
        @PutMapping("/{cozinhaId}")
        public ResponseEntity<?> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
                try {
                        //cozinhaatual é a cozinha persistida no banco de dados  eu tenho que pegar cozinha e colocar dentro de cozinhaatual
                        Optional<Cozinha> cozinhaatual = cozinhaRepository.findById(cozinhaId);

                        //Existe alguma coisa dentro desse Optional?
                        if (cozinhaatual.isPresent()) {

                                //eu quero copiar as propriedades de cozinha para dentro das propriedade de cozinhaatual que está dentro do Optional
                                BeanUtils.copyProperties(cozinha, cozinhaatual.get(), "id");

                              Cozinha cozinhaSalva = cozinhaService.salvar(cozinhaatual.get());

                                return ResponseEntity.ok(cozinhaatual); //200 OK- Estas requisição foi bem sucedida. O significado do sucesso varia de acordo com o método HTTP:

                        }

                        return ResponseEntity.notFound().build();
                } catch (EntidadeNaoEncontradaException e){
                        return ResponseEntity.badRequest().body(e.getMessage());
                }

                }

                //Utilizaremos ResponseEntity por que precisaremos tratar a resposta http e fazer alguma lógica
                @DeleteMapping(path = "/{cozinhaId}")
                public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {//utilizaremos o @PathVariable para fazer o biding dele /{cozinhaId} nesse parametro cozinhaId do método
                        try {
                                cozinhaService.remover(cozinhaId);

                                return ResponseEntity.noContent().build();
                        }

                        //entidade não foi encontrada?
                        catch (EntidadeNaoEncontradaException e){
                                return ResponseEntity.notFound().build();
                        }

                        //entidade esta em uso?
                        catch (EntidadeEmUsoException e) {//quando o recurso não pode ser excluido
                                return ResponseEntity.status(HttpStatus.CONFLICT).build(); //vou lançar a exceção 409 conflict , seria interessante lançar um corpo descrevendo qual foi o problema
                                                                                        //tratamento de exceptions

                        }


                }


}


