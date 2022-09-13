package com.temchik.lms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

@Data
public class TenantDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @Size(max = 35)
    private String name;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String img;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedAt;
}
