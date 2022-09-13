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
@AttributeOverride(name = "id", column = @Column(name = "lesson_id"))
public class Lesson extends PersistentEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    private String name;

    private String description;

    private String img;
}
