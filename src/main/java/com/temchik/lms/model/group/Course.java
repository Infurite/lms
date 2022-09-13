package com.temchik.lms.model.group;

import com.temchik.lms.model.PersistentEntity;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@AttributeOverride(name = "id", column = @Column(name = "course_id"))
public class Course extends PersistentEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id")
    private Group group;

    private String name;

    private String description;

    private String img;
}
