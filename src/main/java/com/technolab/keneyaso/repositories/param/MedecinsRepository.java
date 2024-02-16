/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.technolab.keneyaso.repositories.param;


import com.technolab.keneyaso.entities.param.Medecins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedecinsRepository extends JpaRepository<Medecins, Long> {

    Optional<Medecins> findByMatricule(String matricule);
    List<Medecins> findByNomLike(String nom);

    /*Optional<Dossiers> findByAncienDossier(String code);
    Optional<Dossiers> findByNumero(String numero);
    List<Dossiers> findAllByAncienDossier(String code);*/
}
