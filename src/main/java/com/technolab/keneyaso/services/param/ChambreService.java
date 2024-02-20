package com.technolab.keneyaso.services.param;

import com.technolab.keneyaso.common.wrapper.FilterWrapper;
import com.technolab.keneyaso.common.wrapper.ResponseWrapper;
import com.technolab.keneyaso.entities.hospi.Chambres;
import com.technolab.keneyaso.entities.hospi.Lits;

import com.technolab.keneyaso.repositories.param.ChambresRepository;
import com.technolab.keneyaso.repositories.param.LitsRepository;

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
public class ChambreService {
    private final ChambresRepository chambresRepository;
    private final LitsRepository litsRepository;
    private final EntityManager entityManager;
    ChambreService(
            ChambresRepository chambresRepository,
            LitsRepository litsRepository, EntityManager entityManager
    ){
        this.chambresRepository = chambresRepository;
        this.litsRepository = litsRepository;
        this.entityManager = entityManager;
    }

    public ResponseWrapper<Chambres> createChambre(Chambres o) {
        try {
            o = chambresRepository.saveAndFlush(o);
            return ResponseWrapper.ok(o);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper<Lits> createLit(Lits o) {
        try {
            Chambres c=chambresRepository.getReferenceById(o.getChambreId());
            if(c.getType().equals("I")){
                if(litsRepository.findByChambreId(o.getChambreId()).size()>0){
                    return ResponseWrapper.ko("Cette chambre a deja un lit");
                }else{
                    o = litsRepository.saveAndFlush(o);
                }
            }else{
                o = litsRepository.saveAndFlush(o);
            }

            return ResponseWrapper.ok(o);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }
    public ResponseWrapper update( Chambres o) {
        try {
            return chambresRepository
                    .findById(o.getId())
                    .map(oldEntity -> updateWithOld(oldEntity, o))
                    .orElseGet(() -> ResponseWrapper.ko("Impossible de mettre a jour"));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    private ResponseWrapper updateWithOld(Chambres old, Chambres o) {
        try {
            chambresRepository.saveAndFlush(o);
            return ResponseWrapper.ok(o);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public ResponseWrapper delete(Long id) {
        try {
            Optional<Chambres> optional = chambresRepository.findById(id);
            if (!optional.isPresent()) {
                return ResponseWrapper.ok("Error");
            }
            Chambres o = optional.get();
            chambresRepository.delete(o);
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
                chambresRepository.deleteById(Long.parseLong(s));
            });
            return ResponseWrapper.ok(parts);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseWrapper.ko(e.getMessage());
        }
    }

    public Chambres findOne(Long id) {
        try {
            return chambresRepository.findById(id).orElse(null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }


    public List<Chambres> findAllChambres() {
        try {
            return chambresRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }

    public List<Lits> findAllLits() {
        try {
            return litsRepository.findAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }
    public List<Chambres> search(FilterWrapper f) {
        List<Chambres> l = new ArrayList<Chambres>();
        String hql = "";
        try {
            hql += "FROM Chambres as o where o.etat=1";
            if (f.getNom() != null && !f.getNom().equals("") ) {
                hql += " and o.nom like '%" + f.getNom() + "%' ";
            }
            log.info(hql);
            l = (List<Chambres>) entityManager.createQuery(hql).getResultList();
            return l;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Collections.EMPTY_LIST;
        }
    }
}
