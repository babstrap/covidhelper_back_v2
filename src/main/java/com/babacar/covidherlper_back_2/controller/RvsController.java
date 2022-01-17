package com.babacar.covidherlper_back_2.controller;

import com.babacar.covidherlper_back_2.model.Rvs;
import com.babacar.covidherlper_back_2.service.RvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RvsController {

    @Autowired
    private RvService rvService;

    @PostMapping(value = "/rvs")
    public ResponseEntity<Object> add(@RequestBody Rvs rvs) {

        return rvService.add(rvs);
    }

    @PutMapping(value = "/rvs/{rv_id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> update(@PathVariable long rv_id, @RequestBody Rvs rvs) {

        return rvService.update(rv_id, rvs);
    }

    @GetMapping(value = "/rvs")
    public List<Rvs> getAll() {
        return rvService.getAll();
    }

    @GetMapping(value = "/rvs/{rvs_id}")
    public ResponseEntity<Object> getOne(@PathVariable long rvs_id) {
        return rvService.getOne(rvs_id);
    }

    @GetMapping(value = "/rvs/{struct_id}/structures")
    public ResponseEntity<Object> getOneStructureRvs(@PathVariable long struct_id) {

        return rvService.getOneStructureRvs(struct_id);
    }

    @GetMapping(value = "/rvs/{user_id}/users")
    public ResponseEntity<Object> getOneUserRvs(@PathVariable long user_id) {

        return rvService.getOneUserRvs(user_id);
    }

}
