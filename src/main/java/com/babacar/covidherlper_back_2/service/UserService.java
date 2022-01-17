package com.babacar.covidherlper_back_2.service;

import com.babacar.covidherlper_back_2.controller.RvsController;
import com.babacar.covidherlper_back_2.model.Users;
import com.babacar.covidherlper_back_2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Entity;
import java.net.URI;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping(value = "/users")
    public ResponseEntity<Object> add(@RequestBody Users users) {

        Users users1 = usersRepository.save(users);

        if(users1 == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(users1.getUser_id())
                .toUri();

        return ResponseEntity.status(201).body(users1);
    }

    @PostMapping(value = "/users/login")
    public ResponseEntity<Object> login(@RequestBody Users users) {
        Users users1 = usersRepository.findByLoginAndPwd(users.getLogin(), users.getPwd());

        if (users == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Cette utilisateur n'existe pas !'}");

        URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/rvs/{id}/users")
                .buildAndExpand(users1.getUser_id())
                .toUri();

        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RvsController.class).getOneUserRvs(users1.getUser_id())).withRel("Liste des rendez-vous");
        users1.add(link);

        return ResponseEntity.ok(users1);
    }

    @PutMapping(value = "/users/{user_id}")
    public ResponseEntity<Object> update(@PathVariable long user_id, @RequestBody Users users) {

        Users users1 = usersRepository.findById(user_id);

        if(users1 == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Cet utilisateur n'existe pas !'}");

        users.setUser_id(users1.getUser_id());
        users1 = usersRepository.save(users);
        if(users1 == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(users1.getUser_id())
                .toUri();

        return ResponseEntity.ok(location);
    }

    @GetMapping(value = "/users")
    public List<Users> getAll() {
        return usersRepository.findAll();
    }

    @GetMapping(value = "/users/{user_id}")
    public ResponseEntity<Object> getOne(@PathVariable long user_id) {
        Users users = usersRepository.findById(user_id);
        if (users == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Cette utilisateur n'existe pas !'}");


        URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/rvs/{id}/users")
                .buildAndExpand(users.getUser_id())
                .toUri();

        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RvsController.class).getOneUserRvs(user_id)).withRel("Liste des RVS");
        users.add(link);

        return ResponseEntity.created(location).body(users);
    }
}
