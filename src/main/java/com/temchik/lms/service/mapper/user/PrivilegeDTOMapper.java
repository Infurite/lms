package com.temchik.lms.service.mapper.user;

import com.temchik.lms.dto.user.PrivilegeDTO;
import com.temchik.lms.model.user.Privilege;
import com.temchik.lms.service.mapper.EntityToDTOMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PrivilegeDTOMapper implements EntityToDTOMapper<PrivilegeDTO, Privilege, PrivilegeDTO> {

    private final ModelMapper mapper;

    public PrivilegeDTOMapper() {
        mapper = new ModelMapper();
        mapper.addMappings(new PropertyMap<PrivilegeDTO, Privilege>() {
            protected void configure() {
                skip().setId(null);
            }
        });
    }

    @Override
    public PrivilegeDTO toDTO(Privilege entity, Object... args) {
        return mapper.map(entity, PrivilegeDTO.class);
    }

    @Override
    public Privilege toEntity(PrivilegeDTO dto, Object... args) {
        return mapper.map(dto, Privilege.class);
    }
}
