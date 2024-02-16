package com.technolab.keneyaso.entities.consult;

import com.technolab.keneyaso.entities.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import org.hibernate.annotations.Where;



@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prestations")
@Where(clause = "etat <> 'DELETED'")
@EqualsAndHashCode(callSuper = false)
@SQLDelete(sql = "UPDATE clients SET etat = 'DELETED' WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Prestations extends AbstractEntity {

    private String famille;
    private String code;
    private String nom;
    private double tarif;


}
