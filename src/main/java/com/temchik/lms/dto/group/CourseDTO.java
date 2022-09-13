package com.temchik.lms.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class CourseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UUID groupId;

    @Size(min = 1, max = 50)
    private String name;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String img;
}
