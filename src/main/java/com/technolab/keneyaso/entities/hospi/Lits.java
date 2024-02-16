package com.technolab.keneyaso.entities.hospi;

import com.technolab.keneyaso.entities.AbstractEntity;
import jakarta.persistence.*;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lits")
@Where(clause = "etat <> 0")
@SQLDelete(sql = "UPDATE lits SET etat = 0 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Lits extends AbstractEntity {
    private String numero;
    private  long chambreId;
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT ),name = "chambreId",referencedColumnName = "id", insertable = false, updatable = false)
    private Chambres chambre;
}
