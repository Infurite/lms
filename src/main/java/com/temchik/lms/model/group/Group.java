package com.temchik.lms.model.group;

import com.temchik.lms.model.TenantPersistentEntity;
import com.temchik.lms.model.user.User;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "groups")
@AttributeOverride(name = "id", column = @Column(name = "group_id"))
public class Group extends TenantPersistentEntity {

    private String name;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "group_user",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;

    private String img;

    private Date createdAt;

    private Date updatedAt;
}
