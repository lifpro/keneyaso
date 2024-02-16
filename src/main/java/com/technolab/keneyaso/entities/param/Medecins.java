package com.technolab.keneyaso.entities.param;

import com.technolab.keneyaso.entities.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medecins")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE medecins SET etat = 0 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Medecins extends AbstractEntity {
    private String matricule;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    @Column(length = 500)
    private String adresse;
}
