package segalas.teste.apiBlocoDeNota.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import segalas.teste.apiBlocoDeNota.dto.DadosAtualizacaoAnotacao;
import segalas.teste.apiBlocoDeNota.repository.AnotacaoRepository;
import segalas.teste.apiBlocoDeNota.anotacao.Anotacao;
import segalas.teste.apiBlocoDeNota.dto.DadosCadastroAnotacao;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("anotacao")
public class AnotacaoController {

    @Autowired
    private AnotacaoRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Transactional
    public void saveAnotacao(@RequestBody @Valid DadosCadastroAnotacao dados){
        repository.save(new Anotacao(dados));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping()
    @Transactional
    public void atualizarAnotacao(@RequestBody @Valid DadosAtualizacaoAnotacao dados) {
       var anotacao = repository.getReferenceById(dados.id());
       anotacao.atualizarInformacoes(dados);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<Anotacao> getAll(){
        List<Anotacao> anotacaoList = repository.findAll();
        return anotacaoList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Anotacao> getById(@PathVariable Long id){
        Optional<Anotacao> anotacaoList = repository.findById(id);
        if(anotacaoList.isPresent()) {
            Anotacao anotacao = anotacaoList.get();
            return ResponseEntity.ok(anotacao);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Transactional
    public void excluirAnotacao(@PathVariable Long id) {
        var anotacao = repository.getReferenceById(id);
        repository.deleteById(id);
    }
}
