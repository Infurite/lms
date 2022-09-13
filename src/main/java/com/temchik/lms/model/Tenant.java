package com.temchik.lms.model;

import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
@AttributeOverride(name = "id", column = @Column(name = "tenant_id"))
public class Tenant extends PersistentEntity {

    private String name;

    private String description;

    private Date createdAt;

    private Date updatedAt;

    private String img;
}
