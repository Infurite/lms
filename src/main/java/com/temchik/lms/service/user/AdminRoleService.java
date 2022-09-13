package com.temchik.lms.service.user;

import com.temchik.lms.common.exception.BusinessException;
import com.temchik.lms.dto.user.AdminRoleDTO;
import com.temchik.lms.model.user.Role;
import com.temchik.lms.repository.user.RoleRepository;
import com.temchik.lms.service.mapper.user.AdminRoleDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminRoleService {

    private final RoleRepository repository;
    private final AdminRoleDTOMapper mapper;

    public List<AdminRoleDTO> get() {
        return repository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public AdminRoleDTO create(AdminRoleDTO dio) {
        checkExistDefaultAndName(dio.getName());
        Role role = mapper.toEntity(dio);
        role.setCreatedAt(new Date(System.currentTimeMillis()));
        return mapper.toDTO(repository.save(role));
    }

    public void delete(UUID id) {
        if (repository.existsByIsDefaultTrueAndId(id)) {
            throw new BusinessException("The role can't be deleted ");
        }
        repository.deleteById(id);
    }

    private void checkExistDefaultAndName(String name) {
        if (repository.existsByIsDefaultTrueAndNameIgnoreCase(name)) {
            throw new BusinessException("Admin with name = " + name + " exist");
        }
    }
}
