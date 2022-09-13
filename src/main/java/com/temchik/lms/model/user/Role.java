package com.temchik.lms.model.user;

import com.temchik.lms.model.PersistentEntity;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
public class Role extends PersistentEntity {

    private String name;

    private String description;

    @ManyToMany
    @JoinTable(name = "role_privilege",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private List<Privilege> privileges;

    private boolean isDefault;

    private Date createdAt;

    private Date updatedAt;
}
