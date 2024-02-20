package com.technolab.keneyaso.repositories.param;

import com.technolab.keneyaso.entities.hospi.Chambres;
import com.technolab.keneyaso.entities.hospi.Lits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LitsRepository extends JpaRepository<Lits, Long> {

    List<Lits>  findByChambreId(long id);

}
