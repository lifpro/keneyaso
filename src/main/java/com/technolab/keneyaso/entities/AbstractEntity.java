package com.technolab.keneyaso.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.io.Serializable;
import java.util.Date;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements Serializable, Comparable<AbstractEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Column
    private int etat;//1=actif,0=inactif

    @PrePersist
    public void beforePersist() {
        this.etat = 1;
    }

    @PreRemove
    public void beforeDelete() {
        this.etat =0;
    }

    @Override
    public int compareTo(AbstractEntity abstractEntity) {
        if (createdDate == null || abstractEntity.createdDate == null) {
            return 0;
        }
        return createdDate.compareTo(abstractEntity.createdDate);
    }
}
