package com.kitcut.helloworld.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
    @Column(name = "cmn_created_at", nullable = false, updatable = false)
    private Date cmnCreatedAt;

    @Column(name = "cmn_created_by", nullable = false, updatable = false)
    private String cmnCreatedBy;

    @Column(name = "cmn_updated_at")
    private Date cmnUpdatedAt;

    @Column(name = "cmn_updated_by")
    private String cmnUpdatedBy;

    @Column(name = "cmn_is_deleted")
    private boolean cmnIsDeleted;
}
