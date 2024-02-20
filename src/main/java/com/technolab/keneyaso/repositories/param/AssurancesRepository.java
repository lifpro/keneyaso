package com.technolab.keneyaso.repositories.param;

import com.technolab.keneyaso.entities.param.Assurance;
import com.technolab.keneyaso.entities.param.Medecins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssurancesRepository extends JpaRepository<Assurance, Long> {

    Optional<Assurance> findByCode(String code);
    List<Assurance> findByTypeLike(String type);
}
