package com.temchik.lms.service.user;

import com.temchik.lms.dto.user.RoleDTO;
import com.temchik.lms.model.user.Role;
import com.temchik.lms.repository.user.RoleRepository;
import com.temchik.lms.service.mapper.user.RoleDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;
    private final RoleDTOMapper mapper;

    public Page<RoleDTO> getAvailableRoles(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    public Role getDefaultRole() {
        return repository.findByIsDefaultTrue();
    }
}
