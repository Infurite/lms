package com.temchik.lms.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class GroupDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @Size(min = 1, max = 50)
    private String name;

    private String description;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<UUID> userIds;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String img;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date updatedAt;
}
