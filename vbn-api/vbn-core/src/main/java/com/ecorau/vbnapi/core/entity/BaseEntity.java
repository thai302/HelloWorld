package com.ecorau.vbnapi.core.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(name = "cmn_created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date cmnCreatedAt;

    @Column(name = "cmn_created_by", nullable = false, updatable = false)
    @CreatedBy
    private String cmnCreatedBy;

    @Column(name = "cmn_updated_at")
    @LastModifiedDate
    private Date cmnUpdatedAt;

    @Column(name = "cmn_updated_by")
    @LastModifiedBy
    private String cmnUpdatedBy;

    @Column(name = "cmn_is_deleted")
    private boolean cmnIsDeleted;
}
