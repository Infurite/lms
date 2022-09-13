package com.temchik.lms.model.user;

import com.temchik.lms.common.constants.account.Gender;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Account implements Serializable {

    @Id
    private UUID userId;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Date dateOfBirth;

    private String img;

    private Date createdAt;

    private Date updatedAt;
}
