package com.babacar.covidherlper_back_2.repository;

import com.babacar.covidherlper_back_2.model.Rvs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RvsRepository extends JpaRepository<Rvs, Long> {

    Rvs findById(long rvs_id);

    @Query(value = "SELECT * FROM rvs r, structures s, users u WHERE r.struct_id = s.struct_id and r.struct_id = r.user_id and r.struct_id = ?", nativeQuery = true)
    List<Rvs> findAllByStructId(long struct_id);

    @Query(value = "SELECT * FROM rvs r, structures s, users u WHERE r.struct_id = s.struct_id and r.user_id = u.user_id and r.user_id = ?", nativeQuery = true)
    List<Rvs> findAllByUserId(long struct_id);
}
