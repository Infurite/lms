package com.temchik.lms.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.temchik.lms.common.constants.account.PrivilegeAction;
import com.temchik.lms.common.constants.account.PrivilegeResource;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class PrivilegeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @NotNull
    private PrivilegeResource resource;

    @NotNull
    private PrivilegeAction action;

    private String description;
}

