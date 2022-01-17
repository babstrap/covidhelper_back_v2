package com.babacar.covidherlper_back_2.service;

import com.babacar.covidherlper_back_2.model.Rvs;
import com.babacar.covidherlper_back_2.model.Structures;
import com.babacar.covidherlper_back_2.repository.RvsRepository;
import com.babacar.covidherlper_back_2.repository.StructuresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class RvService {

    @Autowired
    private RvsRepository rvsRepository;

    @Autowired
    private StructuresRepository structuresRepository;

    public ResponseEntity<Object> add(Rvs rvs) {
        Rvs rvs1 = rvsRepository.save(rvs);

        if(rvs1 == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(rvs1.getRv_id())
                .toUri();

        Structures structures = structuresRepository.findById(rvs.getStructures().getStruct_id());
        System.out.println(structures.getNom());

        // structures.setNbr_vaccin_dispo();
        if (rvs.isObjet()) {
            structures.setNbr_test_dispo(structures.getNbr_test_dispo() - 1);
        } else {
            structures.setNbr_vaccin_dispo(structures.getNbr_vaccin_dispo() - 1);
        }

        structuresRepository.save(structures);

        return ResponseEntity.status(201).body("{'new_rv_link':"+location+"}");
    }

    public ResponseEntity<Object> update(long rv_id, Rvs rvs) {
        Rvs rvs1 = rvsRepository.findById(rv_id);

        if(rvs1 == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Ce rendez-vous n'existe pas !'}");

        rvs.setRv_id(rvs1.getRv_id());
        rvs1 = rvsRepository.save(rvs);
        if(rvs1 == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(rvs1.getRv_id())
                .toUri();

        return ResponseEntity.ok(location);
    }

    public ResponseEntity<Object> getOne(long rvs_id) {
        Rvs rvs = rvsRepository.findById(rvs_id);
        if (rvs == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Erreur': 'Ce rendez-vous n'existe pas !'}");


        URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/rvs/{id}")
                .buildAndExpand(rvs.getRv_id()+1)
                .toUri();

        return ResponseEntity.ok(rvs);
    }

    public List<Rvs> getAll() {
        return rvsRepository.findAll();
    }

    public ResponseEntity<Object> getOneStructureRvs(long struct_id) {
        // TODO: Check if struct exit before
        List<Rvs> userRvsList = rvsRepository.findAllByStructId(struct_id);

        // Get structs rvs
        return  ResponseEntity.ok(userRvsList);
    }

    public ResponseEntity<Object> getOneUserRvs(long user_id) {

        // TODO: Check if user exit before

        // Get user rvs
        List<Rvs> userRvsList = rvsRepository.findAllByUserId(user_id);

        return  ResponseEntity.ok(userRvsList);
    }
}
