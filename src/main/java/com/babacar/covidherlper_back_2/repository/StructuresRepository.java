package com.babacar.covidherlper_back_2.repository;


import com.babacar.covidherlper_back_2.model.Structures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructuresRepository extends JpaRepository<Structures, Long> {

    Structures findById(long struct_id);
}
