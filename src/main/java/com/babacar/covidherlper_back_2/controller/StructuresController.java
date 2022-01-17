package com.babacar.covidherlper_back_2.controller;

import com.babacar.covidherlper_back_2.model.Structures;
import com.babacar.covidherlper_back_2.repository.StructuresRepository;
import com.babacar.covidherlper_back_2.service.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
public class StructuresController {

    @Autowired
    private StructureService structureService;

    @PostMapping(value = "/structures")
    public ResponseEntity<Object> add(@RequestBody Structures structures) {

        return structureService.add(structures);
    }

    @PutMapping(value = "/structures/{struct_id}")
    public ResponseEntity<Object> update(@PathVariable long struct_id, @RequestBody Structures structures) {

        return structureService.update(struct_id, structures);
    }

    @GetMapping(value = "/structures")
    public List<Structures> getAll() {

        return structureService.getAll();
    }

    @GetMapping(value = "/structures/{struct_id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getOne(@PathVariable long struct_id) {
       return structureService.getOne(struct_id);
    }
}
