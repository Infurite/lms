package com.temchik.lms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class TenantPersistentEntity extends PersistentEntity {

    @Column(name = "tenant_id")
    private UUID tenantId;
}
