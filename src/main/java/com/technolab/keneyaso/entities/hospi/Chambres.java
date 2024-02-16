package com.technolab.keneyaso.entities.hospi;

import com.technolab.keneyaso.entities.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chambres")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE chambres SET etat = 0 WHERE id = ?", check = ResultCheckStyle.COUNT)

public class Chambres extends AbstractEntity {
    private String numero;
    private  String type; //I:Individuelle,C:Commun
}
