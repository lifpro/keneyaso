package com.technolab.keneyaso.services.param;

import com.technolab.keneyaso.common.wrapper.FilterWrapper;
import com.technolab.keneyaso.common.wrapper.ResponseWrapper;
import com.technolab.keneyaso.entities.param.Medecins;
import com.technolab.keneyaso.repositories.param.MedecinsRepository;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
@Slf4j
@Service
public class MedecinService {
    private final MedecinsRepository medecinsRepository;
    private final EntityManager entityManager;
    MedecinService(
             MedecinsRepository medecinsRepository,
             EntityManager entityManager
             ){
        this.medecinsRepository = medecinsRepository;
        this.entityManager = entityManager;
    }

   public ResponseWrapper<Medecins> create(Medecins o) {
        try {
           o.setNom(o.getNom().toUpperCase());
           o.setPrenom(StringUtils.capitalize(o.getPrenom()));
           o = medecinsRepository.saveAndFlush(o);
           return ResponseWrapper.ok(o);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }


    public ResponseWrapper update( Medecins o) {
        try {
            o.setNom(StringUtils.capitalize(o.getNom()));
            o.setPrenom(StringUtils.capitalize(o.getPrenom()));
            return medecinsRepository
                    .findById(o.getId())
                    .map(oldEntity -> updateWithOld(oldEntity, o))
                    .orElseGet(() -> ResponseWrapper.ko("Impossible de mettre a jour"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private ResponseWrapper updateWithOld(Medecins old, Medecins o) {
        try {
            medecinsRepository.saveAndFlush(o);
            return ResponseWrapper.ok(o);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper delete(Long id) {
        try {
            Optional<Medecins> optional = medecinsRepository.findById(id);
            if (!optional.isPresent()) {
                return ResponseWrapper.ok("Error");
            }
            Medecins o = optional.get();
            medecinsRepository.delete(o);
            return ResponseWrapper.ok(o);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper deleteAll(String ids) {
        try {
            List<String> parts = Pattern.compile(":")
                    .splitAsStream(ids.replaceAll("\"", ""))
                    .collect(Collectors.toList());
            parts.forEach(s -> {
                medecinsRepository.deleteById(Long.parseLong(s));
            });
            return ResponseWrapper.ok(parts);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public Medecins findOne(Long id) {
        try {
            return medecinsRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public List<Medecins> findAll() {
        try {
            return medecinsRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }
    public List<Medecins> search(FilterWrapper f) {
        List<Medecins> l = new ArrayList<Medecins>();
        String hql = "";
        try {
            hql += "FROM Medecins as o where o.etat=1";
            if (f.getNom() != null && !f.getNom().equals("") ) {
                hql += " and o.nom like '%" + f.getNom() + "%' ";
            }
            log.info(hql);
            l = (List<Medecins>) entityManager.createQuery(hql).getResultList();
            return l;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }
}
