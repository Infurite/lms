package com.temchik.lms.service.mapper.user;

import com.temchik.lms.dto.user.RoleDTO;
import com.temchik.lms.model.user.Role;
import com.temchik.lms.service.mapper.EntityToDTOMapper;
import com.temchik.lms.service.user.PrivilegeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class RoleDTOMapper implements EntityToDTOMapper<RoleDTO, Role, RoleDTO> {

    private final ModelMapper mapper = new ModelMapper();
    private final PrivilegeService privilegeService;
    private final PrivilegeDTOMapper privilegeDTOMapper;

    public RoleDTOMapper(PrivilegeService privilegeService, PrivilegeDTOMapper privilegeDTOMapper) {
        this.privilegeService = privilegeService;
        this.privilegeDTOMapper = privilegeDTOMapper;

        mapper.addMappings(new PropertyMap<RoleDTO, Role>() {
            protected void configure() {
                skip().setId(null);
                skip().setPrivileges(null);
            }
        });
    }

    @Override
    public RoleDTO toDTO(Role entity, Object... args) {
        RoleDTO dto = mapper.map(entity, RoleDTO.class);
        dto.setPrivileges(privilegeDTOMapper.toDTOs(entity.getPrivileges()));
        return dto;
    }

    @Override
    public Role toEntity(RoleDTO dto, Object... args) {
        Role role = mapper.map(dto, Role.class);
        role.setPrivileges(dto.getPrivilegeIds().stream().map(privilegeService::findById).collect(Collectors.toList()));
        role.setUpdatedAt(new Date(System.currentTimeMillis()));
        return role;
    }
}
