package com.temchik.lms.service.user;

import com.temchik.lms.common.constants.account.PrivilegeAction;
import com.temchik.lms.common.constants.account.PrivilegeResource;
import com.temchik.lms.common.exception.BusinessEntityNotFoundException;
import com.temchik.lms.common.exception.BusinessException;
import com.temchik.lms.dto.user.PrivilegeDTO;
import com.temchik.lms.model.user.Privilege;
import com.temchik.lms.repository.user.PrivilegeRepository;
import com.temchik.lms.service.mapper.user.PrivilegeDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrivilegeService {

    private final PrivilegeRepository repository;
    private final PrivilegeDTOMapper mapper;

    public Page<PrivilegeDTO> get(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Transactional
    public PrivilegeDTO create(PrivilegeDTO dio) {
        checkUniquePrivilege(dio.getResource(), dio.getAction());

        Privilege privilege = mapper.toEntity(dio);
        return mapper.toDTO(repository.save(privilege));
    }

    private void checkUniquePrivilege(PrivilegeResource resource, PrivilegeAction action) {
        if (repository.existsByResourceAndAction(resource, action)) {
            throw new BusinessException("Privilege already exists");
        }
    }

    public Privilege findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new BusinessEntityNotFoundException(Privilege.class, "id"));
    }
}
