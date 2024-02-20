package com.technolab.keneyaso.services.param;

import com.technolab.keneyaso.common.wrapper.FilterWrapper;
import com.technolab.keneyaso.common.wrapper.ResponseWrapper;
import com.technolab.keneyaso.entities.param.Examens;
import com.technolab.keneyaso.entities.param.Medecins;
import com.technolab.keneyaso.repositories.param.ExamensRepository;
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
public class ExamenService {
    private final ExamensRepository examensRepository;
    private final EntityManager entityManager;
    ExamenService(
            ExamensRepository examensRepository,
            EntityManager entityManager
    ){
        this.examensRepository = examensRepository;
        this.entityManager = entityManager;
    }

    public ResponseWrapper<Examens> create(Examens o) {
        try {
            o.setNom(StringUtils.capitalize(o.getNom()));
            o.setTarif(o.getTarif());
            o.setCode(o.getCode());
            o = examensRepository.saveAndFlush(o);
            return ResponseWrapper.ok(o);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }


    public ResponseWrapper update( Examens o) {
        try {
            o.setNom(StringUtils.capitalize(o.getNom()));
            o.setCode(o.getCode());
            o.setTarif(o.getTarif());
            return examensRepository
                    .findById(o.getId())
                    .map(oldEntity -> updateWithOld(oldEntity, o))
                    .orElseGet(() -> ResponseWrapper.ko("Impossible de mettre a jour"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private ResponseWrapper updateWithOld(Examens old, Examens o) {
        try {
            examensRepository.saveAndFlush(o);
            return ResponseWrapper.ok(o);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper delete(Long id) {
        try {
            Optional<Examens> optional = examensRepository.findById(id);
            if (!optional.isPresent()) {
                return ResponseWrapper.ok("Error");
            }
            Examens o = optional.get();
            examensRepository.delete(o);
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
                examensRepository.deleteById(Long.parseLong(s));
            });
            return ResponseWrapper.ok(parts);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public Examens findOne(Long id) {
        try {
            return examensRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public List<Examens> findAll() {
        try {
            return examensRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }
    public List<Examens> search(FilterWrapper f) {
        List<Examens> l = new ArrayList<Examens>();
        String hql = "";
        try {
            hql += "FROM Examens as o where o.etat=1";
            if (f.getNom() != null && !f.getNom().equals("") ) {
                hql += " and o.nom like '%" + f.getNom() + "%' ";
            }
            log.info(hql);
            l = (List<Examens>) entityManager.createQuery(hql).getResultList();
            return l;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }
}
