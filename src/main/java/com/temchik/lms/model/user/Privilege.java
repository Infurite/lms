package com.temchik.lms.model.user;

import com.temchik.lms.common.constants.account.PrivilegeAction;
import com.temchik.lms.common.constants.account.PrivilegeResource;
import com.temchik.lms.model.PersistentEntity;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
@AttributeOverride(name = "id", column = @Column(name = "privilege_id"))
public class Privilege extends PersistentEntity {

    @Enumerated(EnumType.STRING)
    private PrivilegeResource resource;

    @Enumerated(EnumType.STRING)
    private PrivilegeAction action;

    private String description;

}
