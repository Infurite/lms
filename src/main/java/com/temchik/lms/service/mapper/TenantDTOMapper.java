package com.temchik.lms.service.mapper;

import com.temchik.lms.dto.TenantDTO;
import com.temchik.lms.model.Tenant;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TenantDTOMapper implements EntityToDTOMapper<TenantDTO, Tenant, TenantDTO> {

    private final ModelMapper mapper = new ModelMapper();

    public TenantDTOMapper() {
        mapper.addMappings(new PropertyMap<TenantDTO, Tenant>() {
            protected void configure() {
                skip().setId(null);
            }
        });
    }

    @Override
    public TenantDTO toDTO(Tenant entity, Object... args) {
        return mapper.map(entity, TenantDTO.class);
    }

    @Override
    public Tenant toEntity(TenantDTO dto, Object... args) {
        Tenant tenant = mapper.map(dto, Tenant.class);
        tenant.setUpdatedAt(new Date(System.currentTimeMillis()));
        return tenant;
    }
}
