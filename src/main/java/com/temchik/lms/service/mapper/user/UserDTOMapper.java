package com.temchik.lms.service.mapper.user;

import com.temchik.lms.dto.user.UserDTO;
import com.temchik.lms.model.user.User;
import com.temchik.lms.service.mapper.EntityToDTOMapper;
import com.temchik.lms.service.user.RoleService;
import com.temchik.lms.service.user.TenantService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserDTOMapper implements EntityToDTOMapper<UserDTO, User, UserDTO> {

    private final TenantService tenantService;
    private final RoleService roleService;
    private final RoleDTOMapper roleDTOMapper;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper = new ModelMapper();

    public UserDTOMapper(TenantService tenantService, RoleService roleService, RoleDTOMapper roleDTOMapper,
                         PasswordEncoder passwordEncoder, AccountDTOMapper accountDTOMapper) {
        this.tenantService = tenantService;
        this.roleService = roleService;
        this.roleDTOMapper = roleDTOMapper;
        this.passwordEncoder = passwordEncoder;

        mapper.addMappings(new PropertyMap<UserDTO, User>() {
            protected void configure() {
                skip().setId(null);
                skip().setRoles(null);
            }
        });
    }

    @Override
    public UserDTO toDTO(User entity, Object... args) {
        UserDTO dto = mapper.map(entity, UserDTO.class);
        dto.setTenantId(entity.getTenantId());
        dto.setRoles(roleDTOMapper.toDTOs(entity.getRoles()));
        return dto;
    }

    @Override
    public User toEntity(UserDTO dto, Object... args) {
        User user = mapper.map(dto, User.class);
        user.setTenantId(tenantService.findById(dto.getTenantId()).getId());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(Arrays.asList(roleService.getDefaultRole()));
        return user;
    }
}
