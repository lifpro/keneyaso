package com.technolab.keneyaso.services.param;

import com.technolab.keneyaso.common.wrapper.FilterWrapper;
import com.technolab.keneyaso.common.wrapper.ResponseWrapper;
import com.technolab.keneyaso.entities.param.Assurance;
import com.technolab.keneyaso.repositories.param.AssurancesRepository;
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
public class AssurancesService {
    private final AssurancesRepository assurancesRepository;
    private final EntityManager entityManager;
    AssurancesService(
            AssurancesRepository assurancesRepository,
            EntityManager entityManager
    ){
        this.assurancesRepository = assurancesRepository;
        this.entityManager = entityManager;
    }

    public ResponseWrapper<Assurance> create(Assurance a) {
        try {
            a.setType(StringUtils.capitalize(a.getType()));
            a.setPrime(a.getPrime());
            a = assurancesRepository.saveAndFlush(a);
            return ResponseWrapper.ok(a);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }


    public ResponseWrapper update( Assurance a) {
        try {
            a.setCode(a.getCode());
            a.setType(StringUtils.capitalize(a.getType()));
            return assurancesRepository
                    .findById(a.getId())
                    .map(oldEntity -> updateWithOld(oldEntity, a))
                    .orElseGet(() -> ResponseWrapper.ko("Impossible de mettre a jour"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private ResponseWrapper updateWithOld(Assurance old, Assurance a) {
        try {
            assurancesRepository.saveAndFlush(a);
            return ResponseWrapper.ok(a);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper delete(Long id) {
        try {
            Optional<Assurance> optional = assurancesRepository.findById(id);
            if (!optional.isPresent()) {
                return ResponseWrapper.ok("Error");
            }
            Assurance a = optional.get();
            assurancesRepository.delete(a);
            return ResponseWrapper.ok(a);
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
                assurancesRepository.deleteById(Long.parseLong(s));
            });
            return ResponseWrapper.ok(parts);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public Assurance findOne(Long id) {
        try {
            return assurancesRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public List<Assurance> findAll() {
        try {
            return assurancesRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }
    public List<Assurance> search(FilterWrapper f) {
        List<Assurance> l = new ArrayList<Assurance>();
        String hql = "";
        try {
            hql += "FROM Medecins as o where o.etat=1";
            if (f.getNom() != null && !f.getNom().equals("") ) {
                hql += " and o.nom like '%" + f.getNom() + "%' ";
            }
            log.info(hql);
            l = (List<Assurance>) entityManager.createQuery(hql).getResultList();
            return l;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }
}
