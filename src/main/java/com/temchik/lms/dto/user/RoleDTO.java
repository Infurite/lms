package com.temchik.lms.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Data
public class RoleDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @Size(max = 35)
    private String name;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<PrivilegeDTO> privileges;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<UUID> privilegeIds;
}
