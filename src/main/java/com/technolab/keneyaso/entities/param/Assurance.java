package com.technolab.keneyaso.entities.param;

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
@Table(name = "assurances")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE assurances SET etat = 0 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Assurance extends AbstractEntity {
    private String code;
    private String type;
    private int prime;
    private double franchise;
}
