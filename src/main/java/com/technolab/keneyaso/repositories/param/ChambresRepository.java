package com.technolab.keneyaso.repositories.param;

import com.technolab.keneyaso.entities.hospi.Chambres;
import com.technolab.keneyaso.entities.param.Examens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChambresRepository extends JpaRepository<Chambres, Long> {


}
