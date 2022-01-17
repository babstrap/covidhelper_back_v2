package com.babacar.covidherlper_back_2.service;

import com.babacar.covidherlper_back_2.model.Structures;
import com.babacar.covidherlper_back_2.repository.StructuresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Service
public class StructureService {

    @Autowired
    private StructuresRepository structuresRepository;

    @PostMapping(value = "/structures")
    public ResponseEntity<Object> add(@RequestBody Structures structures) {

        Structures structures1 = structuresRepository.save(structures);

        if(structures1 == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(structures1.getStruct_id())
                .toUri();

        return ResponseEntity.status(201).body("{'new_struct_link':"+location+"}");
    }

    @PutMapping(value = "/structures/{struct_id}")
    public ResponseEntity<Object> update(@PathVariable long struct_id, @RequestBody Structures structures) {

        Structures structures1 = structuresRepository.findById(struct_id);

        if(structures1 == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Cette structures n'existe pas !'}");

        structures.setStruct_id(structures1.getStruct_id());
        structures1 = structuresRepository.save(structures);
        if(structures1 == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(structures1.getStruct_id())
                .toUri();

        return ResponseEntity.ok(location);
    }

    @GetMapping(value = "/structures")
    public List<Structures> getAll() {

        List<Structures> structuresList = structuresRepository.findAll();
        Collections.sort(structuresList);

        return structuresList;
    }

    @GetMapping(value = "/structures/{struct_id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getOne(@PathVariable long struct_id) {
        Structures structures = structuresRepository.findById(struct_id);
        if (structures == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Cette structures n'existe pas !'}");


        URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/rvs/{id}/structures")
                .buildAndExpand(structures.getStruct_id())
                .toUri();

        return ResponseEntity.ok(structures);
    }
}
