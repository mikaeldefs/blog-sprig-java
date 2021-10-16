package com.digitalhouse.blog.controller;

import com.digitalhouse.blog.model.Postagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.digitalhouse.blog.repository.PostagemRepository;

import java.util.List;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

    @Autowired
    private PostagemRepository repository;

    @GetMapping
    public ResponseEntity<List<Postagem>> getAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getByID(@PathVariable Long id){

        return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity <List<Postagem>> GetByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @GetMapping("/texto/{texto}")
    public ResponseEntity<List<Postagem>> getByTexto(@PathVariable String texto) {
        return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));

    }

    @PostMapping

    public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
    }

    @PutMapping
    public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}


