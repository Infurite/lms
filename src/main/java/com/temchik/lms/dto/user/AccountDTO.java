package com.temchik.lms.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.temchik.lms.common.constants.account.Gender;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class AccountDTO {

    @Size(min = 2, max = 35)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 35)
    private String lastName;

    @NotNull
    private Gender gender;

    @NotNull
    private Date dateOfBirth;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String img;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedAt;
}
