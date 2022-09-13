package com.temchik.lms.service.user;

import com.temchik.lms.common.exception.BusinessEntityNotFoundException;
import com.temchik.lms.common.exception.BusinessException;
import com.temchik.lms.dto.TenantDTO;
import com.temchik.lms.model.Tenant;
import com.temchik.lms.repository.TenantRepository;
import com.temchik.lms.service.mapper.TenantDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantService {

    private final TenantRepository repository;
    private final TenantDTOMapper mapper;

    public Tenant findById(UUID tenantId) {
        return repository.findById(tenantId)
                .orElseThrow(() -> new BusinessEntityNotFoundException(Tenant.class, "id"));
    }

    public Page<TenantDTO> get(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Transactional
    public TenantDTO create(TenantDTO dio) {
        checkExistsByName(dio.getName());

        Tenant tenant = mapper.toEntity(dio);
        tenant.setCreatedAt(new Date(System.currentTimeMillis()));
        return mapper.toDTO(repository.save(tenant));
    }

    public void delete(UUID tenantId) {
        repository.deleteById(tenantId);
    }

    private void checkExistsByName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessException("Tenant with name = " + name + " exist");
        }
    }
}
